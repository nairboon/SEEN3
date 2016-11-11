package we.are.en3.server;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

import we.are.en3.client.model.DataPoint;


/**
 * This class represents our data store.
 * It is implemented as a singleton (singleton design pattern)
 * (It is a mini data base
 * that serves the data in Java data structures like
 * HashMaps ArrayLists and such)
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class DataStore {

    //Area Hash map
    // with keys: countries and cities (defined as areas)
    // with values: array of all DataPoint's for one area
    HashMap<String,ArrayList<DataPoint>> areaMap = new HashMap<String, ArrayList<DataPoint>>();

    //Data Structures for Dropdown Lists
    ArrayList citySortedList;
    ArrayList countrySortedList;
    HashMap<String,ArrayList<String>> areaToYearMap = new HashMap<String,ArrayList<String>>();

    /**
     * Method that loads the CSV file from tha app's web folder
     * @pre
     * @post
     * @param
     * @return
     */
    @SuppressWarnings("Duplicates")
    public void loadCSVFile(InputStream input) {

        //Information for Database Administrator
        System.out.println("loadCSVFile...");

        //Line reader that connects to the csv-file.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        //Line returned by the BufferedReader
        String line;

        //Sets to generate dropdown lists
        HashSet<String> citySet = new HashSet<String>(100);
        HashSet<String> countrySet = new HashSet<String>(100);

        try {

            //read first line and do nothing to skip header in the csv-file
            line=reader.readLine();

            //read line after line of the csv-file in a loop
            while((line=reader.readLine())!=null){

                //System.out.println(line);

                //split line with a comma delimiter
                String arr[] = line.split(",");

                //assign read data strings to java objects
                String dt=arr[0];
                double AverageTemperature= Double.parseDouble(arr[1]);
                double AverageTemperatureUncertainty= Double.parseDouble(arr[2]);
                String City=arr[3];
                String Country=arr[4];
                String Latitude=arr[5];
                String Longitude=arr[6];

                //extract year
                String dateSplitArr[] = dt.split("-");
                String year = dateSplitArr[0];

                /*
                * Generate Area Sets
                * */

                citySet.add(City);
                countrySet.add(Country);


                /*
                * Generate Area HashMaps
                * */

                //construct a data point: one line/record of the csv-file
                DataPoint dp = new DataPoint(
                        dt,
                        AverageTemperature,
                        AverageTemperatureUncertainty,
                        City,
                        Country,
                        Latitude,
                        Longitude);

                //areaKey: during loop it will take on the value of cities and countries
                String areaKey;

                //The first time a new area=Country is encountered
                // it "puts" a (key, value) - pair to the areaMap
                // key=area, value=ArrayList of DataPoints or Strings
                areaKey=Country;

                if (!areaMap.containsKey(areaKey)){
                    ArrayList<DataPoint> arr1List = new ArrayList<DataPoint>();
                    ArrayList<String> arr2List = new ArrayList<String>();
                    areaMap.put(areaKey, arr1List);
                    areaToYearMap.put(areaKey,arr2List);
                }

                //"adds" a new DataPoint object reference to the ArrayList
                areaMap.get(areaKey).add(dp);

                //"adds" a year to the ArrayList
                Integer lastIndex = areaToYearMap.get(areaKey).size();
                if (lastIndex!=0){
                    String lastElement = areaToYearMap.get(areaKey).get(lastIndex-1);
                    if (!lastElement.contains(year)) {
                        areaToYearMap.get(areaKey).add(year);
                    }
                }else{
                    areaToYearMap.get(areaKey).add(year);
                }
                //The first time a new area=City is encountered
                // it "puts" a (key, value) - pair to the areaMap
                // key=area, value=ArrayList of DataPoints or Strings
                areaKey=City;

                if (!areaMap.containsKey(areaKey)){
                    ArrayList<DataPoint> arr3List = new ArrayList<DataPoint>();
                    ArrayList<String> arr4List = new ArrayList<String>();
                    areaMap.put(areaKey, arr3List);
                    areaToYearMap.put(areaKey,arr4List);
                }

                //"adds" a new DataPoint object reference to the ArrayList
                areaMap.get(areaKey).add(dp);

                //"adds" a year to the ArrayList if not there yet
                lastIndex = areaToYearMap.get(areaKey).size();
                if (lastIndex!=0) {
                    String lastElement = areaToYearMap.get(areaKey).get(lastIndex-1);
                    if (!lastElement.contains(year)) {
                        areaToYearMap.get(areaKey).add(year);
                    }
                }else{
                    areaToYearMap.get(areaKey).add(year);
                }

            }

            //System.out.println(cityMap);

            //Convert Sets into sorted ArrayLists
            this.citySortedList = new ArrayList<String>(citySet);
            Collections.sort(citySortedList);
            this.countrySortedList = new ArrayList<String>(countrySet);
            Collections.sort(countrySortedList);

        }
        catch (Throwable ignore) {}
        finally {
            //Information for Database Administrator
            System.out.println("loadCSVFile...print areaToYearMap");
            System.out.println(Arrays.toString(areaToYearMap.get("London").toArray()));
            System.out.println("loadCSVFile...print citySortedList");
            System.out.println(Arrays.toString(this.citySortedList.toArray()));
            System.out.println("loadCSVFile...print countrySortedList");
            System.out.println(Arrays.toString(this.countrySortedList.toArray()));
            System.out.println("loadCSVFile...print again countrySortedList");
            for(Object s : this.countrySortedList) {
                System.out.println(s);
            }
        }

    }

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    public ArrayList<DataPoint>  getData(String city, String dateFrom, String dateTo){

        return null;
    }

    /**
     * Singleton Design Pattern: the instance object
     */
    private static DataStore ourInstance = new DataStore();

    /**
     * Singleton Design Pattern: constructur of the instance object
     */
    private DataStore() {
    }

    /**
     * Singleton Design Pattern: method to get the instance object
     */
    public static DataStore getInstance() {
        return ourInstance;
    }




}
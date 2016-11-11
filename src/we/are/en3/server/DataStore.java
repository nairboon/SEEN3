package we.are.en3.server;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

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

    //Hash map
    // with keys: countries and cities (defined as areas)
    // with values: array of all DataPoint's for one area
    HashMap<String,ArrayList<DataPoint>> areaMap = new HashMap<String, ArrayList<DataPoint>>();

    /**
     * Method that loads the CSV file from tha app's web folder
     * @pre
     * @post
     * @param
     * @return
     */
    public void loadCSVFile(InputStream input) {

        //Information for Database Administrator
        System.out.println("loadCSVFile...");

        //Line reader that reads that connects to the csv-file.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        //Line returned by the BufferedReader
        String line;

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

                //references data point objects to countries
                areaKey=Country;

                //The first time a new area=Country is encountered
                // it "puts" a (key, value) - pair to the areaMap
                // key=area, value=ArrayList of DataPoints
                if (!areaMap.containsKey(areaKey)){
                    ArrayList<DataPoint> arrList = new ArrayList<DataPoint>();
                    areaMap.put(areaKey, arrList);
                }

                //"adds" a new DataPoint object reference to the ArrayList
                areaMap.get(areaKey).add(dp);

                //The first time a new area=City is encountered
                // it "puts" a (key, value) - pair to the areaMap
                // key=area, value=ArrayList of DataPoints
                areaKey=City;
                if (!areaMap.containsKey(areaKey)){
                    ArrayList<DataPoint> arrList = new ArrayList<DataPoint>();
                    areaMap.put(areaKey, arrList);
                }

                //"adds" a new DataPoint object reference to the ArrayList
                areaMap.get(areaKey).add(dp);

            }

            //System.out.println(cityMap);

        } catch (Throwable ignore) {}


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
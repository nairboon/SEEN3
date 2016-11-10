package we.are.en3.server;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import we.are.en3.client.model.DataPoint;

public class DataStore {

    HashMap<String,ArrayList<DataPoint>> cityMap = new HashMap<String, ArrayList<DataPoint>>();
    HashMap<String,ArrayList<DataPoint>> countryMap = new HashMap<String, ArrayList<DataPoint>>();

    public void loadCSVFile(InputStream input) {
        System.out.println("loadCSVFile...");

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));


        String line;
        try {
            //skip header
            line=reader.readLine();

            while((line=reader.readLine())!=null){
                //System.out.println(line);
                String arr[] = line.split(",");
                String dt=arr[0];
                double AverageTemperature= Double.parseDouble(arr[1]);
                double AverageTemperatureUncertainty= Double.parseDouble(arr[2]);
                String City=arr[3];
                String Country=arr[4];
                String Latitude=arr[5];
                String Longitude=arr[6];
                DataPoint dp = new DataPoint(
                        dt,
                        AverageTemperature,
                        AverageTemperatureUncertainty,
                        City,
                        Country,
                        Latitude,
                        Longitude);
                String countryKey;
                String cityKey;
                countryKey=Country;
                cityKey=City;
                if (!cityMap.containsKey(cityKey)){
                    ArrayList<DataPoint> arrList = new ArrayList<DataPoint>();
                    cityMap.put(cityKey, arrList);
                }
                cityMap.get(cityKey).add(dp);
            }
            //System.out.println(cityMap);
        } catch (Throwable ignore) {}


    }

    public ArrayList<DataPoint>  getData(String city, String dateFrom, String dateTo){

        return null;
    }


    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private DataStore() {
    }


}
package we.are.en3.server;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import we.are.en3.client.model.DataPoint;

public class DataStore {

    ArrayList<String> ls = new ArrayList<String>();
    HashMap<String,DataPoint> cityMap = new HashMap<String, DataPoint>();

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
                cityMap.put(arr[3], dp);
            }
            System.out.println(cityMap);
        } catch (Throwable ignore) {}


        //ls.add("foo");
        //ls.add("bar");
        //ls.add("er");
        //ls.add("yo");
    }


    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private DataStore() {
    }


}
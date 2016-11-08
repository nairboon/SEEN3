package we.are.en3.server;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataStore {

    ArrayList<String> ls = new ArrayList<String>();

    public void loadCSVFile(InputStream input) {
        System.out.println("loadCSVFile...");


        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line;
        try {
            while((line=reader.readLine())!=null){
                System.out.println(line);
            }
        } catch (Throwable ignore) {}


        ls.add("foo");
        ls.add("bar");
        ls.add("er");
        ls.add("yo");
    }


    private static DataStore ourInstance = new DataStore();

    public static DataStore getInstance() {
        return ourInstance;
    }

    private DataStore() {
    }



}

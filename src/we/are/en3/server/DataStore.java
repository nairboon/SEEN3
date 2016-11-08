package we.are.en3.server;


import java.util.ArrayList;

public class DataStore {

    ArrayList<String> ls = new ArrayList<String>();

    public void loadCSVFile(String path) {
        System.out.println("loadCSVFile...");

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

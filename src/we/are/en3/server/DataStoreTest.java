package we.are.en3.server;


import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by dominik on 10/11/16.
 */
public class DataStoreTest {


    @org.junit.Test
    public void loadCSVFile() throws Exception {
        DataStore testStore = DataStore.getInstance();
        File file = new File("web/data.csv");
        InputStream input = new FileInputStream(file);
        testStore.loadCSVFile(input);

        assertTrue(!testStore.cityMap.isEmpty());
        assertNotNull(DataStore.getInstance().countryMap.isEmpty());
        assertEquals("Côte D'Ivoire", testStore.cityMap.get("Abidjan").get(0).getCountry());



    }

}
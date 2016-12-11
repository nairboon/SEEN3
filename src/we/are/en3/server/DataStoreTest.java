package we.are.en3.server;


import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * JUnit test cases for the class DataSore
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class DataStoreTest {



    @Test
    public void strPosToFloatPos() throws Exception {

        double r1 = DataStore.StrPosToFloatPos("18.48N");

        double sydnedL = DataStore.StrPosToFloatPos("34.56S");
        assertEquals(-34.56, sydnedL,0.01);

        assertEquals(18.48, r1,0.01);
    }



    /**
     * test method loads csv-file and lists JUnit-assert statements
     * @pre
     * @post
     * @param
     * @return
     */
    @org.junit.Test
    public void loadCSVFile() throws Exception {

        //load csv-file
        DataStore testStore = DataStore.getInstance();
        File file = new File("web/WEB-INF/data.csv");
        InputStream input = new FileInputStream(file);
        testStore.loadCSVFile(input);

        //JUnit-assert statements
        assertTrue(!testStore.areaMap.isEmpty());
        assertNotNull(DataStore.getInstance().areaMap.isEmpty());
        assertEquals("Côte D'Ivoire", testStore.areaMap.get("Abidjan").get(0).getCountry());

    }


}

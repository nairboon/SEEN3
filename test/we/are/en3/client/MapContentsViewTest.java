package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import org.junit.Test;
import we.are.en3.server.DataStore;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by dominik on 27/11/16.
 */
public class MapContentsViewTest {
    @Test
    public void prepareDataTable() throws Exception {

        //prepare dataArray
        ArrayList<ArrayList<String>> dataArray = new ArrayList<>();
        ArrayList<String> bern = new ArrayList<String>();
        bern.add("Bern");
        bern.add("18");
        ArrayList<String> genf = new ArrayList<String>();
        genf.add("Genf");
        genf.add("20");
        ArrayList<String> zug = new ArrayList<String>();
        bern.add("Zug");
        bern.add("15");
        dataArray.add(bern);
        dataArray.add(genf);
        dataArray.add(zug);

        //Create the DataTable
        DataTable testtable = prepareDataTable(dataArray, "2000");


        assertEquals("Bern", testtable.getValueString(0,0));

    }

    public DataTable prepareDataTable(ArrayList<ArrayList<String>> dataArray, String currentYear){

        //Information for Developer
        GWT.log("MapPresenter:prepareDataTable()");

        // Prepare the data table
        DataTable dataTable = DataTable.create();

        dataTable.addColumn(ColumnType.STRING, "City");
        dataTable.addColumn(ColumnType.STRING, "Label");

        //fill dataTable
        int numberOfRows=dataArray.size();
        dataTable.addRows(numberOfRows);
        int iter=0;
        for (ArrayList<String> city_Temp : dataArray) {
            //city
            String city = city_Temp.get(0);
            dataTable.setValue(iter, 0, city);
            //label
            String label = city_Temp.get(0) + " ( " + city_Temp.get(1) + ", " + currentYear + " )";
            dataTable.setValue(iter, 1, label);
            iter++;
        }

        return dataTable;

    }

}
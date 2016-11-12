package we.are.en3.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import we.are.en3.client.MyClimateService;
import we.are.en3.client.model.DataPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class extends a RemoteServiceServlet that implements the MyClimateService interface.
 * It is the server side service point for RPC requests from the client side.
 * It offers services in connection with the DataStore.
 *
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class MyClimateServiceImpl extends RemoteServiceServlet implements MyClimateService {


    /**
     * Method returns for a given area the lowest and highest year.
     * This method is called from method fetchTable() in class TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous?
     */
    @Override
    public ArrayList<String> getMinMaxYear(String area) {

        //
        ArrayList<String> returnList = new ArrayList<String>();
        String minYear = new String();
        String maxYear = new String();

        //Todo: remove meta from all methods in classes and interfaces
        String meta="city";

        //extracts first and last year in DataStore for chosen area
        if (meta.equals("city")){
            ArrayList<DataPoint> resList = DataStore.getInstance().areaMap.get(area);
            minYear=resList.get(0).getDate();
            maxYear=resList.get(resList.size()-1).getDate();
        }else {
            ArrayList<DataPoint> resList = DataStore.getInstance().areaMap.get(area);
            //Todo
        }

        //add first and last year to the data structure and return
        returnList.add(minYear);
        returnList.add(maxYear);
        return returnList;
    }


    /**
     * Method returns the number of DataPoints stored in DataStore's areaMap
     * dependent on the values Area, StartDate, EndDate
     *
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous?
     */
    @Override
    public Integer getResultsCount(String area, String meta, Date start, Date end) {

        //return variable
        Integer size_;

        //toDo: start end Datum in Logik einbauen

        //returns for an area: the size of the array, i.e. the number of data points
        if (meta.equals("city")){
            size_ = DataStore.getInstance().areaMap.get(area).size();
        }else{
            size_ = DataStore.getInstance().areaMap.get(area).size();
        }

        //return number of data points
        return size_;
    }


    /**
     * Method returns an ArrayList of DataPoints stored in DataStore's areaMap
     * It is called from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous?
     */
    @Override
    public ArrayList<DataPoint> getResults(String area, String meta, Date start, Date end, Integer seqStart, Integer seqEnd) {

        //Of type list since sublist() call below returns a List
        List<DataPoint> returnList;

        //toDo: start end Datum in Logik einbauen

        //exception handling: if area is null
        if (area==null) return null;

        //Read out the data store
        if (meta.equals("city")){
            returnList = DataStore.getInstance().areaMap.get(area).subList(seqStart, seqEnd);

        }else{
            returnList = DataStore.getInstance().areaMap.get(area).subList(seqStart, seqEnd);
        }

        //Cast List to ArrayList of DataPoints
        return new ArrayList<DataPoint>(returnList);
    }

    /**
     * This method returns the sorted list of countries taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getCountryList() {

        return DataStore.getInstance().countrySortedList;
    }

    /**
     * This method returns the sorted list of cities taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getCityList() {
        return DataStore.getInstance().citySortedList;
    }

    /**
     * This method returns the sorted list of cities taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getAreaList() {
        //
        ArrayList<String> cities = new ArrayList<String>();

        //
        for(String key: DataStore.getInstance().areaMap.keySet()) {
            cities.add(key);
        }
        return cities;
    }

    /**
     * This method returns the list of dates taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getDatesList(String area) {
        //
        return DataStore.getInstance().areaToYearMap.get(area);
    }
}
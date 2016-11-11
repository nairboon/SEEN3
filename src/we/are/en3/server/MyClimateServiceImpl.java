package we.are.en3.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import we.are.en3.client.MyClimateService;
import we.are.en3.client.model.DataPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ToDo: What is this code doing
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class MyClimateServiceImpl extends RemoteServiceServlet implements MyClimateService {

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     * ToDo: Problem after change to areaMap!!!
     */
    public ArrayList<String> getList() {

        //
        ArrayList<String> cities = new ArrayList<String>();

        //
       for(String key: DataStore.getInstance().areaMap.keySet()) {
           cities.add(key);
       }
       return cities;
    }


    /**
     * Method returns for a given area the lowest and highest year
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous
     */
    @Override
    public ArrayList<String> getMinMaxYear(String area) {

        //
        ArrayList<String> returnList = new ArrayList<String>();
        String minYear = new String();
        String maxYear = new String();

        //Todo:
        String meta="city";

        //
        if (meta.equals("city")){
            ArrayList<DataPoint> resList = DataStore.getInstance().areaMap.get(area);
            minYear=resList.get(0).getDate();
            maxYear=resList.get(resList.size()-1).getDate();
        }else {
            ArrayList<DataPoint> resList = DataStore.getInstance().areaMap.get(area);
            //Todo
        }

        //
        returnList.add(minYear);
        returnList.add(maxYear);

        //
        return returnList;
    }


    /**
     * Method returns for a given area the number of data points stored in the areaMap
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous
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
     * Method returns for a given area an ArrayList of DataPoints
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous
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
}
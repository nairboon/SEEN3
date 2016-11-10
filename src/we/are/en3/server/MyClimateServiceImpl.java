package we.are.en3.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import we.are.en3.client.MyClimateService;
import we.are.en3.client.model.DataPoint;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyClimateServiceImpl extends RemoteServiceServlet implements MyClimateService {


    public ArrayList<String> getList() {
        ArrayList<String> cities = new ArrayList<String>();


       for(String key: DataStore.getInstance().cityMap.keySet()) {
           cities.add(key);
       }
       return cities;
    }

    @Override
    public ArrayList<String> getMinMaxYear() {
        // to implement
        // returns the lowest and highest year in our dataset
        return null;
    }

    @Override
    public Integer getResultsCount(String area, String meta, Date start, Date end) {
        Integer size_;

        //toDo: start end Datum in Logik einbauen
        if (meta.equals("city")){
            size_ = DataStore.getInstance().cityMap.get(area).size();
        }else{
            size_ = DataStore.getInstance().countryMap.get(area).size();
        }
        return size_;
    }

    @Override
    public ArrayList<DataPoint> getResults(String area, String meta, Date start, Date end, Integer seqStart, Integer seqEnd) {
        List<DataPoint> returnList;
        //toDo: start end Datum in Logik einbauen
        if (meta.equals("city")){
            returnList = DataStore.getInstance().cityMap.get(area).subList(seqStart, seqEnd);

        }else{
            returnList = DataStore.getInstance().countryMap.get(area).subList(seqStart, seqEnd);
        }

        return new ArrayList<DataPoint>(returnList);
    }
}
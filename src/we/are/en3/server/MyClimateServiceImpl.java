package we.are.en3.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import we.are.en3.client.MyClimateService;

import java.util.ArrayList;

public class MyClimateServiceImpl extends RemoteServiceServlet implements MyClimateService {


    public ArrayList<String> getList() {
        ArrayList<String> cities = new ArrayList<String>();


       for(String key: DataStore.getInstance().cityMap.keySet()) {
           cities.add(key);
       }
       return cities;
    }
}
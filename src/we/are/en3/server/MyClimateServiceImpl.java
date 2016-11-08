package we.are.en3.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import we.are.en3.client.MyClimateService;

import java.util.ArrayList;

public class MyClimateServiceImpl extends RemoteServiceServlet implements MyClimateService {
    // Implementation of sample interface method
    public ArrayList<String> getList() {
        ArrayList<String> ls = new ArrayList<String>();
        ls.add("foo");
        ls.add("bar");
        ls.add("er");
        ls.add("yo");
        return ls;
    }
}
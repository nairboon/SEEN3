package we.are.en3.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import we.are.en3.client.model.DataPoint;

import java.util.ArrayList;
import java.util.Date;

@RemoteServiceRelativePath("MyClimateService")
public interface MyClimateService extends RemoteService {
    // Sample interface method of remote interface
    ArrayList<String> getList();

    ArrayList<String> getMinMaxYear();
    Integer getResultsCount(String area, String meta, Date start, Date end);

    ArrayList<DataPoint> getResults(String area, String meta, Date start, Date end, Integer seqStart, Integer seqEnd);

    /**
     * Utility/Convenience class.
     * Use MyClimateService.App.getInstance() to access static instance of MyClimateServiceAsync
     */
    public static class App {
        private static MyClimateServiceAsync ourInstance = GWT.create(MyClimateService.class);

        public static synchronized MyClimateServiceAsync getInstance() {
            return ourInstance;
        }
    }
}

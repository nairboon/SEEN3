package we.are.en3.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.ArrayList;

@RemoteServiceRelativePath("MyClimateService")
public interface MyClimateService extends RemoteService {
    // Sample interface method of remote interface
    ArrayList<String> getList();

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

package we.are.en3.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import we.are.en3.client.model.DataPoint;

import java.util.ArrayList;
import java.util.Date;

/**
 * ToDo: What is this code doing
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
@RemoteServiceRelativePath("MyClimateService")
public interface MyClimateService extends RemoteService {

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    ArrayList<String> getCountryList();

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    ArrayList<String> getCityList();

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    ArrayList<String> getAreaList();

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    ArrayList<String> getDatesList(String area);

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    ArrayList<String> getMinMaxYear(String area);

    /**
     * ToDo: What is this code doing
     *
     * @param
     * @return
     * @pre
     * @post
     */
    Integer getResultsCount(String area, String dateFrom, String dateTo);

    /**
     * ToDo: What is this code doing
     *
     * @param
     * @return
     * @pre
     * @post
     */
    ArrayList<DataPoint> getResults(String area, String dateFrom, String dateTo, Integer seqStart, Integer seqEnd);

    /**
     * ToDo: What is this code doing
     *
     * @param
     * @return
     * @pre
     * @post
     */
    ArrayList<ArrayList<String>> getCitiesAverageTemperatureList(String Temperature);

    /**
     * Utility/Convenience class.
     * Use MyClimateService.App.getInstance() to access static instance of MyClimateServiceAsync
     */
    public static class App {

        /**
         * ToDo: What is this code doing
         * @pre
         * @post
         * @param
         * @return
         */
        private static MyClimateServiceAsync ourInstance = GWT.create(MyClimateService.class);

        /**
         * ToDo: What is this code doing
         * @pre
         * @post
         * @param
         * @return
         */
        public static synchronized MyClimateServiceAsync getInstance() {
            return ourInstance;
        }
    }
}

package we.are.en3.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import we.are.en3.client.model.DataPoint;

import java.util.ArrayList;
import java.util.Date;

/**
 * ToDo: What is this code doing
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public interface MyClimateServiceAsync {

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    void getCountryList(AsyncCallback<ArrayList<String>> async);

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    void getCityList( AsyncCallback<ArrayList<String>> async);


    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    void getResultsCount(String area, String meta, String dateFrom, String dateTo, AsyncCallback<Integer> async);

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    void getMinMaxYear(String area, AsyncCallback<ArrayList<String>> async);

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    void getResults(String area, String meta, String dateFrom, String dateTo, Integer seqStart, Integer seqEnd, AsyncCallback<ArrayList<DataPoint>> async);

    /**
     * ToDo: What is this code doing
     *
     * @param
     * @return
     * @pre
     * @post
     */
    void getAreaList(AsyncCallback<ArrayList<String>> async);

    /**
     * ToDo: What is this code doing
     *
     * @param
     * @return
     * @pre
     * @post
     */
    void getDatesList(String area, AsyncCallback<ArrayList<String>> async);
}

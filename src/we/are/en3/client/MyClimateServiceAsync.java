package we.are.en3.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import we.are.en3.client.model.DataPoint;

import java.util.ArrayList;
import java.util.Date;

public interface MyClimateServiceAsync {
    void getList( AsyncCallback<ArrayList<String>> async);

    void getResultsCount(String area, String meta, Date start, Date end, AsyncCallback<Integer> async);

    void getMinMaxYear(AsyncCallback<ArrayList<String>> async);

    void getResults(String area, String meta, Date start, Date end, Integer seqStart, Integer seqEnd, AsyncCallback<ArrayList<DataPoint>> async);
}

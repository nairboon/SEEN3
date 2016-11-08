package we.are.en3.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;

public interface MyClimateServiceAsync {
    void getList( AsyncCallback<ArrayList<String>> async);
}

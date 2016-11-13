package we.are.en3.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import we.are.en3.client.MyClimateService;


import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * ToDo: What is this code doing
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class MyClimateServiceTest extends GWTTestCase {

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */

    public String getModuleName() {
        return "we.are.en3.MyClimate";
    }


    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */

    public void testgetAreaList() throws Exception {
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getAreaList(
                new AsyncCallback<ArrayList<String> >() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught);
                        fail("failure");
                        finishTest();
                    }

                    public void onSuccess(ArrayList<String> result) {
                        System.out.println("success");

                        assertTrue(result.contains("Afghanistan"));
                        assertTrue(result.contains("Peru"));
                        assertTrue(result.contains("Paris"));

                        finishTest();
                    }
                });
        delayTestFinish(1000);

    }


    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */

    public void getMinMaxYear() throws Exception {

    }


    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */

    public void getResultsCount() throws Exception {

    }


    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */

    public void getResults() throws Exception {

    }

}

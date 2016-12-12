package we.are.en3.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;
import we.are.en3.client.MyClimateService;
import we.are.en3.client.model.DataPoint;


import java.util.ArrayList;

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
/*
    public void testgetCitiesAverageTemperatureList() throws Exception {
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getCitiesAverageTemperatureList("2008",
                new AsyncCallback<ArrayList<ArrayList<String>>>() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught);
                        fail("failure");
                        finishTest();
                    }

                    public void onSuccess(ArrayList<ArrayList<String>> result) {
                        System.out.println("success");



                        assertTrue(result.get(0).contains("Abidjan"));
                        assertEquals("26.94°C",result.get(0).get(1));
                        assertEquals(100,result.size());
                        assertEquals(2,result.get(0).size());


                        finishTest();
                    }
                });
        delayTestFinish(1000);

    } */

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */

    public void testgetDatesList() throws Exception {
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getDatesList("Bogotá",
                new AsyncCallback<ArrayList<String> >() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught);
                        fail("failure");
                        finishTest();
                    }

                    public void onSuccess(ArrayList<String> result) {
                        System.out.println("success");
                        String startDate = result.get(0);
                        String endDate = result.get(result.size() - 1 );

                        assertEquals(startDate, "1824");
                        assertEquals(endDate, "2013");

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

    public void testgetCityList() throws Exception {
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getCityList(
                new AsyncCallback<ArrayList<String> >() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught);
                        fail("failure");
                        finishTest();
                    }

                    public void onSuccess(ArrayList<String> result) {
                        System.out.println("success");

                        assertTrue(result.contains("Bogotá"));
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

    public void testgetCountryList() throws Exception {
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getCountryList(
                new AsyncCallback<ArrayList<String> >() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught);
                        fail("failure");
                        finishTest();
                    }

                    public void onSuccess(ArrayList<String> result) {
                        System.out.println("success");

                        assertTrue(result.contains("Afghanistan"));
                        assertTrue(result.contains("Italy"));
                        assertTrue(result.contains("Japan"));

                        finishTest();
                    }
                });
        delayTestFinish(1000);

    }



    public void testgetResultsCount() throws Exception {
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getResultsCount("Bogotá", "1824","2013",
                new AsyncCallback<Integer >() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught);
                        fail("failure");
                        finishTest();
                    }

                    public void onSuccess(Integer result) {
                        System.out.println("success");

                        assertEquals(result.intValue(), 2018);

                        finishTest();
                    }
                });
        delayTestFinish(1000);

    }



    public void testgetResults() throws Exception {
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getResults("Bogotá", "1824","2013", 0, 50,
                new AsyncCallback<ArrayList<DataPoint> >() {
                    public void onFailure(Throwable caught) {
                        System.out.println(caught);
                        fail("failure");
                        finishTest();
                    }

                    public void onSuccess(ArrayList<DataPoint> result) {
                        System.out.println("success");

                        assertEquals(result.size(), 50);
                        assertEquals(result.get(0).getAverageTemperature(),20.116);
                        finishTest();
                    }
                });
        delayTestFinish(1000);

    }

    public void testGetCitiesAverageTempPerYearList() throws Exception {
        ArrayList<String> city = new ArrayList<>();
        city.add("Abidjan");
        MyClimateServiceAsync MyClimateService = GWT.create(MyClimateService.class);
        MyClimateService.getCitiesAverageTempPerYearList(city, "2012", "2012",
            new AsyncCallback<ArrayList<ArrayList<String>> >() {
                public void onFailure(Throwable caught) {
                    System.out.println(caught);
                    fail("failure");
                    finishTest();
                }

                public void onSuccess(ArrayList<ArrayList<String>> result) {
                    System.out.println("success");

                    assertEquals(result.size(), 1);
                    assertEquals(result.get(0).size(), 2);
                    assertEquals(result.get(0).get(0), "Abidjan");
                    assertEquals(result.get(0).get(1), "26.766333333333336");
                    finishTest();
                }
            });
            delayTestFinish(1000);

    }

}

package we.are.en3.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import we.are.en3.client.MyClimateServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the data flow
 * between the Sever
 * and the Chart View (of Class ChartContentsView)
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class ChartPresenter implements Presenter{

    /**
     * The class ChartContentsView implements this interface Display.
     * Because of this interface Display the class ChartPresenter
     * can access the attributes (instance variables, methods)
     * of the class ChartContentsView which are defined in the
     * this interface, but not anything else.
     */
    public interface Display {

        void initialize(ArrayList<ArrayList<String>> dataArray);

        //Fills the dropdown lists of the filter panel.
        void setInitAreas(List<String> areas);

        HasClickHandlers getLoadChartButton();

        String getSelectedArea();

        //Returns View as Widget
        Widget asWidget();

        String getStartYear();
        String getEndYear();
    }

    //instance variables from constructor
    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;
    private List<String> areas;

    public ChartPresenter(MyClimateServiceAsync rpcService, HandlerManager eventBus, Display view){
        //Information for Developer
        GWT.log("ChartPresenter: ChartPresenter()");

        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
        init();


    }

    /**
     * This method initializes the dropdown list of the TextBoxes.
     * It uses RpcService to fetch ArrayList from Server side MyClimateServiceImpl
     * and populates the Textbox's dropdown list
     *
     * @pre
     * @post
     * @param
     * @return
     */
    private void init() {
        //Information for Developer
        GWT.log("ChartPresenter:init()");

        //Two rpc requests are called in sequence
        rpcService_getAreaList_getDateList();
    }

    /**
     * Two RpcServices in sequence fetch ArrayLists from Server side class MyClimateServiceImpl
     * and populate the Textbox's dropdown lists
     * The first RPC call fetches the areas,
     * the second call fetches the dates conditional on the first area (the one shown in TextBox)
     *
     * @pre
     * @post
     * @param
     * @return
     */
    void rpcService_getAreaList_getDateList(){

        rpcService.getAreaList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                areas = result;

                //method from display interface, implemented in TableContentsView
                display.setInitAreas(areas);

                //RpcService fetches ArrayList from Server side class MyClimateServiceImpl
                //and populates the Textbox's dropdown list
                //This rpc-call needs to wait for onSuccess of areas list.
                String area= areas.get(0);
                GWT.log("----area----- "+area);


            }
            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });
    }


    void rpcService_getCitiesAverageTempPerYearList(ArrayList<String> cities, String startYear, String endYear){

        //Information for Developer
        GWT.log("ChartPresenter:getCitiesAverageTempPerYearList()");

        rpcService.getCitiesAverageTempPerYearList(cities, startYear, endYear, new AsyncCallback<ArrayList<ArrayList<String>>>() {
            public void onSuccess(ArrayList<ArrayList<String>> result) {

                GWT.log("first: " + result.size() + " second: " + result.get(0).size());
                GWT.log(result.get(0).toString());
                //GWT.log(result.get(1).toString());
                //method from display interface, implemented in TableContentsView
                display.initialize(result);

            }
            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

    }



    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public void go(HasWidgets container) {
        //Information for Developer
        GWT.log("ChartPresenter: go()");

        //Todo

        bind();

        //clears body-tag of Html-Host-file
        container.clear();

        //adds TableContentsView to the tableContentsPanel
        container.add(display.asWidget());
    }


    public void bind() {
        //Information for Developer
        GWT.log("ChartPresenter:bind()");

        //on click of LoadTable-Button method fetchTable() is called
        display.getLoadChartButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                fetchChart();
            }
        });

    }

    private void fetchChart() {
        GWT.log("ChartPresenter:fetchChart()");

        ArrayList<String> cities = new ArrayList<String>();
        cities.add(display.getSelectedArea());
        rpcService_getCitiesAverageTempPerYearList(cities,display.getStartYear(),display.getEndYear());
    }
}

package we.are.en3.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import we.are.en3.client.MyClimateServiceAsync;
import we.are.en3.client.model.DataPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This class handles the data flow
 * between the Sever
 * and the Table View (of Class TableContentsView)
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class TablePresenter implements Presenter{

    /**
     * The class TableContentsView implements this interface Display.
     * Because of this interface Display the class TablePresenter
     * can access the attributes (instance variables, methods)
     * of the class TableContentsView which are defined in the
     * this interface, but not anything else.
     */
    public interface Display {

        //returns the button from TableContentsView
        HasClickHandlers getLoadTableButton();

        //ToDo: what is this code doing (not implemented)
        void setData(List<String> data);

        //Fills the dropdown lists of the filter panel.
        void setInitAreas(List<String> areas);
        void setInitCountries(List<String> countries);
        void setInitCities(List<String> cities);
        void setInitDatesFrom(List<String> dateFrom);
        void setInitDatesTo(List<String> dateTo);

        //get the country, city, dateFrom or dateTo respectively
        // which are selected in TextBoxes
        String getSelectedArea();
        String getSelectedCountry();
        String getSelectedCity();
        String getSelectedDateFrom();
        String getSelectedDateTo();

        //Returns View as Widget
        Widget asWidget();

        //Returns the Content Panel with CellTable
        Widget getTableView();

        //Returns the cellTable.
        HasData getCellTableDisplay();
    }

    //instance variables from constructor
    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    //lists fetched by RPC from server side MyClimateServiceImpl
    private List<String> areas;
    private List<String> countries;
    private List<String> cities;
    private List<String> dates;

    String currentArea;
    String currentCountry;
    String currentCity;
    String currentDateFrom;
    String currentDateTo;

    //Test data
    private static List<DataPoint> DATA = Arrays.asList(
            new DataPoint("John", 0.0,0.0,"a", "b", "123 Fourth Road","y"));

    /**
     * Constructor: sets instance variables rpcService, eventBus and view
     * and calls init()
     */
    public TablePresenter(MyClimateServiceAsync rpcService, HandlerManager eventBus, Display view) {
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

        //RpcService fetches ArrayList from Server side class MyClimateServiceImpl
        //and populates the Textbox's dropdown list
        rpcService.getAreaList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                areas = result;

                //method from display interface, implemented in TableContentsView
                display.setInitAreas(areas);

            }
            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

        //RpcService fetches ArrayList from Server side class MyClimateServiceImpl
        //and populates the Textbox's dropdown list
        rpcService.getCountryList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                countries = result;

                //method from display interface, implemented in TableContentsView
                display.setInitCountries(countries);

            }
            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

        //RpcService fetches ArrayList from Server side class MyClimateServiceImpl
        //and populates the Textbox's dropdown list
        rpcService.getCityList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                cities = result;

                //method from display interface, implemented in TableContentsView
                display.setInitCities(cities);

            }
            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

        //RpcService fetches ArrayList from Server side class MyClimateServiceImpl
        //and populates the Textbox's dropdown list
        String area="Abidjan";
        rpcService.getDatesList(area, new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                dates = result;

                //method from display interface, implemented in TableContentsView
                display.setInitDatesFrom(dates);
                display.setInitDatesTo(dates);

            }
            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

    }

    /**
     * This method adds click event to button which fetches table,
     * it clears body-tag of Html-Host-file
     * It is called from class AppController
     *
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public void go(HasWidgets container) {

        //adds click event to button which fetches table
        bind();

        //clears body-tag of Html-Host-file
        container.clear();

        //adds TableContentsView to the body of the Html-host-file
        //ToDo: what is this code doing
        container.add(display.asWidget());

        //fetches the countryList from servers class MyClimateServiceImpl
        rpcService.getCountryList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                //ToDo: what is this code doing (not implemented)
                display.setData(result);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

    }

    /**
     * This method adds a clickEvent to the Button
     * to call method fetchTable
     *
     * @pre
     * @post
     * @param
     * @return
     */
    public void bind() {
        display.getLoadTableButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                fetchTable();
            }
        });
    }


    /**
     * This method fetches the Table from Server side class MyClimateServiceImpl
     *
     * @pre
     * @post
     * @param
     * @return
     */
    private void fetchTable() {

        //gets the selected Area from the TextBox
        currentArea = display.getSelectedArea();

        //Information for Developer
        GWT.log("fetch table for: " + currentArea);

        //Variable to access only functionality of TableContentsView defined in Interface Display
        Display view = display;

        //ToDo remove "city" from all methods in classes and interfaces
        //
        rpcService.getResultsCount(currentArea,"city", new Date(), new Date(), new AsyncCallback<Integer>() {
            public void onSuccess(Integer result) {

                //Information for Developer
                GWT.log("table size: " + result);

                //
                display.getCellTableDisplay().setRowCount(result, true);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

        //
        rpcService.getMinMaxYear(currentArea, new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                GWT.log("min/max: " + result.get(0) + " " + result.get(1));
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });


        // Create a data provider.
        AsyncDataProvider<DataPoint> dataProvider = new AsyncDataProvider<DataPoint>() {
            @Override
            protected void onRangeChanged(HasData<DataPoint> display) {
                final Range range = display.getVisibleRange();

                int start = range.getStart();
                int end = start + range.getLength();

                GWT.log("fetch table chunk...");

                //
                rpcService.getResults(view.getSelectedArea(), "city", new Date(), new Date(), start, end, new AsyncCallback<ArrayList<DataPoint>>() {
                    public void onSuccess(ArrayList<DataPoint> result) {

                        if (result != null) {
                            GWT.log("table chunk size: " + result.size());

                            GWT.log("fill:" + result.get(0).getAverageTemperature() );

                            view.getCellTableDisplay().setRowData(start, result);
                        }
                    }

                    public void onFailure(Throwable caught) {
                        Window.alert("Error fetching contact details");
                    }
                });

                // Get the ColumnSortInfo from the table.
                //final ColumnSortList sortList = view.getCellTableDisplay().getColumnSortList();

                // This timer is here to illustrate the asynchronous nature of this data
                // provider. In practice, you would use an asynchronous RPC call to
                // request data in the specified range.
                /*
                new Timer() {
                    @Override
                    public void run() {
                        int start = range.getStart();
                        int end = start + range.getLength();
                        // This sorting code is here so the example works. In practice, you
                        // would sort on the server.

                        List<DataPoint> dataInRange = DATA.subList(start, end);

                        // Push the data back into the list.
                        view.getCellTableDisplay().setRowData(start, dataInRange);
                    }
                }.schedule(2000);
                */
            }
        };

        // Connect the list to the data provider.
        dataProvider.addDataDisplay(display.getCellTableDisplay());

        //
        display.getTableView().setVisible(true);

    }

}

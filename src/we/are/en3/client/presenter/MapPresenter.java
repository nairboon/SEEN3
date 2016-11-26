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
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import we.are.en3.client.MyClimateServiceAsync;

import java.util.ArrayList;

/**
 * This class handles the data flow
 * between the Sever
 * and the Map View (of Class MapContentsView)
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class MapPresenter implements Presenter{

    /**
     * The class MapContentsView implements this interface Display.
     * Because of this interface Display the class MapPresenter
     * can access the attributes (instance variables, methods)
     * of the class MapContentsView which are defined in the
     * this interface, but not anything else.
     */
    public interface Display {

        //returns the slider from MapContentsView
        HasClickHandlers getLoadMapButton();

        //shows map.
        public void showMap(ArrayList<ArrayList<String>> dataArray);

        //Returns View as Widget
        Widget asWidget();

        void updateVisualization(GeoChart geoChart, DataTable dataTable);

        //ToDo: Needed? Returns the Content Panel with Map
        //Widget getMapView();

        //ToDo: Needed? Returns the cellTable.
        //HasData getMapDisplay();


    }


    //instance variables from constructor
    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    //GeoChart
    private GeoChart geoChart;


    /**
     * Constructor: sets instance variables rpcService, eventBus and view
     * and calls init()
     */
    public MapPresenter(MyClimateServiceAsync rpcService, HandlerManager eventBus, Display view){
        //Information for Developer
        GWT.log("MapPresenter: MapPresenter()");

        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
        init();

    }

    /**
     * ToDo: What is this code doing
     *
     * @pre
     * @post
     * @param
     * @return
     */
    private void init() {
        //Information for Developer
        GWT.log("TablePresenter:init()");

        //rpc request is called
        rpcService_getCitiesAverageTemperatureList();
    }


    /**
     * ToDo: What is this code doing
     *
     * @pre
     * @post
     * @param
     * @return
     */
    void rpcService_getCitiesAverageTemperatureList(){

        //Information for Developer
        GWT.log("MapPresenter:getCitiesAverageTemperatureList()");

        //lists fetched by RPC from server side MyClimateServiceImpl
        //ArrayList <ArrayList <String>> dataArray = new ArrayList <ArrayList <String>>();

        //ToDo: get the selected Area from Slider Widget
        String currentYear = "2008"; //display.getSelectedArea();

        rpcService.getCitiesAverageTemperatureList(currentYear, new AsyncCallback<ArrayList<ArrayList<String>>>() {
            public void onSuccess(ArrayList<ArrayList<String>> result) {

                //dataArray = result;

                //method from display interface, implemented in TableContentsView
                loadGeoMap(result);

            }
            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });

    }


    /**
     Draws the initial visualization of the map
     @pre nothing
     @post nothing
     @param
     **/
    public void loadGeoMap(final ArrayList<ArrayList<String>> dataArray) {
        //Information for Developer
        GWT.log("MapPresenter:loadGeoMap()");

        ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
        chartLoader.loadApi(new Runnable() {


            public void run() {
                // Create and attach the map to the panel
                geoChart = new GeoChart();
                //prepare dataTable data structure
                DataTable dataTable = prepareDataTable(dataArray);
                display.updateVisualization(geoChart, dataTable);
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
    public DataTable prepareDataTable(ArrayList<ArrayList<String>> dataArray){

        //Information for Developer
        GWT.log("MapPresenter:prepareDataTable()");

        // Prepare the data table
        DataTable dataTable = DataTable.create();

        dataTable.addColumn(ColumnType.STRING, "City");
        dataTable.addColumn(ColumnType.STRING, "Label");

        //fill dataTable
        dataTable.addRows(dataArray.size());
        int iter=0;
        for (ArrayList<String> city_Temp : dataArray) {
            //city
            dataTable.setValue(iter, 0, city_Temp.get(0));
            //label
            String label = city_Temp.get(0) + "(" + city_Temp.get(1) + ")";
            dataTable.setValue(iter, 1, label);
            dataTable.setValue(iter,1,"Test");
            iter++;
        }

        return dataTable;
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
        GWT.log("MapPresenter: go()");

        //adds click event to button which fetches map
        bind();

        //clears body-tag of Html-Host-file
        container.clear();

        //
        //init();

        //adds MapContentsView to the MapContentsPanel
        container.add(display.asWidget());


    }

    /**
     * This method adds a clickEvent to the Button
     * to call method rpcService_getCitiesAverageTemperatureList()
     *
     * @pre
     * @post
     * @param
     * @return
     */
    public void bind() {
        //Information for Developer
        GWT.log("TablePresenter:bind()");

        //on click of LoadTable-Button method fetchTable() is called
        display.getLoadMapButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                rpcService_getCitiesAverageTemperatureList();
            }
        });



    }

}

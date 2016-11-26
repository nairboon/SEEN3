package we.are.en3.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
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
       // HasClickHandlers getLoadMapButton();


        public void updateMap(final ArrayList<ArrayList<String>> inp);

        //Returns View as Widget
        Widget asWidget();

        //ToDo: Needed? Returns the Content Panel with Map
        //Widget getMapView();

        //ToDo: Needed? Returns the cellTable.
        //HasData getMapDisplay();


    }


    //instance variables from constructor
    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;



    private String currentYear;

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
     * Display the map for the first time
     *
     * @pre
     * @post
     * @param
     * @return
     */
    private void init() {
        //Information for Developer
        GWT.log("TablePresenter:init()");

        // init map
        this.currentYear = "2000";
        updateMap();
    }

    /**
     * Updates the map in the view for the currently selected year
     *
     * @pre this.currentYear is valid
     * @post -
     * @param -
     * @return void
     */
    private void updateMap() {

        rpcService.getCitiesAverageTemperatureList(currentYear, new AsyncCallback<ArrayList<ArrayList<String>>>() {
            public void onSuccess(ArrayList<ArrayList<String>> result) {
                GWT.log(result.toString());
                display.updateMap(result);
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
        GWT.log("MapPresenter: go()");

        //adds click event to button which fetches map
        bind();

        //clears body-tag of Html-Host-file
        container.clear();

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
       /* display.getLoadMapButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                rpcService_getCitiesAverageTemperatureList();
            }
        });
        */


    }

}

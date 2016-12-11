package we.are.en3.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import we.are.en3.client.MyClimateServiceAsync;
import we.are.en3.client.widget.slider.Slider;
import we.are.en3.client.widget.slider.SliderEvent;
import we.are.en3.client.widget.slider.SliderListener;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the data flow
 * between the Sever
 * and the Map View (of Class MapContentsView)
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class MapPresenter implements Presenter {

    /**
     * The class MapContentsView implements this interface Display.
     * Because of this interface Display the class MapPresenter
     * can access the attributes (instance variables, methods)
     * of the class MapContentsView which are defined in the
     * this interface, but not anything else.
     */
    public interface Display {

        //returns the slider from MapContentsView



        public void updateMap(final ArrayList<ArrayList> inp);

        //Returns View as Widget
        Widget asWidget();

        Slider getYearSlider();

       Label getYearText();


        //ToDo: Needed? Returns the Content Panel with Map
        //Widget getMapView();

        //ToDo: Needed? Returns the cellTable.
        //HasData getMapDisplay();


    }


    //instance variables from constructor
    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    //lists fetched by RPC from server side MyClimateServiceImpl
    private List<String> dates;


    private String currentYear;

    /**
     * Constructor: sets instance variables rpcService, eventBus and view
     * and calls init()
     */
    public MapPresenter(MyClimateServiceAsync rpcService, HandlerManager eventBus, MapPresenter.Display view){
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

        rpcService.getCitiesAverageTemperatureList(currentYear, new AsyncCallback<ArrayList<ArrayList>>() {
            public void onSuccess(ArrayList<ArrayList> result) {
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
        GWT.log("MapPresenter:bind()");



        //This is needed for the slider
        //Here are the reactions on a sliderevent implemented.
        display.getYearSlider().addListener(new SliderListener(){

            @Override
            public void onStart(SliderEvent e) {

            }

            @Override
            public boolean onSlide(SliderEvent e)
            {

                return true;
            }

            @Override
            public void onChange(SliderEvent e) {
                int year = e.getValues()[0];
                display.getYearText().setText(String.valueOf(year));
                MapPresenter.this.currentYear = String.valueOf(year);
                updateMap();
            }

            @Override
            public void onStop(SliderEvent e) {

            }
        });



    }

}

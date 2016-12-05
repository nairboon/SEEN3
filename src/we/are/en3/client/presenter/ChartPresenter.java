package we.are.en3.client.presenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import we.are.en3.client.MyClimateServiceAsync;

import java.util.ArrayList;

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


    }

    //instance variables from constructor
    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;


    public ChartPresenter(MyClimateServiceAsync rpcService, HandlerManager eventBus, Display view){
        //Information for Developer
        GWT.log("ChartPresenter: ChartPresenter()");

        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;
        //init();


    }

    void rpcService_getCitiesAverageTempPerYearList(ArrayList<String> cities, String startYear, String endYear){

        //Information for Developer
        GWT.log("MapPresenter:getCitiesAverageTempPerYearList()");

        rpcService.getCitiesAverageTempPerYearList(cities, startYear, endYear, new AsyncCallback<ArrayList<ArrayList<String>>>() {
            public void onSuccess(ArrayList<ArrayList<String>> result) {

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

    }

}

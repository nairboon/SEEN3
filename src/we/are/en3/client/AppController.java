package we.are.en3.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
import we.are.en3.client.presenter.ChartPresenter;
import we.are.en3.client.presenter.MapPresenter;
import we.are.en3.client.presenter.Presenter;
import we.are.en3.client.presenter.TablePresenter;
import we.are.en3.client.view.*;

/**
 * This class handles the Presenter Classes
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class AppController implements Presenter, ValueChangeHandler<String> {

    // HTML-Host-File Body fetched with RootLayoutPanel.get()
    private HasWidgets container;

    //ApplicationView has a MainPanel which contains a ContentsView
    AppView appView = new AppView(container);
    DockLayoutPanel mainLayoutPanel = appView.mainLayoutPanel;
    ContentsView contentsView = appView.contentsView;

    //TablePresenter and its Input
    private TablePresenter tablePresenter = null;
    private final TableContentsView tableContentsView =  new TableContentsView();

    //MapPresenter and its Input
    private MapPresenter mapPresenter = null;
    private final MapContentsView mapContentsView =  new MapContentsView();

    //ChartPresenter and its Input
    private ChartPresenter chartPresenter = null;
    private final ChartContentsView chartContentsView =  new ChartContentsView();

    //Event Bus and RPC Service
    private final HandlerManager eventBus;
    private final MyClimateServiceAsync rpcService;

    /**
     * Constructor method
     */
    public AppController(MyClimateServiceAsync rpcService, HandlerManager eventBus) {
        //Information for Developer
        GWT.log("AppController: AppController()");

        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();

    }

    /**
     * ToDo: What is this code doing
     *
     * @pre
     * @post
     * @param
     * @return
     */
    private void bind() {
        //Information for Developer
        GWT.log("AppController: bind()");

        //ToDo: What is this code doing
        History.addValueChangeHandler(this);

    }

    /**
     * ToDo: What is this code doing
     *
     * @pre
     * @post
     * @param
     * @return
     */
    public void go(final HasWidgets container) {
        //Information for Developer
        GWT.log("AppController: go()");

        // HTML-Host-File Body fetched with RootLayoutPanel.get()
        this.container = container;

        // Container: Represents the body of the HTML-Host-File, set with RootLayoutPanel.get()
        container.add(mainLayoutPanel);

        //ToDo: What is this code doing
        if ("".equals(History.getToken())) {
            History.newItem("Home");
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    /**
     * ToDo: What is this code doing
     *
     * @pre
     * @post
     * @param
     * @return
     */
    public void onValueChange(ValueChangeEvent<String> event) {
        //Information for Developer
        GWT.log("AppController: onValueChange()");

        //returns the TabPanel names defined in class ContentsView
        String token = event.getValue();

        //no empty string
        if (token != null) {

            //Presenter interface
            Presenter presenter = null;

            //fired event token is TableTab
            if (token.equals("Table")) {

                //create tablePresenter if non-existent,
                // this starts rpc.requests and populates the dropdown lists of the TextBoxes
                if (tablePresenter == null) {
                    tablePresenter = new TablePresenter(rpcService, eventBus, tableContentsView);
                }

                //execute
                tablePresenter.go(contentsView.tableContentsPanel);
                //tabPanelView.selectTab(1);

                //
                return;

            }

            //fired event token is MapTab
            if (token.equals("Map")) {

                //create tablePresenter if non-existent,
                // this starts rpc.requests and populates the dropdown lists of the TextBoxes
                if (mapPresenter == null) {
                    mapPresenter = new MapPresenter(rpcService, eventBus, mapContentsView);
                }

                //execute
                mapPresenter.go(contentsView.mapContentsPanel);
                //tabPanelView.selectTab(1);

                //
                return;

            }


            //fired event token is ChartTab
            if (token.equals("Chart")) {

                //create tablePresenter if non-existent,
                // this starts rpc.requests and populates the dropdown lists of the TextBoxes
                if (chartPresenter == null) {
                    chartPresenter = new ChartPresenter(rpcService, eventBus, chartContentsView);
                }

                //execute
                chartPresenter.go(contentsView.chartContentsPanel);
                //tabPanelView.selectTab(1);

                //
                return;

            }

            //ToDo: why should presenter be not null?
            if (presenter != null) {

                //ToDo: can that be called?
                presenter.go(container);
            }


        }
    }
}

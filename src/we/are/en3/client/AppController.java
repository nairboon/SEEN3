package we.are.en3.client;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;
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
    private final HandlerManager eventBus;
    private final MyClimateServiceAsync rpcService;
    private final TableContentsView tableContentsView =  new TableContentsView();

    /**
     * Constructor method
     */
    public AppController(MyClimateServiceAsync rpcService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();
    }

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    private void bind() {
        History.addValueChangeHandler(this);
    }

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    public void go(final HasWidgets container) {

        // HTML-Host-File Body fetched with RootLayoutPanel.get()
        this.container = container;

        // Container: Represents the body of the HTML-Host-File, set with RootLayoutPanel.get()
        container.add(mainLayoutPanel);

        if ("".equals(History.getToken())) {
            History.newItem("Home");
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    /**
     * ToDo: What is this code doing
     * @pre
     * @post
     * @param
     * @return
     */
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();
        if (token != null) {
            Presenter presenter = null;
            if (token.equals("Table")) {
                if(tablePresenter == null) {
                    tablePresenter = new TablePresenter(rpcService, eventBus, tableContentsView);
                }
                tablePresenter.go(contentsView.tableContentsPanel);
                //tabPanelView.selectTab(1);
                return;
            }
            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}

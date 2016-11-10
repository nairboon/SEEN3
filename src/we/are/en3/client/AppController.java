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

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final MyClimateServiceAsync rpcService;
    private HasWidgets container;

    private TablePresenter tablePresenter = null;

    TabLayoutPanel tabPanelView = new TabLayoutPanel(1.5, Style.Unit.EM);
    HomeContentsView homeContentsView = new HomeContentsView();
    MapContentsView mapContentsView = new MapContentsView();
    IMapContentsView iMapContentsView = new IMapContentsView();
    ScrollPanel tableContentsView = new ScrollPanel();


    public AppController(MyClimateServiceAsync rpcService, HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();


    }

    private void bind() {
        History.addValueChangeHandler(this);

    }


    public void go(final HasWidgets container) {
        this.container = container;

        // Main layout + panels
        setupLayout();




        if ("".equals(History.getToken())) {
            History.newItem("Home");
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    private  void setupLayout() {

        // replace dockpanel with View.java content
        DockLayoutPanel p = new DockLayoutPanel(Style.Unit.EM);
        p.addNorth(new HTML("header"), 2);
        p.addSouth(new HTML("footer"), 2);

        ContentsView contentsView = new ContentsView();



        String[] tabTitles = {"Home", "Table", "Map", "IMap"};
        tabPanelView.add(homeContentsView, tabTitles[0]);
        tabPanelView.add(tableContentsView, tabTitles[1]);
        tabPanelView.add(mapContentsView, tabTitles[2]);
        tabPanelView.add(iMapContentsView, tabTitles[3]);

        tabPanelView.addSelectionHandler(new SelectionHandler<Integer>(){
            public void onSelection(SelectionEvent<Integer> event) {
                //GWT.log("Easy to find: "+ event.getSelectedItem());
                History.newItem(tabTitles[event.getSelectedItem()]);
            }
        });


        p.add(tabPanelView);

        // container = RootLayoutPanel.get()
        container.add(p);
    }

    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
            Presenter presenter = null;

            if (token.equals("Table")) {
                if(tablePresenter == null) {
                    tablePresenter = new TablePresenter(rpcService, eventBus, new TableContentsView());
                }
                tablePresenter.go(tableContentsView);
                //tabPanelView.selectTab(1);
                return;
            }

            if (presenter != null) {
                presenter.go(container);
            }
        }
    }
}
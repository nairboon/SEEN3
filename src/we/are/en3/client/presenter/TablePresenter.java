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
import java.util.List;


public class TablePresenter implements Presenter{

    public interface Display {
        HasClickHandlers getLoadTableButton();
        void setData(List<String> data);
        void setInitData(List<String> countries, List<String> cities);
        String getSelectedCountry();
        Widget asWidget();
        Widget getTableView();
        HasData getCellTableDisplay();
    }

    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    private List<String> countries;

    String currentCountry;

    private static List<DataPoint> DATA = Arrays.asList(new DataPoint("John", 0.0,0.0,"a", "b", "123 Fourth Road","y"));

    public TablePresenter(MyClimateServiceAsync rpcService, HandlerManager eventBus, Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;


        // Create a data provider.
        AsyncDataProvider<DataPoint> dataProvider = new AsyncDataProvider<DataPoint>() {
            @Override
            protected void onRangeChanged(HasData<DataPoint> display) {
                final Range range = display.getVisibleRange();

                // Get the ColumnSortInfo from the table.
                //final ColumnSortList sortList = view.getCellTableDisplay().getColumnSortList();

                // This timer is here to illustrate the asynchronous nature of this data
                // provider. In practice, you would use an asynchronous RPC call to
                // request data in the specified range.
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
            }
        };

        // Connect the list to the data provider.
       // dataProvider.addDataDisplay(display.getCellTableDisplay());

        init();
    }

    public void bind() {


        display.getLoadTableButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                fetchTable();
            }
        });
    }

    @Override
    public void go(HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());


        rpcService.getList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {


                display.setData(result);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });


    }

    private void fetchTable() {
        currentCountry = display.getSelectedCountry();
        GWT.log("fetch table for: " + currentCountry);

        display.getTableView().setVisible(true);



    }

    private void init() {

        rpcService.getList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {

                countries = result;

                display.setInitData(countries, countries);
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });
    }
}

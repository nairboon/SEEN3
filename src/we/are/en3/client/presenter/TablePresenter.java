package we.are.en3.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import we.are.en3.client.MyClimateServiceAsync;

import java.util.ArrayList;
import java.util.List;


public class TablePresenter implements Presenter{

    public interface Display {
        void setData(List<String> data);
        Widget asWidget();
    }

    private final MyClimateServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    private List<String> countries;

    public TablePresenter(MyClimateServiceAsync rpcService, HandlerManager eventBus, Display view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = view;

        init();
    }

    @Override
    public void go(HasWidgets container) {

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

    private void init() {

        rpcService.getList(new AsyncCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> result) {
               countries = result;
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error fetching contact details");
            }
        });
    }
}

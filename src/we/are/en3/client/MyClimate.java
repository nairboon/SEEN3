package we.are.en3.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import we.are.en3.client.view.*;

/**
 * This is the entry point class on the client side.
 * Entry point classes define <code>onModuleLoad()</code>
 * With the RootLayoutPanel class they interact with the
 * Html-Host-file containers.
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 */
public class MyClimate implements EntryPoint {

    /**
     * This is the entry point method.
     * @pre
     * @post
     * @param
     * @return
     */
    public void onModuleLoad() {

        //load rpcService
        MyClimateServiceAsync rpcService = GWT.create(MyClimateService.class);

        //load eventBus
        HandlerManager eventBus = new HandlerManager(null);

        //load AppController
        AppController appController = new AppController(rpcService, eventBus);

        //Reference whole body-tag (or special div-tag-id) in Html-host-file
        appController.go(RootLayoutPanel.get());

    }

    /**
     * ToDo: What is this code doing
     */
    private static class MyAsyncCallback implements AsyncCallback<String> {

        /**
         * ToDo: What is this code doing
         */
        private Label label;

        /**
         * ToDo: What is this code doing
         * @pre
         * @post
         * @param
         * @return
         */
        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        /**
         * ToDo: What is this code doing
         * @pre
         * @post
         * @param
         * @return
         */
        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        /**
         * ToDo: What is this code doing
         * @pre
         * @post
         * @param
         * @return
         */
        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}

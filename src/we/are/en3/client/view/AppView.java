package we.are.en3.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * Created by benzro on 10.11.16.
 */
public class AppView {

    //Main Application Panel
    public DockLayoutPanel mainLayoutPanel = new DockLayoutPanel(Style.Unit.EM);

    //Content Panel is a TabPanel
    public ContentsView contentsView = new ContentsView();

    //Setup the App-Layout main View
    public AppView(HasWidgets container) {

        //Header
        mainLayoutPanel.addNorth(new HTML("MyClimate"), 2);

        //Footer
        mainLayoutPanel.addSouth(new HTML("Data Source: K. Meier, Data Supplier"), 2);

        //Content
        contentsView.tabPanelView.addSelectionHandler(new SelectionHandler<Integer>(){
            public void onSelection(SelectionEvent<Integer> event) {
                //GWT.log("Easy to find: "+ event.getSelectedItem());
                History.newItem(contentsView.tabTitles[event.getSelectedItem()]);
            }
        });

        //Add contents view
        mainLayoutPanel.add(contentsView.tabPanelView);

        //Add Layout
        this.mainLayoutPanel.getElement().getStyle().setVerticalAlign(2,Style.Unit.EM);
        this.mainLayoutPanel.getElement().getStyle().setBackgroundColor("rgb(240,240,240)");
        this.mainLayoutPanel.getElement().getStyle().setProperty("border","ridge gray 12px");
        this.mainLayoutPanel.getElement().getStyle().setHeight(24,Style.Unit.EM);
        this.mainLayoutPanel.getElement().getStyle().setWidth(42,Style.Unit.EM);
        this.mainLayoutPanel.getElement().getStyle().setMarginLeft(10,Style.Unit.EM);
        this.mainLayoutPanel.getElement().getStyle().setMarginTop(1,Style.Unit.EM);



    }

}

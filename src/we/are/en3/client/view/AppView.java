package we.are.en3.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * This class has the GUI Elements (Widgets)
 * to be rendered for the Dock Panel.
 * The Dock Panel constitutes the Main Panel element
 * that contains all other GUI Views.
 * It will be added into the Root Panel (in class AppController/MyClimate).
 * (Side note: The Root Panel sets up a reference to the HTML body tag in
 * the HTML-Host-File:MyClimate.html)
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class AppView {

    //Main Application Panel
    public DockLayoutPanel mainLayoutPanel = new DockLayoutPanel(Style.Unit.EM);

    //Content Panel is a TabPanel
    public ContentsView contentsView = new ContentsView();

    /**
     * Constructor: Sets up the (outer) App-Layout
     *
     */
    public AppView(HasWidgets container) {

        //Header
        HTML text;
        text = new HTML("<div style='color:green;text-align:center;padding:10px;'>" +
                "<b>MyClimate.com<b> </div>" );
        mainLayoutPanel.addNorth(text, 2);

        //Footer
        text = new HTML("<div style='color:black;text-align:center;padding:10px;'>" +
                "Data Source: K. Meier, Data Supplier. </div>" );
        mainLayoutPanel.addSouth(text, 2);

        /**
         * Selection handler for the AppView
         * ToDo: Method should probably be put into a separate AppPresenter or the AppController
         */
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

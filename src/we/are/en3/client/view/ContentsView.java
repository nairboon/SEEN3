package we.are.en3.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import we.are.en3.client.presenter.Presenter;

/**
 * This class has the GUI Elements (Widgets)
 * to be rendered for the Tab Panel
 * within the Dock Panel (in class AppView).
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class ContentsView extends Composite {
	
	//Main Content Panel
    public TabLayoutPanel tabPanelView = new TabLayoutPanel(1.5, Style.Unit.EM);
	
	//Home Tab Panel
    HomeContentsView homeContentsView = new HomeContentsView();

    //Table Tab Panel
    TableContentsView tableContentsView = new TableContentsView();
    public ScrollPanel tableContentsPanel = new ScrollPanel();

    //Map Tab Panel
	MapContentsView mapContentsView = new MapContentsView();

    //Chart Tab Panel
	ChartContentsView chartContentsView = new ChartContentsView();

	//TabPanel Titles
	public String[] tabTitles = {"Home", "Table", "Map", "Chart"};

	//Constructor: Sets up the (outer) Tab Panel
	public ContentsView() {

        //Assemble tabPanel
		tabPanelView.add(homeContentsView, tabTitles[0]);
	    tabPanelView.add(tableContentsPanel, tabTitles[1]);
	    tabPanelView.add(mapContentsView, tabTitles[2]);
	    tabPanelView.add(chartContentsView, tabTitles[3]);
	    
	    //Give focus to first tab
	    tabPanelView.selectTab(0);

	    //tabPanelView.ensureDebugId("cwTabPanel");
	}


}


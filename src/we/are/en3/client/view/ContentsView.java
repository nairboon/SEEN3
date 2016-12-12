package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;

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
	public MapContentsView mapContentsView = new MapContentsView();
	public ScrollPanel mapContentsPanel = new ScrollPanel();

    //Chart Tab Panel
	public ChartContentsView chartContentsView = new ChartContentsView();
	public ScrollPanel chartContentsPanel = new ScrollPanel();

	//TabPanel Titles
	public String[] tabTitles = {"Home", "Table", "Map", "Chart"};

	/**
	 * Constructor: Sets up the (outer) Tab Panel
	 *
	 */
	public ContentsView() {
		//Information for Developer
		GWT.log("ContentsView:  ContentsView()");

        //Assemble tabPanel
		tabPanelView.add(homeContentsView, tabTitles[0]);
	    tabPanelView.add(tableContentsPanel, tabTitles[1]);
	    tabPanelView.add(mapContentsPanel, tabTitles[2]);
	    tabPanelView.add(chartContentsPanel, tabTitles[3]);
	    
	    //Give focus to first tab
	    tabPanelView.selectTab(0);

	    //tabPanelView.ensureDebugId("cwTabPanel");
	}


}


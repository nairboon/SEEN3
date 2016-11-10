package we.are.en3.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.*;
import we.are.en3.client.presenter.Presenter;

/**
 * The Navigation as a GWT-TabPanel-Composite
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
	
	public ContentsView() {	
		
		//Initialize parent widget
		//initWidget(this.tabPanelView);
				
		//TabPanel settings 
		//tabPanelView.getElement().getStyle().setMarginBottom(0.0, Unit.EM);
		//tabPanelView.setPixelSize(650, 270);

		tabPanelView.add(homeContentsView, tabTitles[0]);
	    tabPanelView.add(tableContentsPanel, tabTitles[1]);
	    tabPanelView.add(mapContentsView, tabTitles[2]);
	    tabPanelView.add(chartContentsView, tabTitles[3]);
	    
	    //Give focus to first tab
	    tabPanelView.selectTab(0);
	    //tabPanelView.ensureDebugId("cwTabPanel");
	}


}


package we.are.en3.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TabPanel;
import we.are.en3.client.presenter.Presenter;

/**
 * The Navigation as a GWT-TabPanel-Composite
 * 
 */
public class ContentsView extends Composite {
	
	TabPanel tabPanelView = new TabPanel();
	
	HomeContentsView homeContentsView = new HomeContentsView();
	MapContentsView mapContentsView = new MapContentsView();
	IMapContentsView iMapContentsView = new IMapContentsView();
	TableContentsView tableContentsView = new TableContentsView();	
	
	public ContentsView() {	
		
		//Initialize parent widget
		initWidget(this.tabPanelView);
				
		//TabPanel settings 
		tabPanelView.getElement().getStyle().setMarginBottom(0.0, Unit.EM);
		tabPanelView.setPixelSize(650, 270);
		
		//TabPanel Titles
		String[] tabTitles = {"Home", "Table", "Map", "IMap"};
		tabPanelView.add(homeContentsView, tabTitles[0]);
	    tabPanelView.add(tableContentsView, tabTitles[1]);
	    tabPanelView.add(mapContentsView, tabTitles[2]);
	    tabPanelView.add(iMapContentsView, tabTitles[3]);
	    
	    //Give focus to first tab
	    tabPanelView.selectTab(0);
	    //tabPanelView.ensureDebugId("cwTabPanel");
	}


}


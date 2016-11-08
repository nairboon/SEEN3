package we.are.en3.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Composite;

/**
 * The View as a GWT-Composite
 */
public class View extends Composite{

	//fixed structure
	private HorizontalPanel hPanel = new HorizontalPanel();
	private VerticalPanel vPanelLeft = new VerticalPanel();
	private VerticalPanel vPanelRight = new VerticalPanel();	
	private HorizontalPanel hPanelLeftTop = new HorizontalPanel();
	private HorizontalPanel hPanelLeftBottom = new HorizontalPanel();
	private HorizontalPanel hPanelRightTop = new HorizontalPanel();
	private HorizontalPanel hPanelRightBottom = new HorizontalPanel();
	
	//content
	ContentsView contentsView = new ContentsView();
	DataSourceView dataSourceView = new DataSourceView();
	MainFilterView filterView = new MainFilterView();
	TimelineFilterView timelineView = new TimelineFilterView();
		
	public View () {
		
		//Initialize parent widget to be wrapped
		initWidget(this.hPanel);
		
		//parent panel
		this.hPanel.getElement().getStyle().setBackgroundColor("rgb(240,240,240)");
		this.hPanel.setBorderWidth(10);
		this.hPanel.setSpacing(5);
		
		//add child left panel
		this.hPanel.add(vPanelLeft);
		this.hPanel.setCellWidth(vPanelLeft, "200px");
		this.hPanel.setCellHeight(vPanelLeft, "370px");
		this.vPanelLeft.setBorderWidth(1);
		this.vPanelLeft.setSpacing(1);
		
		//add child right panel
		this.hPanel.add(vPanelRight);
		this.hPanel.setCellWidth(vPanelRight, "650px");
		this.hPanel.setCellHeight(vPanelRight, "370px");
		this.vPanelRight.setBorderWidth(1);
		this.vPanelRight.setSpacing(1);
		
		//add grand-child left top panel
		this.vPanelLeft.add(hPanelLeftTop);
		this.vPanelLeft.setCellWidth(hPanelLeftTop, "200px");
		this.vPanelLeft.setCellHeight(hPanelLeftTop, "270px");
		this.hPanelLeftTop.setSpacing(1);
		//--> add content to left top
		this.hPanelLeftTop.add(filterView);
		
		//add grand-child left bottom panel
		this.vPanelLeft.add(hPanelLeftBottom);
		this.vPanelLeft.setCellWidth(hPanelLeftBottom, "200px");
		this.vPanelLeft.setCellHeight(hPanelLeftBottom, "100px");
		this.hPanelLeftBottom.setSpacing(1);
		//--> add content to left bottom
		this.hPanelLeftBottom.add(dataSourceView);
		
		//add grand-child right top panel
		this.vPanelRight.add(hPanelRightTop);
		this.vPanelRight.setCellWidth(hPanelRightTop, "650px");
		this.vPanelRight.setCellHeight(hPanelRightTop, "270px");
		this.hPanelRightTop.setSpacing(1);
		//--> add content right top
		this.hPanelRightTop.add(contentsView);
		
		//add grand-child right bottom panel 
		this.vPanelRight.add(hPanelRightBottom);
		this.vPanelRight.setCellWidth(hPanelRightBottom, "650px");
		this.vPanelRight.setCellHeight(hPanelRightBottom, "100px");
		this.hPanelRightBottom.setSpacing(1);
		//--> add content right bottom
		this.hPanelRightBottom.add(timelineView);
		
	}
}

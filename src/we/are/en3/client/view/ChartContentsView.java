package we.are.en3.client.view;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.*;

/**
 * This class has the GUI Elements (Widgets)
 * to be rendered for the Chart Tab
 * of the Tab Panel (in class ContentsView).
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class ChartContentsView extends Composite{

	//Main Panel
	VerticalPanel vPanel = new VerticalPanel();

	//Filter Panel
	FlowPanel selectionPanel = new FlowPanel();
	ListBox countryDB = new ListBox();
	ListBox cityDB = new ListBox();
	Button loadChartButton = new Button("Load Chart");

	//Content Panel
	VerticalPanel vchartPanel = new VerticalPanel();
	ScrollPanel scrollPanel = new ScrollPanel();

	//Constructor: Sets up the Chart Tab's panels, filtering widgets and static elements
	public ChartContentsView(){

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Top: Titel
		HTML text = new HTML("<div style='color:blue;text-align:justify;padding:10px;'>" +
				" This Site shows a Chart with a Temparature Graph </div>");
		vPanel.add(text);

		//Center: Filter
		vPanel.add(selectionPanel);
		selectionPanel.add(cityDB);
		selectionPanel.add(loadChartButton);

		//Bottom: Content
		vPanel.add(scrollPanel);
		scrollPanel.setHeight("225px");
		Image img = new Image("timeline.png");
		img.asWidget().setPixelSize(650,220);
		scrollPanel.add(img);

	}
}

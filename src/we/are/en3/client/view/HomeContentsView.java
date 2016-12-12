package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.Window;

/**
 * This class has the GUI Elements (Widgets)
 * to be rendered for the Home Tab
 * of the Tab Panel (in class ContentsView).
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class HomeContentsView extends Composite {

	//Main Contents View Panel
	VerticalPanel vPanel = new VerticalPanel();

	//Content Panel
	ScrollPanel scrollPanel = new ScrollPanel();

	/**
	 * Constructor: Sets up the Home Tab's panels and static elements
	 *
	 */
	public HomeContentsView(){
		//Information for Developer
		GWT.log("HomeContentsView:  HomeContentsView()");

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Top: Titel
		HTML text = new HTML("<div style='color:blue;text-align:justify;padding:10px;'>" +
				"Climate change is one of the most complex issues facing us today. " +
				"It involves many dimensions – science, economics, society, politics and moral " +
				"and ethical questions – and is a global problem, felt on local scales, that " +
				"will be around for decades and centuries to come. " +
				"( NASA's Jet Propulsion Lab | California Institute of Technology )</div>");

		vPanel.add(text);

		//Center: Image
		vPanel.add(scrollPanel);

		int clientWidth = Window.getClientWidth()-10;
		int clientHeight = Window.getClientHeight()-100;
		String clientHeightString = String.valueOf(clientHeight)+"px";
		scrollPanel.setHeight(clientHeightString);
		Image img = new Image("climate-change.jpg");

		img.asWidget().setPixelSize(clientWidth,clientWidth*220/657);
		scrollPanel.add(img);

	}
}

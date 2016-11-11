package we.are.en3.client.view;

import com.google.gwt.user.client.ui.*;

public class HomeContentsView extends Composite {

	//Main Panel
	VerticalPanel vPanel = new VerticalPanel();

	//Content Panel
	VerticalPanel vchartPanel = new VerticalPanel();
	ScrollPanel scrollPanel = new ScrollPanel();

	public HomeContentsView(){

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

		//Center: Filter
		vPanel.add(scrollPanel);
		scrollPanel.setHeight("225px");
		Image img = new Image("climate-change.jpg");
		img.asWidget().setPixelSize(630,220);
		scrollPanel.add(img);

	}
}

package we.are.en3.client.view;

import com.google.gwt.user.client.ui.*;

//two possible slider packages
//import com.kiouri.sliderbar.client.view.SliderBarHorizontal;
//import com.google.gwt.widgetideas.client.SliderBar;

//a possible map package
//import com.google.gwt.visualization.client.visualizations.GeoMap;

/**
 * This class has the GUI Elements (Widgets)
 * to be rendered for the Map Tab
 * of the Tab Panel (in class ContentsView).
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class MapContentsView extends Composite{

	//Main Panel
	VerticalPanel vPanel = new VerticalPanel();

	//Filter Panel with slider
	FlowPanel selectionPanel = new FlowPanel();

	//ToDo: Choose a Slider Type (Whatever works best)
	//SliderBarHorizontal slider = new SliderBarHorizontal();
	//OR
	//SliderBar slider = new SliderBar(1750,2016);

	//Content Panel
	VerticalPanel vchartPanel = new VerticalPanel();
	ScrollPanel scrollPanel = new ScrollPanel();

	//Constructor: Sets up the Map Tab's panels, filtering widgets and static elements
	public MapContentsView(){

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Top: Titel
		HTML text = new HTML("<div style='color:blue;text-align:justify;padding:10px;'>" +
				"This Site shows a world Map with Temparature for your prefered date. </div>" );

		vPanel.add(text);

		//Center: Filter
		vPanel.add(selectionPanel);
		//ToDo: Implement slider widget
//		slider.setStepSize(1.0);
//		slider.setCurrentValue(2000.0);
//		slider.setNumTicks(28);
//		slider.setNumLabels(14);
		//selectionPanel.add(slider);

		//Bottom: Content
		vPanel.add(scrollPanel);
		scrollPanel.setHeight("225px");
		Image img = new Image("TempMap.jpg");
		img.asWidget().setPixelSize(630,220);
		scrollPanel.add(img);

	}

}

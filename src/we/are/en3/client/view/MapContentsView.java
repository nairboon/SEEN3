package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;
import we.are.en3.client.presenter.MapPresenter;

import java.util.ArrayList;

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
public class MapContentsView extends Composite implements MapPresenter.Display{

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

	Button loadMapButton = new Button("Load Map");


	/**
	 * Constructor: Sets up the Map Tab's panels, filtering widgets and static elements
	 *
	 */
	public MapContentsView(){
		//Information for Developer
		GWT.log("MapContentsView: MapContentsView()");

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

	/**
	 * This method is necessary for GWT to provide its magic.
	 * It returns this class as a Widget.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	public Widget asWidget() {
		//Information for Developer
		GWT.log("TableContentsView: asWidget()");

		return this;
	}

	/**
	 * ToDo
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	public HasClickHandlers getLoadMapButton(){
		//Information for Developer
		GWT.log("MapContentsView: getLoadMapButton()");

		return loadMapButton;
	}

	/**
	 * ToDo
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	public void showMap(ArrayList<ArrayList<String>> dataArray){
		//Information for Developer
		GWT.log("MapContentsView: showMap()");


	}
}

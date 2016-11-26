package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.kiouri.sliderbar.client.solution.simplehorizontal.SliderBarSimpleHorizontal;
import we.are.en3.client.presenter.MapPresenter;

import java.util.ArrayList;


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

	//TextPanel
	VerticalPanel vTextPanel = new VerticalPanel();

	//Filter Panel with slider
	VerticalPanel vSliderPanel = new VerticalPanel();

	//Content Panel
	VerticalPanel vMapPanel = new VerticalPanel();

	//ToDo: decide if needed
	Button loadMapButton = new Button("Load Map");

	//Slider
	SliderBarSimpleHorizontal slider = new SliderBarSimpleHorizontal(20 ,"200px", true);


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

		vTextPanel.add(text);
		vPanel.add(vTextPanel);

		//
//		geoChart = new GeoChart();
//		display.updateVisualization(geoChart, dataTable);
//		vPanel.add(vMapPanel);
//		updateVisualization(vMapPanel);

	}

	/**
	 Updates the visualization and displays the map and the requested data
	 @pre nothing
	 @post nothing
	 @param  geoChart todo
	 **/
	@Override
	public void updateVisualization(GeoChart geoChart, DataTable dataTable) {
		//Information for Developer
		GWT.log("MapContentsView:updateVisualization()");

		//Center: Filter
		//ToDo: Implement slider widget
		vSliderPanel.setHeight("50px");
		vSliderPanel.setWidth("500px");
		vSliderPanel.add(slider);
		vPanel.add(vSliderPanel);

		//bottom: Map
		vPanel.setHeight("70vh");
		vPanel.setWidth("70vw");

		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		options.setDatalessRegionColor("OliveDrab");

		// Draw the chart
		geoChart.draw(dataTable, options);
		vPanel.add(geoChart);

	}

	/**
	 Returns the name respectively type of the visualization as String
	 @pre nothing
	 @post nothing
	 @return Returns the name of the visualization
	 **/
	public String getName() {
		return "Map Visualization";
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

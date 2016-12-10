package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.*;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;
import com.googlecode.gwt.charts.client.options.DisplayMode;
import com.kiouri.sliderbar.client.solution.simplehorizontal.SliderBarSimpleHorizontal;
import we.are.en3.client.presenter.MapPresenter;
import we.are.en3.client.util.GeoChartLoader;
import we.are.en3.client.util.OurGeoChartOptions;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import we.are.en3.client.widget.slider.Slider;




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

	private GeoChart geoChart;
	private boolean geoChartIsLoaded = false;

	OurGeoChartOptions mapOptions = null;

	//Main Panel
	VerticalPanel vPanel = new VerticalPanel();

	//TextPanel
	VerticalPanel vTextPanel = new VerticalPanel();

	//Filter Panel with slider
	HorizontalPanel hSliderPanel = new HorizontalPanel();
	HorizontalPanel hSelectionPanel = new HorizontalPanel();

	//Map Panel
	VerticalPanel vMapPanel = new VerticalPanel();



	//ToDo: decide if needed
	ListBox dateDB = new ListBox();
	Button loadMapButton = new Button("Load Map");

	private Slider yearSlider;

	private Label yearText;

	/**
	 * Constructor: Sets up the Map Tab's panels, filtering widgets and static elements
	 *
	 */
	public MapContentsView(){
		//Information for Developer
		GWT.log("MapContentsView: MapContentsView()");

		initWidget(this.vPanel);

		//Top: Titel
		HTML text = new HTML("<div style='color:blue;text-align:justify;padding:10px;'>" +
				"This Site shows a world Map with Temparature for your prefered date. </div>" );

		vTextPanel.add(text);
		vTextPanel.setHeight("5vh");
		vPanel.add(vTextPanel);

		//Set size constraints
		hSliderPanel.setHeight("5vh");
		hSliderPanel.setWidth("40vw");


		vPanel.add(hSliderPanel);


		//New Slider
		FlowPanel sliderWrapper = new FlowPanel();
		sliderWrapper.getElement().setId("sliderwrapper");
		int defaultYear = 2012;
		yearSlider = new Slider("slider", 1743,2013,defaultYear);


		yearText = new Label();
		yearText.setText(String.valueOf(defaultYear));
		sliderWrapper.add(yearText);
		sliderWrapper.add(yearSlider);

		hSliderPanel.add(sliderWrapper);

		mapOptions = OurGeoChartOptions.create();
		mapOptions.setDisplayMode("markers");
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		//geoChartColorAxis.setColors(["green", "yellow", "red"]);
		mapOptions.setColorAxis(geoChartColorAxis);
		mapOptions.setDatalessRegionColor("gray");


	}

	/**
	 Draws the initial visualization of the map
	 @pre nothing
	 @post nothing
	 @param
	 **/
//	AIzaSyAoi_p3fThssrmv_xkdy5v2B5D6J5FsSR4

	private void loadGeoChart(final Runnable done) {
		GeoChartLoader gcloader = new GeoChartLoader("AIzaSyC5bRUsAWLBxt-xnJriy7uVXTsLikTmGGQ");

		gcloader.load(new Runnable() {
			@Override
			public void run() {
				GWT.log("api loaded");
				// Create and attach the chart
				geoChart = new GeoChart();
				vPanel.add(geoChart);
				geoChartIsLoaded = true;
				done.run();
			}
		});
	}
	/**
	public void loadGeoMap(final ArrayList<ArrayList<String>> dataArray, final String currentYear) {
		//Information for Developer
		GWT.log("MapPresenter:loadGeoMap()");


		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable() {

			public void run() {

				//GeoChart
				GeoChart geoChart = new GeoChart();

				//prepare dataTable data structure
				DataTable dataTable = prepareDataTable(dataArray, currentYear);
				vMapPanel.add(geoChart);
				vPanel.add(vMapPanel);
				updateVisualization(geoChart, dataTable);

			}
		});

	}
**/
	/**
	 * ToDo: What is this code doing
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	public DataTable prepareDataTable(ArrayList<ArrayList<String>> dataArray, String currentYear){

		//Information for Developer
		GWT.log("MapPresenter:prepareDataTable()");

		// Prepare the data table
		DataTable dataTable = DataTable.create();

		dataTable.addColumn(ColumnType.STRING, "City");
		dataTable.addColumn(ColumnType.STRING, "Label");

		//fill dataTable
		int numberOfRows=dataArray.size();
		dataTable.addRows(numberOfRows);
		int iter=0;
		for (ArrayList<String> city_Temp : dataArray) {
			//city
			String city = city_Temp.get(0);
			dataTable.setValue(iter, 0, city);
			//label
			String label = city_Temp.get(0) + " ( " + city_Temp.get(1) + ", " + currentYear + " )";
			dataTable.setValue(iter, 1, label);
			iter++;
		}

		return dataTable;

	}

	/**
	 Updates the visualization and displays the map and the requested data
	 @pre nothing
	 @post nothing
	 @param  geoChart todo
	 **/

	public void updateMap(final ArrayList<ArrayList<String>> inp, final String currentYear) {

		if(geoChartIsLoaded) {
			drawMap(prepareDataTable(inp,currentYear));
		} else {
			loadGeoChart(new Runnable() {
				@Override
				public void run() {
					drawMap(prepareDataTable(inp,currentYear));
				}
			});
		}
	}
/**
	public void updateVisualization(GeoChart geoChart, DataTable dataTable) {
		//Information for Developer
		GWT.log("MapContentsView:updateVisualization()");

		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		options.setDatalessRegionColor("OliveDrab");
		geoChart.setPixelSize(650,320);
		options.setDisplayMode(DisplayMode.findByName("markers"));

		//bottom: Map

		geoChart.draw(dataTable, options);

	}
 **/

	public Label getYearText() { return this.yearText; }
	public Slider getYearSlider() { return this.yearSlider; }




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

	private void drawMap(DataTable tbl) {
		GWT.log("drawMap");
		geoChart.draw(tbl, mapOptions);
		GWT.log("drawMap:done");
	}


}

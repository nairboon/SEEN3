package we.are.en3.client.view;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.options.DisplayMode;
import com.googlecode.gwt.charts.client.util.ArrayHelper;
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
	private boolean geoChartisLoaded = false;

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

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Top: Titel
		HTML text = new HTML("<div style='color:blue;text-align:justify;padding:10px;'>" +
				"This Site shows a world Map with Temparature for your prefered date. </div>" );

		vTextPanel.add(text);
		vTextPanel.setHeight("5vh");



		//Set size constraints
		hSliderPanel.setHeight("5vh");
		hSliderPanel.setWidth("40vw");






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

		vPanel.add(vTextPanel);

		vPanel.add(hSliderPanel);



		mapOptions = OurGeoChartOptions.create();
		//mapOptions.setDisplayMode("markers");
		mapOptions.setDisplayMode(DisplayMode.findByName("markers"));

		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		geoChartColorAxis.setColors("blue", "red");
		mapOptions.setColorAxis(geoChartColorAxis);
		mapOptions.setDatalessRegionColor("gray");

	}

	/**
	 * Laode the google geochart js code
	 *
	 * @pre geoChartisLoaded == false
	 * @post geoChartisLoaded == true
	 * @param done the callback after geocharts is loaded
	 * @return -
	 */

    private void loadGeoChart(final Runnable done) {
		GeoChartLoader gcloader = new GeoChartLoader("AIzaSyAoi_p3fThssrmv_xkdy5v2B5D6J5FsSR4");

		gcloader.load(new Runnable() {
			@Override
			public void run() {
				GWT.log("api loaded");
				// Create and attach the chart
				geoChart = new GeoChart();
				vPanel.add(geoChart);
				geoChartisLoaded = true;
				done.run();
			}
		});
	}

	/**
	 * Converts a String[][2] to a geochart.Datatable
	 *
	 * @pre geoChartisLoaded == true
	 * @post -
	 * @param input is a String[][2] array
	 * @return the inputarray as a Datatable with columns City, Label
	 */

	public DataTable ArrayToDataTable(ArrayList<ArrayList> input) {
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.NUMBER, "Lat");
		dataTable.addColumn(ColumnType.NUMBER, "Long");

		dataTable.addColumn(ColumnType.STRING, "Label");
		dataTable.addColumn(ColumnType.NUMBER, "Temperature");



		for(ArrayList<String> city : input) {
			GWT.log("add:" + city.toString());
			dataTable.addRow(city.toArray());
		}

		return dataTable;
	}


	/**
	 * Starts updating the map, loads geoCharts if it is not yet loaded
	 *
	 * @pre
	 * @post -
	 * @param input is a String[][2] array
	 * @return void
	 */

    public void updateMap(final ArrayList<ArrayList> inp) {

    	if(geoChartisLoaded) {
    		_drawMap(ArrayToDataTable(inp));
		} else {
    		loadGeoChart(new Runnable() {
				@Override
				public void run() {
					_drawMap(ArrayToDataTable(inp));
				}
			});
		}
	}

	public Label getYearText() { return this.yearText; }
	public Slider getYearSlider() { return this.yearSlider; }

	/**
	 * Actually draws the map
	 *
	 * @pre geoChartisLoaded == true
	 * @post -
	 * @param tbl the datatable to be displayed
	 * @return void
	 */
	private void _drawMap(DataTable tbl) {
    	GWT.log("drawMap");
		geoChart.draw(tbl, mapOptions);
		GWT.log("drawMap:done");
	}


}

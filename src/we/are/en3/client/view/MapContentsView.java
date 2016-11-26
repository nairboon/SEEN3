package we.are.en3.client.view;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.ui.*;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import we.are.en3.client.presenter.MapPresenter;
import we.are.en3.client.util.GeoChartLoader;
import we.are.en3.client.util.OurGeoChartOptions;

/*
import com.kiouri.sliderbar.client.event.BarValueChangedEvent;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.kiouri.sliderbar.client.solution.simplehorizontal.SliderBarSimpleHorizontal;

*/
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

	private GeoChart geoChart;
	private boolean geoChartisLoaded = false;

	OurGeoChartOptions mapOptions = null;

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

	/**
	 * Constructor: Sets up the Map Tab's panels, filtering widgets and static elements
	 *
	 */
	public MapContentsView(){
		//Information for Developer
		GWT.log("MapContentsView: MapContentsView()");

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);


		mapOptions = OurGeoChartOptions.create();
		mapOptions.setDisplayMode("text");
		GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
		//geoChartColorAxis.setColors(["green", "yellow", "red"]);
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

	public DataTable ArrayToDataTable(ArrayList<ArrayList<String>> input) {
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "City");
		dataTable.addColumn(ColumnType.STRING, "Label");


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

    public void updateMap(final ArrayList<ArrayList<String>> inp) {

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

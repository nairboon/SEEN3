package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.*;
import we.are.en3.client.presenter.ChartPresenter;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;

import java.util.ArrayList;

/**
 * This class has the GUI Elements (Widgets)
 * to be rendered for the Chart Tab
 * of the Tab Panel (in class ContentsView).
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class ChartContentsView extends Composite implements ChartPresenter.Display{

	//Main Panel
	VerticalPanel vPanel = new VerticalPanel();

	 public void initialize(ArrayList<ArrayList<String>> dataArray) {
	 ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
	 chartLoader.loadApi(new Runnable() {

	@Override
	public void run() {
	// Create and attach the chart
	lineChart = new LineChart();
	vchartPanel.add(lineChart);
	// draw(dataArray);
	}
	});
	 }


	private void draw(final ArrayList<ArrayList<String>> dataArray) {

		DataTable dataTable = this.prepareDataTable(dataArray);

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("#f0f0f0");
		options.setFontName("Tahoma");
		options.setTitle("Yearly Coffee Consumption by Country");
		options.setHAxis(HAxis.create("Year"));
		options.setVAxis(VAxis.create("Cups"));


		// Draw the chart
		lineChart.draw(dataTable, options);

	}




	//Filter Panel
	FlowPanel selectionPanel = new FlowPanel();
	ListBox countryDB = new ListBox();
	ListBox cityDB = new ListBox();

	Button loadChartButton = new Button("Load Chart");

	//Content Panel
	VerticalPanel vchartPanel = new VerticalPanel();


	LineChart lineChart;
	/**
	 * Constructor: Sets up the Chart Tab's panels, filtering widgets and static elements
	 *
	 */
	public ChartContentsView(){
		//Information for Developer
		GWT.log("ChartContentsView: ChartContentsView()");

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
	//	vPanel.add(scrollPanel);
	//	scrollPanel.setHeight("225px");
	//	Image img = new Image("timeline.png");
	//	img.asWidget().setPixelSize(650,220);
	//	scrollPanel.add(img);





	}

	public DataTable prepareDataTable(ArrayList<ArrayList<String>> dataArray) {

		// Temporarily used to have range of years
		int startYear = 1800, endYear = 2012;
		int numberOfYears = endYear - startYear;

		//Information for Developer
		GWT.log("MapPresenter:prepareDataTable()");

		// Prepare the data table
		DataTable dataTable = DataTable.create();

		// Adds
		dataTable.addColumn(ColumnType.STRING, "Year");
		for (int i = 0; i < dataArray.size(); i++) {
			dataTable.addColumn(ColumnType.NUMBER, dataArray.get(i).get(0));
		}
		dataTable.addRows(numberOfYears);
		for (int i = 0; i < numberOfYears + 1; i++) {
			dataTable.setValue(i, 0, String.valueOf(startYear + i));
		}
		for (int col = 0; col < dataArray.size(); col++) {
			for (int row = 0; row < numberOfYears - 1; row++) {
				dataTable.setValue(row, col + 1, dataArray.get(col).get(row + 1));
			}
		}


		return dataTable;

	}

}

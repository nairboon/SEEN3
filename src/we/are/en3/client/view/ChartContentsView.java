package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
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
import java.util.List;

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





	//Filter Panel
	FlowPanel selectionPanel = new FlowPanel();
	ListBox countryDB = new ListBox();
	ListBox cityDB = new ListBox();

	Button loadChartButton = new Button("Load Chart");

	//Content Panel
	VerticalPanel vchartPanel = new VerticalPanel();


	LineChart lineChart;

	public int startYear = 1800;
	public int endYear = 2013;
	/**
	 * Constructor: Sets up the Chart Tab's panels, filtering widgets and static elements
	 *
	 */
	public ChartContentsView(){
		//Information for Developer
		GWT.log("ChartContentsView: ChartContentsView()");

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		vPanel.setWidth("100%");

		//Top: Titel
		HTML text = new HTML("<div style='color:blue;text-align:justify;padding:10px;'>" +
				" This Site shows a Chart with a Temparature Graph </div>");
		vPanel.add(text);

		//Center: Filter
		vPanel.add(selectionPanel);
		selectionPanel.add(cityDB);
		selectionPanel.add(loadChartButton);

		vPanel.add(vchartPanel);

	}

	public void initialize(final ArrayList<ArrayList<String>> dataArray) {
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {

				GWT.log("linechart loaded");
				// Create and attach the chart
				lineChart = new LineChart();
				vchartPanel.add(lineChart);
				 draw(dataArray);
			}
		});
	}


	private void draw(final ArrayList<ArrayList<String>> dataArray) {

		DataTable dataTable = this.prepareDataTable(dataArray);

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setBackgroundColor("#f0f0f0");
		options.setFontName("Tahoma");
		options.setTitle("Yearly Average Temperature");
		options.setHAxis(HAxis.create("Year"));
		options.setVAxis(VAxis.create("Temperature"));
		options.setWidth(1000);
		options.setHeight(500);

		// Draw the chart
		lineChart.draw(dataTable, options);

	}


	/**
	 * his method is filling the TextBox's areas dropdown lists in the filter panel.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public void setInitAreas(List<String> areas) {
		//Information for Developer
		GWT.log("ChartContentsView: setInitAreas()");

		cityDB.clear();

		GWT.log(areas.toString());
		for (int i = 0; i < areas.size(); ++i) {
			cityDB.addItem(areas.get(i));
		}

	}

	@Override
	public String getSelectedArea() {
		//Information for Developer
		GWT.log("TableContentsView: getSelectedArea()");

		return cityDB.getSelectedItemText();
	}

	@Override
	public HasClickHandlers getLoadChartButton() {
		GWT.log("ChartContentsView: getLoadChartButton()");
		return loadChartButton;
	}

	public DataTable prepareDataTable(ArrayList<ArrayList<String>> dataArray) {

		// Temporarily used to have range of years

		int numberOfYears = endYear - startYear;

		//Information for Developer
		GWT.log("MapPresenter:prepareDataTable(), size: " + dataArray.size());

		// Prepare the data table
		DataTable dataTable = DataTable.create();

		// Adds
		dataTable.addColumn(ColumnType.STRING, "Year");
		for (int i = 0; i < dataArray.size(); i++) {
			dataTable.addColumn(ColumnType.NUMBER, dataArray.get(i).get(0));
		}
		dataTable.addRows(numberOfYears);
		for (int i = 0; i < numberOfYears; i++) {
			dataTable.setValue(i, 0, String.valueOf(startYear + i));
		}
		for (int col = 0; col < dataArray.size(); col++) {
			for (int row = 0; row < numberOfYears - 1; row++) {
				String val = dataArray.get(col).get(row + 1);
				if(val == "NaN")
					continue;
					//GWT.log("NaN");
				dataTable.setValue(row, col + 1,val );
			}
		}


		return dataTable;

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
		GWT.log("ChartContentsView: asWidget()");

		return this;
	}

	@Override
	public String getStartYear() {
		return String.valueOf(startYear);
	}

	@Override
	public String getEndYear() {
		return String.valueOf(endYear);
	}

}

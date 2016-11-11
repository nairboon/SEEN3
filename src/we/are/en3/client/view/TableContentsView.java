package we.are.en3.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.HasData;
import we.are.en3.client.model.DataPoint;
import we.are.en3.client.presenter.TablePresenter;

import java.util.List;

/**
 * This class has the GUI Elements (Widgets)
 * to be rendered for the Table Tab
 * of the Tab Panel (in class ContentsView).
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class TableContentsView extends Composite implements TablePresenter.Display{

	//Main Panel
	VerticalPanel vPanel = new VerticalPanel();

	//Filter Panel
	FlowPanel selectionPanel = new FlowPanel();
	ListBox countryDB = new ListBox();
	ListBox cityDB = new ListBox();
	ListBox dateFromDB = new ListBox();
	ListBox dateToDB = new ListBox();
	Button loadTableButton = new Button("Load Table");

	//Content Panel with CellTable
	VerticalPanel vtablePanel = new VerticalPanel();
	ScrollPanel scrollPanel = new ScrollPanel();
	final CellTable<DataPoint> table = new CellTable<DataPoint>();

	//Constructor: Sets up the Table Tab's panels, filtering widgets and static elements
	public TableContentsView(){

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Add Panels for Filtering and Results Table
		vPanel.add(selectionPanel);
		vPanel.add(scrollPanel);

		//Add Filtering Elements
		selectionPanel.add(countryDB);
		selectionPanel.add(cityDB);
		selectionPanel.add(dateFromDB);
		selectionPanel.add(dateToDB);
		selectionPanel.add(loadTableButton);

		// Create name column.
		TextColumn<DataPoint> countryColumn = new TextColumn<DataPoint>() {
			@Override
			public String getValue(DataPoint dp) {
				return dp.getCountry();
			}
		};

		// Make the name column sortable.
		countryColumn.setSortable(true);

		// Create address column.
		TextColumn<DataPoint> cityColumn = new TextColumn<DataPoint>() {
			@Override
			public String getValue(DataPoint dp) {
				return dp.getCity();
			}
		};

		TextColumn<DataPoint> dateColumn = new TextColumn<DataPoint>() {
			@Override
			public String getValue(DataPoint dp) {
				return dp.getDate();
			}
		};

		TextColumn<DataPoint> tempColumn = new TextColumn<DataPoint>() {
			@Override
			public String getValue(DataPoint dp) {
				return String.valueOf(dp.getAverageTemperature());
			}
		};

		// Add the columns to the CellTable
		table.addColumn(countryColumn, "Country");
		table.addColumn(cityColumn, "City");
		table.addColumn(tempColumn, "Temp");
		table.addColumn(dateColumn, "Date");

		// Set the total row count. You might send an RPC request to determine the
		// total row count.
		// table.setRowCount(DATA.size(), true);

		// Set the range to display. In this case, our visible range is smaller than
		// the data set.
		table.setVisibleRange(0, 10);


		// Add a ColumnSortEvent.AsyncHandler to connect sorting to the
		// AsyncDataPRrovider.
		ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(table);
		table.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		//table.getColumnSortList().push(nameColumn);


		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		pager.setDisplay(table);


		vtablePanel.add(table);
		vtablePanel.add(pager);

		scrollPanel.add(vtablePanel);

		vtablePanel.setVisible(false);

	}

	@Override
	public HasClickHandlers getLoadTableButton() {
		return loadTableButton;
	}

	public void setData(List<String> data) {

	}

	@Override
	public void setInitData(List<String> countries, List<String> cities) {
		countryDB.clear();
		cityDB.clear();

		for (int i = 0; i < countries.size(); ++i) {
			countryDB.addItem(countries.get(i));
		}

		for (int i = 0; i < cities.size(); ++i) {
			cityDB.addItem(cities.get(i));
		}
	}

	@Override
	public String getSelectedCountry() {
		return countryDB.getSelectedItemText();
	}

	public Widget asWidget() {
		return this;
	}

	@Override
	public Widget getTableView() {
		return vtablePanel;
	}

	@Override
	public HasData getCellTableDisplay() {
		return table;
	}
}


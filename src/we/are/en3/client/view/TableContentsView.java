package we.are.en3.client.view;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import we.are.en3.client.model.DataPoint;
import we.are.en3.client.presenter.TablePresenter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TableContentsView extends Composite implements TablePresenter.Display{
	VerticalPanel vPanel = new VerticalPanel();

	FlowPanel selectionPanel = new FlowPanel();


	VerticalPanel vtablePanel = new VerticalPanel();
	ScrollPanel scrollPanel = new ScrollPanel();

	ListBox countryDB = new ListBox();
	ListBox cityDB = new ListBox();
	Button loadTableButton = new Button("Load Table");

	final CellTable<DataPoint> table = new CellTable<DataPoint>();






	public TableContentsView(){

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		vPanel.add(selectionPanel);
		vPanel.add(scrollPanel);


		//scrollPanel.setHeight("225px");
		Image img = new Image("Table.png");
		img.asWidget().setPixelSize(630,220);
		//scrollPanel.add(img);



		selectionPanel.add(countryDB);

		selectionPanel.add(cityDB);
		selectionPanel.add(loadTableButton);



		// Create a CellTable.


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

		// Add the columns.
		table.addColumn(countryColumn, "Country");
		table.addColumn(cityColumn, "City");

		// Set the total row count. You might send an RPC request to determine the
		// total row count.
		// table.setRowCount(DATA.size(), true);

		// Set the range to display. In this case, our visible range is smaller than
		// the data set.
		table.setVisibleRange(0, 1);





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


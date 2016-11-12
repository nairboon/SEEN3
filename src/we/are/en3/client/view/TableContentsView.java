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
	ListBox areaDB = new ListBox();
	ListBox countryDB = new ListBox();
	ListBox cityDB = new ListBox();
	ListBox dateFromDB = new ListBox();
	ListBox dateToDB = new ListBox();
	Button loadTableButton = new Button("Load Table");

	//Content Panel with CellTable
	VerticalPanel vTablePanel = new VerticalPanel();
	ScrollPanel scrollPanel = new ScrollPanel();
	final CellTable<DataPoint> table = new CellTable<DataPoint>();

	/**
	 * Constructor: Sets up the TableTab's panels, filtering widgets and static elements
	 */
	public TableContentsView(){

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Add panels for filtering and results table
		vPanel.add(selectionPanel);
		vPanel.add(scrollPanel);

		//Add filtering elements
		selectionPanel.add(areaDB);
		selectionPanel.add(countryDB);
		selectionPanel.add(cityDB);
		selectionPanel.add(dateFromDB);
		selectionPanel.add(dateToDB);
		selectionPanel.add(loadTableButton);

		//ToDo: what is this code doing
		TextColumn<DataPoint> countryColumn = new TextColumn<DataPoint>() {

			//this method returns the country of a given DataPoint
			@Override
			public String getValue(DataPoint dp) {return dp.getCountry();}

		};

		// Make the name column sortable.
		countryColumn.setSortable(true);

		//ToDo: what is this code doing
		TextColumn<DataPoint> cityColumn = new TextColumn<DataPoint>() {

			//this method returns the city of a given DataPoint
			@Override
			public String getValue(DataPoint dp) {
				return dp.getCity();
			}

		};

		//ToDo: what is this code doing
		TextColumn<DataPoint> tempColumn = new TextColumn<DataPoint>() {

			//this method returns the average temperature of a given DataPoint
			@Override
			public String getValue(DataPoint dp) {
				return String.valueOf(dp.getAverageTemperature());
			}

		};

		//ToDo: what is this code doing
		TextColumn<DataPoint> dateColumn = new TextColumn<DataPoint>() {

			//this method returns the date of a given DataPoint
			@Override
			public String getValue(DataPoint dp) {
				return dp.getDate();
			}

		};

		// Add the columns to the CellTable
		table.addColumn(countryColumn, "Country");
		table.addColumn(cityColumn, "City");
		table.addColumn(tempColumn, "Average Temperature");
		table.addColumn(dateColumn, "Measurement Date");

		// Set the total row count. You might send an RPC request to determine the
		// total row count.
		// table.setRowCount(DATA.size(), true);

		// Set the range to display. In this case, our visible range is smaller than
		// the data set.
		table.setVisibleRange(0, 10);

		//ToDo: what is this code doing
		// Add a ColumnSortEvent.AsyncHandler to connect sorting to the
		// AsyncDataProvider.
		ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(table);
		table.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		//table.getColumnSortList().push(nameColumn);

		//Pager Widget
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		pager.setDisplay(table);

		//Add table and pager to panels
		vTablePanel.add(table);
		vTablePanel.add(pager);
		scrollPanel.add(vTablePanel);
		vTablePanel.setVisible(false);

	}

	/**
	 * returns the button
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public HasClickHandlers getLoadTableButton() {
		return loadTableButton;
	}

	/**
	 * ToDo: What is this code doing (not implemented)
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	public void setData(List<String> data) {

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
		areaDB.clear();

		for (int i = 0; i < areas.size(); ++i) {
			countryDB.addItem(areas.get(i));
		}

	}

	/**
	 * This method is filling the TextBox's countries dropdown lists in the filter panel.
	 * It is called from class TablePresenter.
	 * 
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public void setInitCountries(List<String> countries) {
		countryDB.clear();

		for (int i = 0; i < countries.size(); ++i) {
			countryDB.addItem(countries.get(i));
		}

	}

	/**
	 * This method is filling the TextBox's cities dropdown lists in the filter panel.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public void setInitCities(List<String> cities) {

		cityDB.clear();

		for (int i = 0; i < cities.size(); ++i) {
			cityDB.addItem(cities.get(i));
		}

	}

	/**
	 * This method is filling the TextBox's dateFrom dropdown lists in the filter panel.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public void setInitDatesFrom(List<String> dateFrom) {

		dateFromDB.clear();

		for (int i = 0; i < dateFrom.size(); ++i) {
			countryDB.addItem(dateFrom.get(i));
		}

	}

	/**
	 * This method is filling the TextBox's dateTo dropdown lists in the filter panel.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public void setInitDatesTo(List<String> dateTo) {

		dateToDB.clear();

		for (int i = 0; i < dateTo.size(); ++i) {
			countryDB.addItem(dateTo.get(i));
		}

	}

	/**
	 * This method returns the selected city from ListBox cityDB.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public String getSelectedArea() {
		return areaDB.getSelectedItemText();
	}

	/**
	 * This method returns the selected country from ListBox countryDB.
	 * It is called from class TablePresenter.
	 * 
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public String getSelectedCountry() {
		return countryDB.getSelectedItemText();
	}

	/**
	 * This method returns the selected city from ListBox cityDB.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public String getSelectedCity() {
		return cityDB.getSelectedItemText();
	}


	/**
	 * This method returns the selected dateFrom from ListBox dateFromDB.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public String getSelectedDateFrom() {
		return dateFromDB.getSelectedItemText();
	}

	/**
	 * This method returns the selected dateTo from ListBox dateToDB.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public String getSelectedDateTo() {
		return dateToDB.getSelectedItemText();
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
		return this;
	}

	/**
	 * This method returns the content panel with cellTable.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public Widget getTableView() {
		return vTablePanel;
	}

	/**
	 * This method returns the cellTable.
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public HasData getCellTableDisplay() {
		return table;
	}
}


package we.are.en3.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.HasData;
import we.are.en3.client.model.DataPoint;
import we.are.en3.client.presenter.TablePresenter;

import java.util.ArrayList;
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
	Label cityAndCountry = new Label("Filter for City or Country: ");
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
		//Information for Developer
		GWT.log("TableContentsView: TableContentsView()");

		/**
		 * Add Panels and Buttons
		 */

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Add panels for filtering and results table
		vPanel.add(selectionPanel);
		vPanel.add(scrollPanel);

		//Add filtering elements
		selectionPanel.add(cityAndCountry);
		selectionPanel.add(areaDB);
		//selectionPanel.add(countryDB);
		//selectionPanel.add(cityDB);
		selectionPanel.add(dateFromDB);
		selectionPanel.add(dateToDB);
		selectionPanel.add(loadTableButton);

		/**
		 * Table: Define Columns and Methods used to populate the table
		 */

		//ToDo: what is this code doing
		TextColumn<DataPoint> countryColumn = new TextColumn<DataPoint>() {

			//this method returns the country of a given DataPoint
			@Override
			public String getValue(DataPoint dp) {return dp.getCountry();}

		};

		// Make the name column sortable.
		//countryColumn.setSortable(true);

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


		// Set the total row count. This is done from within fetch table.
		// table.setRowCount(DATA.size(), true);

		// Set the range to display. In this case, our visible range is smaller than
		// the data set. This is a functionality of CellTable<DataPoint> Widget.
		table.setVisibleRange(0, 24);

		//ToDo: what is this code doing
		// Add a ColumnSortEvent.AsyncHandler to connect sorting to the
		// AsyncDataProvider.
		ColumnSortEvent.AsyncHandler columnSortHandler = new ColumnSortEvent.AsyncHandler(table);
		table.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		//table.getColumnSortList().push(nameColumn);

		/**
		 * Table: add Pager and Table to ScrollPanel
		 */

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
		//Information for Developer
		GWT.log("TableContentsView: getLoadTableButton()");

		return loadTableButton;

	}

	/**
	 * returns the Area TextBox
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public HasChangeHandlers getAreaListBox() {
		//Information for Developer
		GWT.log("TableContentsView: getAreaTextBox()");

		return areaDB;
	}

	/**
	 * returns the dateFrom TextBox
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public HasChangeHandlers getDateFromListBox() {
		//Information for Developer
		GWT.log("TableContentsView: getDateFromTextBox()");

		return  dateFromDB;
	}

	/**
	 * returns the dateTo TextBox
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@Override
	public HasChangeHandlers getDateToListBox() {
		//Information for Developer
		GWT.log("TableContentsView: getDateToTextBox()");

		return  dateToDB;
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
		GWT.log("TableContentsView: setInitAreas()");

		areaDB.clear();

		for (int i = 0; i < areas.size(); ++i) {
			areaDB.addItem(areas.get(i));
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
		//Information for Developer
		GWT.log("TableContentsView: setInitDatesFrom()");

		dateFromDB.clear();

		for (int i = 0; i < dateFrom.size(); ++i) {
			dateFromDB.addItem(dateFrom.get(i));
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
		//Information for Developer
		GWT.log("TableContentsView: setInitDatesTo()");

		dateToDB.clear();

		for (int i = 0; i < dateTo.size(); ++i) {
			dateToDB.addItem(dateTo.get(i));
		}

		dateToDB.setSelectedIndex(dateTo.size()-1);

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
		//Information for Developer
		GWT.log("TableContentsView: getSelectedArea()");

		return areaDB.getSelectedItemText();
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
		//Information for Developer
		GWT.log("TableContentsView: getSelectedDateFrom()");

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
		//Information for Developer
		GWT.log("TableContentsView: getSelectedDateTo()");

		return dateToDB.getSelectedItemText();
	}

	/**
	 * This method sets dateFrom in ListBox dateFromDB
	 * equal to dateTo in ListBox dateToDB
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@SuppressWarnings("Duplicates")
	@Override
	public void setSelectedDateFrom(String dateTo) {
		//Information for Developer
		GWT.log("TableContentsView: setSelectedDateTo()");

		//Strange implementation because GWT doesn't offer indexOf()
		//method for ListBoxes
		ArrayList<String> listItems = new ArrayList<String>();
		Integer index=0;

		//Fill ArrayList to get index of item
		for (int i=0; i<dateFromDB.getItemCount(); i++) {
			listItems.add(dateFromDB.getItemText(i));
		}

		//get index of item
		index = listItems.indexOf(dateTo);

		//set index as selected
		dateFromDB.setSelectedIndex(index);
	}

	/**
	 * This method sets dateTo in ListBox dateToDB
	 * equal to dateFrom in ListBox dateFromDB
	 * It is called from class TablePresenter.
	 *
	 * @pre
	 * @post
	 * @param
	 * @return
	 */
	@SuppressWarnings("Duplicates")
	@Override
	public void setSelectedDateTo(String dateFrom) {
		//Information for Developer
		GWT.log("TableContentsView: setSelectedDateTo()");

		//Strange implementation because GWT doesn't offer indexOf()
		//method for ListBoxes
		ArrayList<String> listItems = new ArrayList<String>();
		Integer index=0;

		//Fill ArrayList to get index of item
		for (int i=0; i<dateToDB.getItemCount(); i++) {
			listItems.add(dateToDB.getItemText(i));
		}

		//get index of item
		index = listItems.indexOf(dateFrom);

		//set index as selected
		dateToDB.setSelectedIndex(index);
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
		//Information for Developer
		GWT.log("TableContentsView: getTableView()");

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
		//Information for Developer
		GWT.log("TableContentsView: getCellTableDisplay()");

		return table;
	}
}


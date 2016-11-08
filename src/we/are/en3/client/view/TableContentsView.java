package we.are.en3.client.view;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import we.are.en3.client.presenter.TablePresenter;

import java.util.Arrays;
import java.util.List;


public class TableContentsView extends Composite implements TablePresenter.Display{
	
	ScrollPanel scrollPanel = new ScrollPanel();
	ListBox lb = new ListBox();

	private static final List<String> DAYS = Arrays.asList("Sunday", "Monday",
			"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

	public TableContentsView(){
		/* lb.addItem("foo");
		lb.addItem("bar");
		lb.addItem("baz");
		lb.addItem("toto");
		lb.addItem("tintin"); */

		//Initialize parent widget to be wrapped
		initWidget(this.scrollPanel);
		
		scrollPanel.setHeight("225px");
		Image img = new Image("Table.png");
		img.asWidget().setPixelSize(630,220);
		//scrollPanel.add(img);

		scrollPanel.add(lb);


/*
		TextCell textCell = new TextCell();

		// Create a CellList that uses the cell.
		final CellList<String> cellList = new CellList<String>(textCell);

		// Set the total row count. You might send an RPC request to determine the
		// total row count.
		cellList.setRowCount(DAYS.size(), true);

		// Set the range to display. In this case, our visible range is smaller than
		// the data set.
		cellList.setVisibleRange(1, 3);

		// Create a data provider.
		AsyncDataProvider<String> dataProvider = new AsyncDataProvider<String>() {
			@Override
			protected void onRangeChanged(HasData<String> display) {
				final Range range = display.getVisibleRange();

				// This timer is here to illustrate the asynchronous nature of this data
				// provider. In practice, you would use an asynchronous RPC call to
				// request data in the specified range.
				new Timer() {
					@Override
					public void run() {
						int start = range.getStart();
						int end = start + range.getLength();
						List<String> dataInRange = DAYS.subList(start, end);

						// Push the data back into the list.
						cellList.setRowData(start, dataInRange);
					}
				}.schedule(2000);
			}
		};

		// Connect the list to the data provider.
		dataProvider.addDataDisplay(cellList);

		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		pager.setDisplay(cellList);

		// Add the pager and list to the page.
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(pager);
		vPanel.add(cellList);

		scrollPanel.add(vPanel);
		*/
	}

	public void setData(List<String> data) {
		lb.clear();

		for (int i = 0; i < data.size(); ++i) {
			lb.addItem(data.get(i));
		}
	}

	public Widget asWidget() {
		return this;
	}
}


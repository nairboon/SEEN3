package we.are.en3.client.view;

import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
//
//import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
//import com.google.gwt.visualization.client.DataTable;
//import com.google.gwt.visualization.client.VisualizationUtils;
//import com.google.gwt.visualization.client.visualizations.GeoMap;

public class MapContentsView extends Composite{
	
	ScrollPanel scrollPanel = new ScrollPanel();

	public MapContentsView(){
		
		//Initialize parent widget to be wrapped
		initWidget(this.scrollPanel);
		
		scrollPanel.setHeight("225px");
		Image img = new Image("TempMap.jpg");
		img.asWidget().setPixelSize(630,220);
		scrollPanel.add(img);
				
	}

}

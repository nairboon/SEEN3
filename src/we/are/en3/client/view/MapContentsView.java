package we.are.en3.client.view;

import com.google.gwt.user.client.ui.*;
import com.kiouri.sliderbar.client.view.SliderBarHorizontal;
//
//import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
//import com.google.gwt.visualization.client.DataTable;
//import com.google.gwt.visualization.client.VisualizationUtils;
//import com.google.gwt.visualization.client.visualizations.GeoMap;

public class MapContentsView extends Composite{

	//Main Panel
	VerticalPanel vPanel = new VerticalPanel();

	//Filter Panel
	FlowPanel selectionPanel = new FlowPanel();
	//SliderBarHorizontal slider = new SliderBarHorizontal();

	//Content Panel
	VerticalPanel vchartPanel = new VerticalPanel();
	ScrollPanel scrollPanel = new ScrollPanel();

	public MapContentsView(){

		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);

		//Top: Titel
		HTML text = new HTML("<div style='color:blue;text-align:justify;padding:10px;'>" +
				"This Site shows a world Map with Temparature for your prefered date. </div>" );

		vPanel.add(text);

		//Center: Filter
		vPanel.add(selectionPanel);
		//selectionPanel.add(slider);

		//Bottom: Content
		vPanel.add(scrollPanel);
		scrollPanel.setHeight("225px");
		Image img = new Image("TempMap.jpg");
		img.asWidget().setPixelSize(630,220);
		scrollPanel.add(img);

	}

}

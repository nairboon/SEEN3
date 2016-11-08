package we.are.en3.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Image;
//
//import com.google.gwt.widgetideas.client.SliderBar;


public class TimelineFilterView extends Composite{
	
	ScrollPanel scrollPanel = new ScrollPanel();

	public TimelineFilterView(){
		
		//Initialize parent widget to be wrapped
		initWidget(this.scrollPanel);
		
		scrollPanel.setHeight("100px");
		Image img = new Image("timeline.png");
		img.asWidget().setPixelSize(650,95);
		scrollPanel.add(img);
		
		//
//		slider = new SliderBar(1889.0,2016.0);
//		slider.setStepSize(1.0);
//		slider.setCurrentValue(2000.0);
//		slider.setNumTicks(28);
//		slider.setNumLabels(14);		
//		dataTable = DataTable.create();
//		dataTable.addColumn(ColumnType.STRING, "Country");
//		dataTable.addColumn(ColumnType.NUMBER, "Value");
		
		
		
	
	}

}
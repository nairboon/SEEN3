package we.are.en3.client.view;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DataSourceView extends Composite{

	VerticalPanel vPanel = new VerticalPanel();

	public DataSourceView(){
		
		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);
		
		vPanel.setHeight("100px");		
		Image img = new Image("climateChange.jpg");
		img.asWidget().setPixelSize(190,60);
		Label label = new Label();
		label.setText(".   Data source: xyz xyz xzy xzy xyz xyz xzy xzy xyz xyz xzy xzy");
		label.asWidget().setPixelSize(190,35);
		vPanel.add(img);
		vPanel.add(label);
		
	}
}
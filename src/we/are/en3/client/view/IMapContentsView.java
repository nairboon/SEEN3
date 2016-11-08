package we.are.en3.client.view;

import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;

public class IMapContentsView extends Composite{

	ScrollPanel scrollPanel = new ScrollPanel();

	public IMapContentsView(){
		
		//Initialize parent widget to be wrapped
		initWidget(this.scrollPanel);
		
		scrollPanel.setHeight("225px");
		Image img = new Image("IMap.jpg");
		img.asWidget().setPixelSize(630,220);
		scrollPanel.add(img);
	}
}

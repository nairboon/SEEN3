package we.are.en3.client.view;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;


public class MainFilterView extends Composite{
	
	VerticalPanel vPanel = new VerticalPanel();
	
	//Filter Widgets
	CheckBox checkBox = new CheckBox();
	FlowPanel fPanel = new FlowPanel();
	DisclosurePanel dPanel = new DisclosurePanel();
	List<CheckBox> checkBoxes = new ArrayList<CheckBox>();
	TextBox textBox1 = new TextBox();
	TextBox textBox2 = new TextBox();
	Label label1 = new Label();
	Label label2= new Label();

	public MainFilterView(){
		
		//Initialize parent widget to be wrapped
		initWidget(this.vPanel);
		
		vPanel.setHeight("265px");
		Image img = new Image("TempMap.jpg");
		img.asWidget().setPixelSize(190,30);
		vPanel.add(img);
		
		//
		label1.setText(". Enter a country:");
		vPanel.add(label1);
		vPanel.add(textBox1);
		
		label2.setText(". Enter a city:");
		vPanel.add(label2);		
		vPanel.add(textBox2);
		
		vPanel.add(checkBox);
		
		
	}

}
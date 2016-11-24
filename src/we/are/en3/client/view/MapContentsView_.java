//package we.are.en3.client.view;
//
//import com.google.gwt.user.client.ui.Composite;
//import com.google.gwt.user.client.ui.Widget;
//import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
//import com.google.gwt.visualization.client.DataTable;
//import com.google.gwt.visualization.client.visualizations.GeoMap;
//import com.google.gwt.visualization.client.visualizations.GeoMap.Options;
//
///**
// * Demo for GeoMap visualization.
// */
//public class MapContentsView_  extends Composite {
//    public Widget getWidget() {
//        final Options options = Options.create();
//        options.setDataMode(GeoMap.DataMode.REGIONS);
//        options.setHeight(300);
//        options.setWidth(450);
//        options.setShowLegend(false);
//        options.setColors(0xFF8747, 0xFFB581, 0xc06000);
//        options.setRegion("world");
//
//        final DataTable dataTable = DataTable.create();
//        dataTable.addRows(7);
//        dataTable.addColumn(ColumnType.STRING, "ADDRESS", "address");
//        dataTable.setValue(0, 0, "Israel");
//        dataTable.setValue(1, 0, "United States");
//        dataTable.setValue(2, 0, "Germany");
//        dataTable.setValue(3, 0, "Brazil");
//        dataTable.setValue(4, 0, "Canada");
//        dataTable.setValue(5, 0, "France");
//        dataTable.setValue(6, 0, "RU");
//
//        final GeoMap geo = new GeoMap(dataTable, options);
//        return geo;
//    }
//}

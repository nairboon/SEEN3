package we.are.en3.client.util;

import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;

/**
 * Quick fix to add the displaymode 'text'
 * @author Team SE_EN3, University of Zurich
 *
 */
public class OurGeoChartOptions extends GeoChartOptions{

    public static OurGeoChartOptions create() {
        return createObject().cast();
    }

    protected OurGeoChartOptions() {
    }

    public final native void setDisplayMode(String displayMode) /*-{
        this.displayMode = displayMode;
    }-*/;
}
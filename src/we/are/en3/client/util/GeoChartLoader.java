package we.are.en3.client.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.Window;

/**
 * Rewrite of the gwt-charts js api loader to use the new api (45+) and apikeys
 * @author Team SE_EN3, University of Zurich
 *
 */
public class GeoChartLoader {

    public String apiKey;
    private Runnable cb;

    public GeoChartLoader(String apiKey) {
        this.apiKey = apiKey;
    }

    public native void loadGeocharts() /*-{
        $wnd.google.charts.load('current', {packages: ['corechart'], mapsApiKey: this.@we.are.en3.client.util.GeoChartLoader::apiKey});
        var self = this;
        var callbackFn = $entry(function() {
            self.@we.are.en3.client.util.GeoChartLoader::onLoadCallback()();
        });
        $wnd.google.charts.setOnLoadCallback(callbackFn);

    }-*/;

    public void load(Runnable callback) {
        this.cb = callback;
        loadGeocharts();
    }

    protected void onLoadCallback() {
        GWT.log("onLoadedCallback");
        this.cb.run();
    }
}

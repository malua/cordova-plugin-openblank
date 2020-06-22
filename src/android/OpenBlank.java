package org.apache.cordova.openblank;

import org.apache.cordova.*;
import android.util.Log;


public class OpenBlank extends CordovaPlugin {
    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }
	
    public Boolean shouldAllowRequest(String url) {
        return true;
    }
	
    public Boolean shouldAllowBridgeAccess(String url) {
        return true;
    }
	
    @Override
    public boolean onOverrideUrlLoading(String url) {
    	Log.d("OpenBlank", "onOverrideUrlLoading called with URL " + url);
   		if(url.indexOf("idagio") > -1 || url.indexOf(".com") > -1 || url.indexOf(".net") > -1 || url.indexOf(".org") > -1) {
			try {
			    webView.getView().post(new Runnable() {
				@Override
				public void run() {
					webView.loadUrl("javascript:cordova.InAppBrowser.open('"+url+"', '_blank');");
				}
			    });
			    return true;
			} catch (android.content.ActivityNotFoundException e) {
			    Log.d("OpenBlank", "OpenBlank: Error loading url "+url+":"+ e.toString());
			    return false;
			}
   		}
   		return false;
    }
}

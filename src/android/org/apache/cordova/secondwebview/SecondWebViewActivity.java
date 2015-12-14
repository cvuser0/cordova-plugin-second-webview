package org.apache.cordova.secondwebview;

import android.os.Bundle;
import android.webkit.WebView;
import nl.epassonline.secondwebview.MainActivity;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;

public class SecondWebViewActivity extends CordovaActivity {
    public static SecondWebViewActivity child = null;
    public static CordovaWebView appview = null;
    public static WebView webview = null;
    public static MainActivity parent = null;
    public static SecondWebViewInterface JSinterface = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SecondWebViewActivity.child != null) {
            this.finish();
            return;
        }
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
        SecondWebViewActivity.JSinterface = new SecondWebViewInterface("child");
        ((WebView) appView.getEngine().getView()).addJavascriptInterface(SecondWebViewActivity.JSinterface, "interface");
        loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
        SecondWebViewActivity.child = this;
        SecondWebViewActivity.appview = appView;
        SecondWebViewActivity.webview = (WebView) appView.getEngine().getView();
        SecondWebViewPlugin.child = this;
        MainActivity.child = this;
    }
}

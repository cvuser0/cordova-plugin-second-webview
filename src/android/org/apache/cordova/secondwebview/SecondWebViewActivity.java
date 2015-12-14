package org.apache.cordova.secondwebview;

import android.os.Bundle;
import android.webkit.WebView;
import org.example.app.MainActivity;
import org.apache.cordova.CordovaActivity;

public class SecondWebViewActivity extends CordovaActivity {
    public static SecondWebViewActivity self = null;
    public static SecondWebViewInterface Interface = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
        //noinspection ConstantConditions
        loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
        SecondWebViewActivity.self = this;
        MainActivity.Interface._other = SecondWebViewActivity.self;
        SecondWebViewInterface._child = SecondWebViewActivity.self;
        SecondWebViewActivity.Interface = new SecondWebViewInterface("child", SecondWebViewActivity.self, MainActivity.self);
        ((WebView) appView.getEngine().getView()).addJavascriptInterface(SecondWebViewActivity.Interface, "Interface");
    }

    public void load(String _url) {
        final String url = _url;
        appView.getEngine().getView().post(new Runnable() {
            @Override
            public void run() {
                appView.loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
            }
        });
    }
}

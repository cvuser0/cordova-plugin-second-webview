package org.apache.cordova.secondwebview;

import android.content.Context;
import android.os.Bundle;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;
import org.json.JSONObject;

public class SecondWebViewActivity extends CordovaActivity {
    public CordovaWebView webview;
    public static CordovaActivity activity;
    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        SecondWebViewActivity.mContext = mContext;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
        if (!(url != null && !url.equals(""))) {
            loadUrl("javascript:alert('No URL given!');");
            setActivity(this);
            webview = appView;
            return;
        }
        loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
        setActivity(this);
        setContext(this);
        webview = appView;
    }

    public JSONObject executeJS(String JS) {
        webview.loadUrlIntoView(JS, false);
        return new JSONObject();
    }

    public static void setActivity(CordovaActivity c) {
        activity = c;
    }

    public static CordovaActivity getActivity() {
        return activity;
    }
}

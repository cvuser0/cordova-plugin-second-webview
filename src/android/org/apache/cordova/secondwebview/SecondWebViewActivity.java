package org.apache.cordova.secondwebview;

import android.os.Bundle;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaWebView;
import org.json.JSONObject;

public class SecondWebViewActivity extends CordovaActivity {
    public CordovaWebView webview;
    public static CordovaActivity activity;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        Bundle b = getIntent ( ).getExtras ( );
        String url = b.getString ( "url" );
        loadUrl ( ( url.matches ( "^(.*://|javascript:)[\\s\\S]*$" ) ? "" : "file:///android_asset/www/" ) + url );
        webview = appView;
        setActivity ( this );
    }

    public JSONObject executeJS ( String JS ) {
        webview.loadUrlIntoView ( JS, false );
        return new JSONObject ( );
    }

    public static void setActivity ( CordovaActivity c ) {
        activity = c;
    }

    public static CordovaActivity getActivity ( ) {
        return activity;
    }
}

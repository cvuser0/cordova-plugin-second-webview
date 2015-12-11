package org.apache.cordova.secondwebview;

import android.os.Bundle;
import nl.epassonline.dev.MainActivity;
import org.apache.cordova.CordovaActivity;

public class SecondWebViewActivity extends CordovaActivity {
    public static MainActivity parent = null;
    public static SecondWebViewActivity child = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SecondWebViewActivity.child != null) {
            this.finish();
            return;
        }
        Bundle b = getIntent().getExtras();
        String url = b.getString("url");
        loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
        SecondWebViewActivity.child = this;
        MainActivity.child = this;
        SecondWebViewActivity.parent = MainActivity.parent;
        SecondWebViewActivity.parent.execJS("console.log('hello');");
    }
}

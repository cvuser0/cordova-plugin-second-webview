package org.apache.cordova.secondwebview;

import android.webkit.JavascriptInterface;

public class SecondWebViewInterface {
    public SecondWebViewInterface() {

    }

    @JavascriptInterface
    public String ping(String input) {
        return input;
    }
}

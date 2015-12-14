package org.apache.cordova.secondwebview;

import android.webkit.JavascriptInterface;

public class SecondWebViewInterface {
    @JavascriptInterface
    public String ping(String input) {
        return input;
    }
}

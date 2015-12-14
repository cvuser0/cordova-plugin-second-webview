package org.apache.cordova.secondwebview;

import android.webkit.JavascriptInterface;
import nl.epassonline.secondwebview.MainActivity;

public class SecondWebViewInterface {
    public SecondWebViewInterface(String role) {
        this._role = role;
    }

    @JavascriptInterface
    public String ping(String input) {
        return input;
    }

    @JavascriptInterface
    public String getRole() {
        return this._role;
    }

    public String _role = "";

    @JavascriptInterface
    public boolean childExists() {
        return (SecondWebViewActivity.child == null);
    }

    @JavascriptInterface
    public void loadURLParent(String url) {
        MainActivity.parent.loadUrl(url);
    }

    @JavascriptInterface
    public void loadURLChild(String url) {
        final String _url = url;
        if (childExists()) {
            SecondWebViewActivity.webview.post(new Runnable() {
                @Override
                public void run() {
                    SecondWebViewActivity.parent.loadUrl(_url);
                }
            });
        }
    }
}

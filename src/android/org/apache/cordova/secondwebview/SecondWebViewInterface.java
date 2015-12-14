package org.apache.cordova.secondwebview;

import android.webkit.JavascriptInterface;
import org.apache.cordova.CordovaActivity;

public class SecondWebViewInterface {
    public CordovaActivity _self = null;
    public CordovaActivity _other = null;
    public static CordovaActivity _parent = null;
    public static CordovaActivity _child = null;

    public SecondWebViewInterface(String role, CordovaActivity self, CordovaActivity other) {
        this._self = self;
        this._other = other;
        SecondWebViewInterface._parent = role.equals("parent") ? self : other;
        SecondWebViewInterface._child = role.equals("child") ? self : other;
    }

    @JavascriptInterface
    public boolean childExists() {
        return SecondWebViewInterface._child != null;
    }

    @JavascriptInterface
    public boolean isParent() {
        return _self == SecondWebViewInterface._parent;
    }

    @JavascriptInterface
    public boolean isChild() {
        return _self == SecondWebViewInterface._child;
    }

    @JavascriptInterface
    public void exeuteSelf(String expression) {
        _self.loadUrl("javascript:" + expression);
    }

    @JavascriptInterface
    public void exeuteOther(String expression) {
        _other.loadUrl("javascript:" + expression);
    }

    @JavascriptInterface
    public void loadSelf(String url) {
        _self.loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
    }

    @JavascriptInterface
    public void loadOther(String url) {
        _other.loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
    }
}

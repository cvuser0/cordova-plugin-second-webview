package org.apache.cordova.secondwebview;

import android.webkit.JavascriptInterface;
import org.example.app.MainActivity;
import org.apache.cordova.CordovaActivity;

public class SecondWebViewInterface {
    public CordovaActivity _self = null;
    public CordovaActivity _other = null;
    public static CordovaActivity _parent = null;
    public static MainActivity parent = null;
    public static CordovaActivity _child = null;
    public static SecondWebViewActivity child = null;

    public SecondWebViewInterface(String role, CordovaActivity self, CordovaActivity other) {
        this._self = self;
        this._other = other;
        SecondWebViewInterface._parent = role.equals("parent") ? self : other;
        SecondWebViewInterface._child = role.equals("child") ? self : other;
        SecondWebViewInterface.parent = MainActivity.self;
        SecondWebViewInterface.child = SecondWebViewActivity.self;
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
    public void executeSelf(String expression) {
        loadSelf("javascript:" + expression);
    }

    @JavascriptInterface
    public void executeOther(String expression) {
        loadOther("javascript:" + expression);
    }

    @JavascriptInterface
    public void loadSelf(String url) {
        if (_self == SecondWebViewInterface.parent) SecondWebViewInterface.parent.load(url);
        if (_other == SecondWebViewInterface.parent) SecondWebViewInterface.child.load(url);
    }

    @JavascriptInterface
    public void loadOther(String url) {
        if (_self == SecondWebViewInterface.parent) SecondWebViewInterface.child.load(url);
        if (_other == SecondWebViewInterface.parent) SecondWebViewInterface.parent.load(url);
    }
}

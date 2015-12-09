package org.apache.cordova.secondwebview;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;

import android.widget.LinearLayout;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.PluginEntry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.cordova.LOG;

import org.apache.cordova.engine.*;


public class SecondWebViewPlugin extends CordovaPlugin {

    private static final String LOG_TAG = "SecondWebview";
    private CallbackContext closeCallback;
    private customCallback ctpCallback = null; //Child To Parent
    private customCallback ptcCallback = null; //Parent To Child

    public SecondWebViewPlugin() {

    }

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action          The action to execute.
     * @param args            JSONArry of arguments for the plugin.
     * @param callbackContext The callback id used when calling back into JavaScript.
     * @return True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("show") && args.length() > 0) {
            LOG.d(LOG_TAG, "Show Second Webview");
            final String url = args.getString(0);
            if (!"".equals(url)) {
                showWebView(url);
                JSONObject r = new JSONObject();
                r.put("responseCode", "ok");
                callbackContext.success(r);
            } else {
                callbackContext.error("Empty Parameter url");
            }

        } // end show
        else if (action.equals("hide")) {
            LOG.d(LOG_TAG, "Hide Second Webview");
            hideWebView();
            JSONObject r = new JSONObject();
            r.put("responseCode", "ok");
            callbackContext.success(r);
        } // end hide
        else if (action.equals("injectJSCode")) {
            //TODO inject JS Code
            JSONObject r = new JSONObject();
            r.put("responseCode", "ok");
            callbackContext.success(r);
        } // end injectJSCode
        else if (action.equals("injectJSFile")) {
            //TODO inject JS File
            JSONObject r = new JSONObject();
            r.put("responseCode", "ok");
            callbackContext.success(r);
        } //end injectJSFile
        else if (action.equals("injectCSSFile")) {
            //TODO inject CSS File
            JSONObject r = new JSONObject();
            r.put("responseCode", "ok");
            callbackContext.success(r);
        } // end injectCSSFile
        else if (action.equals("registerReceiver")) {
            //TODO register JS Function Callback
            JSONObject r = new JSONObject();
            r.put("responseCode", "ok");

            final ctpCallback=new customCallback() {
                public void call() {
                    JSONObject r = new JSONObject();
                    r.put("responseCode", "ok");
                    callbackContext.success(r); //use first callback as callback for webview
                }
            };
            callbackContext.error(r); //use second callback as success and fail
        } // end registerReceiver
        else if (action.equals("sendData")) {
            //TODO inject JS Function Call
            JSONObject r = new JSONObject();
            r.put("responseCode", "ok");
            final ptcCallback=new customCallback() {
                public void call() {
                    JSONObject r = new JSONObject();
                    r.put("responseCode", "ok");
                    callbackContext.success(r); //use first callback as return from webview
                }
            };
            callbackContext.error(r); //use second callback as success and fail
        } // end sendData
        else if (action.equals("getJSONArray")) {
            JSONObject r = new JSONObject();
            r.put("responseCode", "ok");
            r.put("args", args);
            callbackContext.success(r);
        } // end getJSONArray
        else {
            return false;
        }

        return true;
    }

    private void loadURL(String url) {
        //TODO get second webview and call loadUrl on it
    }

    private void showWebView(final String url) {
        LOG.d(LOG_TAG, "Url: " + url);
        Intent i = new Intent(this.cordova.getActivity(), SecondWebViewActivity.class);
        i.putExtra("url", url);
//        i.putExtra("callback", );
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.cordova.getActivity().getApplicationContext().startActivity(i);
    }

    private void hideWebView() {
        LOG.d(LOG_TAG, "hideWebView");
        this.cordova.getActivity().finish();
    }

//    public void callParent(Object data, CallBack callback){
//        //TODO add code to call parent callback
//
//    }

    public interface customCallback {
        public void call();
    }
}

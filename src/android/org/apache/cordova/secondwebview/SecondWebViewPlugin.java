package org.apache.cordova.secondwebview;

import android.content.Intent;
import nl.epassonline.secondwebview.MainActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondWebViewPlugin extends CordovaPlugin {

    public SecondWebViewPlugin() {

    }

    public static MainActivity parent;
    public static SecondWebViewActivity child;

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
        switch (action) {
            case "open":
                open(args, callbackContext);
                break;
            case "addInterface":
                MainActivity.webview.post(new Runnable() {
                    public void run() {
                        MainActivity.webview.addJavascriptInterface(new SecondWebViewInterface(), "Android");
                    }
                });
                break;
//            case "closeChild":
////                closeChild(args, callbackContext);
//                break;
//            case "closeParent":
////                closeParent(args, callbackContext);
//                break;
//            case "sendToParent":
////                closeParent(args, callbackContext);
//                break;
//            case "sendToChild":
////                closeParent(args, callbackContext);
//                break;
            default:
                return false;
        }
        return true;
    }

    private void open(JSONArray args, CallbackContext callback) throws JSONException {
        JSONObject response = new JSONObject();
        response.put("url", "url_error");
        String url = args.getJSONObject(0).getString("url");
        Intent i = new Intent(this.cordova.getActivity(), SecondWebViewActivity.class);
        i.putExtra("url", url);
        response.putOpt("url", url);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.cordova.getActivity().getApplicationContext().startActivity(i);
        response.put("status", "pass");
        callback.success(response);
    }
}

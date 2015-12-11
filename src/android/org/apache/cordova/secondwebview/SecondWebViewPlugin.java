package org.apache.cordova.secondwebview;

import android.content.Intent;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SecondWebViewPlugin extends CordovaPlugin {

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
        switch (action) {
            case "open":
                open(args, callbackContext);
                break;
            case "closeChild":
                closeChild(args, callbackContext);
                break;
            case "closeParent":
                closeParent(args, callbackContext);
                break;
            case "registerPingReceiver":
                registerPingReceiver(args, callbackContext);
                break;
            case "ping":
                ping(args, callbackContext);
                break;
            case "execJS":
                execJS(args, callbackContext);
                break;
            default:
                callbackContext.error("Action not found");
                return false;
        }
        return true;
    }

    private void open(JSONArray args, CallbackContext callback) throws JSONException {
        JSONObject response = new JSONObject();
        response.put("url", "url_error");
        try {
            String url = args.length() > 0 && !args.getString(0).equals("") ? args.getString(0) : "javascript:alert('Empty URL');";
            if (!url.equals(args.getString(0))) callback.error("Empty URL");
            Intent i = new Intent(this.cordova.getActivity(), SecondWebViewActivity.class);
            i.putExtra("url", url);
            response.putOpt("url", url);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            this.cordova.getActivity().getApplicationContext().startActivity(i);
            response.put("status", "pass");
            callback.success(response);
        } catch (Exception e) {
            response.put("exception", e.getMessage());
            response.put("status", "fail");
            callback.error(response);
        }
    }

    private void closeChild(JSONArray args, CallbackContext callback) throws JSONException {
        SecondWebViewActivity.getActivity().finish();
        JSONObject response = new JSONObject();
        response.put("status", "pass");
        callback.success(response);
    }

    private void closeParent(JSONArray args, CallbackContext callback) throws JSONException {
        this.cordova.getActivity().finish();
        JSONObject response = new JSONObject();
        response.put("status", "pass");
        callback.success(response);
    }

    public void registerPingReceiver(JSONArray args, CallbackContext callback) {

    }

    public void ping(JSONArray args, CallbackContext callback) {

    }

    public void execJS(JSONArray args, CallbackContext callback) {
//        SecondWebViewActivity.getActivity().
    }
}

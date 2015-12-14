package org.apache.cordova.secondwebview;

import android.content.Intent;
import org.example.app.MainActivity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

public class SecondWebViewPlugin extends CordovaPlugin {

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
                open(args.getString(0));
                break;
            case "closeParent":
                closeParent();
                break;
            case "closeChild":
                closeChild();
                break;
            default:
                return false;
        }
        return true;
    }

    private void open(String url) throws JSONException {
        if (SecondWebViewActivity.self != null) {
            cordova.getActivity().finish();
            return;
        }
        Intent i = new Intent(this.cordova.getActivity(), SecondWebViewActivity.class);
        i.putExtra("url", url);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.cordova.getActivity().getApplicationContext().startActivity(i);
    }

    public void closeParent() {
        MainActivity.self.finish();
    }

    public void closeChild() {
        SecondWebViewActivity.self.finish();
    }
}

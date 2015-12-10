# DO NOT CLONE THIS
Unless you plan to fix it

# Second Webview Plugin
This cordova plugin enables you to open a second webview in your app.
This webview is totally independent from the main webview but **can** (WIP) communicate with it, and allows you to access plugins and other Cordova resources.

It's possible to modify this plugin to allow multiple webviews.

## How to use it

First, add the plugin to your cordova application

    cordova plugin add https://github.com/cvuser0/cordova-plugin-second-webview.git

To open a new webview, just call in your app's js:

    webview.Show(URL);

Where `URL` is the path to the page to be opened. In Android, the plugin automatically adds the prefix `file:///android_asset/www/`

Then, to close the second webview and return to the main view, call in your second webview (the opened webview, not the main webview):

    webview.Close();

This will close and destroy the second webview.

## Incoming changes

* Communication towards the child webview

* Possibility to communicate from the child to the parent
 
* Moar code!

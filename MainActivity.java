/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package org.example.app;

import android.os.Bundle;
import android.webkit.WebView;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.secondwebview.SecondWebViewActivity;
import org.apache.cordova.secondwebview.SecondWebViewInterface;

public class MainActivity extends CordovaActivity {
    public static MainActivity self = null;
    public static SecondWebViewInterface Interface = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadUrl(launchUrl);
        MainActivity.self = this;
        MainActivity.Interface = new SecondWebViewInterface("parent", MainActivity.self, SecondWebViewActivity.self);
        ((WebView) appView.getEngine().getView()).addJavascriptInterface(MainActivity.Interface, "Interface");
    }

    public void load(String _url) {
        final String url = _url;
        appView.getEngine().getView().post(new Runnable() {
            @Override
            public void run() {
                appView.loadUrl((url.matches("^(.*://|javascript:)[\\s\\S]*$") ? "" : "file:///android_asset/www/") + url);
            }
        });
    }
}

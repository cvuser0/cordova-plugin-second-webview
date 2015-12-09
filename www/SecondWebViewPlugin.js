/*global elu, module */
module.exports = (function () {
    'use strict';
    return {
        Show: function (url, successCallback, errorCallback) {
            cordova.exec(successCallback, errorCallback, 'SecondWebViewPlugin', 'show', [url]);
        },
        Hide: function (successCallback, errorCallback) {
            cordova.exec(successCallback, errorCallback, 'SecondWebViewPlugin', 'hide', []);
        },
        SubscribeCallback: function (successCallback, errorCallback) {
            cordova.exec(successCallback, errorCallback, 'SecondWebViewPlugin', 'subscribeCallback', []);
        },
        Close: function (successCallback, errorCallback) {
            cordova.exec(successCallback, errorCallback, 'SecondWebViewPlugin', 'hide', []);
        },
        InjectJSCode: function (code, callback) {
            cordova.exec(callback, callback, 'SecondWebViewPlugin', 'injectJSCode', [code, !!callback]);
        },
        InjectJSFile: function (file, callback) {
            cordova.exec(callback, callback, 'SecondWebViewPlugin', 'injectJSFile', [file, !!callback]);
        },
        InjectCSSFile: function (file, callback) {
            cordova.exec(callback, callback, 'SecondWebViewPlugin', 'injectCSSFile', [file, !!callback]);
        },
        RegisterReceiver: function (callback, receiver) {
            cordova.exec(callback, callback, 'SecondWebViewPlugin', 'registerReceiver', [receiver]);
        },
        SendData: function (callback, data) {
            cordova.exec(callback, callback, 'SecondWebViewPlugin', 'sendData', [data]);
        },
        GetJSONArray: function (callback, args) {
            cordova.exec(callback, callback, 'SecondWebViewPlugin', 'getJSONArray', [args]);
        }
    };
})();

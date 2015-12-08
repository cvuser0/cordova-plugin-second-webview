/*global elu, module */
module.exports = (function()
{
    'use strict';
    return {
        Show             : function( url, successCallback, errorCallback )
        {
            cordova.exec( successCallback, errorCallback, 'SecondWebViewPlugin', 'show', [ url ] );
        },
        Hide             : function( successCallback, errorCallback )
        {
            cordova.exec( successCallback, errorCallback, 'SecondWebViewPlugin', 'hide', [] );
        },
        SubscribeCallback: function( successCallback, errorCallback )
        {
            cordova.exec( successCallback, errorCallback, 'SecondWebViewPlugin', 'subscribeCallback', [] );
        },
        Close            : function( successCallback, errorCallback )
        {
            cordova.exec( successCallback, errorCallback, 'SecondWebViewPlugin', 'hide', [] );
        },
        InjectJSCode     : function( code, callback )
        {
            cordova.exec( callback, callback, 'SecondWebViewPlugin', 'injectJSCode', [ code, !!callback ] );
        },
        InjectJSFile     : function( file, callback )
        {
            cordova.exec( callback, callback, 'SecondWebViewPlugin', 'injectJSFile', [ file, !!callback ] );
        },
        InjectCSSCode    : function( code, callback )
        {
            cordova.exec( callback, callback, 'SecondWebViewPlugin', 'injectCSSCode', [ code, !!callback ] );
        },
        InjectCSSFile    : function( file, callback )
        {
            cordova.exec( callback, callback, 'SecondWebViewPlugin', 'injectCSSFile', [ file, !!callback ] );
        }
    };
})();

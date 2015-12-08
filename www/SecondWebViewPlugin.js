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
        Close            : this.Hide,
        ExecuteCode      : function()
        {
            //TODO
        },
        ExecuteFile      : function()
        {
            //TODO
        }
    };
})();

/*global elu, module */
module.exports = (function()
{
    'use strict';

    var _show = function( url, successCallback, errorCallback )
    {
        cordova.exec( successCallback, errorCallback, 'SecondWebViewPlugin', 'show', [ url ] );
    };

    var _hide = function( successCallback, errorCallback )
    {
        cordova.exec( successCallback, errorCallback, 'SecondWebViewPlugin', 'hide', [] );
    };

    var _subscribeCallback = function( successCallback, errorCallback )
    {
        cordova.exec( successCallback, errorCallback, 'SecondWebViewPlugin', 'subscribeCallback', [] );
    };

    return {
        Show             : _show,
        Hide             : _hide,
        Close            : _hide,
        SubscribeCallback: _subscribeCallback
    };

})();

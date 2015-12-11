module.exports = (function () {
    'use strict';
    return {
        _plugin :              'SecondWebViewPlugin' ,
        _action :              {
            open :                 'open' ,
            close :                'close' ,
            registerPingReceiver : 'registerPingReceiver' ,
            ping :                 'ping'
        } ,
        Open :                 function ( url , pass , fail ) {
            cordova.exec( pass , fail , this._plugin , this._action.open , [ url ] );
        } ,
        Close :                function ( pass , fail ) {
            cordova.exec( pass , fail , this._plugin , this._action.close , [] );
        } ,
        RegisterPingReceiver : function ( receiver , pass , fail ) {
            cordova.exec( pass , fail , this._plugin , this._action.registerPingReceiver , [ receiver ] );
        } ,
        Ping :                 function ( data , isBased , pass , fail ) {
            cordova.exec( pass , fail , this._plugin , this._action.ping , [ {
                data :    data ,
                isBased : isBased
            } ] );
        }
    };
}) ();

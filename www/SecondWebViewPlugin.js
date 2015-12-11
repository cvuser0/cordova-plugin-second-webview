module.exports = (function () {
    'use strict';
    return {
        _plugin              : 'SecondWebViewPlugin' ,
        _action              : {
            open                 : 'open' ,
            closeChild           : 'closeChild' ,
            closeParent          : 'closeParent' ,
            registerPingReceiver : 'registerPingReceiver' ,
            ping                 : 'ping'
        } ,
        Open                 : function ( url , pass , fail ) {
            cordova.exec ( pass , fail , this._plugin , this._action.open , [ url ] );
        } ,
        CloseChild           : function ( pass , fail ) {
            cordova.exec ( pass , fail , this._plugin , this._action.closeChild , [] );
        } ,
        CloseParent          : function ( pass , fail ) {
            cordova.exec ( pass , fail , this._plugin , this._action.closeParent , [] );
        } ,
        RegisterPingReceiver : function ( receiver , pass , fail ) {
            cordova.exec ( pass , fail , this._plugin , this._action.registerPingReceiver , [ receiver ] );
        } ,
        Ping                 : function ( data , isBased , pass , fail ) {
            cordova.exec ( pass , fail , this._plugin , this._action.ping , [ { data : data , isBased : isBased } ] );
        }
    };
}
) ();

module.exports = (
    function () {
        'use strict';
        return {
            Open        : function ( url ) {
                cordova.exec ( null , null , 'SecondWebViewPlugin' , 'open' , [ url ] );
            } ,
            CloseParent : function () {
                cordova.exec ( null , null , 'SecondWebViewPlugin' , 'closeParent' , [] );

            } ,
            CloseChild  : function () {
                cordova.exec ( null , null , 'SecondWebViewPlugin' , 'closeChild' , [] );

            }
        };
    }
) ();

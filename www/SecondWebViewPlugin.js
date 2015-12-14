module.exports = (
    function () {
        'use strict';
        return {
            open         : function ( pass , fail , url ) {
                cordova.exec ( pass , fail , 'SecondWebViewPlugin' , 'open' , [ { target : 'plugin' , url : url } ] );
            } ,
            addInterface : function () {
                cordova.exec ( null , null , 'SecondWebViewPlugin' , 'addInterface' , [] );

            }
        };
    }
) ();

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
//                    close  : function ( target ) {
//                        cordova.exec ( null , null , 'SecondWebViewPlugin' , 'close' , [ { target : target } ] );
//                    } ,
//                    callJS : function ( target , function_name , params , callback ) {
//                        var param = "";
//                        for ( var i = 0 ; i < params.length ; i ++ ) {
//                            param += params[ i ] + ',';
//                        }
//                        param += params[ params.length ] = callback;
//                        cordova.exec ( null , null , 'SecondWebViewPlugin' , 'close' , [ { target : target , payload : 'javascript:' + function_name + '(' + param + ');' } ] );
//                    }
        };
    }
) ();

(function(){
console.log("hello");
var module = angular.module("myApp");
 
        module.controller("MainCtrlConnectioPool", MainCtrlConnectioPool);
 
        function Connection(url, ammount)
            {
        		this.url = url;
        		this.ammount = ammount;
            }

           // Ctor method for the MainCtrl
   function MainCtrlConnectioPool( Server,$location)
    {
      console.log("MainCtrlConnectioPool");
       	this.Connection = new Connection("jdbc:derby://localhost:1527/coupondb","10");
        this.Server = Server;
        this.url = $location.search().url;
        this.pool = {objects:[]};
    }
})()
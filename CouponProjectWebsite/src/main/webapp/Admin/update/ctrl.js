(function(){
console.log("hello");
var module = angular.module("myApp");
 
        module.controller("MainCtrlUpdate", MainCtrlUpdate);
 
           // Ctor method for the MainCtrl
   function MainCtrlUpdate( Server,$location)
    {
      
       this.location = $location;
        this.Server = Server;
        this.url = $location.search().url;
        console.log($location.search().method)
        this.method = $location.search().method;
        this.pool = {objects:[],names:[]};
        
    }
})()
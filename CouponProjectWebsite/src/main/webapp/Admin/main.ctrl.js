/**
 * 
 */
//main.ctrl.js
	
(function(){
console.log("hello");
var module = angular.module("myApp");
 
        module.controller("MainCtrl", MainCtrlCtor);
 

           // Ctor method for the MainCtrl
   function MainCtrlCtor(UserService)
    {
        this.UserService = UserService
        //this.$route = $route;
        this.user = "Admin"
        this.type = "";//company,customer,coupon
        this.page = ""//update,signin
        this.status = "";
        this.pool = {objects:[],names:[]};
        
    }

})()
           
           
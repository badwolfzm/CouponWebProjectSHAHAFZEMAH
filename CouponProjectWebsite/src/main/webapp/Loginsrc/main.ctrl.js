/**
 * 
 */
//main.ctrl.js
	
(function(){
console.log("hello");
var module = angular.module("myApp");
 
        module.controller("MainCtrl", MainCtrlCtor);
 
        function User(userName, password, clientType)
            {
        		this.userName = userName;
        		this.password = password;
        		this.clientType = clientType;
            }

           // Ctor method for the MainCtrl
   function MainCtrlCtor( Server )
    {
      console.log("MainCtrlCtor");
       	this.User = new User("admin","1234","Admin");
        this.Server = Server;
        this.k = function(){return "helllllo"};
    }
})()
           
            
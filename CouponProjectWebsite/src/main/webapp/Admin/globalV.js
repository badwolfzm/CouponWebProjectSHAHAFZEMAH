var myApp = angular.module('myApp');
myApp.factory('UserService', function() {
  return {
      projectName:"CouponProjectWebsite",
      port:"8080",
      domain:"localhost"
  };
});
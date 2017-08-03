var app = angular.module("myApp") ;
/*
 app.controller("BookCtrl", BookCtrlCtor);
 app.controller("ChocolateCtrl", ChocolateCtrlCtor);
 app.controller("FooterCtrl", FooterCtrlCtor);
 */
 // http://stackoverflow.com/questions/41211875/angularjs-1-6-0-latest-now-routes-not-working
 app.config(['$locationProvider', function($locationProvider) {
      $locationProvider.hashPrefix('');
}]);
 
 // another example doing it using text/ng-template
app.config(function($stateProvider, $urlRouterProvider) {
	
    $stateProvider
    .state("CreateConnections", {
        url : "/CreateConnections",
    	templateUrl : "/CouponProjectWebsite/Admin/createConnection/index.html",
        controller : "MainCtrlConnectioPool as CONTR"
    })
    .state("get", {
    	 url : "/get",
    	 templateUrl : "/CouponProjectWebsite/Admin/getAllCompenies/index.html",
    	 controller : "MainCtrlgetCompanies as CONTR"
    })
    .state("update", {
    	url : "/update",
        templateUrl : "/CouponProjectWebsite/Admin/update/index.html",
        controller : "MainCtrlUpdate as CONTR"
    })
    .state("doTask", {
    	url : "/doTask",
        templateUrl : "/CouponProjectWebsite/Admin/doTask/index.html",
    	 controller : "MainCtrlgetCompanies as CONTR"
    })/*
    .state("404", {
    	url : "/404",
        templateUrl : "404.html"
    });

    $urlRouterProvider.when("", "/main"); // first browsing postfix is empty --> route it to /main
    $urlRouterProvider.otherwise('/404'); // when no switch case matches --> route to /404
    */
    
});
/*
function MainCtrlCtor()
{
   
}

function BookCtrlCtor( $location, $state, $stateParams)
{
	console.log ( $state);
    console.log ( $stateParams);
        this.where = "Book Controller";      
        this.urlParameter = $location.path();
}

function ChocolateCtrlCtor($location,  $state, $stateParams)
{
	console.log ( $state);
    console.log ( $stateParams);
        this.where = "Chocolate Controller";     
        var s = $location.path();;
        //this.urlParameter = s.substring( s.lastIndexOf(":") + 1 );
        this.urlParameter = $state.type;
        this.goHome = function()
        {
            	$state.go("main");
            }
       // this.urlParameter = this.urlParameter.subString(urlParameter.lastIndexOf(":"));
    }

function FooterCtrlCtor()
{
  this.cocoType = "mocca";    
}
*/
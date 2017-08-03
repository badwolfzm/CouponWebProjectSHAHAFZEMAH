(function()
{
    console.log("mmmmmmmmm");
    var module = angular.module("myApp");
    
    module.service("Server", Server);

    function Server( $http,$location )
    {
        this.sendLogin = function(data)
        {
            console.log("sending login")
            $http({
                "method":"POST",
                "url":"/CouponProjectWebsite/Login",
                "headers":{"Content-Type":"application/json","Accept": "application/json"},
                "data":data,
                "dataType":"json"
            }).then(function success(data, status, headers,config){
                console.log("successed");
                //console.log("location "+ $location.absUrl())
                console.log(data.config.url);
                console.log(config.url)
                window.location = "/CouponProjectWebsite/Login";
            },function error(data, status, headers,config){
                console.log("not successed");
                console.log(data)
                window.location = "/CouponProjectWebsite/Login";
            });
        }
        // in the future will be replaced by 
        //return $http.post('URL', coupon);
    }


})();
 
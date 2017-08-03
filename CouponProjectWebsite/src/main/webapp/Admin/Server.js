(function()
{
    console.log("mmmmmmmmm");
    var module = angular.module("myApp");
    
    module.service("Server", Server);


    function Server( $http,$location )
    {
        this.sendLogin = function(data,url,messege,method,headers,controler,removeList,querry)
        {
            function serialize(obj) {
                var str = [];
                for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            }
            var info = ""
            if(querry == undefined)
            {
                querry = {};
                if(querry.search == undefined)
                {
                    querry.search={};
                }  
            }
            else if(querry.search != undefined)
            {
                 info = "?"+serialize(querry.search);
            }
            
            
            console.log("sending ")
            console.log(JSON.stringify(querry.search).replace("{", "").replace("}", "").replace('"', "").replace(",", "&").replace(":", "=").replace("\"", "").replace("\"", "").replace('"', ""))
            let packedg = {
                "method":method,
                "url":url+info,
                "headers":headers,
                "data":data
                
            };
            querry.search = {};
            console.log(packedg)
            $http(packedg).then(function success(response){
                console.log("successed");
                //console.log("location "+ $location.absUrl())
                console.log(response.config.url);
                //console.log(config.url)
                //window.location = "/CouponProjectWebsite/Login";
                console.log(typeof response.data)
                console.log(response.data instanceof Array)
                console.log(response.data)
                if(/*(response.data !== null && typeof response.data === 'object' ) */ response.data[0] == undefined)
                {
                    console.log("adding []")
                    response.data = [response.data]
                }
                if(response.data.length > 0)
                {
                    controler.pool.names = Object.keys(response.data[0]);
                    console.log( controler.pool.names)
                    
                    if(removeList)
                    {
                        for(let j in response.data)
                        {
                            for(let i in controler.pool.names)
                            {
                                console.log(response.data[j][controler.pool.names[i]] !== null)
                                console.log(typeof response.data[j][controler.pool.names[i]] === 'object')
                                console.log(response.data[j][controler.pool.names[i]])
                                console.log(typeof( response.data[j][controler.pool.names[i]] ))
                                console.log("*********")
                                if(response.data[j][controler.pool.names[i]] !== null && typeof response.data[j][controler.pool.names[i]] === 'object' )
                                {
                                    console.log("deleting "+controler.pool.names.indexOf(controler.pool.names[i]))
                                    //console.log(controler.pool.names[controler.pool.names.indexOf(response.data[j][controler.pool.names[i]])])
                                    console.log(controler.pool.names[i])
                                    console.log(controler.pool.names)
                                    controler.pool.names.splice(controler.pool.names.indexOf(controler.pool.names[i]), 1)
                                    console.log(controler.pool.names)
                                }
                                else
                                {
                                    console.log(typeof response.data[j][controler.pool.names[i]]);
                                }
                            }
                        }
                        
                    }
                }
            
               
                messege.status = "success "+response.data;

                controler.pool.objects = response.data;
            },function error(response){///////////////////////////////////////////////ERROR
               
                console.log("not successed");
                console.log(response)
                console.log(response.data)
                messege.status = "failed "+response;
                controler.pool.objects = [];
                controler.pool.objects = response;













                //window.location = "/CouponProjectWebsite/Login";
            });
        }
        // in the future will be replaced by 
        //return $http.post('URL', coupon);
    }


})();

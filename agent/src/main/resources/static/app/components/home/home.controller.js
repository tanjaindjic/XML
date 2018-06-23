(function () {
    'use strict';

    angular
		.module('app')
		.controller('homeController', homeController);

    homeController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window'];
    function homeController($location, $scope, $rootScope, $http, $cookies, $window) {
    	var hc = this;
    	function getJwtToken() {
            return localStorage.getItem($scope.TOKEN_KEY);
        }
    	var init = function(){
    		var username = "";
    		if (getJwtToken()) {
                $scope.profileShow = true;
                username = jwt_decode(getJwtToken()).sub
			} else{
                $location.path('/login');		
			}
    		$scope.all=[];
    		$http({
                method: 'GET',
                url: '/api/agent/'+username+'/smestaj'
              }).then(function successCallback(response) {
            	  if(response.data!=""){
            		  for(var i=0; i<response.data.length; i++){
            			  $scope.all.push(response.data[i]);
            		  }
            	  }
            	  
              });

        };
        init();
        
        hc.edit = function(acom){
        	$cookies.put("edit", JSON.stringify(acom));
            $location.path('/edit');	
        }
    }

})();
(function () {
    'use strict';

    angular
		.module('app')
		.controller('agentConfirmStayController', agentConfirmStayController);

    agentConfirmStayController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window', '$state'];
    function agentConfirmStayController($location, $scope, $rootScope, $http, $cookies, $window, $state) {
    	var acsc = this;
    	
    	
    	function getJwtToken() {
            return localStorage.getItem($scope.TOKEN_KEY);
        }
    	$scope.goToState = function(state) {
			$state.go(state, {
				"id" : $scope.userId
			});
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
                url: '/reservation/pending',
                headers: createAuthorizationTokenHeader()
              }).then(function successCallback(response) {
            	  if(response.data!=""){
            		  for(var i=0; i<response.data.length; i++){
            			  $scope.all.push(response.data[i]);
            			  console.log($scope.all.length)
            			  console.log($scope.all)
            		  }
            	  }
            	  
              });
    		
    	

        };
        init();
    
    	function getJwtToken() {
            return localStorage.getItem($scope.TOKEN_KEY);
        }
    	
        $scope.approve = function(id){
        		var data = {
        				"status" : "CONFIRMED",
        				"id" : id
        		};
        	$http({
                method: 'PUT',
                url: 'https://localhost:8097/reservation/update',
                data: JSON.stringify(data),
                headers : createAuthorizationTokenHeader()
              }).then(function successCallback(response) {
            	 alert("Success!")
            	 init();
              });
        }
        
        $scope.decline = function(id){
    		var data = {
    				"status" : "REJECTED",
    				"id" : id
    		};
    	$http({
            method: 'PUT',
            url: 'https://localhost:8097/reservation/update',
            data: JSON.stringify(data),
            headers : createAuthorizationTokenHeader()
          }).then(function successCallback(response) {
        	 alert("Success!")
        	 init();
          });
    }
        
    	function createAuthorizationTokenHeader() {
            var token = getJwtToken();
            if (token) {
                return {
                    "Authorization" : "Bearer " + token
                };
            } else {
                return {};
            }
        }
       
    }

})();
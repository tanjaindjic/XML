(function () {
    'use strict';

    angular
		.module('app')
		.controller('agentReservationsController', agentReservationsController);

    agentReservationsController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window', '$state'];
    function agentReservationsController($location, $scope, $rootScope, $http, $cookies, $window, $state) {
    	var arc = this;
		$scope.selectedSmestaj = "";
		$scope.selectedSoba	 = "";
		$scope.period = "";
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
                url: '/api/agent/'+username+'/smestaj'
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
    
        $scope.makeRes = function(){
        	var data = {
        			"idSobe":$scope.selectedSoba.id,
        			"idSmestaja":$scope.selectedSmestaj.id,
        			"idKorisnika" : jwt_decode(getJwtToken()).jti,
        			"pocetnoVreme" : $scope.period.datumOd,
        			"krajnjeVreme" : $scope.period.datumDo
        			
        	};
        	console.log(JSON.stringify(data))
        	$http({
                method: 'POST',
                url: 'https://localhost:8097/reservation/make',
                data: JSON.stringify(data),
                headers : createAuthorizationTokenHeader()
              }).then(function successCallback(response) {
            	 alert("Success!")
            	  
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
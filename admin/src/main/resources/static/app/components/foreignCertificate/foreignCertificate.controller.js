(function () {
    'use strict';

    angular
		.module('app')
		.controller('foreignCertificateController', foreignCertificateController);

    foreignCertificateController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$timeout'];
    function foreignCertificateController($location, $scope, $rootScope, $http, $window, $cookies, $timeout) {
        var fcc = this;
        $scope.TOKEN_KEY = "jwtToken";
        var refresh = function(){
			$state.go($state.current.name, {}, {reload: true})
		}
        function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}

        var init = function (){
        	
        	if (getJwtToken()) {				
				$scope.loggedIn = true;
			} else {
			
				$location.path("/login")
				$scope.loggedIn= false;
			}
			
        	              
        }
        
        init();
       
        fcc.send = function(){
        	$scope.cert.issuerName="";
        	$scope.cert.startDate=$scope.dateFrom;
        	$scope.cert.endDate=$scope.dateTo;
        	if($scope.cert.isCa)
        		$scope.cert.caa=1;
        	else
        		$scope.cert.caa=0;
        	$scope.cert.serialNumber=0;
        	$scope.cert.adresa = "";
        	$scope.cert.PIB = "";
        	$scope.cert.id = "noid";
        	$scope.cert.issuerSerialNumber="";
        	var regData = $scope.cert;
        	console.log(JSON.stringify(regData))
			$http({
				method : 'POST',
				url : "https://localhost:8090/certificate",
				data : JSON.stringify(regData)
			}).then(function successCallback(response) {

				$location.path("/success/1") 
			}, function errorCallback(response) {
				$scope.message = response.data.text;
				console.log(response)
			});
        	
        	
        }
        
        fcc.showDone= function() {			
		      $scope.showStatus= true;
		      $timeout(function() {
		         $scope.showStatus = false;
		      }, 20000);
		   };
    }


})();
(function () {
    'use strict';

    angular
		.module('app')
		.controller('certificateController', certificateController);

    certificateController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$timeout'];
    function certificateController($location, $scope, $rootScope, $http, $window, $cookies, $timeout) {
        var certc = this;
        
        $scope.TOKEN_KEY = "jwtToken";
        
        var init = function (){
        	
        		
        	$scope.self=false;
        	$scope.status = "";
        	$scope.showStatus= false;
        	$scope.cert = {};
        	$scope.isAll=true;
        	$scope.isCheck=false;
        	$scope.isMake=false;
        	$scope.isGet=false;
        	$scope.isRevoke=false;
        	$scope.checkedStatus="";
        	$scope.showCheckedStatus=false;
        	$scope.showIsRevoked=false;
        	$scope.isDownload=false;
        	$scope.revokedStatus="";
        	$scope.allCerts = [];
        	$scope.downloadNumber="";
     
        	$http({
                method: 'GET',
                url: 'https://localhost:8096/certificates',
                headers: createAuthorizationTokenHeader()
              }).then(function successCallback(response) {
            	  for(var i=0; i<response.data.length; i++)            		  
            		  $scope.allCerts.push(response.data[i]);
              });    
              
        }
        
        init();
       
        
        certc.checkStatus = function(){
        	$http({
                method: 'GET',
                url: 'https://localhost:8096/certificate/check/'+$scope.checkNumber, 
                headers: createAuthorizationTokenHeader()
              }).then(function successCallback(response) {
            	
            	  if(response.data=="good")
            		  $scope.checkedStatus = "status for "+$scope.checkNumber+" : VALID";
            	  else if(response.data=="undefined")
            		  $scope.checkedStatus = "Certificate not found.";
            	  else if(response.data=="revoked")
            		  $scope.checkedStatus = "status for "+$scope.checkNumber+" :NOT VALID";
            	  $scope.showCheckedStatus=true;
              }); 
        }
        certc.makeItDownlaod = function(){
        	if($scope.downloadNumber==null || $scope.downloadNumber==""){
        		return;
        	}
        	$http({
                method: 'GET',
                url: 'https://localhost:8096/certificate/download/'+$scope.downloadNumber, 
                headers: createAuthorizationTokenHeader()
              }).then(function successCallback(data, status, headers, config) {            	
            	  var res = data;
            	  var blob = new Blob([data], {type: 'application/binary'});
            	    var objectUrl = URL.createObjectURL(blob);
            	    window.open(objectUrl);
              }); 
        }
        certc.showDone= function() {			
		      $scope.showStatus= true;
		      $timeout(function() {
		         $scope.showStatus = false;
		      }, 20000);
		   };
    
    function getJwtToken() {
		return localStorage.getItem($scope.TOKEN_KEY);
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
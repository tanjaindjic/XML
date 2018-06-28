(function () {
    'use strict';

    angular
		.module('app')
		.controller('uploadCertController', uploadCertController);

    uploadCertController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$sce', '$stateParams'];
    function uploadCertController($location, $scope, $rootScope, $http, $cookies, $sce, $stateParams) {
        var uc = this;
     
        var init = function (){
        	$scope.myFile="";
	         $scope.showW= false;
	         $scope.message="";
	         $scope.user=$stateParams.username;
	         console.log($scope.user)
        };
        init();
        
        uc.logOut = function(){
        	
        }
        $scope.uploadFile = function(){
        	var file = $scope.myFile;
        	var fileFormData = new FormData();
            fileFormData.append('file', file);
        	$http({
        		method: 'POST',
                url: 'https://localhost:8096/certificates/upload/' + $scope.user,
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined},
                data: fileFormData
            }).then(function onSuccess(response) {
            	window.location = response.data.Location;
            	 removeJwtToken();
            }).catch(function onError(response) {
            	
               $scope.message = response.data.text;
            });
        }
        
        uc.showWrong= function() {			
		      $scope.showW= true;
		      $timeout(function() {
		         $scope.showW= false;
		      }, 5000);
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
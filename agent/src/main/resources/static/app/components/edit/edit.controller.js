(function () {
    'use strict';

    angular
		.module('app')
		.controller('editController', editController);

    editController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window'];
    function editController($location, $scope, $rootScope, $http, $cookies, $window) {
    	var ec = this;
    	function getJwtToken() {
            return localStorage.getItem($scope.TOKEN_KEY);
        }
    	var init = function(){
    		if (getJwtToken()) {
                $scope.profileShow = true;
			} else{
                $location.path('/login');		
			}
    		$scope.smestaj = JSON.parse($cookies.get('edit'));
    		$scope.caths=[];
    		$http({
                method: 'GET',
                url: '/api/kategorija'
              }).then(function successCallback(response) {
            	  if(response.data!=undefined){
            		  var tipovi = response.data
            		  for(var i=0; i<tipovi.length; i++){
            			  $scope.caths.push(tipovi[i]);
            		  }
            	  }
            	  
              });
    		
    		$scope.dodatne = [];
			$http({
                method: 'GET',
                url: '/api/dodatneUsluge'
              }).then(function successCallback(response) {
            	  if(response.data!=undefined){
            		  var dod = response.data;
            		  for(var i=1; i<=dod.length; i++){
            			  $scope.dodatne.push(dod[i]);
            		  }
            	  }
            	  
              });
        };
        init();
        
    }

})();
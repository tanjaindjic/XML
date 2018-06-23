(function() {
	'use strict';

	angular.module('app').controller('agentAccomodationController', agentAccomodationController);

	agentAccomodationController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$window', '$cookies', '$stateParams', '$state', '$timeout' ];
	function agentAccomodationController($location, $scope, $rootScope, $http, $window,
			$cookies, $stateParams, $state, $timeout) {

		var aac = this;

		$scope.goToState = function(state) {
			$state.go(state, {
				"id" : $scope.userId
			});
		}

		var init = function() {
			$scope.types = [];
			$scope.smestaj = {};
			$scope.smestaj.slike = [];
			$scope.soba = {};
			$scope.cena = {};
			$rootScope.images="";
			$scope.dodatneUsluge=[];
			$scope.uplImage={};
			$scope.kateg = [];
			$scope.isAddingRoom=false;
			$http({
                method: 'GET',
                url: '/api/tipService'
              }).then(function successCallback(response) {
            	  if(response.data!=undefined){
            		  var tipovi = response.data
            		  for(var i=0; i<tipovi.length; i++){
            			  $scope.types.push(tipovi[i]);
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
            		  for(var i=0; i<dod.length; i++){
            			  $scope.dodatne.push(dod[i]);
            		  }
            	  }
            	  
              });

			$scope.dodatne = [];
			$http({
                method: 'GET',
                url: '/api/kategorija'
              }).then(function successCallback(response) {
            	  if(response.data!=undefined){
            		  var dod = response.data;
            		  for(var i=0; i<dod.length; i++){
            			  $scope.kateg.push(dod[i]);
            		  }
            	  }
            	  
              });

		};

		init();


		aac.uploadImage = function(){
			if($scope.uplImage!=undefined && $scope.uplImage!=""){
				var file = $scope.uplImage;
	        	var fileFormData = new FormData();
	            fileFormData.append('file', file);
				$http({
	                method: 'POST',
	                url: '/api/postFile',
	                transformRequest: angular.identity,
	                headers: {'Content-Type': undefined},
	                data: fileFormData
	              }).then(function successCallback(response) {
	            	  if(response.data!=""){
	            		  $scope.smestaj.slike.push(response.data);
	            		  if($rootScope.images!="")
	            			  $rootScope.images = $rootScope.images+', ';
	            		  $rootScope.images= $rootScope.images + $scope.uplImage.name;
	            		  $scope.uplImage={};
	            		  $scope.uplImage.name="";
	            	  }
	            	  
	              });
			}
		}
		
		aac.addDodatne = function(opcija){
			if($scope.dodatneUsluge.indexOf(opcija)!=-1){
				var num = $scope.dodatneUsluge.indexOf(opcija);
				$scope.dodatneUsluge.splice(num, 1)
			}
			else{
				var num = $scope.dodatneUsluge.indexOf(opcija);
				$scope.dodatneUsluge.push(opcija);
			}
		}
		aac.addSmestaj = function(){
			$http({
                method: 'POST',
                url: '/api/smestaj',
                data: $scope.smestaj
              }).then(function successCallback(response) {
            	  if(response.data!=""){
            		  $scope.smestaj.slike.push(response.data);
            		  if($rootScope.images!="")
            			  $rootScope.images = $rootScope.images+', ';
            		  $rootScope.images= $rootScope.images + $scope.uplImage.name;
            		  $scope.uplImage={};
            		  $scope.uplImage.name="";
            	  }
            	  
              });
		}
	}

})();
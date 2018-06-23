(function() {
	'use strict';

	angular.module('app').controller('coreController', coreController);

	coreController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$cookies', '$window', 'userService', '$state', ];
	function coreController($location, $scope, $rootScope, $http, $cookies,
			$window, userService, $state) {
		
        function getJwtToken() {
            return localStorage.getItem($scope.TOKEN_KEY);
        }
        $scope.goToState = function(state) {
			$state.go(state, {
				"id" : $scope.userId
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


		$scope.profile = function(){
			$scope.panelToShow = -1;
            $location.path('/profile');
		}
		
		
		var cc = this;
		$scope.TOKEN_KEY = "jwtToken";
		$scope.profile = function(){
			$scope.panelToShow = -1;
            $location.path('/profile');
		}

		$scope.profileShow = false;

		$scope.username = "";
		
		function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}
		var init = function() {
			if (getJwtToken()) {
                $scope.profileShow = true;
				$scope.username = " " +jwt_decode(getJwtToken()).sub;
		
			} else{
                $scope.profileShow = false;
                $scope.username="";
			}
				

		};
		init();

		function removeJwtToken() {
			localStorage.removeItem($scope.TOKEN_KEY);
		}
		
		$scope.login = function() {
			$scope.panelToShow = -1;
			$location.path('/login');
		}
		
		$scope.register = function() {
			$scope.swapPanel(-1);
			$location.path('/register');
		}
		
		$scope.logout = function(){
			$scope.panelToShow = -1;
			removeJwtToken();
			$scope.profileShow = false;
			$scope.username="";
			$window.location="https://localhost:8097/#!/login";
		}
		
		$scope.typeList = [];
		
		$scope.addToTypeList = function(type){
			if(!ainTypes($scope.typeList, type)){
				alert("Iteam already in list.")
			}else{
				$scope.typeList.push(type);
			}
			
			
		}
		
		$scope.serviceList = [];
		
		$scope.addToServiceList = function(service){
			if(!ainServices($scope.serviceList, service)){
				alert("Iteam already in list.")
			}else{
				$scope.serviceList.push(service);
			}
			
		}
		
		$scope.catList = [];
		
		$scope.addToCatList = function(cat){
			if(!ainCats($scope.catList, cat)){
				alert("Iteam already in list.")
			}else{
				$scope.catList.push(cat);
			}
			
		}
		
		$scope.deleteTypeIteam = function(t){
			$scope.typeList.splice(ainAtTypes($scope.typeList, t),1);
		}
		
		$scope.deleteCatIteam = function(t){
			$scope.catList.splice(ainAtCats($scope.catList, t),1);
		}
		
		$scope.deleteServiceIteam = function(t){
			$scope.serviceList.splice(ainAtServices($scope.serviceList, t),1);
		}
		
		function ainCats(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].kategorija==itm.kategorija){
					return false;
				}
			}
			return true;
		}
		
		function ainAtCats(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].kategorija==itm.kategorija){
					return i;
				}
			}
			return -1;
		}
		
		function ainTypes(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].tip==itm.tip){
					return false;
				}
			}
			return true;
		}
		
		function ainAtTypes(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].tip==itm.tip){
					return i;
				}
			}
			return -1;
		}
		
		function ainServices(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].opcija==itm.opcija){
					return false;
				}
			}
			return true;
		}
		
		function ainAtServices(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].opcija==itm.opcija){
					return i;
				}
			}
			return -1;
		}

	}

})();
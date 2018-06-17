(function() {
	'use strict';

	angular.module('app').controller('coreController', coreController);

	coreController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$cookies', '$window', 'userService'];
	function coreController($location, $scope, $rootScope, $http, $cookies,
			$window, userService) {
		
		$scope.typeList = [];
		
		$scope.addToTypeList = function(type){
			$scope.typeList.push(type);
		}
		
		$scope.serviceList = [];
		
		$scope.addToServiceList = function(service){
			$scope.serviceList.push(service);
		}
		
		$scope.catList = [];
		
		$scope.addToCatList = function(cat){
			$scope.catList.push(cat);
		}
		
		userService.getAllTypes(
				function(info){
					$scope.loadedTypes = info.data;
				},
				function(){
					alert("Error loading accomodation types!!!");
				}
		)
		
		userService.getAllAditional(
				function(info){
					$scope.loadedServices = info.data;
				},
				function(){
					alert("Error loading accomodation services!!!");
				}
		)
		
		userService.getAllKategorija(
				function(info){
					$scope.loadedCategories = info.data;
				},
				function(){
					alert("Error loading accomodation categories!!!");
				}
		)
		
		$scope.showAdvancedSearch = false;
		
		$scope.advSearch = function(){
			return "Advanced search";
		}
		$scope.profile = function(){
            $location.path('/profile');
		}
		
		$scope.toggleAdvancedSearch = function(){
			$scope.showAdvancedSearch = !$scope.showAdvancedSearch;
		}
		
		
		
		
		var cc = this;
		$scope.TOKEN_KEY = "jwtToken";
		$scope.logout = $("#logoutBtn");
		$scope.login = $("#loginBtn");
		$scope.profile = function(){
            $location.path('/profile');
		}

		$scope.profileShow = false;

		$scope.reg = $("#registerBtn");
		$scope.username = "";
		
		function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}
		var init = function() {

			if (getJwtToken()) {
				$scope.login.hide();
				$scope.logout.show();

                $scope.profileShow = true;
				$scope.reg.hide();
				$scope.username = " " +jwt_decode(getJwtToken()).sub;
		
			} else{
				$scope.login.show();
				$scope.logout.hide();
				$scope.reg.show();
                $scope.profileShow = false;
			}
				

		};
		init();

		function removeJwtToken() {
			localStorage.removeItem($scope.TOKEN_KEY);
		}
		
		$scope.login = function() {
			$location.path('/login');
		}
		
		$scope.register = function() {
			$location.path('/register');
		}
		
		$scope.logout = function(){
			removeJwtToken();
			$scope.login.show();
			$scope.logout.hide();
			$scope.reg.show();
			
			$location.path("/home")
		}

	}

})();
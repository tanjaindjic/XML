(function() {
	'use strict';

	angular.module('app').controller('coreController', coreController);

	coreController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$cookies', '$window' ];
	function coreController($location, $scope, $rootScope, $http, $cookies,
			$window) {
		var cc = this;
		$scope.TOKEN_KEY = "jwtToken";
		$scope.logout = $("#logoutBtn");
		$scope.login = $("#loginBtn");
		$scope.reg = $("#registerBtn");
		$scope.username = "";
		
		function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}
		var init = function() {

			if (getJwtToken()) {
				$scope.login.hide();
				$scope.logout.show();
				$scope.reg.hide();
				$scope.username = " " +jwt_decode(getJwtToken()).sub;
		
			} else{
				$scope.login.show();
				$scope.logout.hide();
				$scope.reg.show();
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
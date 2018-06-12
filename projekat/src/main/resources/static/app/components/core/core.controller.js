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
		function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}
		var init = function() {

			if (getJwtToken()) {
				$scope.login.hide();
				$scope.logout.show();
				$scope.reg.hide();
			} else{
				$scope.login.show();
				$scope.logout.hide();
				$scope.reg.show();
			}
				

		};
		init();

		$scope.login = function() {
			$location.path('/login');
		}

	}

})();
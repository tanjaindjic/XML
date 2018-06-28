(function() {
	'use strict';

	angular.module('app').controller('resetPassController', resetPassController);

	resetPassController.$inject = [ '$location', '$scope', '$rootScope',
			'$http', '$window', '$cookies', '$stateParams', '$state',
			'$timeout' ];
	function resetPassController($location, $scope, $rootScope, $http, $window,
			$cookies, $stateParams, $state, $timeout) {

		

		var rpc = this;

		var init = function() {
			console.log(window.location.href.split("/").pop())
			$scope.TOKEN_KEY = "jwtToken"

			$scope.message = "";

			// INITIAL CALLS
			// =============================================================
			if (getJwtToken()) {

				$location.path("/home")
			}

		};

		init();

		// FUNCTIONS
		// =============================================================
		function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}

		function doReset(regData) {
			
			console.log(JSON.stringify(regData))
			$http({
				method : 'POST',
				url : "https://localhost:8096/user/resetToken/" + window.location.href.split("/").pop(),
				data : JSON.stringify(regData)
			}).then(function successCallback(response) {

				window.location = response.data.Location;
			}, function errorCallback(response) {
				$scope.message = response.data.text;
				console.log(response)
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

		// REGISTER EVENT LISTENERS
		// =============================================================
		$("#registerForm")
				.submit(
						function(event) {
							event.preventDefault();
							var $form = $(this);
							var pass1 = $form.find('input[name="password1"]')
									.val();
							var pass2 = $form.find('input[name="password2"]')
									.val();
							if (pass1 != pass2) {
								$scope.message = "Passwords don't match";
								return;
							}
							
							$scope.message="";
							var formData = {

								"password1" : $form.find(
										'input[name="password1"]').val(),
								"password2" : $form.find(
										'input[name="password2"]').val()
							};

							doReset(formData);
						});

	
	}

})();
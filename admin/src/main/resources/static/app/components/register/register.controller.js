(function() {
	'use strict';

	angular.module('app').controller('registerController', registerController);

	registerController.$inject = [ '$location', '$scope', '$rootScope',
			'$http', '$window', '$cookies', '$stateParams', '$state',
			'$timeout' ];
	function registerController($location, $scope, $rootScope, $http, $window,
			$cookies, $stateParams, $state, $timeout) {

		var regc = this;

		var init = function() {
			$scope.TOKEN_KEY = "jwtToken"

			$scope.message = "";
			$scope.loggedIn = false;

			// INITIAL CALLS
			// =============================================================
			if (getJwtToken()) {

				$scope.loggedIn = true;
			} else
				$location.path("/login")

		};

		init();

		// FUNCTIONS
		// =============================================================
		function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}

		
			function doRegister(regData) {
			console.log(JSON.stringify(regData))
			$http({
				method : 'POST',
				url : "https://localhost:8096/register/admin",
				data : JSON.stringify(regData)
			}).then(function successCallback(response) {

				$location.path("/success/1") 
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
		$("#registerForm").submit(function(event) {
			event.preventDefault();
			var $form = $(this);
			var pass1 = $form.find('input[name="password1"]').val();
			var pass2 = $form.find('input[name="password2"]').val();
			if (pass1 != pass2) {
				$scope.message = "Passwords don't match";
				return;
			}
			if($form.find('input[name="pib"]').val().trim().match(/^[0-9]+$/) != null){
				if($form.find('input[name="pib"]').val().trim().length<9){
					$scope.message = "PIB must contain exactly 9 digits!";
					return;
				}
			}else{
				$scope.message = "PIB must contain exactly 9 digits. ";
				return;
			}
			
			if($form.find('input[name="pib"]').val().trim() == "" || $form.find('input[name="adresa"]').val().trim() == ""){
				$scope.message = "Agents must provide PIB and Adress information.";
				return;
			}

			$scope.message="";
			var formData = {
				"firstname" : $form.find('input[name="firstname"]').val(),
				"lastname" : $form.find('input[name="lastname"]').val(),
				"username" : $form.find('input[name="username"]').val(),
				"password1" : $form.find('input[name="password1"]').val(),
				"password2" : $form.find('input[name="password2"]').val(),
				"agent" : true,
				"email" : $form.find('input[name="email"]').val(),
				"pib" : $form.find('input[name="pib"]').val(),
				"adresa" : $form.find('input[name="adresa"]').val()
			};

			doRegister(formData);
		});

		

	}

})();
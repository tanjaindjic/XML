(function() {
	'use strict';

	angular.module('app').controller('registerController', registerController);

	registerController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$window', '$cookies', '$stateParams', '$state', '$timeout' ];
	function registerController($location, $scope, $rootScope, $http, $window,
			$cookies, $stateParams, $state, $timeout) {

		var regc = this;

		var init = function() {
			$scope.TOKEN_KEY = "jwtToken"
			$scope.login = $("#loginBtn");
			$scope.reg = $("#registerBtn");
			$scope.logout = $("#logoutBtn").hide();
			$scope.message = "";

			// INITIAL CALLS
			// =============================================================
			if (getJwtToken()) {
				$scope.login.hide();
				$scope.logout.show();
				$scope.reg.hide();
				$location.path("/home")
			}

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
	                method: 'POST',
	                url: "https://localhost:8096/register",
	                data : JSON.stringify(regData)
	            }).then(function successCallback(response) {
	            	
	            	window.location = response.data.Location;
	            }, function errorCallback(response) {
	            	$scope.message = response.data.text;
	            	console.log(response)
	            });

		}

		function doLogout() {
			removeJwtToken();
			$scope.login.show();
			$scope.logout.hide();
			$scope.reg.show();
			$location.path("/home")
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
			if(pass1!=pass2){
				$scope.message = "Passwords don't match";
				return;
			}
			if(document.getElementById("agentCheck").checked && ($form.find('input[name="pib"]').val().trim()=="" || $form.find('input[name="adresa"]').val().trim()=="") ){
				$scope.message = "Agents must provide PIB and Adress information.";
				return;
			}
				
			var formData = {
					
				"firstname" : $form.find('input[name="firstname"]').val(),
				"lastname" : $form.find('input[name="lastname"]').val(),
				"username" : $form.find('input[name="username"]').val(),
				"password1" : $form.find('input[name="password1"]').val(),
				"password2" : $form.find('input[name="password2"]').val(),
				"isAgent" : document.getElementById("agentCheck").checked,
				"email" : $form.find('input[name="email"]').val(),
				"pib" : $form.find('input[name="pib"]').val(),
				"adresa" : $form.find('input[name="adresa"]').val()
			};

			doRegister(formData);
		});

		$("#logoutBtn").click(doLogout);
		

		
		$("#agentCheck").click(function() {
			if (document.getElementById('agentCheck').checked) {
		        document.getElementById('pibField').style.display = 'block';
		        document.getElementById('adressField').style.display = 'block';
		    } else {
		        document.getElementById('pibField').style.display = 'none';
		        document.getElementById('adressField').style.display = 'none';
		    }
		});

	}

})();
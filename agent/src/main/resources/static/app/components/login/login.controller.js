(function() {
	'use strict';

	angular.module('app').controller('loginController', loginController);

	loginController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$window', '$cookies', '$stateParams', '$state', '$timeout' ];
	function loginController($location, $scope, $rootScope, $http, $window,
			$cookies, $stateParams, $state, $timeout) {

		var lc = this;

		$scope.goToState = function(state) {
			$state.go(state, {
				"id" : $scope.userId
			});
		}

		var init = function() {
			$scope.TOKEN_KEY = "jwtToken"
			
			$scope.userInfo = $("#userInfo").hide();
			$scope.logout = $("#logoutBtn").hide();

			// INITIAL CALLS
			// =============================================================
			if (getJwtToken()) {
				$scope.logout.show();
				$location.path("/agentAccomodation")
			}

		};

		init();

		/**
		 * Created by stephan on 20.03.16.
		 */

		// FUNCTIONS
		// =============================================================
		function getJwtToken() {
			return localStorage.getItem($scope.TOKEN_KEY);
		}

		function setJwtToken(token) {
			localStorage.setItem($scope.TOKEN_KEY, token);
		}

		function removeJwtToken() {
			localStorage.removeItem($scope.TOKEN_KEY);
		}

		function doLogin(loginData) {
			console.log(JSON.stringify(loginData))
			$http({
				method : 'POST',
				url : "https://localhost:8097/auth",
				data : JSON.stringify(loginData)
			}).then(function successCallback(response) {
				console.log(response.data.token)
				setJwtToken(response.data.token);
				$scope.login.hide();
				$scope.logout.show();
				$scope.reg.hide();
				var decoded = jwt_decode(response.data.token);
				console.log(decoded);
				$window.location.reload();

			}, function errorCallback(response) {
				$scope.message = "Bad credentials.";
			});

			/*
			 * $.ajax({ url : "http://localhost:8096/#!/auth", type : "POST",
			 * data : JSON.stringify(loginData), contentType :
			 * "application/json; charset=utf-8", dataType : "json", success :
			 * function(data, textStatus, jqXHR) {
			 * 
			 * setJwtToken(data.token); $scope.login.hide();
			 * $scope.notLoggedIn.hide(); showTokenInformation();
			 * showUserInformation(); }, error : function(jqXHR, textStatus,
			 * errorThrown) { if (jqXHR.status === 401 || jqXHR.status === 403) {
			 * $('#loginErrorModal').modal("show").find(".modal-body")
			 * .empty().html( "<p>Message from server:<br>" +
			 * jqXHR.responseText + "</p>"); } else { throw new Error("an
			 * unexpected error occured: " + errorThrown); } } });
			 */
		}

		function doLogout() {
			removeJwtToken();
			$scope.login.show();
			$scope.logout.hide();
			$scope.reg.show();
		
			$location.path("/login")
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
		$("#loginForm").submit(function(event) {
			event.preventDefault();

			var $form = $(this);
			var formData = {
				username : $form.find('input[name="username"]').val(),
				password : $form.find('input[name="password"]').val()
			};

			doLogin(formData);
		});

		$("#logoutBtn").click(doLogout);

		

	}

})();
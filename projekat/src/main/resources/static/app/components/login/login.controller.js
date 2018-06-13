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
			$scope.notLoggedIn = $("#notLoggedIn");
			$scope.loggedIn = $("#loggedIn").hide();
			$scope.loggedInBody = $("#loggedInBody");
			$scope.response = $("#response");
			$scope.login = $("#loginBtn");
			$scope.reg = $("#registerBtn");
			$scope.userInfo = $("#userInfo").hide();
			$scope.logout = $("#logoutBtn").hide();

			// INITIAL CALLS
			// =============================================================
			if (getJwtToken()) {
				$scope.login.hide();
				$scope.notLoggedIn.hide();
				$scope.logout.show();
				showTokenInformation();
				showUserInformation();
				$scope.reg.hide();
				$location.path("/home")
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
	                method: 'POST',
	                url: "https://localhost:8096/auth",
	                data : JSON.stringify(loginData)
	            }).then(function successCallback(response) {
	            	console.log(response.data.token)
	            	setJwtToken(response.data.token);
					$scope.login.hide();
					$scope.logout.show();
					$scope.reg.hide();
					$scope.notLoggedIn.hide();
					$location.path("/home")
			
	            }, function errorCallback(response) {alert("Bad credentials")});

			 
		
			/*$.ajax({
				url : "http://localhost:8096/#!/auth",
				type : "POST",
				data : JSON.stringify(loginData),
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data, textStatus, jqXHR) {
					
					setJwtToken(data.token);
					$scope.login.hide();
					$scope.notLoggedIn.hide();
					showTokenInformation();
					showUserInformation();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					if (jqXHR.status === 401 || jqXHR.status === 403) {
						$('#loginErrorModal').modal("show").find(".modal-body")
								.empty().html(
										"<p>Message from server:<br>"
												+ jqXHR.responseText + "</p>");
					} else {
						throw new Error("an unexpected error occured: "
								+ errorThrown);
					}
				}
			});*/
		}

		function doLogout() {
			removeJwtToken();
			$scope.login.show();
			$scope.logout.hide();
			$scope.reg.show();
			$scope.userInfo.hide().find("#userInfoBody").empty();
			$scope.loggedIn.hide();
			$scope.loggedInBody.empty();
			$scope.notLoggedIn.show();
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

		function showUserInformation() {
			$.ajax({
				url : "/user",
				type : "GET",
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				headers : createAuthorizationTokenHeader(),
				success : function(data, textStatus, jqXHR) {
					$scope.userInfoBody = $scope.userInfo.find("#userInfoBody");

					$scope.userInfoBody.append($("<div>").text(
							"Username: " + data.username));
					$scope.userInfoBody.append($("<div>")
							.text("Email: " + data.email));

					var $authorityList = $("<ul>");
					data.authorities.forEach(function(authorityItem) {
						$authorityList.append($("<li>").text(
								authorityItem.authority));
					});
					var $authorities = $("<div>").text("Authorities:");
					$authorities.append($authorityList);

					$scope.userInfoBody.append($authorities);
					$scope.userInfo.show();
				}
			});
		}

		function showTokenInformation() {
			var jwtToken = getJwtToken();
			var decodedToken = jwt_decode(jwtToken);

			$scope.loggedInBody.append($("<h4>").text("Token"));
			$scope.loggedInBody.append($("<div>").text(jwtToken).css("word-break",
					"break-all"));
			$scope.loggedInBody.append($("<h4>").text("Token claims"));

			var $table = $("<table>").addClass("table table-striped");
			appendKeyValue($table, "sub", decodedToken.sub);
			appendKeyValue($table, "iat", decodedToken.iat);
			appendKeyValue($table, "exp", decodedToken.exp);

			$scope.loggedInBody.append($table);

			$scope.loggedIn.show();
		}

		function appendKeyValue($table, key, value) {
			var $row = $("<tr>").append($("<td>").text(key)).append(
					$("<td>").text(value));
			$table.append($row);
		}

		function showResponse(statusCode, message) {
			$scope.response.empty().text(
					"status code: " + statusCode
							+ "\n-------------------------\n" + message);
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

		

		$scope.loggedIn.click(function() {
			$scope.loggedIn.toggleClass("text-hidden").toggleClass("text-shown");
		});

	}

})();
(function() {
	'use strict';

	angular.module('app').controller('loginController', loginController);

	loginController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$window', '$cookies', '$stateParams', '$state', '$timeout' ];
	function loginController($location, $scope, $rootScope, $http, $window,
			$cookies, $stateParams, $state, $timeout) {

		var lc = this;

	    $scope.TOKEN_KEY = "jwtToken"

		$scope.goToState = function(state) {
			$state.go(state, {
				"id" : $scope.userId
			});
		}

        var getStatus = function(id){
        console.log("usao u status")
            $http({
                method: 'GET',
                url: "https://localhost:8096/user/status/" + id,
                headers: createAuthorizationTokenHeader()
            }).then(function successCallback(response) {
                var partsOfStr = response.data.split(',');
                $scope.status = partsOfStr[0];
                $scope.role = partsOfStr[1];
                 console.log($scope.status)
                  console.log($scope.role)
                  if($scope.status=="NEPOTVRDJEN" && $scope.role=="AGENT"){
                                  console.log("nepotvrdjen")
                                  $location.path("/uploadCert");
                              }
                  else if($scope.role=="ADMIN"){
                	  $location.path("/success/5")
                  }
                  else $state.go("core.home", {}, {reload:true})
            }, function errorCallback(response) {
                console.log(response.data)
                console.log(response)
            });
        }
		var init = function() {

			if (getJwtToken()) {
			 console.log($scope.status)
                              console.log($scope.role)
			var id = jwt_decode(getJwtToken()).jti;
            getStatus(id);

			}

		};

		init();
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
             $http({
                    method: 'POST',
                    url: "https://localhost:8096/auth",
                    data : JSON.stringify(loginData)
                }).then(function successCallback(response) {

                    setJwtToken(response.data.token);
                    var id = jwt_decode(getJwtToken()).jti;
                    getStatus(id);

                }, function errorCallback(response) {
                    $scope.message="Bad credentials."
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
		$("#loginForm").submit(function(event) {
			event.preventDefault();

			var $form = $(this);
			var formData = {
				username : $form.find('input[name="username"]').val(),
				password : $form.find('input[name="password"]').val()
			};

			doLogin(formData);
		});


		

	}

})();
(function () {
    'use strict';

    angular
		.module('app')
		.controller('allUsersController', allUsersController);

    allUsersController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window','$state', '$timeout'];
    function allUsersController($location, $scope, $rootScope,$http, $cookies, $window,$state, $timeout) {
    	var auc = this;
    	
    	$scope.TOKEN_KEY = "jwtToken"
    	
    		$scope.message = "";
    		$scope.allUsers = [];
    		$scope.loggedIn = false;    		
    		
    		var refresh = function(){
    			$state.go($state.current.name, {}, {reload: true})
    		}
		
    		var getRequests = function() {

    			$.ajax({
    				url : "https://localhost:8096/user",
    				type : "GET",/*
									 * contentType: "application/json;
									 * charset=utf-8", dataType: "json",
									 */
    				headers : createAuthorizationTokenHeader(),
    				success : function(data, textStatus, jqXHR) {
    					
    						$scope.allUsers = data;
    						$timeout(function(){ $scope.$apply(); }, 150);
        					$scope.message ="";
        					console.log($scope.allUsers.length)
    					
    				}
    				
    			});
    		}

    		
    		
    		var init = function() {

    			// INITIAL CALLS
    			// =============================================================
    			if (getJwtToken()) {
    				
    				$scope.loggedIn = true;
    			} else {
    			
    				$location.path("/login")
    				$scope.loggedIn= false;
    			}
    			
    			getRequests();

    		};

    		init();

    		
    		
    		// FUNCTIONS
    		// =============================================================

    		
    
    		$scope.block = function(id){
    			$.ajax({
    				url : "https://localhost:8096/user/block/" + id,
    				type : "GET",/*
									 * contentType: "application/json;
									 * charset=utf-8", dataType: "json",
									 */
    				headers : createAuthorizationTokenHeader(),
    				success : function(data, textStatus, jqXHR) {
    					getRequests();

    					}

    			});
    			
    			
    		}
    		
    		
 

    		function getJwtToken() {
    			return localStorage.getItem($scope.TOKEN_KEY);
    		}


    		function removeJwtToken() {
    			localStorage.removeItem($scope.TOKEN_KEY);
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

    		

    	}


})();
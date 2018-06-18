(function() {
	'use strict';

	angular.module('app').controller('coreController', coreController);

	coreController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$cookies', '$window', 'userService', '$state', 'DataTransfer'];
	function coreController($location, $scope, $rootScope, $http, $cookies,
			$window, userService, $state, DataTransfer) {
		
		$scope.searchResults = null;
		$scope.searchDTO = {};
		$scope.searchSmestaji = function(){
			
			$scope.searchDTO.types =$scope.typeList;
			$scope.searchDTO.services = $scope.serviceList;
			$scope.searchDTO.cats = $scope.catList;
			if($scope.showAdvancedSearch == false){
				userService.getAllSmestajiSimple($scope.searchDTO, 
					function(info){
						$scope.searchResults = info.data;
						DataTransfer.setSmestajDetails(info.data);
						$state.go('core.searchResults');
					},
					function(){
						alert("Error loading search results!!!");
					}	
				)
			}
			else{
				userService.getAllSmestajiAdvanced($scope.searchDTO, 
					function(info){
						$scope.searchResults = info.data;
						DataTransfer.setSmestajDetails(info.data);
						$state.go('core.searchResults');
					},
					function(){
						alert("Error loading search results!!!");
					}	
					)
			}
			
		}
		
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
			if($scope.showAdvancedSearch == false)
			return "Advanced search";
			return "Cancel";
		}
		$scope.profile = function(){
            $location.path('/profile');
		}
		
		$scope.toggleAdvancedSearch = function(){
			$scope.showAdvancedSearch = !$scope.showAdvancedSearch;
		}
		
		
		
		
		var cc = this;
		$scope.TOKEN_KEY = "jwtToken";
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
                $scope.profileShow = true;
				$scope.username = " " +jwt_decode(getJwtToken()).sub;
		
			} else{
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
			$scope.profileShow = false;
			$scope.username="";
			$window.location="https://localhost:8096/#!/home";
		}

	}

})();
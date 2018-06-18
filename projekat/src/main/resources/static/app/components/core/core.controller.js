(function() {
	'use strict';

	angular.module('app').controller('coreController', coreController);

	coreController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$cookies', '$window', 'userService', '$state', 'DataTransfer'];
	function coreController($location, $scope, $rootScope, $http, $cookies,
			$window, userService, $state, DataTransfer) {
		
		$scope.getNumberArray = function(num) {
		    return new Array(num);   
		}
		
		$scope.smestajToShowPictureIndex=0;
		$scope.pitcuresForward = function(){
			$scope.smestajToShowPictureIndex = ($scope.smestajToShowPictureIndex + 1)%($scope.smestajToShow.sobe.length-1);
			//console.log($scope.smestajToShowPictureIndex);
		}
		
		$scope.goHomeApp = function(){
			$location.path('/home');
			$scope.panelToShow = -1;
			/*userService.mileTest(
					function(info){
						alert("jeeej");
					},
					function(){
						
					}
			)*/
		}

        function getJwtToken() {
            return localStorage.getItem($scope.TOKEN_KEY);
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



        $scope.reserve =function(idSobe, idSmestaja, pocetnoVreme, krajnjeVreme){


            var temp=jwt_decode(getJwtToken()).jti; // Zameniti sa cookies.get('user') ili sta god kada bude login

			console.log(temp);
			if(temp == null){
				alert("Please login or register to make a reservation");
                $location.path('/login');
			}

            $scope.userId = Number(temp);
			if( $scope.userId == null ){
                alert("Please login or register to make a reservation");
                $location.path('/login');
			}
            console.log($scope.userId);

			var b = false;

			if(idSmestaja)
				if(idSobe)
					if(pocetnoVreme)
						if(krajnjeVreme)
							if(idSobe>0)
								if(idSmestaja>0)
									b=true;


			if(b){

				console.log("USAO U DEO SA DTO");

                var dto = {
                    "idSobe" : idSobe,
                    "idSmestaja" : idSmestaja,
                    "idKorisnika" :   $scope.userId,
                    "pocetnoVreme" : pocetnoVreme,
                    "krajnjeVreme": krajnjeVreme
                };

                console.log(dto);

                $http({
                    method: 'POST',
                    url: 'https://localhost:8096/reservation/make/',
                    headers : createAuthorizationTokenHeader(),
                    data: dto
                }).then(function successCallback(response) {
                   console.log("USPEO");
                   alert("Reservation made successfully !");
                   $location.path('/reservations');

                }, function errorCallback(response) {
                	console.log("FAILOVAO");
                    alert("An error occurred the reservation was not made");

                });

			}


		}
		
		$scope.swapPanel = function(p){
			$scope.panelToShow = 0;
		}
		
		$scope.searchResults = null;
		$scope.searchDTO = {
				"destination":"MALDIVI VIP",
				"from":new Date(1970, 1,1),
				"to":new Date(1970, 12,12),
				"howManyPeople":5
				
		};
		$scope.panelToShow = -1;
		$scope.smestajToShow = null;
		
		$scope.isLast = function(index, list){
			if(index==list.length-1) return false;return true;
		}
		
		$scope.showSemstaj = function(smestaj){
			
			
			userService.getAllReviewsBySmestaj(smestaj.id,
					function(info){
						$scope.reviewsToShow = info.data;
						$scope.smestajToShow = smestaj;
						$scope.panelToShow = 1;
					},
					function(info){
						
					}
			)
		}
		
		$scope.searchSmestaji = function(){
			
			$scope.searchDTO.types =$scope.typeList;
			$scope.searchDTO.services = $scope.serviceList;
			$scope.searchDTO.cats = $scope.catList;
			if($scope.showAdvancedSearch == false){
				
				userService.getAllSmestajiSimple($scope.searchDTO, 
					function(info){
						$scope.searchResults = info.data;
						$scope.panelToShow = 0;
						//DataTransfer.setSmestajDetails(info.data);
						//$state.go('core.searchResults');
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
					$scope.panelToShow = 0;
						//DataTransfer.setSmestajDetails(info.data);
						//$state.go('core.searchResults');
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
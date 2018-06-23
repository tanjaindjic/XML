(function() {
	'use strict';

	angular.module('app').controller('coreController', coreController);

	coreController.$inject = [ '$location', '$scope', '$rootScope', '$http',
			'$cookies', '$window', 'userService', '$state', 'DataTransfer'];
	function coreController($location, $scope, $rootScope, $http, $cookies,
			$window, userService, $state, DataTransfer) {
		
		$scope.priceRange = function(sobe){
			
		}
		
		$scope.setOrderBy = function(s){
			if(s!='delegate')
			{
				$scope.setedOrderBy = s;
				$scope.setedOrderByBool = !$scope.setedOrderByBool;
			}else{
				$scope.setedOrderBy = $scope.sortFn;
				$scope.setedOrderByBool = !$scope.setedOrderByBool;
			}
		}
		
		$scope.setedOrderBy = '';
		$scope.setedOrderByBool = true;
		
		$scope.sortFn = function(kategorija){
			//console.log(kategorija);
			if(kategorija != undefined){
				console.log(kategorija.kategorija.kategorija.charAt(0));
				return parseInt(kategorija.kategorija.kategorija.charAt(0));
			}else{
				//console.log("UNDEFINED");
				return 0;
			}
		}
		
		$scope.getZvezdiceNumber = function(kategorija){
			//console.log("USO SAM!!!"+kategorija);
			if(kategorija != undefined){
				//console.log(kategorija.kategorija.charAt(0));
				return parseInt(kategorija.kategorija.charAt(0));
			}else{
				return 0;
			}
		}
		
		$scope.getNumberArray = function(num) {
		    return new Array(num);   
		}
		
		$scope.getNumberArrayFloat = function(num) {
			if(num!=null){
				var intvalue = Math.floor(num);
			    return new Array(intvalue);   
			}
		}
		
		$scope.smestajToShowPictureIndex=0;
		$scope.pitcuresForward = function(){
			$scope.smestajToShowPictureIndex = ($scope.smestajToShowPictureIndex + 1)%($scope.smestajToShow.slike.length);
			console.log($scope.smestajToShowPictureIndex);
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

            var temp=null;
            var noErr = true;

            try {
            	temp=jwt_decode(getJwtToken()).jti; // Tanjino
            }
            catch(err) {
            	noErr=false;
                alert("Please login or register to make a reservation");
                $location.path('/login');
            }



            $scope.userId = Number(temp);

            console.log($scope.userId);

			var b = false;

			if(idSmestaja)
				if(idSobe)
					if(pocetnoVreme)
						if(krajnjeVreme)
							if(idSobe>0)
								if(idSmestaja>0)
									b=true;


			if(b && noErr){

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
                   console.log("USPEO");$scope.swapPanel(-1);
                   alert("Reservation made successfully !");
                   $location.path('/reservations');

                }, function errorCallback(response) {
                	console.log("FAILOVAO");
                    alert("An error occurred the reservation was not made");

                });

			}


		}
		
		$scope.swapPanel = function(p){
			$scope.panelToShow = p;
		}
		
		$scope.searchResults = null;
		$scope.searchDTO = {
				"destination":"",
				"grad":"",
				"drzava":"",
				"adressa":"",
				"from":new Date(2018, 3,3),
				"to":new Date(2018, 5,5),
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
						$location.path('/home');
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
						$location.path('/home');
						//DataTransfer.setSmestajDetails(info.data);
						//$state.go('core.searchResults');
					},
					function(){
						alert("Error loading search results!!!");
					}	
					)
			}
			
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
			return "Advanced";
			return "Cancel";
		}
		$scope.profile = function(){
			$scope.panelToShow = -1;
            $location.path('/profile');
		}
		
		$scope.toggleAdvancedSearch = function(){
			$scope.showAdvancedSearch = !$scope.showAdvancedSearch;
		}
		
		
		
		
		var cc = this;
		$scope.TOKEN_KEY = "jwtToken";
		$scope.profile = function(){
			$scope.panelToShow = -1;
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
			$scope.panelToShow = -1;
			$location.path('/login');
		}
		
		$scope.register = function() {
			$scope.swapPanel(-1);
			$location.path('/register');
		}
		
		$scope.logout = function(){
			$scope.panelToShow = -1;
			removeJwtToken();
			$scope.profileShow = false;
			$scope.username="";
			$window.location="https://localhost:8096/#!/home";
		}
		
		$scope.typeList = [];
		
		$scope.addToTypeList = function(type){
			if(!ainTypes($scope.typeList, type)){
				alert("Iteam already in list.")
			}else{
				$scope.typeList.push(type);
			}
			
			
		}
		
		$scope.serviceList = [];
		
		$scope.addToServiceList = function(service){
			if(!ainServices($scope.serviceList, service)){
				alert("Iteam already in list.")
			}else{
				$scope.serviceList.push(service);
			}
			
		}
		
		$scope.catList = [];
		
		$scope.addToCatList = function(cat){
			if(!ainCats($scope.catList, cat)){
				alert("Iteam already in list.")
			}else{
				$scope.catList.push(cat);
			}
			
		}
		
		$scope.deleteTypeIteam = function(t){
			$scope.typeList.splice(ainAtTypes($scope.typeList, t),1);
		}
		
		$scope.deleteCatIteam = function(t){
			$scope.catList.splice(ainAtCats($scope.catList, t),1);
		}
		
		$scope.deleteServiceIteam = function(t){
			$scope.serviceList.splice(ainAtServices($scope.serviceList, t),1);
		}
		
		function ainCats(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].kategorija==itm.kategorija){
					return false;
				}
			}
			return true;
		}
		
		function ainAtCats(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].kategorija==itm.kategorija){
					return i;
				}
			}
			return -1;
		}
		
		function ainTypes(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].tip==itm.tip){
					return false;
				}
			}
			return true;
		}
		
		function ainAtTypes(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].tip==itm.tip){
					return i;
				}
			}
			return -1;
		}
		
		function ainServices(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].opcija==itm.opcija){
					return false;
				}
			}
			return true;
		}
		
		function ainAtServices(arr, itm){
			for(var i=0;i<arr.length;i++){
				if(arr[i].opcija==itm.opcija){
					return i;
				}
			}
			return -1;
		}

	}

})();
(function () {
    'use strict';

    angular
		.module('app')
		.controller('editController', editController);

    editController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window', '$state'];
    function editController($location, $scope, $rootScope, $http, $cookies, $window, $state) {
    	var ec = this;
    	function getJwtToken() {
            return localStorage.getItem($scope.TOKEN_KEY);
        }
    	$scope.goToState = function(state) {
			$state.go(state, {
				"id" : $scope.userId
			});
		}
    	var init = function(){
    		if (getJwtToken()) {
                $scope.profileShow = true;
			} else{
                $location.path('/login');		
			}
    		var editId = $cookies.get('edit');
    		$scope.sveSobe = [];
    		$scope.zaRent = {};
    		$scope.messageRent = "";
    		$scope.rent = {};
    		$http({
                method: 'GET',
                url: '/api/smestaj/'+editId
              }).then(function successCallback(response) {
            	  if(response.data!=undefined || response.data!=""){
            		  $scope.smestaj = response.data;
            		  for(var i=0; i<response.data.sobe.length; i++){
            			  $http({
            	                method: 'GET',
            	                url: '/api/sobe/'+response.data.sobe[i].id
            	              }).then(function successCallback(response) {
            	            	  $scope.sveSobe.push(response.data);
            	              });
            	      }
            	  }            	  
              });
    		$scope.allRooms = true;
    		$scope.caths=[];
    		$http({
                method: 'GET',
                url: '/api/kategorija'
              }).then(function successCallback(response) {
            	  if(response.data!=undefined){
            		  var tipovi = response.data
            		  for(var i=0; i<tipovi.length; i++){
            			  $scope.caths.push(tipovi[i]);
            		  }
            	  }
            	  
              });
    		
    		$scope.dodatne = [];
			$http({
                method: 'GET',
                url: '/api/dodatneUsluge'
              }).then(function successCallback(response) {
            	  if(response.data!=undefined){
            		  var dod = response.data;
            		  for(var i=0; i<dod.length; i++){
            			  $scope.dodatne.push(dod[i]);
            		  }
            	  }
            	  
              });
			$scope.dodatneUsluge = [];
			$scope.soba = {};
        };
        init();
        
        

		ec.addDodatne = function(opcija){
			if($scope.dodatneUsluge.indexOf(opcija)!=-1){
				var num = $scope.dodatneUsluge.indexOf(opcija);
				$scope.dodatneUsluge.splice(num, 1)
			}
			else{
				var num = $scope.dodatneUsluge.indexOf(opcija);
				$scope.dodatneUsluge.push(opcija);
			}
		}
		
        ec.addRoom = function(){
        	if($scope.soba.brojLezaja<=0 || $scope.soba.brojLezaja==null ||
        			$scope.soba.cena<=0 || $scope.soba.cena==null){
        		$scope.showMessage=true;
        		$scope.message="all data necessery"
        			return;
        	}
			var data = $scope.soba;
			data.opcija = $scope.dodatneUsluge;
			data.smestaj = $scope.smestaj;
			$http({
                method: 'POST',
                url: '/api/sobe',
                data: data,
                headers : createAuthorizationTokenHeader()
              }).then(function successCallback(response) {
            	  if(response.data!=""){
            		$scope.soba={};
            		$scope.smestaj.sobe.push(response.data);
            	  }
            	  
              });
		}
        ec.edit = function(s){
        	$scope.allRooms = false;
        	$scope.zaRent = s;
        	for(var i=0; i<s.iznajmljivanja.length; i++){
	        	$scope.zaRent.iznajmljivanja[i].datumOd = sklopiDatume(s.iznajmljivanja[i].datumOd);
	        	$scope.zaRent.iznajmljivanja[i].datumDo = sklopiDatume(s.iznajmljivanja[i].datumDo);
        	}
        }
        var sklopiDatume = function(dat){    		
    		var currentTime = new Date(parseInt(dat));
    		var month = currentTime.getMonth() + 1;
    		var day = currentTime.getDate();
    		var year = currentTime.getFullYear();
    		var date = day + "/" + month + "/" + year;
    		return date;
        }
        ec.goBack=function(){
        	$scope.allRooms = true;
        	$scope.zaRent = {};
    		$scope.rent = {};
        }
        ec.addRent = function(){
        	var data = $scope.rent;
        	if(data.datumOd==null || data.datumDo==null || data.cena==null || data.cena<=0 ){
        		$scope.messageRent = "add all data to proceed";
        			return;
        	} 
        	
        	$http({
                method: 'POST',
                url: '/api/sobe/'+$scope.zaRent.id,
                data: data,
                headers : createAuthorizationTokenHeader()
              }).then(function successCallback(response) {
            	  if(response.data!=""){
            		  var izn = response.data;
            		  izn.datumOd = sklopiDatume(response.data.datumOd);
            		  izn.datumDo = sklopiDatume(response.data.datumDo);
            		  $scope.zaRent.iznajmljivanje.push(izn);
            		  $scope.messageRent = "";
            	  }
            	  
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
    }

})();
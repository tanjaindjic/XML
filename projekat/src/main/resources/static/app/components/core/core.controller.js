(function () {
    'use strict';

    angular
		.module('app')
		.controller('coreController', coreController);

    coreController.$inject = ['$location', '$scope', '$rootScope','$http', '$cookies', '$window'];
    function coreController($location, $scope, $rootScope,$http, $cookies, $window) {
    	var cc = this;
  
    	var init = function(){
    		

        };
        init();

        
    }

})();
(function () {
    'use strict';

    angular
		.module('app')
        .config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function($stateProvider, $urlRouterProvider, $locationProvider) {

      $urlRouterProvider.otherwise("/home");

      $stateProvider
		 .state('core', {
			 url: '/',
			 templateUrl: 'index.html',
			 controller: 'coreController',
			 controllerAs: 'cc'
		 })
		.state('core.profile', {
			url: 'profile',
			templateUrl: 'profile/profile.html',
			controller: 'profileController',
			controllerAs: 'pc'
		});
     /* $locationProvider.html5Mode({
    	  enabled: true,
    	  requireBase: false
    	});*/
    }]);


})();

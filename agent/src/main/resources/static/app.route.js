(function () {
    'use strict';

    angular
		.module('app')
        .config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function($stateProvider, $urlRouterProvider, $locationProvider) {

      $urlRouterProvider.otherwise("/home");

      $stateProvider
		 .state('core', {
			 url: '/',
			 templateUrl: 'app/components/core/core.html',
			 controller: 'coreController',
			 controllerAs: 'cc'
		 })
		.state('core.home', {
			 url: 'home',
			 templateUrl: 'app/components/home/home.html',
			 controller: 'homeController',
			 controllerAs: 'hc'
		 })
		 .state('core.login', {
			 url: 'login',
			 templateUrl: 'app/components/login/login.html',
			 controller: 'loginController',
			 controllerAs: 'lc'
		 })		 
		.state('core.agentAccomodation', {
			url: 'agentAccomodation',
			templateUrl: 'app/components/agentAccomodation/agentAccomodation.html',
			controller: 'agentAccomodationController',
			controllerAs: 'aac'
		})		 
		.state('core.edit', {
			url: 'edit',
			templateUrl: 'app/components/edit/edit.html',
			controller: 'editController',
			controllerAs: 'ec'
		})		 
		.state('core.agentConfirmStay', {
			url: 'agentConfirmStay',
			templateUrl: 'app/components/agentConfirmStay/agentConfirmStay.html',
			controller: 'agentConfirmStayController',
			controllerAs: 'acsc'
		})
		.state('core.agentReservations', {
			url: 'agentReservations',
			templateUrl: 'app/components/agentReservations/agentReservations.html',
			controller: 'agentReservationsController',
			controllerAs: 'arc'
		});
     /* $locationProvider.html5Mode({
    	  enabled: true,
    	  requireBase: false
    	});*/
    }]);


})();

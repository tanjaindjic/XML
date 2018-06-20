﻿(function () {
    'use strict';

    angular
		.module('app')
        .config(['$stateProvider', '$urlRouterProvider', '$locationProvider', function($stateProvider, $urlRouterProvider, $locationProvider) {

      $urlRouterProvider.otherwise("/login");

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
		});
     /* $locationProvider.html5Mode({
    	  enabled: true,
    	  requireBase: false
    	});*/
    }]);


})();
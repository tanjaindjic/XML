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
		 .state('core.searchResults', {
			 url: 'searchRes',
			 templateUrl: 'app/components/searchResults/searchResults.html',
			 controller: 'searchResultsController',
			 controllerAs: 'searchResultsController'
		 })
		 
		 .state('core.resetPass', {
			 url: 'resetPass/{token}',
			 templateUrl: 'app/components/resetPass/resetPass.html',
			 controller: 'resetPassController',
			 controllerAs: 'resetPassController'
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
		 .state('core.succ', {
			 url: 'success/{id}',
			 templateUrl: 'app/components/succ/succ.html',
			 controller: 'succController',
			 controllerAs: 'succ'
		 })
		 .state('core.uploadCert', {
           				url: 'uploadCert',
           				templateUrl: 'app/components/uploadCert/uploadCert.html',
           				controller: 'uploadCertController',
           				controllerAs: 'uc',
                        params: {username:null}
           			})
		 .state('core.register', {
			 url: 'register',
			 templateUrl: 'app/components/register/register.html',
			 controller: 'registerController',
			 controllerAs: 'regc'
		 })
		  .state('core.inbox', {
          url: 'inbox',
          templateUrl: 'app/components/inbox/inbox.html',
          controller: 'inboxController',
          controllerAs: 'ic'
         })
          .state('core.reservations', {
              url: 'reservations',
              templateUrl: 'app/components/reservations/reservations.html',
              controller: 'reservationsController',
              controllerAs: 'rc'
          })
          .state('core.chat', {
              url: 'chat',
              templateUrl: 'app/components/chat/chat.html',
              controller: 'chatController',
              controllerAs: 'cc',
              params: {id :null, id2:null, username:null}

          })
          .state('core.editProfile', {
              url: 'editProfile',
              templateUrl: 'app/components/editProfile/editProfile.html',
              controller: 'editProfileController',
              controllerAs: 'epc'
          })
		.state('core.profile', {
			url: 'profile',
			templateUrl: 'app/components/profile/profile.html',
			controller: 'profileController',
			controllerAs: 'pc'
		});
     /* $locationProvider.html5Mode({
    	  enabled: true,
    	  requireBase: false
    	});*/
    }]);


})();

(function () {
    'use strict';

    angular
        .module('app')
        .controller('profileController', profileController);

    profileController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$stateParams', '$state', '$timeout'];
    function profileController($location, $scope, $rootScope, $http, $window, $cookies, $stateParams, $state, $timeout) {

        var pc = this;


        var init = function (){
        };

        init();


    }

})();
(function () {
    'use strict';

    angular
        .module('app')
        .controller('inboxController', inboxController);

    inboxController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$stateParams', '$state', '$timeout'];
    function inboxController($location, $scope, $rootScope, $http, $window, $cookies, $stateParams, $state, $timeout) {

        var cc = this;


        $scope.goToState=function (state) {
            $state.go(state , {"id" : $scope.userId} );
        }


        $scope.userId={};
        $scope.user = {};

        var init = function (){
            $scope.userId=2; // Zameniti sa cookies.get('user') ili sta god kada bude login

            //$scope.userId= $cookies.get('id');
            $http({
                method: 'GET',
                url: 'http://localhost:8096/user/'+$scope.userId,
            }).then(function successCallback(response) {
                if(response.data ==null)
                    $location.path('/home');

                $scope.user = response.data;

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


        };

        init();


    }

})();
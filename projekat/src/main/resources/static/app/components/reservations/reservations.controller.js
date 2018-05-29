(function () {
    'use strict';

    angular
        .module('app')
        .controller('reservationsController', reservationsController);

    reservationsController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$stateParams', '$state', '$timeout'];
    function reservationsController($location, $scope, $rootScope, $http, $window, $cookies, $stateParams, $state, $timeout) {

        var rc = this;


        $scope.goToState=function (state) {
            $state.go(state , {"id" : $scope.userId} );
        }


        $scope.userId={};
        $scope.rezervacije = [];
        $scope.username = "";

        var init = function (){
            $scope.userId=2; // Zameniti sa cookies.get('user') ili sta god kada bude login
            $scope.username="test";
            //$scope.userId= $cookies.get('id');
            //$scope.username= $cookies.get('username');
            $http({
                method: 'GET',
                url: 'http://localhost:8096/reservation/'+$scope.userId,
            }).then(function successCallback(response) {


                $scope.rezervacije = response.data;

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


        };

        init();

        $scope.sendMessage=function(id){
            $state.go('core.chat' , {"id" : $scope.userId, "id2" : id, "username" : $scope.username} );
        }

        $scope.komentarisi = function (tekst, id) {

            var dto = {
                "tekst" : tekst,
                "rezervacijaId" : id
            };

            $http({
                method: 'POST',
                url: 'http://localhost:8096/comments/add',
                data: dto,
            }).then(function successCallback(response) {
                alert("Comment submitted and awaiting approval ");

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


        }


    }

})();
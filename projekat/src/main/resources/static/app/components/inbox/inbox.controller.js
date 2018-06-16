(function () {
    'use strict';

    angular
        .module('app')
        .controller('inboxController', inboxController);

    inboxController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$stateParams', '$state', '$timeout'];
    function inboxController($location, $scope, $rootScope, $http, $window, $cookies, $stateParams, $state, $timeout) {

        var ic = this;


        $scope.goToState=function (state) {
            $state.go(state , {"id" : $scope.userId} );
        }




        $scope.userId={};
        $scope.username = {};
        $scope.korisnici =[];

        $scope.selectChat=function(id){
            $state.go('core.chat' , {"id" : $scope.userId, "id2" : id, "username" : $scope.username} );
        }

        var init = function (){
            var temp=jwt_decode(getJwtToken()).jti; // Zameniti sa cookies.get('user') ili sta god kada bude login


            $scope.userId = Number(temp);
            $scope.username=jwt_decode(getJwtToken()).sub;
            //$scope.userId= $cookies.get('id');
            //$scope.username= $cookies.get('username');


            $http({
                method: 'GET',
                url: 'https://localhost:8096/messages/inbox/'+$scope.userId,
                headers : createAuthorizationTokenHeader()
            }).then(function successCallback(response) {

                $scope.korisnici = response.data;

                console.log($scope.korisnici);

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


        };

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


        init();


    }

})();
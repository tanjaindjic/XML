(function () {
    'use strict';

    angular
        .module('app')
        .controller('editProfileController', editProfileController);

    editProfileController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$stateParams', '$state', '$timeout'];
    function editProfileController($location, $scope, $rootScope, $http, $window, $cookies, $stateParams, $state, $timeout) {

        var epc = this;


        $scope.goToState=function (state) {
            $state.go(state , {"id" : $scope.userId} );
        }


        $scope.userId={};
        $scope.user = {};
        $scope.rPassword="";

        var init = function (){
            var temp=jwt_decode(getJwtToken()).jti; // Zameniti sa cookies.get('user') ili sta god kada bude login


            $scope.userId = Number(temp);
            $http({
                method: 'GET',
                url: 'https://localhost:8096/user/'+$scope.userId,
                headers : createAuthorizationTokenHeader()
            }).then(function successCallback(response) {
                if(response.data ==null)
                    $location.path('/home');

                $scope.user = response.data;

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

        $scope.saveEdit=function(username,firstName,lastName,email,password,rpassword){

            var ispravno = false;

            if(username)
                if(firstName)
                    if(lastName)
                        if(email)
                            if(password)
                                if(rpassword)
                                    if(username !="")
                                        if(firstName !="")
                                            if(lastName !="")
                                                if(email !="")
                                                    if(password !="")
                                                        if(rpassword !="")
                                                            if(password == rpassword)
                                                                ispravno=true;


            if(ispravno ) {
                var dto = {
                    "username": username,
                    "firstName": firstName,
                    "lastName": lastName,
                    "password": password,
                    "email":email,
                    "pib": "",
                    "role": "USER"
                };


                $http({
                    method: 'PUT',
                    url: 'https://localhost:8096/user',
                    data: dto,
                    headers : createAuthorizationTokenHeader()
                }).then(function successCallback(response) {
                    alert("Edit saved");
                    $location.path('/profile');

                }, function errorCallback(response) {
                    alert("Error occured check connection");
                    $location.path('/home');
                });
            }else{
                alert("All fields must be entered, please repeat your password if you want save the edit");
            }


        }


    }

})();
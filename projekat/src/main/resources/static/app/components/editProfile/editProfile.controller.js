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
                    "pib": "",
                    "role": "USER"
                };


                $http({
                    method: 'PUT',
                    url: 'http://localhost:8096/user',
                    data: dto
                }).then(function successCallback(response) {
                    console.log("PROMENIO JE USERA");

                }, function errorCallback(response) {
                    alert("Error occured check connection");
                    $location.path('/home');
                });
            }


        }


    }

})();
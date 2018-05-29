(function () {
    'use strict';

    angular
        .module('app')
        .controller('chatController', chatController);

    chatController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$stateParams', '$state', '$timeout'];
    function chatController($location, $scope, $rootScope, $http, $window, $cookies, $stateParams, $state, $timeout) {

        var cc = this;



        $scope.goToState=function (state) {
            $state.go(state , {"id" : $scope.userId} );
        }


        $scope.userId={};
        $scope.username ={}
        $scope.messages = [];
        $scope.chatWith={};

        var init = function (){
            //PROVERA DA LI JE USER ID == cookie USER ID

            $scope.userId=$stateParams.id;
            $scope.chatWith = $stateParams.id2;
            $scope.username= $stateParams.username;

            if($scope.userId != null){
                $window.localStorage.setItem("id",  $scope.userId);
                $window.localStorage.setItem("id2",  $scope.chatWith);
                $window.localStorage.setItem("username",  $scope.username);
            }else{

                //PROVERA DA LI JE USER ID == cookie USER ID

                $scope.userId= $window.localStorage.getItem("id");
                $scope.chatWith = $window.localStorage.getItem("id2");
                $scope.username = $window.localStorage.getItem("username");
            }

            //$scope.userId= $cookies.get('id');
            $http({
                method: 'GET',
                url: 'http://localhost:8096/messages/inbox/' +$scope.userId+ '/chat/' +$scope.chatWith,
            }).then(function successCallback(response) {

                $scope.messages = response.data;
                console.log($scope.messages);

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


        };

        $scope.sendMessage = function(text){

            var dto={
                "text": text,
                "posiljalacId":$scope.userId,
                "primalacId": $scope.chatWith
            };



            console.log(dto);

            $http({
                method: 'POST',
                url: 'http://localhost:8096/messages/send',
                data:dto,
            }).then(function successCallback(response) {
                $scope.addMessage(text);

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


        }


        $scope.addMessage = function(text){
            var d = new Date();

            console.log($scope.username);
            var k1 = {
                "id":$scope.userId,
                "username" : $scope.username,
                "firstName": "",
                "lastName" : "",
                "aktivan" : true
            }

            var k2 = {
                "id":$scope.chatWith,
                "username" : "",
                "firstName": "",
                "lastName" : "",
                "aktivan" : true
            }


            var message = {
                "id" : 0,
                "tekst" : text,
                "vremeKreiranja":d,
                "primalac" : k2,
                "posiljalac" :k1
            }

             $scope.messages.push(message);

        }

        init();


    }

})();
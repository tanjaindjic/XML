(function () {
    'use strict';

    angular
        .module('app')

        .directive('ngConfirmClick', [
            function(){
                return {
                    link: function (scope, element, attr) {
                        var msg = attr.ngConfirmClick || "Are you sure?";
                        var clickAction = attr.confirmedClick;
                        element.bind('click',function (event) {
                            if ( window.confirm(msg) ) {
                                scope.$eval(clickAction)
                            }
                        });
                    }
                };
            }])
        .controller('reservationsController', reservationsController);

    reservationsController.$inject = ['$location', '$scope', '$rootScope','$http', '$window', '$cookies', '$stateParams', '$state', '$timeout'];
    function reservationsController($location, $scope, $rootScope, $http, $window, $cookies, $stateParams, $state, $timeout) {

        var rc = this;


        $scope.goToState=function (state) {
            $state.go(state , {"id" : $scope.userId} );
        }


        $scope.cancelReservation = function(id){

            var dto = {
                "status" : "CANCELED",
                "id" : id
            };

            console.log(dto);

            $http({
                method: 'PUT',
                url: 'https://localhost:8096/reservation/update',
                data: dto,
                headers : createAuthorizationTokenHeader()
            }).then(function successCallback(response) {
                alert("The reservation has been canceled ");

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });



        }

        $scope.goToPlace = function(id){ // Dodati ovo kada grba zavrsi

        }

        $scope.prosaoDatum = function(date, status){

            var temp = new Date(date);
            var sada = new Date();
            var b = false;

            if(temp<sada)
                b=true;

            if(status== "CANCELED" || status=="ARRIVED" || status=="REJECTED")
                b=false;

            return b;

        }

        $scope.userId={};
        $scope.rezervacije = [];
        $scope.username = "";

        var init = function (){
            var temp=jwt_decode(getJwtToken()).jti; // Zameniti sa cookies.get('user') ili sta god kada bude login


            $scope.userId = Number(temp);
            $scope.username=jwt_decode(getJwtToken()).sub;
            //$scope.userId= $cookies.get('id');
            //$scope.username= $cookies.get('username');


            $http({
                method: 'GET',
                url: 'https://localhost:8096/reservation/'+$scope.userId,
                headers : createAuthorizationTokenHeader()
            }).then(function successCallback(response) {
                //toDateString() datumDo
                $scope.temp=response.data;

                console.log($scope.temp);

                for(var i =0; i<$scope.temp.length; i++){

                    $scope.temp[i].datumOdLepi = $scope.format($scope.temp[i].datumOd);
                    $scope.temp[i].datumDoLepi = $scope.format($scope.temp[i].datumDo);
                }

                $scope.rezervacije = $scope.temp;

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


        };

        $scope.format = function(date) {

                var temp = new Date(date);
               // temp.toLocaleString();


                return temp.toLocaleString();;
            }


        init();

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

        $scope.sendMessage=function(id){
            $state.go('core.chat' , {"id" : $scope.userId, "id2" : id, "username" : $scope.username} );
        }

        $scope.review = function (tekst, id, ocena, smestajId, rezervacija) {

            var dto = {
                "userId" : $scope.userId,
                "rezervacijaId" : id,
                "comment" : tekst,
                "ocena" : ocena,
                "smestajId": smestajId,
                "userName" : $scope.username
            };

            console.log(dto);


            $http({
                method: 'POST',
                url: 'https://us-central1-xmlcoment.cloudfunctions.net/sqlInsert',
                headers: {
                    "Content-Type": "application/json"
                },
                data: dto
            }).then(function successCallback(response) {


            }, function errorCallback(response) {

            });

            $http({
                method: 'POST',
                url: 'https://localhost:8096/reservation/comment/'+id+'/'+ocena,
                headers : createAuthorizationTokenHeader()
            }).then(function successCallback(response) {
                alert("Comment submitted and awaiting approval ");

            }, function errorCallback(response) {
                alert("Error occured check connection");
                $location.path('/home');
            });


            rezervacija.ocena=ocena;
            rezervacija.ocenio=true;




        }


    }

})();
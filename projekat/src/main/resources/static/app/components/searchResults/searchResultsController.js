angular.module('app')
.controller('searchResultsController', function ($rootScope, $scope, $state, $http, DataTransfer) {
	$scope.toShow = DataTransfer.getSmestajDetails();
	console.log($scope.toShow);
	
	
	$scope.userId={};
    $scope.user = {};

    var init = function (){
        var temp=jwt_decode(getJwtToken()).jti; // Zameniti sa cookies.get('user') ili sta god kada bude login


        $scope.userId = Number(temp);
        //$scope.userId= $cookies.get('id');
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
});
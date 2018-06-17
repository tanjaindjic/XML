angular.module('app')
.controller('searchResultsController', function ($rootScope, $scope, $state, DataTransfer) {
	$scope.toShow = DataTransfer.getSmestajDetails();
	console.log($scope.toShow);
});
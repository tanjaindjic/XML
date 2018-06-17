angular.module('app')
.service('userService', function ($http) {
    return {
    	getAllTypes: function(onSuccess, onError) {
	        var req = {
	                method: 'GET',
	                url: '/api/tipService',
	            };
	            $http(req).then(onSuccess, onError);
        },
        getAllAditional: function(onSuccess, onError) {
	        var req = {
	                method: 'GET',
	                url: '/api/dodatneUsluge',
	            };
	            $http(req).then(onSuccess, onError);
        },
        getAllKategorija: function(onSuccess, onError) {
	        var req = {
	                method: 'GET',
	                url: '/api/kategorija',
	            };
	            $http(req).then(onSuccess, onError);
        }
    }
});
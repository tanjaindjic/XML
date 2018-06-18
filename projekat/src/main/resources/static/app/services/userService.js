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
        },
        getAllSmestajiSimple: function(dto, onSuccess, onError) {
	        var req = {
	                method: 'POST',
	                url:  '/api/smestaj/simplesearch',
	                data: dto
	            };
	            $http(req).then(onSuccess, onError);
        },
        getAllSmestajiAdvanced: function(dto, onSuccess, onError) {
	        var req = {
	                method: 'POST',
	                url: '/api/smestaj/advancedsearch',
	                data: dto
	            };
	            $http(req).then(onSuccess, onError);
        },
        getAllReviewsBySmestaj: function(id, onSuccess, onError){
        	var req = {
	                method: 'GET',
	                url: 'https://us-central1-xmlcoment.cloudfunctions.net/getReviewsBySmestaj?smestajId='+id
	            };
	            $http(req).then(onSuccess, onError);
        }
    }
});
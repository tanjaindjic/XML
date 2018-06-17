angular.module('app').factory('DataTransfer', function () {

    var data = {};

    return {
        getSmestajDetails: function () {
            return data;
        },
        setSmestajDetails: function (Details) {
            data = Details;
        }
    };
});
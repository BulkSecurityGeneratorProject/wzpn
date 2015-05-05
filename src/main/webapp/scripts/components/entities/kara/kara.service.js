'use strict';

angular.module('wzpnApp')
    .factory('Kara', function ($resource) {
        return $resource('api/karas/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dataOtrzymania = new Date(data.dataOtrzymania);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

'use strict';

angular.module('wzpnApp')
    .factory('Usluga', function ($resource) {
        return $resource('api/uslugas/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

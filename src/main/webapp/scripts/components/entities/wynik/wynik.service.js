'use strict';

angular.module('wzpnApp')
    .factory('Wynik', function ($resource) {
        return $resource('api/wyniks/:id', {}, {
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

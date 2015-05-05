'use strict';

angular.module('wzpnApp')
    .factory('ObiektSportowy', function ($resource) {
        return $resource('api/obiektSportowys/:id', {}, {
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

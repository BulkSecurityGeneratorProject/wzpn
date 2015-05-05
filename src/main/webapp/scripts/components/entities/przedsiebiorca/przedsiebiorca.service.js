'use strict';

angular.module('wzpnApp')
    .factory('Przedsiebiorca', function ($resource) {
        return $resource('api/przedsiebiorcas/:id', {}, {
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

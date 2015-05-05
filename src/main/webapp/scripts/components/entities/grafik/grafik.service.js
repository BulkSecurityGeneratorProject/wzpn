'use strict';

angular.module('wzpnApp')
    .factory('Grafik', function ($resource) {
        return $resource('api/grafiks/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.rozpoczecie = new Date(data.rozpoczecie);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

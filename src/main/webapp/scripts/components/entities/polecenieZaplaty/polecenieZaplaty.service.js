'use strict';

angular.module('wzpnApp')
    .factory('PolecenieZaplaty', function ($resource) {
        return $resource('api/polecenieZaplatys/:id', {}, {
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

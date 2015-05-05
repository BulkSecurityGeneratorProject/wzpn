'use strict';

angular.module('wzpnApp')
    .factory('Druzyna', function ($resource) {
        return $resource('api/druzynas/:id', {}, {
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

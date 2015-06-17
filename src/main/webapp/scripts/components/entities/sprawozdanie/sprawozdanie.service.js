'use strict';

angular.module('wzpnApp')
    .factory('Sprawozdanie', function ($resource) {
        return $resource('api/sprawozdanies/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dzienSporzadzenia = new Date(data.dzienSporzadzenia);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

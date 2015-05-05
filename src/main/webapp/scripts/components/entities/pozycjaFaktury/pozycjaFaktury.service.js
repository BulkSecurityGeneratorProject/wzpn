'use strict';

angular.module('wzpnApp')
    .factory('PozycjaFaktury', function ($resource) {
        return $resource('api/pozycjaFakturys/:id', {}, {
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

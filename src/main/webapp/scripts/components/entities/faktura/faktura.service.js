'use strict';

angular.module('wzpnApp')
    .factory('Faktura', function ($resource) {
        return $resource('api/fakturas/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.dataWystawienia = new Date(data.dataWystawienia);
                    data.dataSprzedazy = new Date(data.dataSprzedazy);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

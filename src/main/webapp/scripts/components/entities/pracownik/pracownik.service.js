'use strict';

angular.module('wzpnApp')
    .factory('Pracownik', function ($resource) {
        return $resource('api/pracowniks/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    var dataZatrudnieniaFrom = data.dataZatrudnienia.split("-");
                    data.dataZatrudnienia = new Date(new Date(dataZatrudnieniaFrom[0], dataZatrudnieniaFrom[1] - 1, dataZatrudnieniaFrom[2]));
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    });

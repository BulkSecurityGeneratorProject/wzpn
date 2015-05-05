'use strict';

angular.module('wzpnApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



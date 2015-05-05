'use strict';

angular.module('wzpnApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });

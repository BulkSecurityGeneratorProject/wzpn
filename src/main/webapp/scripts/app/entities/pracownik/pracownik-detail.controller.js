'use strict';

angular.module('wzpnApp')
    .controller('PracownikDetailController', function ($scope, $stateParams, Pracownik) {
        $scope.pracownik = {};
        $scope.load = function (id) {
            Pracownik.get({id: id}, function(result) {
              $scope.pracownik = result;
            });
        };
        $scope.load($stateParams.id);
    });

'use strict';

angular.module('wzpnApp')
    .controller('WynikDetailController', function ($scope, $stateParams, Wynik, Grafik) {
        $scope.wynik = {};
        $scope.load = function (id) {
            Wynik.get({id: id}, function(result) {
              $scope.wynik = result;
            });
        };
        $scope.load($stateParams.id);
    });

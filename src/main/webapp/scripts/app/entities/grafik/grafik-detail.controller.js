'use strict';

angular.module('wzpnApp')
    .controller('GrafikDetailController', function ($scope, $stateParams, Grafik, Druzyna, ObiektSportowy, Wynik) {
        $scope.grafik = {};
        $scope.load = function (id) {
            Grafik.get({id: id}, function(result) {
              $scope.grafik = result;
            });
        };
        $scope.load($stateParams.id);
    });

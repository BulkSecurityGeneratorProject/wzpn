'use strict';

angular.module('wzpnApp')
    .controller('ObiektSportowyDetailController', function ($scope, $stateParams, ObiektSportowy, Grafik) {
        $scope.obiektSportowy = {};
        $scope.load = function (id) {
            ObiektSportowy.get({id: id}, function(result) {
              $scope.obiektSportowy = result;
            });
        };
        $scope.load($stateParams.id);
    });

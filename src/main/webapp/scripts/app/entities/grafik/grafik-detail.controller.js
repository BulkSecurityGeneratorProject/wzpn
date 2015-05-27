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
        $scope.create = function () {
        	$scope.wynik.grafik = $scope.grafik;
            Wynik.update($scope.wynik,
                function () {
                    $scope.loadAll();
                    $('#saveWynikModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Wynik.get({id: id}, function(result) {
                $scope.wynik = result;
                $('#saveWynikModal').modal('show');
                console.log($scope);
            });
        };
    });

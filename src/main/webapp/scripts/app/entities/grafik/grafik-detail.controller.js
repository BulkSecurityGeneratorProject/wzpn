'use strict';

angular.module('wzpnApp')
    .controller('GrafikDetailController', function ($scope, $stateParams,$filter, Grafik, Druzyna, ObiektSportowy, Wynik) {
        $scope.grafik = {};
        $scope.wyniks = Wynik.query();
        $scope.load = function (id) {
            Grafik.get({id: id}, function(result) {
              $scope.grafik = result;
              console.log('=========== test ============='+ $scope.wynik);
              var found = $filter('filter')($scope.wyniks, {id: 1118}, true);
              console.log(found);
              console.log($scope.grafik);
            });
        };
        $scope.load($stateParams.id);
        $scope.create = function () {
        	$scope.wynik.grafik = $scope.grafik;
            Wynik.update($scope.wynik,
                function () {
                    $('#saveWynikModal').modal('hide');
                    console.log($scope.wynik);
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

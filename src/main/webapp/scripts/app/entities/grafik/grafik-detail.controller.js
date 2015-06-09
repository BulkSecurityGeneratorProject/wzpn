'use strict';

angular.module('wzpnApp')
    .controller('GrafikDetailController', function ($scope, $stateParams,$filter, Grafik, Druzyna, ObiektSportowy, Wynik) {
        $scope.grafik = {};
        $scope.wyniks = Wynik.query();
        $scope.isDisableAdd;
        $scope.isDisableEdit;
        $scope.load = function (id) {
            Grafik.get({id: id}, function(result) {
              $scope.grafik = result;
        	if ($scope.grafik.wynik != null)
        		{
        			$scope.isDisableAdd=true;
        			$scope.isDisableEdit=false;
        		}
        	else
        		{
        		$scope.isDisableAdd=false;
    			$scope.isDisableEdit=true;
        		}
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
                    $scope.load($stateParams.id);
                });
        };

        $scope.update = function (id) {
            Wynik.get({id: id}, function(result) {
                $scope.wynik = result;
                $('#saveWynikModal').modal('show');
                $scope.load($stateParams.id);
            });
        };
    });

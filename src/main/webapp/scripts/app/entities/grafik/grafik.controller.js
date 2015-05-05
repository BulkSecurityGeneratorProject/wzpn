'use strict';

angular.module('wzpnApp')
    .controller('GrafikController', function ($scope, Grafik, Druzyna, ObiektSportowy, Wynik, ParseLinks) {
        $scope.grafiks = [];
        $scope.druzynas = Druzyna.query();
        $scope.obiektsportowys = ObiektSportowy.query();
        $scope.wyniks = Wynik.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Grafik.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.grafiks = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Grafik.update($scope.grafik,
                function () {
                    $scope.loadAll();
                    $('#saveGrafikModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Grafik.get({id: id}, function(result) {
                $scope.grafik = result;
                $('#saveGrafikModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Grafik.get({id: id}, function(result) {
                $scope.grafik = result;
                $('#deleteGrafikConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Grafik.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteGrafikConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.grafik = {nazwa: null, opis: null, rozpoczecie: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

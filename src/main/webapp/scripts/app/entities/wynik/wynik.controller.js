'use strict';

angular.module('wzpnApp')
    .controller('WynikController', function ($scope, Wynik, Grafik, ParseLinks) {
        $scope.wyniks = [];
        $scope.grafiks = Grafik.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Wynik.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.wyniks = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
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
            });
        };

        $scope.delete = function (id) {
            Wynik.get({id: id}, function(result) {
                $scope.wynik = result;
                $('#deleteWynikConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Wynik.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteWynikConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.wynik = {wynik: null, bramkiPierwszej: null, bramkiDrugiej: null, zoltePierwszej: null, zolteDrugiej: null, czerwonePierwszej: null, czerwoneDrugiej: null, uwagi: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

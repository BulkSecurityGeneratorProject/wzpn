'use strict';

angular.module('wzpnApp')
    .controller('ObiektSportowyController', function ($scope, ObiektSportowy, Grafik) {
        $scope.obiektSportowys = [];
        $scope.grafiks = Grafik.query();
        $scope.loadAll = function() {
            ObiektSportowy.query(function(result) {
               $scope.obiektSportowys = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            ObiektSportowy.update($scope.obiektSportowy,
                function () {
                    $scope.loadAll();
                    $('#saveObiektSportowyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            ObiektSportowy.get({id: id}, function(result) {
                $scope.obiektSportowy = result;
                $('#saveObiektSportowyModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            ObiektSportowy.get({id: id}, function(result) {
                $scope.obiektSportowy = result;
                $('#deleteObiektSportowyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            ObiektSportowy.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteObiektSportowyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.obiektSportowy = {nazwa: null, aktywny: null, miasto: null, kodPocztowy: null, ulica: null, telefon: null, prezes: null, strona: null, miejscaSiedzace: null, miejscaStojace: null, kryty: null, oswietlenie: null, wymiary: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

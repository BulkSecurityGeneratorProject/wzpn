'use strict';

angular.module('wzpnApp')
    .controller('DruzynaController', function ($scope, Druzyna, Liga, Kara, Grafik) {
        $scope.druzynas = [];
        $scope.ligas = Liga.query();
        $scope.karas = Kara.query();
        $scope.grafiks = Grafik.query();
        $scope.loadAll = function() {
            Druzyna.query(function(result) {
               $scope.druzynas = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Druzyna.update($scope.druzyna,
                function () {
                    $scope.loadAll();
                    $('#saveDruzynaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Druzyna.get({id: id}, function(result) {
                $scope.druzyna = result;
                $('#saveDruzynaModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Druzyna.get({id: id}, function(result) {
                $scope.druzyna = result;
                $('#deleteDruzynaConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Druzyna.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteDruzynaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.druzyna = {nazwa: null, usunieta: null, zawieszona: null, prezes: null, adres: null, telefon: null, email: null, strona: null, trenerzy: null, opis: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

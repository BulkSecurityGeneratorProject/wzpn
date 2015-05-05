'use strict';

angular.module('wzpnApp')
    .controller('LigaController', function ($scope, Liga, Druzyna) {
        $scope.ligas = [];
        $scope.druzynas = Druzyna.query();
        $scope.loadAll = function() {
            Liga.query(function(result) {
               $scope.ligas = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Liga.update($scope.liga,
                function () {
                    $scope.loadAll();
                    $('#saveLigaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Liga.get({id: id}, function(result) {
                $scope.liga = result;
                $('#saveLigaModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Liga.get({id: id}, function(result) {
                $scope.liga = result;
                $('#deleteLigaConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Liga.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteLigaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.liga = {nazwa: null, opis: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

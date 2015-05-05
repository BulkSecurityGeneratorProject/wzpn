'use strict';

angular.module('wzpnApp')
    .controller('UslugaController', function ($scope, Usluga) {
        $scope.uslugas = [];
        $scope.loadAll = function() {
            Usluga.query(function(result) {
               $scope.uslugas = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Usluga.update($scope.usluga,
                function () {
                    $scope.loadAll();
                    $('#saveUslugaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Usluga.get({id: id}, function(result) {
                $scope.usluga = result;
                $('#saveUslugaModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Usluga.get({id: id}, function(result) {
                $scope.usluga = result;
                $('#deleteUslugaConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Usluga.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteUslugaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.usluga = {nazwa: null, cena: null, podatek: null, usunieta: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

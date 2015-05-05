'use strict';

angular.module('wzpnApp')
    .controller('PrzedsiebiorcaController', function ($scope, Przedsiebiorca, Faktura) {
        $scope.przedsiebiorcas = [];
        $scope.fakturas = Faktura.query();
        $scope.loadAll = function() {
            Przedsiebiorca.query(function(result) {
               $scope.przedsiebiorcas = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Przedsiebiorca.update($scope.przedsiebiorca,
                function () {
                    $scope.loadAll();
                    $('#savePrzedsiebiorcaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Przedsiebiorca.get({id: id}, function(result) {
                $scope.przedsiebiorca = result;
                $('#savePrzedsiebiorcaModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Przedsiebiorca.get({id: id}, function(result) {
                $scope.przedsiebiorca = result;
                $('#deletePrzedsiebiorcaConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Przedsiebiorca.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePrzedsiebiorcaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.przedsiebiorca = {nazwa: null, adres: null, miejscowosc: null, kodPocztowy: null, nip: null, email: null, telefon: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

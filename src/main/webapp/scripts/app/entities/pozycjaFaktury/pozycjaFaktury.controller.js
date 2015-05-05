'use strict';

angular.module('wzpnApp')
    .controller('PozycjaFakturyController', function ($scope, PozycjaFaktury, Faktura) {
        $scope.pozycjaFakturys = [];
        $scope.fakturas = Faktura.query();
        $scope.loadAll = function() {
            PozycjaFaktury.query(function(result) {
               $scope.pozycjaFakturys = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            PozycjaFaktury.update($scope.pozycjaFaktury,
                function () {
                    $scope.loadAll();
                    $('#savePozycjaFakturyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            PozycjaFaktury.get({id: id}, function(result) {
                $scope.pozycjaFaktury = result;
                $('#savePozycjaFakturyModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            PozycjaFaktury.get({id: id}, function(result) {
                $scope.pozycjaFaktury = result;
                $('#deletePozycjaFakturyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            PozycjaFaktury.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePozycjaFakturyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.pozycjaFaktury = {nazwa: null, brutto: null, ilosc: null, podatek: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

'use strict';

angular.module('wzpnApp')
    .controller('FakturaController', function ($scope, Faktura, Przedsiebiorca, PozycjaFaktury, ParseLinks) {
        $scope.fakturas = [];
        $scope.przedsiebiorcas = Przedsiebiorca.query();
        $scope.pozycjafakturys = PozycjaFaktury.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Faktura.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.fakturas = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Faktura.update($scope.faktura,
                function () {
                    $scope.loadAll();
                    $('#saveFakturaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Faktura.get({id: id}, function(result) {
                $scope.faktura = result;
                $('#saveFakturaModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Faktura.get({id: id}, function(result) {
                $scope.faktura = result;
                $('#deleteFakturaConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Faktura.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteFakturaConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.faktura = {nazwa: null, dataWystawienia: null, dataSprzedazy: null, miejscowosc: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

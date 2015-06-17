'use strict';

angular.module('wzpnApp')
    .controller('PracownikController', function ($scope, Pracownik, ParseLinks) {
        $scope.pracowniks = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Pracownik.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.pracowniks = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Pracownik.update($scope.pracownik,
                function () {
                    $scope.loadAll();
                    $('#savePracownikModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Pracownik.get({id: id}, function(result) {
                $scope.pracownik = result;
                $('#savePracownikModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Pracownik.get({id: id}, function(result) {
                $scope.pracownik = result;
                $('#deletePracownikConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Pracownik.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePracownikConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.pracownik = {login: null, imie: null, nazwisko: null, pesel: null, nip: null, ulica: null, kodPocztowy: null, miasto: null, pensja: null, dataZatrudnienia: null, sekretariat: null, wydzialGier: null, ksiegowosc: null, administracja: null, sedzia: null, opis: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

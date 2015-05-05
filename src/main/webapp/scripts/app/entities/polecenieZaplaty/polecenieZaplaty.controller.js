'use strict';

angular.module('wzpnApp')
    .controller('PolecenieZaplatyController', function ($scope, PolecenieZaplaty, ParseLinks) {
        $scope.polecenieZaplatys = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            PolecenieZaplaty.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.polecenieZaplatys = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            PolecenieZaplaty.update($scope.polecenieZaplaty,
                function () {
                    $scope.loadAll();
                    $('#savePolecenieZaplatyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            PolecenieZaplaty.get({id: id}, function(result) {
                $scope.polecenieZaplaty = result;
                $('#savePolecenieZaplatyModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            PolecenieZaplaty.get({id: id}, function(result) {
                $scope.polecenieZaplaty = result;
                $('#deletePolecenieZaplatyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            PolecenieZaplaty.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePolecenieZaplatyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.polecenieZaplaty = {nazwaOdbiorcy: null, nazwaZleceniodawcy: null, rachunek: null, tytul: null, kwota: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

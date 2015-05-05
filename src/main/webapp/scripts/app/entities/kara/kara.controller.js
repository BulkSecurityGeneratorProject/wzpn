'use strict';

angular.module('wzpnApp')
    .controller('KaraController', function ($scope, Kara, Druzyna, ParseLinks) {
        $scope.karas = [];
        $scope.druzynas = Druzyna.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Kara.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.karas = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Kara.update($scope.kara,
                function () {
                    $scope.loadAll();
                    $('#saveKaraModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Kara.get({id: id}, function(result) {
                $scope.kara = result;
                $('#saveKaraModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Kara.get({id: id}, function(result) {
                $scope.kara = result;
                $('#deleteKaraConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Kara.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteKaraConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.kara = {dataOtrzymania: null, powod: null, kwota: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

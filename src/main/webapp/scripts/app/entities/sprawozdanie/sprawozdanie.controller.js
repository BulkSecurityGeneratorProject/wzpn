'use strict';

angular.module('wzpnApp')
    .controller('SprawozdanieController', function ($scope, Sprawozdanie) {
        $scope.sprawozdanies = [];
        $scope.loadAll = function() {
            Sprawozdanie.query(function(result) {
               $scope.sprawozdanies = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Sprawozdanie.update($scope.sprawozdanie,
                function () {
                    $scope.loadAll();
                    $('#saveSprawozdanieModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Sprawozdanie.get({id: id}, function(result) {
                $scope.sprawozdanie = result;
                $('#saveSprawozdanieModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Sprawozdanie.get({id: id}, function(result) {
                $scope.sprawozdanie = result;
                $('#deleteSprawozdanieConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Sprawozdanie.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSprawozdanieConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.sprawozdanie = {a1: null, a2: null, a3: null, a4: null, a5: null, a6: null, a7: null, a8: null, a9: null, a10: null, p1: null, p2: null, p3: null, p4: null, p5: null, p6: null, p7: null, p8: null, p9: null, p10: null, p11: null, p12: null, p13: null, p14: null, p15: null, rok: null, dzienSporzadzenia: null, w1: null, w2: null, w3: null, w4: null, w5: null, w6: null, w7: null, w8: null, w9: null, w10: null, w11: null, w12: null, w13: null, w14: null, w15: null, w16: null, w17: null, w18: null, w19: null, w20: null, w21: null, w22: null, w23: null, w24: null, w25: null, w26: null, w27: null, w28: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

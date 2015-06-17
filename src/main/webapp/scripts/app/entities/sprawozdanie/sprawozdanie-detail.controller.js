'use strict';

angular.module('wzpnApp')
    .controller('SprawozdanieDetailController', function ($scope, $stateParams, Sprawozdanie) {
        $scope.sprawozdanie = {};
        $scope.load = function (id) {
            Sprawozdanie.get({id: id}, function(result) {
              $scope.sprawozdanie = result;
            });
        };
        $scope.load($stateParams.id);
    });

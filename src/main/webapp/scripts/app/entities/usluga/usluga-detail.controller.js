'use strict';

angular.module('wzpnApp')
    .controller('UslugaDetailController', function ($scope, $stateParams, Usluga) {
        $scope.usluga = {};
        $scope.load = function (id) {
            Usluga.get({id: id}, function(result) {
              $scope.usluga = result;
            });
        };
        $scope.load($stateParams.id);
    });

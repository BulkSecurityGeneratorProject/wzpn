'use strict';

angular.module('wzpnApp')
    .controller('LigaDetailController', function ($scope, $stateParams, Liga, Druzyna) {
        $scope.liga = {};
        $scope.load = function (id) {
            Liga.get({id: id}, function(result) {
              $scope.liga = result;
            });
        };
        $scope.load($stateParams.id);
    });

'use strict';

angular.module('wzpnApp')
    .controller('KaraDetailController', function ($scope, $stateParams, Kara, Druzyna) {
        $scope.kara = {};
        $scope.load = function (id) {
            Kara.get({id: id}, function(result) {
              $scope.kara = result;
            });
        };
        $scope.load($stateParams.id);
    });

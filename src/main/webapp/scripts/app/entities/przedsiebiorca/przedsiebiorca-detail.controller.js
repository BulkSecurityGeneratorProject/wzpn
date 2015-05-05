'use strict';

angular.module('wzpnApp')
    .controller('PrzedsiebiorcaDetailController', function ($scope, $stateParams, Przedsiebiorca, Faktura) {
        $scope.przedsiebiorca = {};
        $scope.load = function (id) {
            Przedsiebiorca.get({id: id}, function(result) {
              $scope.przedsiebiorca = result;
            });
        };
        $scope.load($stateParams.id);
    });

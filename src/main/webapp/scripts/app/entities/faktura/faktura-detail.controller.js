'use strict';

angular.module('wzpnApp')
    .controller('FakturaDetailController', function ($scope, $stateParams, Faktura, Przedsiebiorca, PozycjaFaktury) {
        $scope.faktura = {};
        $scope.load = function (id) {
            Faktura.get({id: id}, function(result) {
              $scope.faktura = result;
            });
        };
        $scope.load($stateParams.id);
    });

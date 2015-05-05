'use strict';

angular.module('wzpnApp')
    .controller('PozycjaFakturyDetailController', function ($scope, $stateParams, PozycjaFaktury, Faktura) {
        $scope.pozycjaFaktury = {};
        $scope.load = function (id) {
            PozycjaFaktury.get({id: id}, function(result) {
              $scope.pozycjaFaktury = result;
            });
        };
        $scope.load($stateParams.id);
    });

'use strict';

angular.module('wzpnApp')
    .controller('PolecenieZaplatyDetailController', function ($scope, $stateParams, PolecenieZaplaty) {
        $scope.polecenieZaplaty = {};
        $scope.load = function (id) {
            PolecenieZaplaty.get({id: id}, function(result) {
              $scope.polecenieZaplaty = result;
            });
        };
        $scope.load($stateParams.id);
    });

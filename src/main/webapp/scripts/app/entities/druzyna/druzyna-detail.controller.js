'use strict';

angular.module('wzpnApp')
    .controller('DruzynaDetailController', function ($scope, $stateParams, Druzyna, Liga, Kara, Grafik) {
        $scope.druzyna = {};
        $scope.load = function (id) {
            Druzyna.get({id: id}, function(result) {
              $scope.druzyna = result;
            });
        };
        $scope.load($stateParams.id);
    });

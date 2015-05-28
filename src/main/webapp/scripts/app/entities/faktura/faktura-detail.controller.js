'use strict';

angular.module('wzpnApp')
    .controller('FakturaDetailController', function ($scope, $filter, $stateParams, Faktura, Przedsiebiorca, PozycjaFaktury) {
        $scope.faktura = {};
        $scope.pozycjeFaktury = {};
        $scope.load = function (id) {
            Faktura.get({id: id}, function(result) {
              $scope.faktura = result;
            });
            
         PozycjaFaktury.query(function(result) {
            	var resultNew = [];
            	angular.forEach(result, function(value, key) {
            		if(value.faktura.id == id) resultNew.push(value);
            	});
            	
            	 $scope.pozycjeFaktury = resultNew;
            });
        };
        $scope.load($stateParams.id);
    });

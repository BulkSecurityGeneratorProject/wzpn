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
            	var totalMoney = 0;
            	var podatek = 0;
            	angular.forEach(result, function(value, key) {
            		if(value.faktura != null && value.faktura.id == id) {
            			resultNew.push(value);
            			if(value.brutto!=null) totalMoney = totalMoney + value.brutto;
            			if(value.podatek!=null)podatek = podatek + value.podatek;
            		}
            	});
            	
            	 $scope.pozycjeFaktury = resultNew;
            	 $scope.lacznaKwota = totalMoney;
            	 $scope.lacznyPodatek = podatek; 
            });
        };
        $scope.load($stateParams.id);
    });

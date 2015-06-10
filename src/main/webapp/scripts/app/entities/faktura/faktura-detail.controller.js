'use strict';

angular.module('wzpnApp')
    .controller('FakturaDetailController', function ($scope, $filter, $stateParams, Faktura, Przedsiebiorca, PozycjaFaktury) {
        $scope.faktura = {};
        $scope.pozycjeFaktury = {};
        $scope.fakturas = Faktura.query();
        $scope.load = function (id) {
            Faktura.get({id: id}, function(result) {
              $scope.faktura = result;
            });
            
            PozycjaFaktury.query(function(result) {
            	var resultNew = [];
            	var totalMoney = 0;
            	var totalMoneyVat = 0;
            	var totalVat = 0;
            	var podatek = 0;
            	angular.forEach(result, function(value, key) {
            		if(value.faktura != null && value.faktura.id == id) {
            			resultNew.push(value);
            			if(value.brutto!=null  && value.ilosc != null) totalMoneyVat = totalMoneyVat + (value.brutto * value.ilosc);
            			if(value.brutto != null && value.ilosc != null && value.podatek != null) {
            				totalMoney = totalMoney + (value.brutto * value.ilosc)/(100 - value.podatek)*100;
            				totalVat = totalVat + value.brutto * value.ilosc * value.podatek / 100;
            			}
            			if(value.podatek!=null)	podatek = podatek + value.podatek;
            		}
            	});
            	
            	 $scope.pozycjeFaktury = resultNew;
            	 $scope.lacznaKwota = totalMoney;
            	 $scope.lacznaKwotaVat = totalMoneyVat;
            	 $scope.lacznyPodatek = podatek; 
            	 $scope.lacznyVat = totalVat;
            });
        };
        $scope.load($stateParams.id);
        
        $scope.create = function () {
            PozycjaFaktury.update($scope.pozycjaFaktury,
                function () {
                   	// $scope.loadAll();
            		$scope.load($stateParams.id);
                    $('#savePozycjaFakturyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            PozycjaFaktury.get({id: id}, function(result) {
            	//result.faktura.id = $stateParams.id;
                $scope.pozycjaFaktury = result;
                $('#savePozycjaFakturyModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            PozycjaFaktury.get({id: id}, function(result) {
                $scope.pozycjaFaktury = result;
                $('#deletePozycjaFakturyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            PozycjaFaktury.delete({id: id},
                function () {
                    //$scope.loadAll();
            		$scope.load($stateParams.id);
                    $('#deletePozycjaFakturyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.pozycjaFaktury = {nazwa: null, brutto: null, ilosc: null, podatek: null, id: null};
            $scope.editForm.$setPristine();
            $scope.editForm.$setUntouched();
        };
    });

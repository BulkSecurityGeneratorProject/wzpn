'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('faktura', {
                parent: 'entity',
                url: '/faktura',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.faktura.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/faktura/fakturas.html',
                        controller: 'FakturaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('faktura');
                        return $translate.refresh();
                    }]
                }
            })
            .state('fakturaDetail', {
                parent: 'entity',
                url: '/faktura/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.faktura.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/faktura/faktura-detail.html',
                        controller: 'FakturaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('faktura');
                        return $translate.refresh();
                    }]
                }
            });
    });

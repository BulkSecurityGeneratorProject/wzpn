'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('usluga', {
                parent: 'entity',
                url: '/usluga',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.usluga.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/usluga/uslugas.html',
                        controller: 'UslugaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('usluga');
                        return $translate.refresh();
                    }]
                }
            })
            .state('uslugaDetail', {
                parent: 'entity',
                url: '/usluga/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.usluga.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/usluga/usluga-detail.html',
                        controller: 'UslugaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('usluga');
                        return $translate.refresh();
                    }]
                }
            });
    });

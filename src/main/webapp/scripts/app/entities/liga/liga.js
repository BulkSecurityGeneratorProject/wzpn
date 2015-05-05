'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('liga', {
                parent: 'entity',
                url: '/liga',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.liga.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/liga/ligas.html',
                        controller: 'LigaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('liga');
                        return $translate.refresh();
                    }]
                }
            })
            .state('ligaDetail', {
                parent: 'entity',
                url: '/liga/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.liga.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/liga/liga-detail.html',
                        controller: 'LigaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('liga');
                        return $translate.refresh();
                    }]
                }
            });
    });

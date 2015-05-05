'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('grafik', {
                parent: 'entity',
                url: '/grafik',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.grafik.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/grafik/grafiks.html',
                        controller: 'GrafikController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('grafik');
                        return $translate.refresh();
                    }]
                }
            })
            .state('grafikDetail', {
                parent: 'entity',
                url: '/grafik/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.grafik.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/grafik/grafik-detail.html',
                        controller: 'GrafikDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('grafik');
                        return $translate.refresh();
                    }]
                }
            });
    });

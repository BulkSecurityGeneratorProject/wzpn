'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('wynik', {
                parent: 'entity',
                url: '/wynik',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.wynik.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/wynik/wyniks.html',
                        controller: 'WynikController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('wynik');
                        return $translate.refresh();
                    }]
                }
            })
            .state('wynikDetail', {
                parent: 'entity',
                url: '/wynik/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.wynik.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/wynik/wynik-detail.html',
                        controller: 'WynikDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('wynik');
                        return $translate.refresh();
                    }]
                }
            });
    });

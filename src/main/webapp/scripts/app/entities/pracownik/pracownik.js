'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('pracownik', {
                parent: 'entity',
                url: '/pracownik',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.pracownik.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pracownik/pracowniks.html',
                        controller: 'PracownikController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pracownik');
                        return $translate.refresh();
                    }]
                }
            })
            .state('pracownikDetail', {
                parent: 'entity',
                url: '/pracownik/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.pracownik.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pracownik/pracownik-detail.html',
                        controller: 'PracownikDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pracownik');
                        return $translate.refresh();
                    }]
                }
            });
    });

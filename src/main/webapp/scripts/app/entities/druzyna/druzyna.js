'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('druzyna', {
                parent: 'entity',
                url: '/druzyna',
                data: {
                    roles: [],
                    pageTitle: 'wzpnApp.druzyna.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/druzyna/druzynas.html',
                        controller: 'DruzynaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('druzyna');
                        return $translate.refresh();
                    }]
                }
            })
            .state('druzynaDetail', {
                parent: 'entity',
                url: '/druzyna/:id',
                data: {
                    roles: [],
                    pageTitle: 'wzpnApp.druzyna.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/druzyna/druzyna-detail.html',
                        controller: 'DruzynaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('druzyna');
                        return $translate.refresh();
                    }]
                }
            });
    });

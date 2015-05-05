'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('kara', {
                parent: 'entity',
                url: '/kara',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.kara.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/kara/karas.html',
                        controller: 'KaraController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('kara');
                        return $translate.refresh();
                    }]
                }
            })
            .state('karaDetail', {
                parent: 'entity',
                url: '/kara/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.kara.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/kara/kara-detail.html',
                        controller: 'KaraDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('kara');
                        return $translate.refresh();
                    }]
                }
            });
    });

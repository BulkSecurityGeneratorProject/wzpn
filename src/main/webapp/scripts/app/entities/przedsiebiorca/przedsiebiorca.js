'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('przedsiebiorca', {
                parent: 'entity',
                url: '/przedsiebiorca',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.przedsiebiorca.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/przedsiebiorca/przedsiebiorcas.html',
                        controller: 'PrzedsiebiorcaController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('przedsiebiorca');
                        return $translate.refresh();
                    }]
                }
            })
            .state('przedsiebiorcaDetail', {
                parent: 'entity',
                url: '/przedsiebiorca/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.przedsiebiorca.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/przedsiebiorca/przedsiebiorca-detail.html',
                        controller: 'PrzedsiebiorcaDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('przedsiebiorca');
                        return $translate.refresh();
                    }]
                }
            });
    });

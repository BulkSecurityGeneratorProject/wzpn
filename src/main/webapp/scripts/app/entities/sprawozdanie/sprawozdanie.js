'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('sprawozdanie', {
                parent: 'entity',
                url: '/sprawozdanie',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.sprawozdanie.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sprawozdanie/sprawozdanies.html',
                        controller: 'SprawozdanieController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('sprawozdanie');
                        return $translate.refresh();
                    }]
                }
            })
            .state('sprawozdanieDetail', {
                parent: 'entity',
                url: '/sprawozdanie/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.sprawozdanie.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/sprawozdanie/sprawozdanie-detail.html',
                        controller: 'SprawozdanieDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('sprawozdanie');
                        return $translate.refresh();
                    }]
                }
            });
    });

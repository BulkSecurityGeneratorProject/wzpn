'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('obiektSportowy', {
                parent: 'entity',
                url: '/obiektSportowy',
                data: {
                    roles: [],
                    pageTitle: 'wzpnApp.obiektSportowy.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/obiektSportowy/obiektSportowys.html',
                        controller: 'ObiektSportowyController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('obiektSportowy');
                        return $translate.refresh();
                    }]
                }
            })
            .state('obiektSportowyDetail', {
                parent: 'entity',
                url: '/obiektSportowy/:id',
                data: {
                    roles: [],
                    pageTitle: 'wzpnApp.obiektSportowy.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/obiektSportowy/obiektSportowy-detail.html',
                        controller: 'ObiektSportowyDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('obiektSportowy');
                        return $translate.refresh();
                    }]
                }
            });
    });

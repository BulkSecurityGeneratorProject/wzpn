'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('polecenieZaplaty', {
                parent: 'entity',
                url: '/polecenieZaplaty',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.polecenieZaplaty.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/polecenieZaplaty/polecenieZaplatys.html',
                        controller: 'PolecenieZaplatyController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('polecenieZaplaty');
                        return $translate.refresh();
                    }]
                }
            })
            .state('polecenieZaplatyDetail', {
                parent: 'entity',
                url: '/polecenieZaplaty/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.polecenieZaplaty.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/polecenieZaplaty/polecenieZaplaty-detail.html',
                        controller: 'PolecenieZaplatyDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('polecenieZaplaty');
                        return $translate.refresh();
                    }]
                }
            });
    });

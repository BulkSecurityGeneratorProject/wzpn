'use strict';

angular.module('wzpnApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('pozycjaFaktury', {
                parent: 'entity',
                url: '/pozycjaFaktury',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.pozycjaFaktury.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pozycjaFaktury/pozycjaFakturys.html',
                        controller: 'PozycjaFakturyController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pozycjaFaktury');
                        return $translate.refresh();
                    }]
                }
            })
            .state('pozycjaFakturyDetail', {
                parent: 'entity',
                url: '/pozycjaFaktury/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'wzpnApp.pozycjaFaktury.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/pozycjaFaktury/pozycjaFaktury-detail.html',
                        controller: 'PozycjaFakturyDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('pozycjaFaktury');
                        return $translate.refresh();
                    }]
                }
            });
    });

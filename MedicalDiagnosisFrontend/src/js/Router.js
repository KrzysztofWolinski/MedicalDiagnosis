angular.module('medicalDiagnosis')
    .config([
        '$stateProvider',
        function($stateProvider) {
            'use strict';

            // TODO add otherwise

            $stateProvider
                .state('home', {
                	url: '/home',
                    views: {
                        'main': {
                            templateUrl: 'partials/home.html'
                        }
                    }
                })
                .state('about', {
                	url: 'about',
                    views: {
                        'main': {
                            templateUrl: 'partials/about.html'
                        }
                    }
                });
        }
    ]);

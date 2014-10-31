'use strict';

var medicalDiagnosisApp = angular.module('medicalDiagnosis', ['ui.router']);

medicalDiagnosisApp
    .config([
        '$stateProvider',
        function($stateProvider) {
            
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

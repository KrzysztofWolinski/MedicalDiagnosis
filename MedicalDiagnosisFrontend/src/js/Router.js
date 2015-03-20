'use strict';

var medicalDiagnosisApp = angular.module('medicalDiagnosis', ['ui.router']);

medicalDiagnosisApp
    .config([
        '$stateProvider',
        function($stateProvider) {

            $stateProvider
                .state('home', {
                    url: '/home',
                    views: {
                        'navigation': {
                            templateUrl: 'partials/navigation.html'
                        },
                        'main': {
                            templateUrl: 'partials/home.html'
                        }
                    }
                })
                .state('diagnosis', {
                    url: 'diagnosis',
                    views: {
                        'navigation': {
                            templateUrl: 'partials/navigation.html'
                        },
                        'main': {
                            templateUrl: 'partials/diagnosis.html'
                        }
                    }
                })
                .state('profile', {
                    url: 'profile',
                    views: {
                        'navigation': {
                            templateUrl: 'partials/navigation.html'
                        },
                        'main': {
                            templateUrl: 'partials/profile.html'
                        }
                    }
                })
                .state('about', {
                    url: 'about',
                    views: {
                        'navigation': {
                            templateUrl: 'partials/navigation.html'
                        },
                        'main': {
                            templateUrl: 'partials/about.html'
                        }
                    }
                });

           
        }
    ]);

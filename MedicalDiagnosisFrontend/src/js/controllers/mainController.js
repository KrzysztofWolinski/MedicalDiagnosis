'use strict';

angular.module('medicalDiagnosis')
    .controller('mainController', [
        '$rootScope',
        '$scope',
        function($rootScope, $scope) {

            $rootScope.infoList = [];

            $rootScope
                .$on('$stateChangeSuccess',
                    function(event, toState, toParams, fromState, fromParams) {
                        $rootScope.infoList.push({
                            'display': true,
                            'title': 'Home',
                            'contents': 'You have entered state ' + toState.name,
                            'style': 'alert-success'
                        });
                    })
            $rootScope.$on('$stateNotFound',
                function(event, unfoundState, fromState, fromParams) {
                    $rootScope.infoList.push({
                        'display': true,
                        'title': 'Error!',
                        'contents': 'There was a problem entering ' + unfoundState.to,
                        'style': 'alert-danger'
                    });
                });

            $scope.getNotificationPanel = function() {
                return 'partials/notification-panel.html';
            };

        }
    ]);

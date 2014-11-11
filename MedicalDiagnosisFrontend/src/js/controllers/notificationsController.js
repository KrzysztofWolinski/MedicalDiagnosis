'use strict';

angular.module('medicalDiagnosis')
	.controller('notificationsController', [
		'$rootScope',
		'$scope',
		function($rootScope, $scope) {
			
			$scope.deleteAll = function() {
				$rootScope.infoList = [];
			};

			$scope.deleteNotification = function(index) {
				$rootScope.infoList.splice(index, 1);
			};

			$scope.getInfoListSize = function() {
				return $rootScope.infoList.length;
			};

		}
	]);
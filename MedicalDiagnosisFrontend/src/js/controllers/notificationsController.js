'use strict';

angular.module('medicalDiagnosis')
	.controller('notificationsController', [
		'$rootScope',
		'$scope',
		function($rootScope, $scope) {
			
			$scope.deleteAll = function() {
				$rootScope.infoList = [];
			};

			$scope.getInfoListSize = function() {
				return $rootScope.infoList.length;
			};

		}
	]);
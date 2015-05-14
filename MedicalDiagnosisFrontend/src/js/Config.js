angular.module('myApp')
.config(['$httpProvider',
	function($httpProvider) {
		'use strict';
		$httpProvider.defaults.timeout = 5000;
	}
	]);
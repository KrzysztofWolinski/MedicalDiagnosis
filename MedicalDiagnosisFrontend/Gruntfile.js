module.exports = function(grunt) {

	// Project configuration.
	grunt.initConfig({
		watch: {
			options: {
				livereload: false,
				spawn: false
					/*	,
					interval: 5007*/
				},
				scripts: {
					files: ['src/js/**/*.js',
					'src/partials/**/*.html',
					'src/**/*.html',
					'src/css/**/*.css'
					],
				//tasks: ['uglify'],
			},
		},
		clean: {
			server: ["target"]
		},
		concurrent: {
			server: ['coffee', 'sass']
		},
		connect: {
			options: {
				port: 8000,
				protocol: 'https',
				hostname: 'localhost',
				base: 'src/',
				keepalive: false,
				open: {
					target: 'https://localhost:8000/index.html?mock'
				},
				middleware: function(connect, options) {
					var middlewares = [];

					if (!Array.isArray(options.base)) {
						options.base = [options.base];
					}

					// Setup the proxy
					middlewares.push(require('grunt-connect-proxy/lib/utils').proxyRequest);

					// Serve static files
					options.base.forEach(function(base) {
						middlewares.push(connect.static(base));
					});

					return middlewares;
				}
			},
			local: {
				proxies: [{
					context: '/medical-diagnosis',
					host: 'localhost',
					port: 8443,
					https: true
				}]
			},
			live: {
				proxies: [{
					context: '/medical-diagnosis',
					host: '192.168.10.25',
					port: 8443,
					https: true
				}]
			}
		},
		pkg: grunt.file.readJSON('package.json'),
		uglify: {
			build: {
				files: [{
					expand: true,
					src: ['**/*.js'],
					ext: '.min.js',
					dest: 'src/js-min',
					cwd: '.src/js'
				}]
			}
		}
	});

	// Load the plugin that provides the "uglify" task.
	grunt.loadNpmTasks('grunt-contrib-connect');
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-connect-proxy');
	grunt.loadNpmTasks('grunt-contrib-livereload');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-concurrent');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-jshint');

	grunt.registerTask('serve', function(target) {
		if (target === 'dist') {
			return grunt.task.run(['build', 'connect:dist:keepalive']);
		}

		grunt.task.run([
			'clean:server',
			//'bower-install',
			//'concurrent:server',
			//'autoprefixer',
			'uglify',
			'configureProxies:local', // added just before connect
			'connect:local',
			'watch'
			]);
	});

	grunt.registerTask('connect-test', function(target) {
		if (target === 'dist') {
			return grunt.task.run(['build', 'connect:dist:keepalive']);
		}

		grunt.task.run([
			'clean:server',
			//'bower-install',
			//'concurrent:server',
			//'autoprefixer',
			'uglify',
			'configureProxies:live', // added just before connect
			'connect:live',
			'watch'
			]);
	});

	grunt.registerTask('connect-test-lite', function(target) {
		grunt.task.run([
			//'clean:server',
			//'bower-install',
			//'concurrent:server',
			//'autoprefixer',
			//'uglify',
			'configureProxies:live', // added just before connect
			'connect:live',
			'watch'
			]);
	});

};

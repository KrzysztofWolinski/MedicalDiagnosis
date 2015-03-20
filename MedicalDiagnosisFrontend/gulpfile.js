'use strict';

var gulp = require('gulp');
var url = require('url');
var opn = require('opn');
var browserSync = require("browser-sync");

var server = {
    host: 'localhost',
    port: '8444'
};

gulp.task('default', ['browser-sync', 'openbrowser']);

gulp.task('openbrowser', function() {
    opn('https://' + server.host + ':' + server.port + '/index.html');
});

gulp.task('browser-sync', function() {
    browserSync({
        open: false,
        port: server.port,
        https: true,
        livereload: false,
        server: {
            baseDir: './src/'
        }
    });
});
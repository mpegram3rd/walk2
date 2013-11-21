requirejs.config({
	baseUrl: "js",
	paths: {
		'jquery': '//code.jquery.com/jquery-1.10.1.min',
		'bootstrap' : '//netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min',
		'underscore': 'lib/underscore',
		'backbone': 'lib/backbone',
		'text': 'lib/text',
		/*		
		'async': 'lib/async',
		'jquery.number': 'lib/jquery/jquery.number',
		'jquery.cookie': 'lib/jquery/jquery.cookie',
		'select2': 'lib/select2-3.3.2/select2'
*/		
	},

	shim: {
		'underscore': {
			exports: '_'
		},
		'backbone': {
			deps: ['underscore', 'jquery'],
			exports: 'Backbone'
		}
	}
});

require([
     	'jquery',
     	'app',
     	'geo',
     	'backbone'
     ], function ( $, App, Geo, Backbone ) {
     	$(function () {
     		App.initialize();
     	});
});
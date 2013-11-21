define([
	'jquery',
	'underscore',
	'backbone',
	'geo'
], function ($, _, Backbone, Geo) {
	
	var App = Backbone.Router.extend({
		routes: {
			"" 							: "home",
			"findLoc"					: "findLoc",
			"details/:resourceid"		: "details"
		},
		
		initialize: function() {
		
		},
		
		home: function() {
			console.log("Calling Home route");
			require(['views/homeview'], function(HomeView) {
				var homePage = new HomeView();
				$('#content').html(homePage.render().el);
			});
		}, 
		
		findLoc: function () {
			var self = this;
			Geo.fetch(function(position) {
				self.loadSuccess(position);
			}, Geo.error);		
			
		},
		
		
		loadSuccess: function( position ) {
			require(['views/locationview', 'models/location'], function(LocationView, Location) {
				var model = new Location({'latitude' : position.coords.latitude,
										  'longitude' : position.coords.longitude,
										  'heading' : Geo.cardinalDirection(position.coords.heading),
										  'accuracy' : position.coords.accuracy });
				
				
				var view = new LocationView({ model: model} );
				$('#content').html(view.render().el);
			});
		}
	});
	
	var initialize = function() {
		console.log("Initializing app");
		window.app = new App();
		Backbone.history.start();
	};
	
	return {
		initialize: initialize
	};
});
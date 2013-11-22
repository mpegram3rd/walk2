define([
	'jquery',
	'underscore',
	'backbone',
	'geo'
], function ($, _, Backbone, Geo) {
	
	var App = Backbone.Router.extend({
		routes: {
			"" 								: "home",
			"locate"						: "findLoc",
//			"restaurants/:lat/:longitude"	: "restaurantList",
			"details/:resourceid"			: "details"
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
				self.locationSuccess(position);
			}, self.locationError);							
		},
		
		
		locationSuccess: function( position ) {
			console.log("Location success");
			var self = this;
//			require(['views/locationview', 'models/location'], function(LocationView, Location) {
//				var model = new Location({'latitude' : position.coords.latitude,
//					  'longitude' : position.coords.longitude,
//					  'heading' : Geo.cardinalDirection(position.coords.heading),
//					  'accuracy' : position.coords.accuracy });
//
//				var view = new LocationView({ model: model} );
				
			require(['views/restaurant-list-view', 'models/restaurant-collection'], function(RestaurantsView, Restaurants) {
				var collection = new Restaurants([], {'latitude'  : position.coords.latitude,
					                             'longitude' : position.coords.longitude
				});
				collection.fetch();
				collection.each(function(restaurant) {
					  console.log("Got 1");
				});
//				$('#content').html(view.render().el);
				console.log("Finished iterating");
			});
		},
		
		locationError: function (error) {
			var self = this;
			console.log("Location fails");
			require(['views/homeview'], function(HomeView) {
				var ModelProto = Backbone.Model.extend({});
				
				var view = new HomeView({ 
								model: new ModelProto({statusMsg : Geo.errorMsg(error)})
				});
				$('#content').html(view.render().el);
				
			});			
		},
		
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
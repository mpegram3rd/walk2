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
			var self = this;
			require(['views/restaurant-list-view', 'models/restaurant-collection'], function(RestaurantsView, Restaurants) {
				var restaurants = new Restaurants([], {'latitude'  : position.coords.latitude,
					                             'longitude' : position.coords.longitude
				});
	
				var view = new RestaurantsView({collection: restaurants});
				restaurants.fetch({
						complete: function ( data, response ) {
							$('#content').html(view.render().el);
						}

				});
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
		
		details: function(resourceid) {
			require(['views/restaurantview', 'models/restaurant'], function(RestaurantView, Restaurant) {
				var restaurant = new Restaurant({id: resourceid});
	
				var view = new RestaurantView({model: restaurant});
				restaurant.fetch({
						complete: function ( data, response ) {
							$('#content').html(view.render().el);
						}

				});
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
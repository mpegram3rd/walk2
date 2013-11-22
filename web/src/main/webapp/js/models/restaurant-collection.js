define([
        'underscore',
        'backbone',
        'models/restaurant'
], function (_, Backbone, Restaurant) {
	
	var Restaurants = Backbone.Collection.extend({
		initialize: function (models, options) {
			this.latitude = options.latitude;
			this.longitude = options.longitude;
		}, 
		
		model : Restaurant,
		
		url: function () {
			return '/services/v1/restaurant/list/' + this.latitude + '/' + this.longitude;
		},
		
		parse: function (response) {
			return response.restaurants;
		},
		
	});
		
	return Restaurants;
});

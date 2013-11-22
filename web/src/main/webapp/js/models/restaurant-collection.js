define([
        'backbone',
        'models/restaurant'
], function (Backbone, Restaurant) {
	
	var Restaurants = Backbone.Collection.extend({
		initialize: function (models, options) {
			this.latitude = options.latitude;
			this.longitude = options.longitude;
		}, 
		
		model : Restaurant,
		
		url: function () {
			return '/services/v1/restaurants/list/' + this.latitude + '/' + this.longitude;
		}
		
	});
		
	return Restaurants;
});

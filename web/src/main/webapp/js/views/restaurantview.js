define([
     'jquery', 
     'underscore',
     'backbone',
     'text!templates/restaurant.html',
], function ($, _, Backbone, restaurantTemplate) {
	
	var RestaurantView = Backbone.View.extend({
		
		template: _.template(restaurantTemplate),
		
		render: function(eventName) {
			$(this.el).html(this.template(this.model.toJSON()));
			return this;			
		}
	});
	
	return RestaurantView;
});
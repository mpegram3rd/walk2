define([
     'jquery', 
     'underscore',
     'backbone',
     'text!templates/restaurants-list.html',
], function ($, _, Backbone, restaurantsTemplate) {
	
	var RestaurantsListView = Backbone.View.extend({
		
		template: _.template(restaurantsTemplate),
		
		render: function(eventName) {
			
			$(this.el).html(this.template(this.collection));
			return this;			
		}
	});
	
	return RestaurantsListView;
});
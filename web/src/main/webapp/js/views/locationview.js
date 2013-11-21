define([
     'jquery', 
     'underscore',
     'backbone',
     'geo',
     'text!templates/results.html',
], function ($, _, Backbone, Geo, locationTemplate) {
	
	var HomeView = Backbone.View.extend({
		
		template: _.template(locationTemplate),
		
		initialize: function() {
		},
		
		render: function(eventName) {
			$(this.el).html(this.template(this.model.toJSON()));
			return this;			
		},
				
	});
	
	return HomeView;
});
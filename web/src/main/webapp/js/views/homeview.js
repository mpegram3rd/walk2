define([
     'jquery', 
     'underscore',
     'backbone',
     'text!templates/home.html',
], function ($, _, Backbone, homeTemplate) {
	
	var HomeView = Backbone.View.extend({
		
		template: _.template(homeTemplate),
		
		render: function(eventName) {
			// we may not have a model unless there was an error.
			if (this.model) {
				console.log ("Using model based rendering");
				$(this.el).html(this.template(this.model.toJSON()));
			}
			else {
				console.log ("Null model based rendering");
				$(this.el).html(this.template());
			}
			return this;			
		}
	});
	
	return HomeView;
});
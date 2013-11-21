define([
     'jquery', 
     'underscore',
     'backbone',
     'text!templates/home.html',
], function ($, _, Backbone, homeTemplate) {
	
	var HomeView = Backbone.View.extend({
		
		template: _.template(homeTemplate),
		
		render: function(eventName) {
			$(this.el).html(this.template());
			return this;			
		}
	});
	
	return HomeView;
});
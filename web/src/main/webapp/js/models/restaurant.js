define([
        'backbone',        
], function (Backbone, Geo) {
	
	var Restaurant = Backbone.Model.extend({
		defaults: {
			id: null,
			name: "",
			distance: 0.0,
			rating: 0,
		},
		
		url: function () {
			return '/services/v1/restaurants/' + this.id;
		}
		
	});
		
	return Restaurant;
});


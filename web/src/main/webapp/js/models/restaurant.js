define([
        'backbone',        
], function (Backbone, Geo) {
	
	var Restaurant = Backbone.Model.extend({
		defaults: {
			id: null,
			name: "",
			distance: 0.0,
			rating: 0,
			streetAddress: "",
			locality: "",
			state: "",
			postalCode: "",
			latitude: 0.0,
			longitude: 0.0
		},
		
		url: function () {
			return '/services/v1/restaurant/' + this.id;
		},
		
//		parse: function (response, options) {
//			if (options.collection) 
//				return response;
//			return response.restaurants[0];
//		},
		
	});
		
	return Restaurant;
});


define([
        'backbone'
], function (Backbone) {
	
	var Location = Backbone.Model.extend({
		defaults: {
			latitude: "",
			longitude: "",
			heading: "",
			accuracy: ""
		},
		
		
	});
		
	return Location;
});
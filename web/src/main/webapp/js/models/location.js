define([
        'backbone',
        'geo',
], function (Backbone, Geo) {
	
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
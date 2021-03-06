define([
        'jquery'
], function ($) {
	var Geo =  { 
		headings : {
				0 : 'North',
				1 : 'North East',
				2 : 'East',
				3 : 'South East',
				4 : 'South',
				5 : 'South West',
				6 : 'West',
				7 : 'North West'
		},
		
		errors : {
				1: 'Permission denied', 
				2: 'Position unavailable',
				3: 'Request timed out'
		},
		
		fetch : function (successHandler, failureHandler) {
			if (navigator.geolocation) {
				$('#status').html("Locating");
				navigator.geolocation.getCurrentPosition(successHandler, failureHandler, 
						   { enableHighAccuracy: true, timeout: 20000, maximumAge: 1000 });
			}
			else
				$('#status').html("Unsupported API");
			
			return false;
		},
		
		cardinalDirection: function (degrees) {
			var result = this.headings[0];
			
			if (degrees)
				result = this.headings[Math.floor(degrees / 45) % 8];
			
			return result;
		}, 
		
		errorMsg: function (error) {
			var result = "Unknown Error";
			if (error != null && this.errors[error.code]) {
				result = this.errors[error.code];
			}
			
			return result;
		},
		
	};
	
//	var init = function() {
//		window.Geo = Geo;
//		console.log("Running GEO Init");
//	};
	
	return Geo;
	
});
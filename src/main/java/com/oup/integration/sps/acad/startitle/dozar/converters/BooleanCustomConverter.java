package com.oup.integration.sps.acad.startitle.dozar.converters;

public class BooleanCustomConverter {
	
	public String stringToBoolean1(String input) {
		if("true".equals(input))
			return "Yes";
		else 
			return "No";
	}
	
	public boolean stringToBoolean2(String input) {
		if("Y".equals(input))
			return true;
		else 
			return false;
	}
}

package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
	
	AddPlace p = new AddPlace();
	p.setAccuracy(50);
	p.setAddress(address);
	p.setLanguage(language);
	p.setPhone_number("(415)111-1111");
	p.setWebsite("www.google.com");
	p.setName(name);
	List<String> myList = new ArrayList<String>();
	myList.add("restaurant");
	myList.add("bar");
	p.setTypes(myList);
	Location l = new Location();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	p.setLocation(l);
	return p;
	
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\"place_id\":\""+placeId+"\"}";
		
	}
	

}

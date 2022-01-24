package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("98765432");
		ap.setWebsite("www.google.com");
		ap.setName(name);
		List<String> types = new ArrayList<String>();
		types.add("school");
		types.add("park");
		ap.setTypes(types);
		ap.getLocation().setLat(-38.383494);
		ap.getLocation().setLng(33.427362);
		return ap;
	}
	public String deletePlacePayload(String place_id)
	{
		return "{\r\n \"place_id\":\""+place_id+"\"\r\n}";
	}

}

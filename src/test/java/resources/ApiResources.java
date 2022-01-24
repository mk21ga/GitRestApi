package resources;

public enum ApiResources {
	
	AddPlaceApi("/maps/api/place/add/json"),
	GetPlaceApi("/maps/api/place/get/json"),
	DeletePlaceApi("/maps/api/place/delete/json");
	String resource;
	
	ApiResources(String resource)
	{
		this.resource=resource;
	}

	public String getResource()
	{
		return resource;
	}
}

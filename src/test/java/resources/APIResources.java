package resources;

public enum APIResources {
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	//add other resources here
	
	private String resource;

	APIResources(String resource) {
		// TODO Auto-generated constructor stub
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
	

}

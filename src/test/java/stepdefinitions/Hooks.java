package stepdefinitions;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		//write a code to create place_id
		//execute this only when place_id is null
		
		StepDefinition sd = new StepDefinition();		
		
		if  (StepDefinition.placeId == null) { //placeId is static, so we should call it with a class name, instead of the object name
			sd.addplace_payload_with("Shetti", "French", "Marin");
			sd.user_calls_something_with_post_http_method("AddPlaceAPI","POST");
			sd.verify_place_id_created_maps_to_using_getPlaceApi("Shetti", "GetPlaceAPI");
		}
		
	}

}

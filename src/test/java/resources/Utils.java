package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	//This variable must be static, so only one instance is created and shared among all testcases
	public static RequestSpecification reqSpec; 
		
	public RequestSpecification getRequestSpecification() throws IOException {
		
		if (reqSpec == null) { //create log if it does not exist, otherwise append info to it
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			//Using request builder
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
			.addQueryParam("key", "qaclick123")
			.addFilter(RequestLoggingFilter.logRequestTo(log))
			.addFilter(ResponseLoggingFilter.logResponseTo(log))
			.setContentType(ContentType.JSON).build();
		}
		return reqSpec;		
	
	}
	
	public ResponseSpecification getResponseSpecification() {
		//Using response builder
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return resSpec;
	}
	
	public static String getGlobalValue(String propertyName) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\beama\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(propertyName);
				
		
	}
	
	public String getJsonPath(Response response, String key ) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
	}

}

package cucumber.options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
        glue = {"stepdefinitions"},
		plugin = "json:target/jsonReports/cucumber-reports.json")
		//tags = {"@AddPlace"}) //uncomment this to run only that test case
    
public class TestRunner {

}

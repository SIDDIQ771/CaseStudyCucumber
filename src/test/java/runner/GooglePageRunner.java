package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (
		
		features = "src//test//resources//features//GoogleSearch.feature",
		glue={"stepDef"},
		monochrome = true,
		dryRun = false,
		plugin = {"pretty"}
		
		)



public class GooglePageRunner extends AbstractTestNGCucumberTests {
	
	
	

}

package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		dryRun = false, 
		features = "src/test/resources/feature/api.feature", 
		glue = {"stepDefination" }, 
		plugin = { "pretty", "html:target/cucumber-reports.html","json:target/cucumber.json" }, 
		monochrome = true)

public class cucumberRunner {

}

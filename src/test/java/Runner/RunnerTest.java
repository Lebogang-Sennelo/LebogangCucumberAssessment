package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"Steps"},
        tags = "@createGroup",
        plugin = {"html:target/cucumber-report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        publish = true
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}

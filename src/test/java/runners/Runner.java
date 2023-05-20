package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html"
        },
        monochrome = true,
        features = "./src/test/resources/features",
        glue = {"stepdefinitions", "hooks"},
        tags = "@deneme",
        dryRun = false
)
public class Runner {
}

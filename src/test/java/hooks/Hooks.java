package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.Driver;

import static appiumserver.AppiumServer.start;
import static appiumserver.AppiumServer.stop;

public class Hooks {

    @Before
    public void setUp() throws Exception {
        //Her testten once Appium server programatik olarak baslatiliyor...
        //start();
    }

    @After
    public void tearDownScenarios(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] failedScreenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(failedScreenshot, "image/png", "failed-scenario-" + scenario.getName());
        }
        Driver.closeDriver();
        //Her testten once Appium server programatik olarak kapatiliyor...
        //stop();
    }

}

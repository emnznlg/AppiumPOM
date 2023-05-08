package utilities;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
    }

    private static AppiumDriver driver;

    private static DesiredCapabilities capabilities;

    public static AppiumDriver getDriver() {
        if (driver == null) {

            switch (ConfigReader.getProperty("platformName")) {
                case "Android":
                    capabilities = new DesiredCapabilities();
                    capabilities.setCapability("platformName", ConfigReader.getProperty("platformName"));
                    capabilities.setCapability("udid", ConfigReader.getProperty("udid"));
                    capabilities.setCapability("appPackage", ConfigReader.getProperty("appPackage"));
                    capabilities.setCapability("appActivity", ConfigReader.getProperty("appActivity"));
                    capabilities.setCapability("automationName", ConfigReader.getProperty("automationName"));
                    capabilities.setCapability("deviceName", ConfigReader.getProperty("deviceName"));
                    capabilities.setCapability("noReset", ConfigReader.getProperty("noReset"));
                case "IOS":
                    System.out.println("IOS ile ilgili konfigurasyonlar...");
            }

        }

        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }


    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


}

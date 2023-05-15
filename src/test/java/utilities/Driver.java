package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {

    private Driver() {
    }

    private static UiAutomator2Options options;
    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {

        if (driver == null) {
            switch (ConfigReader.getProperty("platformName")) {
                case "Android":
                    options = new UiAutomator2Options()
                            .setAppPackage("com.dmall.mfandroid")
                            .setAppActivity("com.dmall.mfandroid.activity.base.NewSplash")
                            .setUdid("emulator-5554")
                            .setNoReset(false)
                            .setNewCommandTimeout(Duration.ofMinutes(10));
                case "IOS":
                    //ios ile ilgili konfigurasyonlar buraya...
            }
            try {
                driver = new AppiumDriver(
                        new URL("http://127.0.0.1:4723"), options
                );
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
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

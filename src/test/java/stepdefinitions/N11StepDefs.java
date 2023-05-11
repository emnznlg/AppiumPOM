package stepdefinitions;

import appiumserver.AppiumServer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.N11Page;
import utilities.Driver;

import java.net.MalformedURLException;
import java.net.URL;

public class N11StepDefs {

    N11Page n11 = new N11Page();

    @When("Kategorilere tiklanir")
    public void kategorilere_tiklanir() {
        n11.kategorilerBtn3.click();
    }





}

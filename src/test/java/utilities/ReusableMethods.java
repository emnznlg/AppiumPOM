package utilities;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.hu.De;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class ReusableMethods {

    static Actions actions = new Actions(Driver.getDriver());
    static RemoteWebElement remoteWebElement;

    public static void swipeRight(WebElement element, int swipePercentage) {
        waitForClickAbility(element, 10);
        actions.clickAndHold(element)
                .moveByOffset(calculateOffsetForSwipe(swipePercentage), 0)
                .release()
                .perform();
    }

    public static void swipeLeft(WebElement element, int swipePercentage) {
        waitForClickAbility(element, 10);
        actions.clickAndHold(element)
                .moveByOffset(-calculateOffsetForSwipe(swipePercentage), 0)
                .release()
                .perform();
        waitFor(1);
    }

    public static void scrollDown(WebElement element, int scrollPercentage) {
        waitForClickAbility(element, 10);
        actions.clickAndHold(element)
                .moveByOffset(0, -(calculateOffsetForScroll(scrollPercentage)))
                .release()
                .perform();
        waitFor(1);
        System.out.println(calculateOffsetForScroll(scrollPercentage));
    }

    public static void scrollUp(WebElement element, int scrollPercentage) {
        waitForClickAbility(element, 10);
        actions.clickAndHold(element)
                .moveByOffset(0, (calculateOffsetForScroll(scrollPercentage)))
                .release()
                .perform();
        waitFor(1);
    }

    public static void swipeGesture(String fingerDirection, int percentage, WebElement element) {
        remoteWebElement = (RemoteWebElement) element;
        Driver.getDriver().executeScript("gesture: swipe", ImmutableMap.of(
                "elementId",
                remoteWebElement.getId(),
                "percentage",
                percentage,
                "direction",
                fingerDirection.toLowerCase()));
    }

    public static void longPress(WebElement element) {
        remoteWebElement = (RemoteWebElement) element;
        Driver.getDriver().executeScript("gesture: longPress", ImmutableMap.of(
                "elementId", remoteWebElement.getId(),
                "pressure", 0.5,
                "duration", 800));
    }

    public static void doubleTap(WebElement element) {
        remoteWebElement = (RemoteWebElement) element;
        Driver.getDriver().executeScript("gesture: doubleTap", ImmutableMap.of(
                "elementId",
                remoteWebElement.getId()));

    }

    public static void dragAndDrop(WebElement element, WebElement targetElement) {
        actions.clickAndHold(element)
                .moveToElement(targetElement)
                .release()
                .perform();
    }

    private static int calculateOffsetForScroll(int scrollAmount) {
        Dimension dimension = Driver.getDriver().manage().window().getSize();
        return ((scrollAmount * dimension.height) / 100);
    }

    private static int calculateOffsetForSwipe(int scrollAmount) {
        Dimension dimension = Driver.getDriver().manage().window().getSize();
        return ((scrollAmount * dimension.width) / 100);
    }

    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static WebElement waitForClickAbility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void scrollIntoView(String Text) {
        String mySelector = "new UiSelector().text(\"" + Text + "\").instance(0)";
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + mySelector + ");";
        Driver.getDriver().findElement(AppiumBy.androidUIAutomator(command));
    }

}

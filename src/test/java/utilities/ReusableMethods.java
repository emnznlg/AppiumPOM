package utilities;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableMethods {

    static Actions actions = new Actions(Driver.getDriver());

    public static void longPress(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()
                ));
    }

    public static void doubleClick(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "mobile: doubleClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()
                ));
    }

    public static void clickJS(WebElement element) {
        waitForClickAbility(element, 10);
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "mobile: clickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()
                ));
    }

    public static void dragAndDrop(WebElement element, double endX, double endY) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "endX", endX,
                        "endY", endY,
                        "speed", 5000
                ));
    }

    public static void dragAndDrop(double startX, double startY, double endX, double endY) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "startX", startX,
                        "startY", startY,
                        "endX", endX,
                        "endY", endY,
                        "speed", 5000
                ));
    }

    public static void dragAndDrop(double startX, double startY, double endX, double endY, int speed1to5) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript(
                "mobile: dragGesture",
                ImmutableMap.of(
                        "startX", startX,
                        "startY", startY,
                        "endX", endX,
                        "endY", endY,
                        "speed", 1000 * speed1to5
                ));
    }

    public static void dragAndDrop(WebElement element, WebElement targetElement) {
        actions.clickAndHold(element)
                .moveToElement(targetElement)
                .release()
                .perform();
    }

    public static Point getMiddlePointOfTheScreen() {
        Dimension dimension = Driver.getDriver().manage().window().getSize();
        return new Point((int) (dimension.width * 0.5), (int) (dimension.height * 0.5));
    }

    public static void swipe(String fingerDirection, int speed1to100, int count) {
        Point midPoint = getMiddlePointOfTheScreen();
        for (int i = 0; i < count; i++) {
            waitFor(2);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("mobile: swipeGesture",
                    ImmutableMap.of(
                            "left", midPoint.x * 0.5,
                            "top", midPoint.y * 0.5,
                            "width", midPoint.x,
                            "height", midPoint.y,
                            "direction", fingerDirection.toLowerCase(),
                            "percent", 0.75,
                            "speed", speed1to100 * 1000
                    ));
        }
    }

    public static void swipe(WebElement element, String fingerDirection, int speed1to100) {
        waitFor(1);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("mobile: swipeGesture",
                ImmutableMap.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "direction", fingerDirection.toLowerCase(),
                        "percent", 0.75,
                        "speed", speed1to100 * 1000
                ));
    }

    public static void scroll(String direction, int count) {
        Point midPoint = getMiddlePointOfTheScreen();
        for (int i = 0; i < count; i++) {
            waitFor(2);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("mobile: scrollGesture",
                    ImmutableMap.of(
                            "left", midPoint.x * 0.5,
                            "top", midPoint.y * 0.5,
                            "width", midPoint.x,
                            "height", midPoint.y,
                            "direction", direction.toLowerCase(),
                            "percent", 30,
                            "speed", 4000
                    ));
        }
    }

    public static void scroll(WebElement element, String direction) {
            waitFor(2);
            ((JavascriptExecutor) Driver.getDriver()).executeScript("mobile: scrollGesture",
                    ImmutableMap.of(
                            "elementId", ((RemoteWebElement) element).getId(),
                            "direction", direction.toLowerCase(),
                            "percent", 3
                    ));
    }

    public static void scrollIntoView(String uiSelector) {
        String command = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ");";
        Driver.getDriver().findElement(AppiumBy.androidUIAutomator(command));
    }

    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void scrollRight(WebElement element, int swipePercentage) {
        waitForClickAbility(element, 10);
        actions.clickAndHold(element)
                .moveByOffset(calculateOffsetForLeftAndRightScroll(swipePercentage), 0)
                .release()
                .perform();
    }

    public static void scrollLeft(WebElement element, int swipePercentage, int count) {
        for (int i = 0; i < count; i++) {
            waitForClickAbility(element, 10);
            actions.clickAndHold(element)
                    .moveByOffset(-calculateOffsetForLeftAndRightScroll(swipePercentage), 0)
                    .release()
                    .perform();
            waitFor(1);
        }
    }

    public static void scrollDown(WebElement element, int scrollPercentage) {
        waitForClickAbility(element, 10);
        actions.clickAndHold(element)
                .moveByOffset(0, -(calculateOffsetForUpAndDownScroll(scrollPercentage)))
                .release()
                .perform();
        waitFor(1);
        System.out.println(calculateOffsetForUpAndDownScroll(scrollPercentage));
    }

    public static void scrollUp(WebElement element, int scrollPercentage) {
        waitForClickAbility(element, 10);
        actions.clickAndHold(element)
                .moveByOffset(0, (calculateOffsetForUpAndDownScroll(scrollPercentage)))
                .release()
                .perform();
        waitFor(1);
    }

    //sayfanin belli bir yerinden scroll yapan method yaz


    private static int calculateOffsetForUpAndDownScroll(int scrollAmount) {
        Dimension dimension = Driver.getDriver().manage().window().getSize();
        return ((scrollAmount * dimension.height) / 100);
    }

    private static int calculateOffsetForLeftAndRightScroll(int scrollAmount) {
        Dimension dimension = Driver.getDriver().manage().window().getSize();
        return ((scrollAmount * dimension.width) / 100);
    }

    public static WebElement waitForClickAbility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


}

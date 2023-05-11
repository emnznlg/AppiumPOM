package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class N11Page {

    public N11Page() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver()), this);
    }


    @AndroidFindBy(accessibility = "Kategoriler")
    public WebElement kategorilerBtn1;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.dmall.mfandroid:id/smallLabel\")")
    public WebElement kategorilerBtn2;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Kategoriler\")")
    public WebElement kategorilerBtn3;


}
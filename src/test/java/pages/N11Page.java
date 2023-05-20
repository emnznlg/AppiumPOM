package pages;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class N11Page {

    public N11Page() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver()), this);
    }

    @AndroidFindBy(accessibility = "Kategoriler")
    public WebElement kategorilerBtn;

    @AndroidFindBy(uiAutomator = "text(\"Elektronik\")")
    public WebElement elektronikBtn;

    @AndroidFindBy(uiAutomator = "text(\"Bilgisayar\")")
    public WebElement bilgisayarBtn;

    @AndroidFindBy(uiAutomator = "text(\"Tablet\")")
    public WebElement tabletBtn;

    @AndroidFindBy(xpath = "(//*[@resource-id=\"com.dmall.mfandroid:id/ivImage\"])[1]")
    public WebElement ilkUrun;

    @AndroidFindAll({@AndroidBy(xpath = "//*[@resource-id=\"com.dmall.mfandroid:id/productImagesIndicator\"]//*[@class=\"android.view.View\"]")})
    public List<WebElement> urunResimNoktalar;

    @AndroidFindBy(id = "com.dmall.mfandroid:id/productImageIV")
    public WebElement urunResim;











}

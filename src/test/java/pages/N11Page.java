package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class N11Page {

    public N11Page() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Kategoriler\"]")
    public WebElement kategorilerBtn;

    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Sepetim\"]")
    public WebElement sepetimBtn;

}

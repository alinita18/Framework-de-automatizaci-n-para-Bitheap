package selenium.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.automation.drivers.DriverSingleton;

public class OrderDetails {
    private WebDriver driver;

    public OrderDetails(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#post-207 > header > h1")
    private WebElement orderStatus;

    public String getOrderStatus(){
        return orderStatus.getText();
    }
}

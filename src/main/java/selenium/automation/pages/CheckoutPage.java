package selenium.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.automation.drivers.DriverSingleton;
import selenium.automation.utils.Constants;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "billing_first_name")
    private WebElement firstName;

    @FindBy(id = "billing_last_name")
    private WebElement lastName;

    @FindBy(css = "#billing_state_field > span > span > span.selection > span > span.select2-selection__arrow")
    private WebElement countryButton;

    @FindBy(id = "select2-billing_state-container")
    private List<WebElement> country;

    @FindBy(id = "billing_address_1")
    private WebElement address;

    @FindBy(id = "billing_postcode")
    private WebElement zipcode;

    @FindBy(id = "billing_city")
    private WebElement townName;

    @FindBy(css = "#order_review > table > tfoot > tr.order-total > td > strong > span > bdi")
    private WebElement totalAmount;

    @FindBy(id = "place_order")
    private WebElement placeOrder;

    @FindBy(css = "#post-207 > header > h1")
    private WebElement orderStatus;

    public void provideBillingDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(address));
        address.sendKeys("abc");
    }

    public void providePersonalDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.sendKeys("David");
        lastName.sendKeys("Mourlot Matos");
        countryButton.click();
        for(WebElement option : country){
            if(option.getText().equals("Uruguay")){
                option.click();
                break;
            }
        }
        zipcode.sendKeys("1130");
        townName.sendKeys("Punta del Este");

    }

    public String getTotalAmount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.visibilityOf(totalAmount));
        return totalAmount.getText();
    }

    public void placeOrder(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
        placeOrder.click();
    }

    public String getOrderStatus(){
        return orderStatus.getText();
    }
}

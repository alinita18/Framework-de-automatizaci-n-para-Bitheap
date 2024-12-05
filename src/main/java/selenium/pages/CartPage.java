package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.drivers.DriverSingleton;
import selenium.utils.Constants;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;

    public CartPage(){
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#post-206 > content > div > div.woocommerce > div.cart-collaterals > div > div > a")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = "#coupon_code")
    private WebElement couponCode;

    @FindBy(css = "#post-206 > content > div > div.woocommerce > form > table > tbody > tr:nth-child(2) > td > div > button")
    private WebElement applyCoupon;

    @FindBy(css = "#post-206 > content > div > div.woocommerce > form > table > tbody > tr.woocommerce-cart-form__cart-item.cart_item > td.product-price > span > bdi")
    private WebElement price;

    @FindBy(css = "#post-206 > content > div > div.woocommerce > div.cart-collaterals > div > table > tbody > tr.order-total > td > strong > span > bdi")
    private WebElement total;


    public void setProceedToCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));

        proceedToCheckoutButton.click();
    }

    public void checkApplyCoupon(){
        couponCode.sendKeys("UDEMY-CHALLENGE");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.TIMEOUT));
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton));

        applyCoupon.click();
        String total = this.total.getText().replace("$","");
        System.out.println(total);
        String price = this.price.getText().replace("$","");
        System.out.println(price);
        if(Float.parseFloat(total) < Float.parseFloat(price)){
            System.out.println("Cupon aplicado correctamente");
        } else {
            System.out.println("Error aplicando el cupon");
        }
    }
}

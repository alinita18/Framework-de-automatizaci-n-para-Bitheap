package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.drivers.DriverSingleton;
import selenium.pages.*;
import selenium.utils.Constants;
import selenium.utils.FrameworkProperties;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws Exception{
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        DriverSingleton driverSingleton = DriverSingleton.getInstance();
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://bitheap.tech");

        HomePage homePage = new HomePage();
        SignInPage signInPage = new SignInPage();
        ShopPage shopPage = new ShopPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty("email"), frameworkProperties.getProperty("password"));
        if((homePage.getUserName().equals("Hello,Alina"))){
            System.out.println("Test Passed");
        } else{
            System.out.println("Test Failed");
        }
        homePage.clickShopButton();
        shopPage.paging();
        shopPage.addElementToCart();
        shopPage.proceedToCheckout();
        cartPage.setProceedToCheckout();
        //cartPage.checkApplyCoupon();
        checkoutPage.provideBillingDetails();
        checkoutPage.placeOrder();

        if(checkoutPage.getOrderStatus().equals("Order Received"))
            System.out.println("Test Passed");
        else {
            System.out.println("Test failed");
            System.out.println(checkoutPage.getOrderStatus());
        }
        DriverSingleton.closeObjectInstance();
    }
}
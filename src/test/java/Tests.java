import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.drivers.DriverSingleton;
import selenium.pages.*;
import selenium.pages.ShopPage;
import selenium.utils.Constants;
import selenium.utils.FrameworkProperties;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static HomePage homePage;
    static SignInPage signInPage;
    static CheckoutPage checkoutPage;
    static ShopPage shopPage;
    static CartPage cartPage;
    static OrderDetails orderDetails;

    @BeforeAll
    public static void initializeObjects(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance();
        driver = DriverSingleton.getDriver();
        homePage = new HomePage();
        signInPage = new SignInPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        orderDetails = new OrderDetails();
    }


    @ParameterizedTest
    @CsvSource({
            "alinita18@gmail.com, QXNkMTIzKi4=",
            "laurentiu@bitheap.tech, MTIzNDU2"
    })
    public void testingAuthentication(String name, String password) throws Exception{
        driver.get(Constants.URL);
        homePage.clickSignIn();
        /*signInPage.logIn(frameworkProperties.getProperty(Constants.EMAIL),frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME),homePage.getUserName());*/
        signInPage.logIn(name, password);
        Thread.sleep(1000);
        assertTrue(homePage.getUserName().contains("Hello"));
        signInPage.logout();
    }

    @Test
    public void testingAddingThingsToCart(){
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.goToSecondPage();
        shopPage.addElementToCart();
        assertEquals(Constants.CAR_QUANTITY, shopPage.getNumberOfProducts());
    }

    @Test
    public void testingTheFullBuyingProcess(){
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.goToSecondPage();
        shopPage.addElementToCart();
        shopPage.proceedToCheckout();
        cartPage.setProceedToCheckout();
        checkoutPage.provideBillingDetails();
        checkoutPage.providePersonalDetails();
        checkoutPage.placeOrder();
        assertEquals("Checkout",orderDetails.getOrderStatus());
    }

    @AfterAll
    public static void closeObjects(){
        driver.close();
    }

}

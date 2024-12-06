import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.drivers.DriverSingleton;
import selenium.pages.*;
import selenium.pages.ShopPage;
import selenium.utils.Constants;
import selenium.utils.FrameworkProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    static FrameworkProperties frameworkProperties;
    static WebDriver driver;
    static HomePage homePage;
    static SignInPage signInPage;
    static CheckoutPage checkoutPage;
    static ShopPage shopPage;
    static CartPage cartPage;

    @BeforeAll
    public static void initializeObjects(){
        frameworkProperties = new FrameworkProperties();
        DriverSingleton.getInstance();
        driver = DriverSingleton.getDriver();
        homePage = new HomePage();
        signInPage = new SignInPage();
        shopPage = new ShopPage();
        checkoutPage = new CheckoutPage();
    }


    @Test
    public void testingAuthentication(){
        driver.get(Constants.URL);
        homePage.clickSignIn();
        signInPage.logIn(frameworkProperties.getProperty(Constants.EMAIL),frameworkProperties.getProperty(Constants.PASSWORD));
        assertEquals(frameworkProperties.getProperty(Constants.USERNAME),homePage.getUserName());
    }

    @Test
    public void testingAddingThingsToCart(){
        driver.get(Constants.URL);
        homePage.clickShopButton();
        shopPage.goToSecondPage();
        shopPage.addElementToCart();
        assertEquals(Constants.CAR_QUANTITY, shopPage.getNumberOfProducts());
    }

    @AfterAll
    public static void closeObjects(){
        driver.close();
    }

}

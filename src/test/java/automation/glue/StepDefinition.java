package automation.glue;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import selenium.automation.config.AutomationFrameworkConfiguration;
import org.springframework.test.context.ContextConfiguration;
import selenium.automation.drivers.DriverSingleton;
import selenium.automation.pages.*;
import selenium.automation.utils.ConfigurationProperties;
import selenium.automation.utils.Constants;
import selenium.automation.utils.TestCases;
import selenium.automation.utils.Utils;
import selenium.automation.utils.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private HomePage homePage;
    private SignInPage signInPage;
    private CheckoutPage checkoutPage;
    private ShopPage shopPage;
    private CartPage cartPage;
    ExtentTest test;
    static ExtentReports report = new ExtentReports("report/TestReport.html");

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects(){

        DriverSingleton.getInstance(configurationProperties.getBrowser());
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
        TestCases[] tests = TestCases.values();
        test = report.startTest(tests[Utils.testCount].getTestName());
        Log.getLogData(Log.class.getName());
        Log.startTest(tests[Utils.testCount].getTestName());
        Utils.testCount++;
    }

    @Given("^I go to the Website")
    public void i_go_to_the_Website(){
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
        Log.info("Navigating to "+ Constants.URL);
        test.log(LogStatus.PASS,"Navigating to " + Constants.URL);
    }

    @When("^I click on Sign In button")
    public void i_click_on_Sign_In_button(){
        Log.info("Starting \"i_click_on_Sign_In_button\" method");
        homePage.clickSignIn();
        Log.info("Method \"i_click_on_Sign_In_button\" has finished successfully. Sign In button has been clicked");
        test.log(LogStatus.PASS,"Sign In button has been clicked ");
    }

    @When("^I add one element to the cart")
    public void i_add_one_element_to_the_cart(){
        Log.info("Starting \"i_add_one_element_to_the_cart\" method");
        homePage.clickShopButton();
        Log.info("Shop button clicked correctly");
        shopPage.paging();
        Log.info("Shop page scrolled successfully");
        shopPage.addElementToCart();
        Log.info("Method \"i_add_one_element_to_the_cart\" has finished successfully. Element added successfully to the cart");
        test.log(LogStatus.PASS,"One element was added to the cart ");
    }

    @And("^I specify my credentials and click login")
    public void i_specify_my_credentials_and_click_login(){
        Log.info("Starting \"i_specify_my_credentials_and_click_login\" method");
        signInPage.logIn(configurationProperties.getEmail(),configurationProperties.getPassword());
        Log.info("Method \"i_specify_my_credentials_and_click_login\" has finished successfully. Login button has been clicked");
        test.log(LogStatus.PASS,"Login has been clicked");
    }

    @And("^I proceed to checkout")
    public void  i_proceed_to_checkout(){
        Log.info("Starting \"i_proceed_to_checkout\" method");
        shopPage.proceedToCheckout();
        Log.info("Cart button has been clicked");
        cartPage.setProceedToCheckout();
        Log.info("Method \"i_proceed_to_checkout\" has finished successfully. Proceed to Checkout button has been clicked");
        test.log(LogStatus.PASS,"We proceed to checkout");
    }

    @And("^I confirm address, shipping, payment and final order")
    public void i_confirm_address_shipping_payment_and_final_order(){
        Log.info("Starting \"i_confirm_address_shipping_payment_and_final_order\" method");
        checkoutPage.provideBillingDetails();
        Log.info("Address information sent successfully");
        checkoutPage.providePersonalDetails();
        Log.info("Personal information sent successfully");
        checkoutPage.placeOrder();
        Log.info("Method \"i_confirm_address_shipping_payment_and_final_order\" has finished successfully. Checkout button has been clicked");
        test.log(LogStatus.PASS,"We confirm the final order");
    }

    @Then("^I can log into the website")
    public void i_can_log_into_the_website(){
        Log.info("Starting \"i_can_log_into_the_website\" method");
        if(configurationProperties.getUsername().equals(homePage.getUserName())){
            Log.info("Method \" i_can_log_into_the_website\" has finished successfully. Successful authentication");
            test.log(LogStatus.PASS,"The authentication is successful");
        } else{
            Log.error("Method \" i_can_log_into_the_website\" has failed. Failed authentication");
            test.log(LogStatus.FAIL,"The authentication is failed");
        }

        assertEquals(configurationProperties.getUsername(),homePage.getUserName());
    }

    @Then("^The elements are bought")
    public void the_elements_are_bought(){
        Log.info("Starting \"the_elements_are_bought\" method");
        if(checkoutPage.getOrderStatus().equals("Checkout")){
            Log.info("Method \"the_elements_are_bought\" has finished successfully. Successful purchase");
            test.log(LogStatus.PASS,"The item was bought");
        } else{
            Log.error("Method \" the_elements_are_bought\" has failed. Failed purchase");
            test.log(LogStatus.FAIL,"The item wasn't bought");
        }
        assertEquals("Checkout", checkoutPage.getOrderStatus());
    }

    @After
    public void after(){
        report.endTest(test);
        report.flush();
        }

}

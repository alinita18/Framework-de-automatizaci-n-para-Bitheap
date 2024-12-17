package automation.glue;

import io.cucumber.java.Before;
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
import selenium.automation.pages.CheckoutPage;
import selenium.automation.pages.HomePage;
import selenium.automation.pages.SignInPage;
import selenium.automation.utils.ConfigurationProperties;
import selenium.automation.utils.Constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
@CucumberContextConfiguration
@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private HomePage homePage;
    private SignInPage signInPage;
    private CheckoutPage checkoutPage;

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects(){

        DriverSingleton.getInstance(configurationProperties.getBrowser());
        homePage = new HomePage();
        signInPage = new SignInPage();
        checkoutPage = new CheckoutPage();
    }

    @Given("^I go to the Website")
    public void i_go_to_the_Website(){
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
    }

    @When("^I click on Sign In button")
    public void i_click_on_Sign_In_button(){
        homePage.clickSignIn();
    }

    @And("^I specify my credentials and click login")
    public void i_specify_my_credentials_and_click_login(){
        signInPage.logIn(configurationProperties.getEmail(),configurationProperties.getPassword());
    }

    @Then("^I can log into the website")
    public void i_can_log_into_the_website(){
        System.out.println(configurationProperties.getUsername());
        System.out.println(configurationProperties.getPassword());
        System.out.println(configurationProperties.getBrowser());
        System.out.println(configurationProperties.getEmail());

        assertEquals(configurationProperties.getUsername(),homePage.getUserName());
    }
}

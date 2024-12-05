package selenium.drivers.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Firefox implements DriverStrategy {
    @Override
    public WebDriver setStrategy() {
        System.setProperty("web-driver.gecko.driver","src/main/resources/geckodriver.exe");

        return new FirefoxDriver();
    }

}

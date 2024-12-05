package selenium.drivers.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chrome implements DriverStrategy {

    @Override
    public WebDriver setStrategy() {
        System.setProperty("web-driver.chrome.driver","src/main/resources/chrome.exe");
        /*ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtensions", false);
        options.addArguments("--no-sandbox");*/

        return new ChromeDriver();
    }
}

package selenium.automation.drivers.strategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

public class PhantomJs implements DriverStrategy {
    @Override
    public WebDriver setStrategy() {
        System.setProperty("phantoms.binary.path","src/main/resources/phantomjs.exe");
        /* DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setJavascriptEnabled(true);*/

        return new PhantomJSDriver();
    }
}

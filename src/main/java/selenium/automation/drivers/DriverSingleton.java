package selenium.automation.drivers;

import org.openqa.selenium.WebDriver;
import selenium.automation.drivers.strategies.DriverStrategy;
import selenium.automation.drivers.strategies.DriverStrategyImplementer;
import selenium.automation.utils.FrameworkProperties;

import java.time.Duration;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    private DriverSingleton(String browser){
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        instantiate(browser);
    }

    public WebDriver instantiate(String strategy){
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy((strategy));
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }

    public static DriverSingleton getInstance(String browser){
        if(instance == null){
            instance = new DriverSingleton(browser);
        }
        return instance;
    }

    public static void closeObjectInstance(){
        instance = null;
        driver.quit();
    }

    public static WebDriver getDriver(){
        return driver;
    }
}

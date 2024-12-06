package selenium.drivers;

import org.openqa.selenium.WebDriver;
import selenium.drivers.strategies.DriverStrategy;
import selenium.drivers.strategies.DriverStrategyImplementer;
import selenium.utils.Constants;
import selenium.utils.FrameworkProperties;

import java.time.Duration;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static WebDriver driver;

    private DriverSingleton(){
        FrameworkProperties frameworkProperties = new FrameworkProperties();
        instantiate(frameworkProperties.getProperty(Constants.BROWSER));
    }

    public WebDriver instantiate(String strategy){
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy((strategy));
        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        return driver;
    }

    public static DriverSingleton getInstance(){
        if(instance == null){
            instance = new DriverSingleton();
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

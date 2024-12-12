package selenium.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import selenium.drivers.DriverSingleton;

import java.io.File;
import java.util.Base64;
public class Utils {
    public static String decode64(String encodedStr){
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(encodedStr.getBytes()));
    }

    public static boolean takeScreenshot(){
        File file = ((TakesScreenshot) DriverSingleton.getDriver()).getScreenshotAs(OutputType.FILE);
        FileCopyUtils.copy();
    }

}

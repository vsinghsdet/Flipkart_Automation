package flipkart_automation;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSingleton {
    private static WebDriver driver = null;

    private DriverSingleton(){};

    public static WebDriver getDriver() throws MalformedURLException{
     
     if(driver==null){
         System.out.println("Driver---Initializing driver---Started");
         driver = new ChromeDriver();
         System.out.println("Driver---Initializing driver---Success");
     }
     return driver;
 }
}

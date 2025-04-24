package flipkart_automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import flipkart_automation.BaseTest;
import flipkart_automation.DriverFactory;
import flipkart_automation.pages.HomePage;
import flipkart_automation.pages.SearchResults;

public class testCase02 extends BaseTest{
    
    HomePage homePage;
    SearchResults searchResults;


    @Test
    public void iPhoneTC(){
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.flipkart.com/");
        homePage = new HomePage(driver);
        searchResults = new SearchResults(driver);
        homePage.makeSearch("iPhone");
        searchResults.printProductTitle(5);
    }

}

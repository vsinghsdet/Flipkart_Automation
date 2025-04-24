package flipkart_automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import flipkart_automation.BaseTest;
import flipkart_automation.DriverFactory;
import flipkart_automation.pages.HomePage;
import flipkart_automation.pages.SearchResults;

public class testCase01 extends BaseTest{
    
    HomePage homePage;
    SearchResults searchResults;


    @Test
    public void washingMacineTC() throws InterruptedException{
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.flipkart.com/");
        homePage = new HomePage(driver);
        searchResults = new SearchResults(driver);
        homePage.makeSearch("Washing Machine");
        searchResults.sortByPopularity();
        Thread.sleep(2000);
        int count = searchResults.countBasedOnRating(4);
        System.out.println("Number of Items with less than or equal to given rating 4 are: " + count);
    }

}

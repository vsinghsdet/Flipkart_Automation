package flipkart_automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import flipkart_automation.BaseTest;
import flipkart_automation.DriverFactory;
import flipkart_automation.pages.HomePage;
import flipkart_automation.pages.SearchResults;

public class testCase03 extends BaseTest{
    
    HomePage homePage;
    SearchResults searchResults;


    @Test
    public void coffeeMugTC() throws InterruptedException{
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.flipkart.com/");
        homePage = new HomePage(driver);
        searchResults = new SearchResults(driver);
        homePage.makeSearch("Coffee Mug");
        searchResults.filterProductsBasedonRating();
        Thread.sleep(2000);
        searchResults.printCoffeeMug();
    }

}

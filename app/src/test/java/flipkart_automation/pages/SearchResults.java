package flipkart_automation.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import flipkart_automation.Wrappers.Wrappers;

public class SearchResults {
    
    WebDriver driver;

    @FindBy(xpath = "//div[@class='sHCOk2']/div[2]")
    WebElement popularity;

    @FindBy(className = "XQDdHH")
    List<WebElement> rating;

    @FindBy(className = "yKfJKb")
    List<WebElement> itemTiles;

    @FindBy(xpath = "(//div[contains(text(),'above')])[1]")
    WebElement starRatingFilter;

    @FindBy(className = "slAVV4")
    List<WebElement> verticalItemTiles;

    public SearchResults(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void sortByPopularity(){
        popularity.click();
    }

    public int countBasedOnRating(int givenRating){
        int count = Wrappers.countOfItemsBasedOnRatings(driver, rating, givenRating);
        return count;
    }

    public void printProductTitle(int givenDiscount){
        Wrappers.printingProductTitle(driver, itemTiles, givenDiscount);
    }

    public void filterProductsBasedonRating(){
        starRatingFilter.click();
    }

    public void printCoffeeMug(){
        Wrappers.printingCoffeeMugTitle(driver, verticalItemTiles);
    }

}

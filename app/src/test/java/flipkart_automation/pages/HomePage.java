package flipkart_automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import flipkart_automation.Wrappers.Wrappers;

public class HomePage {
    
    WebDriver driver;

    @FindBy(xpath = "//input[contains(@title,'Search')]")
    WebElement searchBoxInput;

    @FindBy(xpath = "//button[contains(@title,'Search ')]")
    WebElement searchIcon;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    public void makeSearch(String searchText){
        Wrappers.sendKeys(driver, searchBoxInput, searchText);
        Wrappers.click(searchIcon, driver);
        
    }


}

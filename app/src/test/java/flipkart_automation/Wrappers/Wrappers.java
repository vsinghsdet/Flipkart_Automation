
package flipkart_automation.Wrappers;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Wrappers {

    public static void click(WebElement elementToClick, WebDriver driver) {
        if (elementToClick.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", elementToClick);
            elementToClick.click();
        }
    }

    public static void sendKeys(WebDriver driver, WebElement inputBox, String keysToSend) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", inputBox);
        inputBox.clear();
        inputBox.sendKeys(keysToSend);
    }

    public static void navigate(WebDriver driver, String url) {
        if (!driver.getCurrentUrl().equals(url)) {
            driver.get(url);
        }
    }

    public static int countOfItemsBasedOnRatings(WebDriver driver, List<WebElement> rating, int givenRating) {
        int count = 0;
        for (WebElement element : rating) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView();", element);
            if (Double.parseDouble(element.getText()) >= 4) {
                count++;
            }
            // rating = driver.findElements(By.className("XQDdHH"));
        }
        
        return count;
    }

    public static void printingProductTitle(WebDriver driver, List<WebElement> items, int givenDiscount) {
        try {
            for (WebElement parentElement : items) {
                WebElement discountElement = parentElement.findElement(By.xpath(".//div[@class='UkUFwK']/span"));
                int discountInteger = Integer.parseInt(discountElement.getText().split("%")[0]);
                if (discountInteger > givenDiscount) {
                    WebElement productTitleElement = parentElement.findElement(By.className("KzDlHZ"));
                    if(discountElement.getText().contains("% off")){
                        System.out.println(productTitleElement.getText() + " " + discountElement.getText());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void printingCoffeeMugTitle(WebDriver driver, List<WebElement> listOfElements) {

            Map<WebElement, Integer> map = new HashMap<>();
            for (WebElement element : listOfElements) {
                WebElement numberOfRatings = element.findElement(By.className("Wphh3N"));
                int ratingsInt = Integer.parseInt(numberOfRatings.getText().replaceAll("[(),]", ""));
                map.put(element, ratingsInt);
            }

            Map<WebElement, Integer> sortedMap = map.entrySet()
                    .stream()
                    .sorted((a,b) -> b.getValue() - a.getValue())
                    .limit(5)
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new));

            
            for (WebElement element : sortedMap.keySet()) {
                System.out.println("Title: " + element.findElement(By.className("wjcEIp")).getText());
                System.out.println("Rating: " + element.findElement(By.className("Wphh3N")).getText());
                System.out.println("Image URL: "
                        + element.findElement(By.xpath(".//img[contains(@class,'DByuf4')]")).getDomAttribute("src"));
            }
        }
    

    public static WebElement findElementWithRetry(WebDriver driver, By by, int retryCount) {
        return driver.findElement(by);
    }

    public static String capture(WebDriver driver) throws IOException{
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String reportsFolder = System.getProperty("user.dir")+File.separator+"reports";
        File directory = new File(reportsFolder);
        if(!directory.exists()){
            directory.mkdirs();
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        long formattedTimeStamp = Long.parseLong(now.format(formatter));

        String screenShotPath = reportsFolder+File.separator+formattedTimeStamp+".png";
        File destFile = new File(screenShotPath);
        FileUtils.copyFile(srcFile, destFile);
        System.out.println("Screenshot path: "+ screenShotPath);
        return screenShotPath;
    }

}

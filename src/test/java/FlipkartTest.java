import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipkartTest {
    public static void main(String[] args) {
        // Configure ChromeDriver path

        System.setProperty("webdriver.chrome.driver", "/Users/ankitgupta/Desktop/prac/selenium/src/main/resources/chromedriver");

        // Configure ChromeOptions and desired capabilities for BrowserStack
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserName", "chrome");
        options.setCapability("browserVersion", "latest");
        options.setCapability("os", "Mac OS X");
        options.setCapability("os_version", "12.5");
        options.setCapability("build", "Parallel Test");
        options.setCapability("name", "Test Case 1");
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        // Instantiate the WebDriver
        WebDriver driver = new ChromeDriver(    options);
        try {
        // Load Flipkart.com
        driver.get("https://www.flipkart.com");

        // Search for the product "Samsung Galaxy S10"
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Samsung Galaxy S10");
        searchBox.submit();
        WebDriverWait wait1 = new WebDriverWait(driver, 15);
         wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@title='Mobiles']")));
        // Click on "Mobiles" in the categories section

            WebDriverWait wait3 = new WebDriverWait(driver, 10);
            wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2KpZ6l._2doB4z")));

            driver.findElement(By.cssSelector("._2KpZ6l._2doB4z")).click();

        WebElement categoriesLink = driver.findElement(By.xpath("//a[@title='Mobiles']"));

        categoriesLink.click();

        WebDriverWait wait2 = new WebDriverWait(driver, 20);
        wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='_2gmUFU _3V8rao'][normalize-space()='Brand']")));
        // Apply the filters  ////div[@title='SAMSUNG']//input[@type='checkbox']
        WebElement brandFilter = driver.findElement(By.xpath("//div[@title='SAMSUNG']//div[@class='_24_Dny']"));
        brandFilter.click();
            WebDriverWait wait5 = new WebDriverWait(driver, 20);
            wait5.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='_24_Dny _3tCU7L']")));

            WebElement assuredFilter = driver.findElement(By.xpath("//div[@class='_24_Dny _3tCU7L']"));
        assuredFilter.click();
            WebDriverWait wait4 = new WebDriverWait(driver, 20);
            wait4.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[normalize-space()='Price -- High to Low']")));

            // Sort by Price: High to Low
       WebElement sortDropdown = driver.findElement(By.xpath("//div[normalize-space()='Price -- High to Low']"));
          sortDropdown.click();
        // Read the set of results on page 1
        WebDriverWait wait = new WebDriverWait(driver, 20);
        List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='_1AtVbE col-12-12']")));
          //List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("._1AtVbE']")));
//class="_1AtVbE col-12-12"

        // Extract the desired information from the results
        List<Map<String, String>> productList = new ArrayList<>();
        for (WebElement result : results) {
          System.out.println(results.size());
            String productName = result.findElement(By.xpath("//div[@class='_4rR01T']")).getText();//_13oc-S//_4rR01T
          System.out.println(productName);
            String displayPrice = result.findElement(By.cssSelector("_1_WHN1")).getText();//_30jeq3 _1_WHN1
            String productLink = result.findElement(By.cssSelector("_1fQZEK")).getAttribute("href");

            Map<String, String> productDetails = new HashMap<>();
            productDetails.put("Product Name", productName);
            productDetails.put("Display Price", displayPrice);
            productDetails.put("Link to Product Details Page", productLink);

            productList.add(productDetails);
        }

      // Print the list on the console
        for (Map<String, String> product : productList) {
            System.out.println(product);
       }
          driver.quit();
        // Close the browser
        } catch (Exception e) {
            e.printStackTrace();
            driver.quit();
        }

    }
}

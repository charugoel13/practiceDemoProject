import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    @BeforeSuite
    public void Test1(){

        System.out.println("hello1");
    }
    @BeforeMethod

    public void Test2(){

        System.out.println("hello2");
    }

    @BeforeClass
    public void Test3(){

        System.out.println("hello3");
    }
    @BeforeTest
    public void Test4(){

        System.out.println("hello4");
    }
    @Test
    public void Test5(){

        System.out.println("hello5");
    }

    public static void main(String[] args)  {

            // Configure ChromeDriver path
        System.setProperty("webdriver.chrome.driver","/Users/charu.goelmonotype.com/Desktop/prac_charu/PageObjectModel-master/chromedriver");

        // Configure ChromeOptions and desired capabilities for BrowserStack
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browserName", "chrome");
            options.setCapability("browserVersion", "latest");
            options.setCapability("os", "Mac OS X");
            options.setCapability("os_version", "12.5");
            options.setCapability("build", "Parallel Test");
            options.setCapability("name", "Test Case 1");

            // Instantiate the WebDriver
            WebDriver driver = new ChromeDriver(options);
        try {
            // Load Flipkart.com
            driver.get("https://www.flipkart.com");

            // Search for the product "Samsung Galaxy S10"
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Samsung Galaxy S10");

           searchBox.submit();
        // Close login popup if present

            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("._2KpZ6l._2doB4z")));

            driver.findElement(By.cssSelector("._2KpZ6l._2doB4z")).click();

            WebDriverWait wait1 = new WebDriverWait(driver, 10);
            wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@title='Mobiles']")));
            // Click on "Mobiles" in the categories section
            WebElement categoriesLink = driver.findElement(By.xpath("//a[@title='Mobiles']"));

            //Alert alert = driver.switchTo().alert();
            //alert.accept();
           // String alertText = alert.getText();
           // System.out.println("Alert text is " + alertText);
           // alert.dismiss();
            categoriesLink.click();
        System.out.println("helllo");
            // Apply the filters
           // WebElement brandFilter = driver.findElement(By.xpath("//div[@title='SAMSUNG']//div[@class='_24_Dny']"));
           // brandFilter.click();
            wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@title='SAMSUNG']//div[@class='_24_Dny']")));
        WebElement samsungCheckbox = driver.findElement(By.xpath("//div[@title='SAMSUNG']//div[@class='_24_Dny']"));
        samsungCheckbox.click();
            wait1.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='_35AjG4']")));

            WebElement samsungCheckboxClose = driver.findElement(By.xpath("//div[@class='_35AjG4']"));
            samsungCheckboxClose.click();


            // Sort by Price: High to Low
            WebElement sortDropdown = driver.findElement(By.xpath("//div[normalize-space()='Price -- High to Low']"));
            //sortDropdown.sendKeys("Price -- High to Low");
            sortDropdown.click();

            WebDriverWait wait2 = new WebDriverWait(driver, 30);
            wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='_24_Dny _3tCU7L']")));
            WebElement assuredFilter = driver.findElement(By.xpath("//div[@class='_24_Dny _3tCU7L']"));
            // Scroll to the Samsung checkbox
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", assuredFilter);

             assuredFilter.click();

            // Read the set of results on page 1
            //WebDriverWait wait1 = new WebDriverWait(driver, 10);
            List<WebElement> results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("._1AtVbE")));

            // Extract the desired information from the results
            List<Map<String, String>> productList = new ArrayList<>();
            for (WebElement result : results) {
                String productName = result.findElement(By.xpath("//div[normalize-space()='SAMSUNG Galaxy A14 5G (Dark Red, 64 GB)']")).getText();
                String displayPrice = result.findElement(By.cssSelector("_30jeq3")).getText();
                String productLink = result.findElement(By.cssSelector("aIRw6_")).getAttribute("href");

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

            // Close the browser

        } catch (Exception e) {
            System.out.println("Login popup not found or already closed.");
            //driver.quit();
            e.printStackTrace();
        }
        }
    }

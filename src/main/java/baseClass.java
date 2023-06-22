import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class baseClass {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","/Users/charu.goelmonotype.com/Desktop/prac_charu/PageObjectModel-master/chromedriver");
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.google.com");
        System.out.println(driver.getTitle());
    }
}

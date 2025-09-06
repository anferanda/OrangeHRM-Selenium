package jpetstore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    WebDriver driver;

    @BeforeMethod
    void setup(){
        driver = new ChromeDriver();
        driver.get("https://jpetstore.aspectran.com/");
    }

    @AfterMethod
    void teardown(){
        driver.close();
    }
}

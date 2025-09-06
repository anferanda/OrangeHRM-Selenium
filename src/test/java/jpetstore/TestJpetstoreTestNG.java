package jpetstore;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestJpetstoreTestNG extends BaseTest{
    @Test
    void loginJpetstore() throws InterruptedException{
        driver.findElement(By.xpath("//a[@href='/account/signonForm']")).click();
        driver.findElement(By.name("username"));
        Thread.sleep(2000);
        driver.findElement(By.name("password"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("WelcomeContent"));
        Thread.sleep(2000);
    }
}

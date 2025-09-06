package OrangeHRM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestOrangeHRMTestNG extends BaseOrange{

    @Test
    void loginOrangeHRM() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys("admin123");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("app"))).isDisplayed();
        Thread.sleep(5000); // tunggu 2 detik
    }

    @Test
    void retrieveDataAdmin() throws InterruptedException {
        loginOrangeHRM();
        driver.findElement(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']")).click();
        WebElement adminHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Admin']")));
        WebElement clickUserRole = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text-input')][1]")));
        clickUserRole.click();
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//span[normalize-space()='Admin']")));
        adminOption.click();

        // Tunggu tombol Search bisa diklik lalu klik
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Search']")));
        searchButton.click();
        Thread.sleep(5000);
    }

    @Test
    void addUserAdmin() throws InterruptedException {
        loginOrangeHRM();
        driver.findElement(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']")).click();
        WebElement adminHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Admin']")));

        // Tunggu tombol Add bisa diklik lalu klik
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Add']")));
        addButton.click();

        // Tunggu header Add User muncul
        WebElement addUserHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Add User']")));
        Thread.sleep(5000);

        // Input User Role
        WebElement clickUserRole = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text-input')][1]")));
                clickUserRole.click();
        WebElement adminOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//span[normalize-space()='Admin']")));
                adminOption.click();

        //Input Status
        WebElement clickStatus = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text-input')][1]")));
                clickStatus.click();
        WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='listbox']//span[normalize-space()='Enabled']")));
                statusOption.click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//input[@placeholder='Type for hints...']"))).sendKeys("manda");

        // Input Nama Employee
        WebElement empName = driver.findElement(By.xpath("//label[text()='Employee Name']/following::input[1]"));
                empName.sendKeys("manda");
        // Tunggu suggestion muncul
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement suggestion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='listbox']//span[text()='manda akhil user']")));
        suggestion.click();

        // Input Username, Password, Confirm Password
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Username']/following::input[1]"))).sendKeys("mandaaa");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Password']/following::input[1]"))).sendKeys("manda123");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Confirm Password']/following::input[1]"))).sendKeys("manda123");
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and normalize-space()='Save']")));
        saveButton.click();

        // Menampilkan data setelah di save
        WebElement newUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='oxd-table']//div[text()='mandaaaa']")));
        Thread.sleep(5000);

        //Error ada pop up block change password
    }

    @Test
    void editUser() throws InterruptedException {
        loginOrangeHRM();
        driver.findElement(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']")).click();
        WebElement adminHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Admin']")));
        WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border'][.//div[text()='mandaaaa']]//button[2]")));
        editBtn.click();

        // Tunggu header Edit User muncul
        WebElement editUserHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Edit User']")));
        WebElement userName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Username']/following::input[1]")));

        userName.sendKeys(Keys.CONTROL + "a"); // select all
        userName.sendKeys(Keys.DELETE);       // delete
        userName.sendKeys("manda1234");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and normalize-space()='Save']")));
        saveButton.click();
//        WebElement editSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//h6[text()='Add User']")));
        Thread.sleep(5000);
    }
    @Test
    void deleteUser() throws InterruptedException {
        loginOrangeHRM();
        driver.findElement(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']")).click();
        WebElement adminHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Admin']")));
        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border'][.//div[text()='manda1234']]//button[1]")));
        deleteBtn.click();
        Thread.sleep(5000);
        WebElement confirmBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@type='button' and normalize-space()='Yes, Delete']")));
        confirmBtn.click();
        WebElement deleteSuccess = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Admin']")));
        Thread.sleep(5000);
    }
    @Test
    void retrieveJob() throws InterruptedException {
        loginOrangeHRM();
        driver.findElement(By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']")).click();
        WebElement adminHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Admin']")));
        driver.findElement(By.xpath("//span[@class='oxd-topbar-body-nav-tab-item' and normalize-space()='Job']")).click();
        driver.findElement(By.xpath("//a[@role='menuitem' and normalize-space()='Job Titles']")).click();
        Thread.sleep(5000);
    }
    @Test
    void addJob() throws InterruptedException {
        retrieveJob();

        // Tunggu tombol Add bisa diklik lalu klik
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Add']")));
        addButton.click();

        // Tunggu header Add User muncul
        WebElement addJobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Add Job Title']")));

        // Input Form
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Job Title']/following::input[1]"))).sendKeys("SQA");
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and normalize-space()='Save']")));
        saveButton.click();
        Thread.sleep(10000);
    }

    @Test
    void cancelJob() throws InterruptedException {
        retrieveJob();
        // Tunggu tombol Add bisa diklik lalu klik
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'oxd-button') and normalize-space()='Add']")));
        addButton.click();

        // Tunggu header Add User muncul
        WebElement addJobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Add Job Title']")));

        // Input Form
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Job Title']/following::input[1]"))).sendKeys("Assurance");
        WebElement cancelButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='button' and normalize-space()='Cancel']")));
        cancelButton.click();
        Thread.sleep(10000);
    }
    @Test
    void editJob() throws InterruptedException {
        retrieveJob();
        WebElement editBtnJob = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border'][.//div[text()='SQA']]//button[2]")));
        editBtnJob.click();

        // Tunggu header Edit Job Title  muncul
        WebElement editJob = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Edit Job Title']")));
        // Input Form
        WebElement jobTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Job Title']/following::input[1]")));

        jobTitle.sendKeys(Keys.CONTROL + "a"); // select all
        jobTitle.sendKeys(Keys.DELETE);       // delete
        jobTitle.sendKeys("Software Quality Assurance");

        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and normalize-space()='Save']")));
        editButton.click();
        Thread.sleep(5000);
    }
    @Test
    void deleteJob() throws InterruptedException {
        retrieveJob();
        WebElement deleteJob = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='oxd-table-row oxd-table-row--with-border'][.//div[text()='Software Quality Assurance']]//button[1]")));
        deleteJob.click();
        Thread.sleep(5000);
        WebElement confirmBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@type='button' and normalize-space()='Yes, Delete']")));
        confirmBtn.click();
        Thread.sleep(10000);
    }
}

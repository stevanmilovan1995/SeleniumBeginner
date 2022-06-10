import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    private static WebDriverManager wdm = new ChromeDriverManager();
    private static WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        wdm.setup();
    }

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
    }


    @Test
    public void positiveLogIn() {
        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#username")).sendKeys("student");
        driver.findElement(By.cssSelector("#password")).sendKeys("Password123");
        driver.findElement(By.cssSelector("#submit")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://practicetestautomation.com/logged-in-successfully/");
        driver.findElement(By.cssSelector("#loop-container > div > article > div.post-content > p.has-text-align-center > strong")).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector("#loop-container > div > article > div.post-content > p.has-text-align-center > strong")).getText(), "Congratulations student. You successfully logged in!");
        Assert.assertTrue(driver.findElement(By.cssSelector(".wp-block-button__link.has-text-color.has-background.has-very-dark-gray-background-color")).isDisplayed());
    }

    @Test
    public void negativeUsername() {
        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#username")).sendKeys("incorrectUser");
        driver.findElement(By.cssSelector("#password")).sendKeys("Password123");
        driver.findElement(By.cssSelector("#submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#error")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("#error")).getText(), "Your username is invalid!");
    }

    @Test
    public void negativePassword() {
        driver.navigate().to("https://practicetestautomation.com/practice-test-login/");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("#username")).sendKeys("student");
        driver.findElement(By.cssSelector("#password")).sendKeys("incorrectPassword");
        driver.findElement(By.cssSelector("#submit")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("#error")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.cssSelector("#error")).getText(), "Your password is invalid!");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @AfterTest
    public void afterTest(){
        wdm.quit();
    }
}

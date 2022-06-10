import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TetsNG {
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
    public void openSite() {
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/dynamic-properties");
        Assert.assertEquals(driver.getCurrentUrl(), "https://demoqa.com/dynamic-properties");
    }

    @Test (dependsOnMethods = "openSite")
    public void firstElementDisplayed() {
        openSite();
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(10));
        wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("enableAfter")));
        Assert.assertTrue(driver.findElement(By.id("enableAfter")).isDisplayed());
    }

    @Test (dependsOnMethods = "firstElementDisplayed")
    public void firstElementIsClicked() {
        firstElementDisplayed();
        driver.findElement(By.id("enableAfter")).click();
        Assert.assertTrue(true);
    }



    @Test (dependsOnMethods = "openSite")
    public void secondElementDisplayed() {
        openSite();
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(10));
        wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("visibleAfter")));
        Assert.assertTrue(driver.findElement(By.id("visibleAfter")).isDisplayed());
    }


    @Test (dependsOnMethods = "secondElementDisplayed")
    public void secondElementIsClicked() {
        secondElementDisplayed();
        driver.findElement(By.id("visibleAfter")).click();
        Assert.assertTrue(true);
    }


    @Test
    public void allElementsDisplayed() {
        firstElementDisplayed();
        WebDriverWait wdw = new WebDriverWait(driver, Duration.ofSeconds(10));
        wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("visibleAfter")));
        Assert.assertTrue(driver.findElement(By.id("visibleAfter")).isDisplayed());
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

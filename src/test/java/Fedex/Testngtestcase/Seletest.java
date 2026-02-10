package Fedex.Testngtestcase;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Seletest {

    static WebDriver driver;
    static WebDriverWait wait;
    static ExtentReports extent;
    static ExtentTest test;

    @BeforeTest
    public void launchBrowser() {

        extent = Extenrreport.getInstance();
        test = extent.createTest("Accenture UI Test");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://accenture.com/#/");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testIframesAndClick() {

        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        System.out.println("Total iframes: " + frames.size());

        boolean elementFound = false;

        for (int i = 0; i < frames.size(); i++) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frames.get(i));

                WebElement searchButton = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='nav-labels']"))
                );

                Screenshot.highlightElement(driver, searchButton);
                String searchShot = Screenshot.takeScreenshot(driver, "search_button");

                searchButton.click();
                test.pass(
                        "Clicked Need to Know",
                        MediaEntityBuilder.createScreenCaptureFromPath(searchShot).build()
                );

                WebElement date = wait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ni-date']"))
                );

                Screenshot.highlightElement(driver, date);
                String dateShot = Screenshot.takeScreenshot(driver, "date");

                test.pass(
                        "Date element visible",
                        MediaEntityBuilder.createScreenCaptureFromPath(dateShot).build()
                );

                elementFound = true;
                break;

            } catch (Exception e) {
                System.out.println("Not found in iframe " + i + ": " + e.getMessage());
            }
        }

        if (!elementFound) {
            test.fail("Element not found in any iframe");
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}

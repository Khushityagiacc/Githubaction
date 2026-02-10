package Fedex.Testngtestcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Seletest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setup() {

        // ✅ USE YOUR CLASS
        extent = Extenrreport.getInstance();
        test = extent.createTest("Smoke Test - Screenshot + Report");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    @Test
    public void openGoogle() {

        driver.get("https://www.google.com");

        String screenshotPath = Screenshot.takeScreenshot(driver, "google_home");

        test.pass(
                "Google opened successfully",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build()
        );
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        extent.flush();
    }
}

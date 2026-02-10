package Fedex.Testngtestcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Seletest {

    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    @BeforeTest
    public void setup() {

        extent = ExtentManager.getReport();
        test = extent.createTest("Sample Selenium Test");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
    }

    @Test
    public void openGoogleAndTakeScreenshot() throws Exception {

        driver.get("https://www.google.com");
        test.pass("Opened Google");

        String screenshot = ScreenshotUtil.takeScreenshot(driver, "google_home");
        test.pass(
                "Screenshot captured",
                MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build()
        );
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) driver.quit();
        extent.flush();
    }
}

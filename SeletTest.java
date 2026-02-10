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

import Fedex.Testngtestcase.Extenrreport;
import Fedex.Testngtestcase.Screenshot;

public class SeletTest {

    static WebDriver driver;
    static WebDriverWait wait;
    static ExtentReports extent;
    static ExtentTest test;

    // =========================
    // SETUP
    // =========================
    @BeforeTest
    public void launchBrowser() {

        extent = Extenrreport.getInstance();
        test = extent.createTest("Accenture Careers Search Test");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new"); // GitHub Actions
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.manage().window().maximize();
        driver.get("https://www.accenture.com/in-en/careers");

        test.pass("Browser launched and navigated to Accenture Careers page");
    }

    // =========================
    // TEST
    // =========================
    @Test
    public void clickSearchButtonInsideIframe() {

        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        test.info("Total iframes found: " + frames.size());

        boolean elementFound = false;

        for (int i = 0; i < frames.size(); i++) {
            try {
                driver.switchTo().defaultContent();
                driver.switchTo().frame(frames.get(i));

                WebElement searchButton = wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector(".rad-header__search-button")
                        )
                );

                Screenshot.highlightElement(driver, searchButton);
                String screenshotPath =
                        Screenshot.takeScreenshot(driver, "search_button_iframe_" + i);

                searchButton.click();

                test.pass(
                        "Clicked Search button in iframe index: " + i,
                        MediaEntityBuilder
                                .createScreenCaptureFromPath(screenshotPath)
                                .build()
                );

                elementFound = true;
                break;

            } catch (Exception e) {
                test.info("Search button not found in iframe index: " + i);
                driver.switchTo().defaultContent();
            }
        }

        if (!elementFound) {
            test.fail("Search button not found in any iframe");
        }
    }

    // =========================
    // TEARDOWN
    // =========================
    @AfterTest
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        extent.flush();
    }
}

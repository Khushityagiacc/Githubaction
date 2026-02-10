package Fedex.Testngtestcase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;

public class Screenshot {

    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public static String takeScreenshot(WebDriver driver, String name) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = "test-output/screenshots";
        String screenshotPath = screenshotDir + "/" + name + "_" + timestamp + ".png";

        try {
            Files.createDirectories(new File(screenshotDir).toPath());
            Files.copy(src.toPath(), new File(screenshotPath).toPath());
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }

        // ✅ EXTENT NEEDS PATH RELATIVE TO REPORT LOCATION
        return "screenshots/" + name + "_" + timestamp + ".png";
    }
}

package Fedex.Testngtestcase;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

public class Screenshot {

    public static void highlightElement(WebDriver driver, WebElement element) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].style.border='3px solid red'", element);

    }

    public static String takeScreenshot(WebDriver driver, String name) {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String relativePath = "screenshots/" + name + "_" + timestamp + ".png";

        File dest = new File("test-output/" + relativePath);

        try {

            Files.createDirectories(dest.getParentFile().toPath());

            Files.copy(src.toPath(), dest.toPath());

        } catch (IOException e) {

            System.out.println("Screenshot failed: " + e.getMessage());

        }

        return relativePath;

    }

}
 

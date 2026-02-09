package Fedex.convertedcodes;

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
	    ExtentReports extent;
	    static ExtentTest test;
 
	    @BeforeTest
	    public void launchBrowser() {
	        extent = Extenrreport.getInstance(); // Initialize Extent Reports
	        test = extent.createTest("Maximo Login Test");
 
	         WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");      // required for GitHub Actions
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

       driver.get("https://accenture.com/#/");
        System.out.println(driver.getTitle());

        driver.quit();
	    }
	    @Test
	    public void test() throws InterruptedException {
	    		    	List<WebElement> frames = driver.findElements(By.tagName("iframe"));
	    	System.out.println("Total iframes on the page: " + frames.size());
	    	for (int i = 0; i < frames.size(); i++) {
	    	    System.out.println("Frame " + i + ": id=" + frames.get(i).getAttribute("id") + ", name=" + frames.get(i).getAttribute("name"));
	    	}
 
	    	boolean elementFound = false;
	    	for (int i = 0; i < frames.size(); i++) {
	    	    try {
	    	    	driver.switchTo().defaultContent();
	    	        driver.switchTo().frame(frames.get(i));
	    	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    	      
	    	   
	    	      
	    	        // ✅ Click Search Button
	    	        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='nav-labels']")));
	    	        Screenshot.highlightElement(driver, searchButton);
	    	        String searchScreenshot = Screenshot.takeScreenshot(driver, "search_button");
	    	        searchButton.click();
	    	        test.pass("click on need to know", MediaEntityBuilder.createScreenCaptureFromPath(searchScreenshot).build());
	    	        
	    	        WebElement high = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ni-date']")));
	    	        Screenshot.highlightElement(driver, high);
	    	        String highscree = Screenshot.takeScreenshot(driver, "search_button");
	    	       
	    	        test.pass("click on date", MediaEntityBuilder.createScreenCaptureFromPath(highscree).build());
	    	       
	    	        
	    	        
	    	        
	    	        
	    	        elementFound = true;
	    	        
	    	        
	    	        
	    	        // Found and done - exit the iframe loop
	    	        
	    	        
	    	   break;
	    	   	            } catch (Exception e) {
	    	   	                System.out.println("Element not found in frame index: " + i + " - " + e.getMessage());
	    	   	                // Switch back to default content before next iteration
	    	   	                driver.switchTo().defaultContent();
	    	   	            }
	    	   	        }
	    }
 
}
 

 

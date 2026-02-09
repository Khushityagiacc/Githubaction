package Fedex.Testngtestcase;
 
import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class Extenrreport {
 
    private static ExtentReports extent;
 
    private static String reportHtmlPath = "test-output/ExtentReport.html";  // HTML report path

    private static String reportPdfPath = "test-output/ExtentReport.pdf";    // PDF report path
 
    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter spark = new ExtentSparkReporter(reportHtmlPath);
 
            // ✅ Add these configuration settings

            spark.config().setEncoding("UTF-8");

            spark.config().setDocumentTitle("Extent Report");

            spark.config().setReportName("Automation Report");

            spark.config().setTimelineEnabled(false); // 🔥 This disables JS timeline for PDF compatibility
 
            extent = new ExtentReports();

            extent.attachReporter(spark);

        }

        return extent;

    }
 
    public static String getHtmlReportPath() {

        return reportHtmlPath;

    }
 
    public static String getPdfReportPath() {

        return reportPdfPath;

    }

}
 

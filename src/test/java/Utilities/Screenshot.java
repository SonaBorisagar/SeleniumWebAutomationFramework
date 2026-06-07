package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class Screenshot {
 
	public static String getScreenshotPath(WebDriver driver) {

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File src = ts.getScreenshotAs(OutputType.FILE);

	    String path = System.getProperty("user.dir")
	            + "/test-output/screenshots/"
	            + System.currentTimeMillis()
	            + ".png";

	    File dest = new File(path);

	    try {
	        FileUtils.copyFile(src, dest);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return path;
	}
	
	public static void attachScreenshot(String path) {

        ExtentTest test = ExtentReportManager.getTest();

        if (test != null) {
            test.fail("Failure Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } else {
            System.out.println("ExtentTest is NULL - cannot attach screenshot");
        }
    }

}
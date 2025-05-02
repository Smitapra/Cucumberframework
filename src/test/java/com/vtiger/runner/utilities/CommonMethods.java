package com.vtiger.runner.utilities;

import Stepsdefination.Basesteps;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.PanelUI;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods {

    public WebDriver driver;

    public WebDriverWait wait;
    public ExtentTest logger;


    public CommonMethods(WebDriver driver, ExtentTest logger) {
        this.driver = driver;
        this.logger = logger;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void Type(WebElement elm, String value, String msg) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.clear();
            elm.sendKeys(value);
            logger.pass(msg);
        } catch (Exception e) {
            e.getStackTrace();
            logger.fail("unable to login" + e.getStackTrace() + Screenshot());
        }
    }


    public void clickelement(WebElement elm, String msg) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
            logger.pass(msg + Screenshot());
        } catch (Exception e) {
            e.getStackTrace();
            logger.fail("unable to click the button" + e.getStackTrace() + Screenshot());
        }
    }

    public void msgdisplay(WebElement elm,String msg) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.isDisplayed();
            logger.pass(msg);
        } catch (Exception e) {
            e.getStackTrace();
            logger.fail("unable to view the message" + e.getStackTrace() + Screenshot());
        }
    }


    public String Screenshot()
    {
        Date d = new Date();
        DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
        String fileName = ft.format(d);
        String path = System.getProperty("user.dir") + "/src/test/java/reports/Screenshot/"+fileName+".png";
        TakesScreenshot ts = ((TakesScreenshot)driver);
        File SrcFile =ts.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(path);
        try {
            FileUtils.copyFile(SrcFile,DestFile);
        } catch (IOException e)
        {
            e.getStackTrace();
        }
        String imagepath = "<a href='"+path+"'<span class='label time-taken green lighten-1 white-text'>Screenshot</span></a>";
        return imagepath;

    }


}



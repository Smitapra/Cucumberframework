package com.vtiger.runner.utilities;

import Stepsdefination.Basesteps;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonMethods {

    private static final Log log = LogFactory.getLog(CommonMethods.class);
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
            logger.fail("unable to login" + e.getStackTrace());
        }
    }

    public void Type(String elmstr, String value) {
        try {
            WebElement elm = driver.findElement(By.xpath(elmstr));
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.clear();
            elm.sendKeys(value);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public void clickelement(WebElement elm, String msg) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
            logger.pass(msg);
        } catch (Exception e) {
            e.getStackTrace();
            logger.fail("unable to click the button" + e.getStackTrace());
        }
    }

    public void clickelement(String elmstr, String elementType) {
        WebElement elm = null;
        try {
            if (elementType.equalsIgnoreCase("Link"))
                elm = driver.findElement(By.linkText("elmstr"));
            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void msgdisplay(WebElement elm) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.isDisplayed();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void textlabledisplay(WebElement elm) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.isDisplayed();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}



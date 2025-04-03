package Stepsdefination;

import Pages.LeadPage;
import Pages.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.runner.utilities.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Basesteps {

    public static WebDriver driver;
    public Properties prop;
    public Map<String , Map<String,String>> dt;
    public static LoginPage lp ;
    public static LeadPage ldp ;
    public String ScenarioName ;
    public static ExtentHtmlReporter htmlReporter ;
    public static ExtentReports extent ;
    public static ExtentTest logger;


    public void initiation() throws FilloException {
        loaddata();
       if (dt==null)
       {
           loaddata();
       }

       if (prop==null)
       {
           readproperties();
       }
       if (driver==null)
       {
        launchapp();
       }

       if (extent==null)
       {
           generatereport();
       }

    }


    public void launchapp()
    {
        if (prop.getProperty("BrowserName").equalsIgnoreCase("Edge"))
        {
            driver = new EdgeDriver();
        }
        else if (prop.getProperty("BrowserName").equalsIgnoreCase("Firefox"))
        {
            driver = new FirefoxDriver();
        }
        else if (prop.getProperty("BrowserName").equalsIgnoreCase("headless"))
        {
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--headless");
            driver = new ChromeDriver(opt);
        }
        else
        {
            driver = new ChromeDriver();
        }

        logger.info(prop.getProperty("BrowserName")+"Browser successfully Lunched");
        driver.get(prop.getProperty("Appurl"));
        logger.info("url: "+prop.getProperty("Appurl")+" navigated successfully");
        driver.manage().window().maximize();
        lp = new LoginPage(driver,logger);
        ldp = new LeadPage(driver,logger);

    }

    public void closeapp()
    {
        if (driver!=null)
        driver.quit();
    }

    public void readproperties()
    {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Settings/Config.properties");
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loaddata() throws FilloException {
        Fillo fillo=new Fillo();
        Connection connection=fillo.getConnection(System.getProperty("user.dir")+"/src/test/resources/TestData/data.xlsx");
        String strQuery="Select * from Sheet1";
        dt = new HashMap<>();
        Recordset recordset=connection.executeQuery(strQuery);
        List<String> lst =  recordset.getFieldNames();

        while(recordset.next()){
            String ScenarioName = (recordset.getField("ScenarioName"));
            Map<String ,String> m = new HashMap<>();

            for (int i = 1; i<lst.size();i++ )
            {
                String colname = lst.get(i);
                String colvalue = (recordset.getField(colname)) ;
                m.put(colname , colvalue);

            }

            dt.put(ScenarioName ,m);
        }

        recordset.close();
        connection.close();
    }


    public void generatereport()
    {
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("ddMMyyyyhhmmss");
        String Filename = df.format(d);
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"src/test/java/Report/ExtendReport"+Filename+".html");
        // Create an object of Extent Report
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name","Automation test Hub");
        extent.setSystemInfo("Enviroment","UAT");
        extent.setSystemInfo("User Name","Smita");
        htmlReporter.config().setDocumentTitle("Vtiger");
        htmlReporter.config().setReportName("Online Report");
        htmlReporter.config().setTheme(Theme.STANDARD);


    }


}

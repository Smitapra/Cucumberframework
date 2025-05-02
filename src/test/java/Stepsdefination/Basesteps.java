package Stepsdefination;

import Pages.HomePage;
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
import java.time.Duration;
import java.util.*;

public class Basesteps {

    public static WebDriver driver;
    public static Properties prop;
    public static LoginPage lp;
    public static LeadPage ldp;
    public static HomePage HM;

    public static Map<String,Map<String,String>> dt;
    public static String ScenarioName;

    public static ExtentHtmlReporter htmlReporter;

    public static ExtentReports extent;

    public static ExtentTest logger;

    public void initiation() throws FilloException {

        if(prop==null)
        {
            readproperties();
        }
        if (driver==null)
        {
            launchapp();
        }

        if(dt==null)
        {
            loaddata();
        }

    }

    public void launchapp()
    {

        if (prop.getProperty("browser").equals("chrome")) {
            driver = new ChromeDriver();
        } else if (prop.getProperty("browser").equals("edge")) {
            driver = new EdgeDriver();
        } else if (prop.getProperty("browser").equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (prop.getProperty("browser").equals("headless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }
        else
        {
            driver = new ChromeDriver();
        }
        logger.info("browser name "+prop.getProperty("browser"));
        driver.get(prop.getProperty("appUrl"));
        logger.info("Url :" + prop.getProperty("appUrl") + " navigated successfully");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("globalwait"))));
        lp = new LoginPage(driver, logger);
        ldp = new LeadPage(driver, logger);
        HM = new HomePage(driver,logger);

    }


    public void readproperties()
    {
        try {
            prop = new Properties();
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
        Recordset recordset=connection.executeQuery(strQuery);
        dt = new HashMap<>();
        List<String> colms = recordset.getFieldNames();
        Map<String,String> colmdata = null;
        while(recordset.next()){
            colmdata = new HashMap<>();
            String ScenarioName = recordset.getField("ScenarioName");
            for(int i=1;i<colms.size();i++)
            {
                String colmname = colms.get(i).trim();
                String colmdt = recordset.getField(colmname).trim();
                colmdata.put(colmname,colmdt);
            }
            dt.put(ScenarioName,colmdata);
        }
        System.out.println(dt);
        recordset.close();
        connection.close();
    }


    public void createExtentReport()
    {
        Date d = new Date();
        DateFormat ft = new SimpleDateFormat("ddMMyyyyhhmmss");
        String fileName = ft.format(d);
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/reports/ExtentReport"+fileName+".html");
        // Create an object of Extent Reports
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Automation Test");
        extent.setSystemInfo("Environment", "UAT");
        extent.setSystemInfo("User Name", "Sushree S");
        htmlReporter.config().setDocumentTitle("Report details ");
        // Name of the report
        htmlReporter.config().setReportName("Vtiger");
        // Dark Theme
        htmlReporter.config().setTheme(Theme.STANDARD);

    }
}

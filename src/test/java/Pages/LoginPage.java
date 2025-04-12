package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.runner.utilities.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {

    public WebDriver driver;
    public ExtentTest logger;

    public LoginPage(WebDriver driver, ExtentTest logger)
    {
        super(driver,logger);
        this.driver = driver;
        this.logger = logger;
        PageFactory.initElements(driver , this);
    }

    @FindBy(name="user_name")
    WebElement userid;

    @FindBy(xpath="//input[@name='user_password']")
    WebElement password;

    @FindBy(name ="Login")
    WebElement login;

    @FindBy(xpath="//*[contains(text(),'You must specify a valid username and password.')]")
    WebElement errormsg;



    public void login(String uid , String pwd)
    {
        Type(userid,uid, uid + "password enter successfully");
        Type(password,pwd, pwd + "password enter successfully");
        clickelement(login, "user successfully click to login button");

    }

    public void verifyerrormessage()
    {
        msgdisplay(errormsg);
    }



}

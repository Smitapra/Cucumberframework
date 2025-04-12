package Pages;

import Stepsdefination.Basesteps;
import com.aventstack.extentreports.ExtentTest;
import com.vtiger.runner.utilities.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.network.Header;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage extends CommonMethods {


    public HeaderPage(WebDriver driver, ExtentTest logger)
    {
        super(driver, logger);
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }

    @FindBy(linkText="Home")
    WebElement home_link;

    @FindBy(linkText="Logout")
    WebElement Logout_link;

    @FindBy(linkText="New Lead")
    WebElement new_lead;

    @FindBy(linkText="Leads")
    WebElement lnk_lead;

    public void Verifyhome()
    {
        home_link.isDisplayed();
    }
    public void verifylogout()


    {
        Logout_link.isDisplayed();
    }

    public void clicklogout()
    {
        Logout_link.click();
    }

    public void clicknewlead()
    {
        new_lead.click();
    }

    public void clicklead()

    {
        lnk_lead.click();
    }


}

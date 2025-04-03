package Pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends HeaderPage {



    public HomePage (WebDriver driver, ExtentTest logger)
    {
        super(driver, logger);
        this.driver = driver;
        PageFactory.initElements(driver ,this);
    }

    @FindBy(xpath = "//td[contains(text(), 'My Tickets')]")
    WebElement tickets_link;

    @FindBy(xpath = "//td[contains(text(), 'My Top Open Potentials ')]")
    WebElement TOP_link;

    public void verifyTickets()
    {
        tickets_link.isDisplayed();
    }
    public void verifytop_potential()
    {
        TOP_link.isDisplayed();
    }



}

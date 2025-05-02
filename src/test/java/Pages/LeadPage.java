package Pages;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.runner.utilities.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Type;

public class LeadPage extends HeaderPage {


    public LeadPage(WebDriver driver, ExtentTest logger)
    {
        super(driver, logger);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(name ="lastname")
    WebElement lst_name;

    @FindBy(name ="company")
    WebElement comp_name;

    @FindBy(name ="button")
    WebElement save;





    public void createlead(String txt_lname , String txt_comp )
    {

    Type(lst_name,txt_lname,txt_lname + "lastname enter successfully");
    Type(comp_name,txt_comp,txt_comp + "compname enter successfully");
    clickelement(save,"user successfully click to login button" + Screenshot());
    }

    public void verifylead(String txt_label,String txt )
    {
        WebElement elm = driver.findElement(By.xpath("//td[text()='"+txt_label+":']/following::td[1][text()='"+txt+"']"));
        msgdisplay(elm,"lead viewed successfully" + Screenshot());
    }















}

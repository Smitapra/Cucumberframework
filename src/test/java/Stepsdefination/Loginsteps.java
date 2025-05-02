package Stepsdefination;


import Pages.LeadPage;
import Pages.LoginPage;
import com.codoid.products.exception.FilloException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static Stepsdefination.Basesteps.lp;

public class Loginsteps extends Basesteps {


    @Before
    public void getScenarioName(Scenario scenario) throws FilloException {
        if(extent==null)
        {
            createExtentReport();
        }
        ScenarioName = scenario.getName();
        logger = extent.createTest(ScenarioName);
    }
    @After
    public void tierdown()

    {
        extent.flush();
        driver.quit();
    }



    @Given("user should be on login page")
    public void user_should_be_on_login_page() throws FilloException
    {

        initiation();
    }

    @When("user enter the valid credential and click to login button")
    public void user_enters_the_valid_credentials_and_click_on_login_button() {
        lp.login(dt.get(ScenarioName).get("userid"),dt.get(ScenarioName).get("password"));

    }

    @When("user enter the invalid credential and click to login button")
    public void user_enters_the_invalid_credentials_and_click_on_login_button() {
        lp.login(dt.get(ScenarioName).get("userid"),dt.get(ScenarioName).get("password"));

    }

    @Then("user can see the error message")
    public void verifyErrormessage() {

        lp.verifyerrormessage();
    }

    @Then("user should be navigated to Home page")
    public void user_should_be_navigated_to_home_page()
    {
        ldp.Verifyhome();
    }
    @Then("user can see the logout link")
    public void user_can_see_the_logout_link()
    {

        ldp.verifylogout();
    }
//    @Then("Close the Browser")
//    public void close_browser()
//    {
//        driver.quit();
//    }

    @Then("user able to logout")
    public void user_able_to_logout()
    {
    ldp.clicklogout();
    }

    @When("user enters the invalid credentials username as {string} and password as {string} and click on login button")
    public void user_enters_the_invalid_credentials_username_as_and_password_as_and_click_on_login_button(String uid, String pwd) {
        lp.login(uid,pwd);
    }
}
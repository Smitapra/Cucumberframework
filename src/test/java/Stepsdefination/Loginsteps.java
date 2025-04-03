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

import static Stepsdefination.Basesteps.lp;

public class Loginsteps extends Basesteps {


    @Before

    public void getscenarioname(Scenario scenario) throws FilloException {

        ScenarioName = scenario.getName();
        logger = extent.createTest(ScenarioName);
    }

    @After
    public void savereport()
    {
        extent.flush();
    }


    @Given("user should be on login page")
    public void user_should_be_on_login_page() throws FilloException {
        initiation();
    }
}

    @When("user enter the valid credential and click to login button")
    public void user_enter_the_valid_credential()

    {
        lp.login();

    }

    @When("user enter the invalid credential and click to login button")
    public void user_enter_the_invalid_credential()

    {
        lp.login();
        Login(dt.get(ScenarioName).get("Userid"),dt.get(ScenarioName).get("Password"));
    }

    @When("user enter the invalid credential username as {string} and password as {string}")
    public void user_enter_the_invalid_credential_username_as_and_password_as(String uid, String pwd)
    {
        lp.Login(uid,pwd);
    }

    @Then("user can see the error message")
    public void user_can_see_the_error_message()
    {
    lp.verifyerrormessage();
    }

    @Then("user should be navigated to Home page")
    public void user_should_be_navigated_to_home_page()
    {

    }


    @Then("Close the Browser")
    public void close_the_browser()
    {
        if (driver!=null)
        {
            driver.quit();
        }
    }


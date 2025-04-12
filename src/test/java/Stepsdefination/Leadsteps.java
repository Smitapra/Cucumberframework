package Stepsdefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Leadsteps extends Basesteps
{

    @When("user click on the new lead link")
    public void user_click_on_the_new_lead_link()

    {
     ldp.clicknewlead();
    }
    @When("fill the lastname and company name and click to save button")
    public void fill_the_lastname_and_company_name_and_click_to_save_button()
    {
     ldp.createlead(dt.get(ScenarioName).get("Lastname"),dt.get(ScenarioName).get("Compname"));

    }
    @Then("Lead should be created and validated successfully")
    public void lead_should_be_created_and_validated_successfully()
    {
        ldp.verifylead("praharaj","synechron");

    }

    @When("user need to create multiple lead and validate based on the dataset")
    public void user_need_to_create_multiple_lead_and_validate_based_on_the_dataset(io.cucumber.datatable.DataTable dataTable)
    {
     List<List<String>> ltr =dataTable.asLists();
     for(int i = 0; i<ltr.size();i++)
     {

         ldp.clicknewlead();
         ldp.createlead(ltr.get(i).get(0),ltr.get(i).get(1));
         ldp.verifylead(ltr.get(i).get(0),ltr.get(i).get(1));


     }
    }


}

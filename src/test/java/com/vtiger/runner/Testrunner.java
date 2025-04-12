package com.vtiger.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/test/resources/Features",
        glue = "Stepsdefination",
        dryRun = false,
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        tags = "@SS",
        monochrome = true

)

public class Testrunner {


}

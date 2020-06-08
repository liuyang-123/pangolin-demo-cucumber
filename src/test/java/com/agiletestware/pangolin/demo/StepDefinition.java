package com.agiletestware.pangolin.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cucumber.api.java.AfterStep;
import cucumber.api.java.BeforeStep;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;

public class StepDefinition {
	static{
		Locale.setDefault(Locale.US);
	}

	public static WebDriver webDriver = null;
	public static final String GOOGLE_URL = "http://www.bing.com";

	@Given("^I have gone to the Google page$")
	public void goToTheGooglePage() {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

		webDriver = new ChromeDriver(options);
		webDriver.manage().window().maximize();
		webDriver.get(GOOGLE_URL);
	}

	@When("^I add agiletestware to the search box$")
	public void addKeywordToSeachBox() {
		final WebElement seachInput = webDriver.findElement(By.name("q"));
		assertTrue(seachInput.isDisplayed());
		seachInput.sendKeys("agiletestware");
	}

	@And("^click the Search Button$")
	public void clickSearch() {
		webDriver.findElement(By.name("go")).submit();
	}

	@Then("^agiletestware should be mentioned in the results$")
	public void displayResults() {
		final String agileTitle = webDriver.findElement(By.xpath("//a[@href='https://www.agiletestware.com/']")).getText();
//		assertEquals("Agiletestware - Software for QA and Development Tools", agileTitle);
		assertEquals("Some Wrong Title", agileTitle);
	}

    @BeforeStep
    public void beforeStep(final Scenario scenario){
	    // do nothing
    }

	@AfterStep
	public void afterStep(final Scenario scenario){
		if (scenario.isFailed()) {
			scenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
			fail("The step failed, So the 'after' step should be failure also so that we can upload screenshot.");
		}
	}

	@After
	public void afterScenario(final Scenario scenario) {
		if (scenario.isFailed()) {
			scenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
			fail("There ars failed steps, so the failure is raised for uploading screenshot via pangolin.");
		}

		if (webDriver != null) {
			webDriver.close();
			webDriver.quit();
		}
	}
}

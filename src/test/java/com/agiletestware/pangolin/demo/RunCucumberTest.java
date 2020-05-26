package com.agiletestware.pangolin.demo;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {
				"json:target/cucumber/reports.json",
				"pretty"
		},
		features = { "src/test/java/com/agiletestware/pangolin/demo/search.feature" }, monochrome = true
		)
public class RunCucumberTest {

}

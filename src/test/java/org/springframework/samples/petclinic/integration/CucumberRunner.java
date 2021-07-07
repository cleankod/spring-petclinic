package org.springframework.samples.petclinic.integration;

import io.cucumber.java.After;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report" },
		glue = { "org.springframework.samples.petclinic.integration" }, features = "classpath:scenarios")
public class CucumberRunner {
public final static WebDriver webDriver = Browser.webDriver;

	@After
	public void quit() {
		webDriver.quit();
	}
}

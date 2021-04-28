package org.springframework.samples.petclinic.integration;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.springframework.samples.petclinic.integration.config.Browser;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/cucumber-html-report" },
		glue = { "org.springframework.samples.petclinic.integration" }, features = "classpath:scenarios")
public class CucumberRunner {

	@Before
	public void setUp() {
		Browser.createBrowserConfig(System.getProperty("browser", "chrome"));
	}

	@After
	public void tearDown() {
		Selenide.closeWebDriver();
	}

}

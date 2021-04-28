package org.springframework.samples.petclinic.integration.steps;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.samples.petclinic.integration.utils.LocatorParser.*;
import static org.springframework.samples.petclinic.integration.utils.SelenideTools.openUrl;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.samples.petclinic.integration.Constants;
import org.springframework.samples.petclinic.integration.SpringIntegrationTest;

public class OwnersSteps extends SpringIntegrationTest {

	@Given("^I go to the main page$")
	public void mainPage() {
		openUrl(Constants.BASE_URL);
	}

	@Given("^I go to the find-owners page$")
	public void findOwnersPage() {
		openUrl(Constants.BASE_URL + "/owners/find");
	}

	@When("^I click on the link with title \"([^\"]*)\"$")
	public void clickOnLinkWithTitle(String linkTitle) {
		By elementSelector = By.xpath("//*[@title='%s']");
		$(parseLocator(elementSelector, linkTitle)).shouldBe(Condition.visible).click();
	}

	@When("^I fill the field named \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void fillInputBoxWithValue(String name, String value) {
		By elementSelector = By.name(name);
		$(elementSelector).shouldBe(Condition.visible).append(value);
	}

	@When("^I submit the form \"([^\"]*)\"$")
	public void submitForm(String id) {
		By elementSelector = By.id(id);
		$(elementSelector).submit();
	}

	@Then("^I should see the \"([^\"]*)\" page$")
	public void shouldSeeThePage(String pageTitle) {
		By elementSelector = By.tagName("h2");
		String text = $(elementSelector).shouldBe(Condition.visible).getText();
		assertThat(text).isEqualTo(pageTitle);
	}

}

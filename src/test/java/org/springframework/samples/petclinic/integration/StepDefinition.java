package org.springframework.samples.petclinic.integration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends SpringIntegrationTest {

	@Given("^I go to the main page$")
	public void mainPage() {
		open(Constants.BASE_URL);
	}

	@Given("^I go to the find-owners page$")
	public void findOwnersPage() {
		open(Constants.BASE_URL + "/owners/find");
	}

	@When("^I click on the link with title \"([^\"]*)\"$")
	public void clickOnLinkWithTitle(String linkTitle) {
		By elementSelector = By.xpath(String.format("//*[@title='%s']", linkTitle));
		$(elementSelector).shouldBe(Condition.visible).click();
	}

	@Then("^I should see the \"([^\"]*)\" page$")
	public void shouldSeeThePage(String pageTitle) {
		By elementSelector = By.tagName("h2");
		String text = $(elementSelector).shouldBe(Condition.visible).getText();
		assertThat(text).isEqualTo(pageTitle);
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

}

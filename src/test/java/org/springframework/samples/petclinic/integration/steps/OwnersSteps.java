package org.springframework.samples.petclinic.integration.steps;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
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

import java.util.List;
import java.util.stream.Stream;

public class OwnersSteps extends SpringIntegrationTest {

	@Given("^I go to the main page$")
	public void mainPage() {
		openUrl(Constants.BASE_URL);
	}

	@Given("^I go to the find-owners page$")
	public void findOwnersPage() {
		openUrl(Constants.BASE_URL + "/owners/find");
	}

	@Given("I go to the new-owner page")
	public void newOwnerPage() {
		openUrl(Constants.BASE_URL + "/owners/new");
	}

	@When("^I click on the link with title \"([^\"]*)\"$")
	public void clickOnLinkWithTitle(String linkTitle) {
		By elementSelector = By.xpath("//*[@title='%s']");
		$(parseLocator(elementSelector, linkTitle)).shouldBe(Condition.visible).click();
	}

	@When("^I click the button with text \"([^\"]*)\"$")
	public void clickOnButtonWithText(String buttonText) {
		By elementSelector = By.xpath("//button[text()='%s']");
		$(parseLocator(elementSelector, buttonText)).shouldBe(Condition.visible).click();
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

	@Then("^I should see results relative to \"([^\"]*)\" query$")
	public void searchResultsAreRelativeToQuery(String query) {
		By elementSelector = By.xpath("//table[@id='owners']/tbody/tr/td[1]/a");
		List<String> texts = $$(elementSelector).texts();

		Stream.of(texts).forEach(name -> assertThat(name.contains(query)));
	}

	@Then("^I should see all existing users found$")
	public void shouldSeeAllExistingUsersFound() {
		By elementSelector = By.xpath("//table[@id='owners']/tbody/tr/td[1]/a");
		List<String> texts = $$(elementSelector).texts();

		assertThat(texts.size() > 0);
	}

	@Then("^I should see 'has not been found' alert$")
	public void shouldSeeHasNotBeenFoundAlert() {
		By elementSelector = By.xpath("//div[@id='lastNameGroup']//div/p");

		assertThat($(elementSelector).shouldBe(Condition.visible).isDisplayed());
	}

	/**
	 * Owner Information
	 */

	@Then("^I should see owner with the name as \"([^\"]*)\"$")
	public void shouldSeeOwnerWithProperty(String name) {
		By elementSelector = By.xpath("//table/tbody/tr/th[text()='Name']/following-sibling::td/b");
		String text = $(elementSelector).shouldBe(Condition.visible).text();

		assertThat(text.equalsIgnoreCase(name));
	}

	@Then("^I should see owner with the \"([^\"]*)\" as \"([^\"]*)\"$")
	public void shouldSeeOwnerWithProperty(String property, String value) {
		By elementSelector = By.xpath("//table/tbody/tr/th[text()='%s']/following-sibling::td");
		String text = $(parseLocator(elementSelector, property)).shouldBe(Condition.visible).text();

		assertThat(text.equalsIgnoreCase(value));
	}

	@Then("^I should see \"([^\"]*)\" field with \"([^\"]*)\" alert$")
	public void shouldSeeFieldWithAlert(String fieldName, String alertText) {
		By elementSelector = By.xpath("//input[@id='%s']/parent::div/following-sibling::span[2]");
		String text = $(parseLocator(elementSelector, fieldName)).shouldBe(Condition.visible).text();

		assertThat(text.equalsIgnoreCase(alertText));
	}


}

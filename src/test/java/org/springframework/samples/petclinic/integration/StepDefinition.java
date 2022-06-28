package org.springframework.samples.petclinic.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class StepDefinition extends SpringIntegrationTest {

	public static final String BASE_URL = "http://localhost:8080";

	private final WebDriver webDriver = Browser.webDriver;

	@Given("^I go to the main page$")
	public void mainPage() {
		webDriver.navigate().to(BASE_URL);
	}

	@Given("^I go to the find-owners page$")
	public void findOwnersPage() {
		webDriver.navigate().to(BASE_URL + "/owners/find");
	}

	@Given("^I go to the add-owner page$")
	public void addOwnerPage() {
		webDriver.navigate().to(BASE_URL + "/owners/new");
	}

	@When("^I click on the link with title \"([^\"]*)\"$")
	public void clickOnLinkWithTitle(String linkTitle) {
		By elementSelector = By.xpath(String.format("//*[@title='%s']", linkTitle));
		new WebDriverWait(webDriver, 60L).until(driver -> driver.findElement(elementSelector).isDisplayed());
		WebElement webElement = webDriver.findElement(elementSelector);
		webElement.click();
	}

	@Then("^I should see the \"([^\"]*)\" page$")
	public void shouldSeeThePage(String pageTitle) {
		By elementSelector = By.tagName("h2");
		new WebDriverWait(webDriver, 60L).until(driver -> driver.findElement(elementSelector).isDisplayed());
		WebElement webElement = webDriver.findElement(elementSelector);
		assertThat(webElement.getText()).isEqualTo(pageTitle);
	}

	@When("^I fill the field named \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void fillInputBoxWithValue(String name, String value) {
		By elementSelector = By.name(name);
		WebElement webElement = webDriver.findElement(elementSelector);
		webElement.sendKeys(value);
	}

	@When("^I submit the form \"([^\"]*)\"$")
	public void submitForm(String id) {
		By elementSelector = By.id(id);
		WebElement webElement = webDriver.findElement(elementSelector);
		webElement.submit();
	}

	@Then("^I Should see all owners found$")
	public void shouldSeeAllOwners() {
		By elementSelector = By.xpath("//table[@id='owners']/tbody/tr/td[1]/a");
		List<WebElement> elements = webDriver.findElements(elementSelector);
		assertThat(elements.size()).isGreaterThan(0);
	}

	@Then("^I should see the \"([^\"]*)\" table$")
	public void shouldSeeTable(String id) {
		By elementSelector = By.id(id);
		WebElement webElement = webDriver.findElement(elementSelector);
		assertThat(webElement.isDisplayed()).isEqualTo(true);
	}

	@Then("^I should see \"([^\"]*)\" message under \"([^\"]*)\" field$")
	public void shouldSeeMessageUnderField(String message, String fieldId) {
		By elementSelector = By.xpath(String.format("//input[@id='%s']/parent::div/span", fieldId));
		WebElement webElement = webDriver.findElement(elementSelector);
		assertThat(webElement.getText()).isEqualTo(message);
	}

	@Then("^I should see field \"([^\"]*)\" with validation message:$")
	public void shouldSeeValidationMessageUnderField(String fieldId, String message) {
		By elementSelector = By.xpath(String.format("//input[@id='%s']/parent::div/following-sibling::span[2]", fieldId));
		WebElement webElement = webDriver.findElement(elementSelector);
		assertThat(webElement.getText()).isEqualTo(message);
	}

	@When("^I click on the owner$")
	public void clickOnTheOwner() {
		By elementSelector = By.xpath("//table[@id='owners']/tbody/tr/td[1]/a");
		WebElement webElement = webDriver.findElement(elementSelector);
		webElement.click();
	}

	@When("^I click on the \"([^\"]*)\" button$")
	public void clickOnTheButton(String button) {
		By elementSelector = By.linkText(button);
		WebElement webElement = webDriver.findElement(elementSelector);
		webElement.click();
	}

	@Then("^I should see \"([^\"]*)\" form$")
	public void addOwnerFormShouldBeVisible(String formName) {
		By elementSelector = By.id(formName);
		WebElement webElement = webDriver.findElement(elementSelector);
		assertThat(webElement.isDisplayed()).isEqualTo(true);
	}

	@Then("^I should see name with value \"([^\"]*)\"$")
	public void shouldBeProperName(String fullName) {
		By elementSelector = By.xpath("//tbody/tr/td");
		WebElement webElement = webDriver.findElement(elementSelector);
		assertThat(webElement.getText()).isEqualTo(fullName);
	}

	@Then("^I should see \"([^\"]*)\" with value \"([^\"]*)\"$")
	public void shouldBeProperName(String field, String value) {
		By elementSelector = By.xpath(String.format("//tbody/tr/th[text()='%s']/following-sibling::td", field));
		WebElement webElement = webDriver.findElement(elementSelector);
		assertThat(webElement.getText()).isEqualTo(value);
	}

}

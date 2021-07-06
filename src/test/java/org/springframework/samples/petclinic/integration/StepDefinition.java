package org.springframework.samples.petclinic.integration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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

	@Given("I go to the new-owner page")
	public void iGoToTheNewOwnerPage() {
		webDriver.navigate().to(BASE_URL + "/owners/new");
	}

	@Then("I should see all owners")
	public void iShouldSeeAllOwners() {
		Assert.assertTrue(webDriver.findElement(By.xpath("//table[@id='owners']")).isDisplayed());
	}

	@Then("I should see {string}")
	public void iShouldSee(String text) {
		Assert.assertSame(webDriver.findElement(By.xpath("//*[@class='help-inline']/div")).getText(), text);
	}

	@Then("I should see all owners who starts the same letters {string}")
	public void iShouldSeeAllOwnersWhoStartsTheSameLetters(String letter) {
		List<WebElement> owners = webDriver.findElements(By.xpath("//td/a[contains(@href,'owners')]"));
		for (WebElement element : owners) {
			Assert.assertTrue(element.getText().contains(letter));
		}
	}

	@And("I click on the first link")
	public void iClickOnTheFirstLink() {
		webDriver.findElement(By.xpath("//td/a[contains(@href,'owners')]")).click();
	}

	@And("I should see the {string} under all field without telephone")
	public void iShouldSeeTheUnderAllFieldWithoutTelephone(String text) {
		List<WebElement> helpInside = webDriver.findElements(By.xpath("//span[@class='help-inline']"));
		for (int i = 0; i < helpInside.size() - 1; i++) {
			Assert.assertSame(helpInside.get(i).getText(), text);
		}
	}

	@And("I should see the {string} under telephone field")
	public void iShouldSeeTheUnderTelephoneField(String text) {
		List<WebElement> helpInside = webDriver.findElements(By.xpath("//span[@class='help-inline']"));
		Assert.assertSame(helpInside.get(5).getText(), text);
	}
}

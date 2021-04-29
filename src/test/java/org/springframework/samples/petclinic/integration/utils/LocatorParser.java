package org.springframework.samples.petclinic.integration.utils;

import org.openqa.selenium.By;

public class LocatorParser {
	public static By parseLocator(By by, Object... args) {
		String formattedLocatorString = formatLocator(by, args);
		String locatorType = getLocatorType(by);

		// Check the locator type and return formatted accordingly
		switch (locatorType) {
			case "ByCssSelector":
				return By.cssSelector(formattedLocatorString);
			case "ById":
				return By.id(formattedLocatorString);
			case "ByName":
				return By.name(formattedLocatorString);
			case "ByClassName":
				return By.className(formattedLocatorString);
			case "ByTagName":
				return By.tagName(formattedLocatorString);
			default:
				return By.xpath(formattedLocatorString);
		}

	}

	private static String formatLocator(By by, Object... args) {
		return String.format(by.toString().replaceAll("By\\.[^:]*:", ""), args);
	}

	// Get the name of locator type
	private static String getLocatorType(By by) {
		return by.getClass().getSimpleName();
	}
}

package org.springframework.samples.petclinic.integration.config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browser {

	public static void createBrowserConfig(String browser) {
		Configuration.browser = browser;
		Configuration.browserSize = "1920x1080";
		Configuration.browserCapabilities = getBrowserCapabilities();
		Configuration.screenshots = true;
		Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
		Configuration.pollingInterval = 5000;
		Configuration.pageLoadStrategy = "eager";
		Configuration.timeout = 120000;
		Configuration.reportsFolder = "screenshots/";
		Configuration.holdBrowserOpen = true;
//        Configuration.startMaximized = true;
	}

	private static DesiredCapabilities getBrowserCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (System.getProperty("browserName", "chrome").equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("enable-automation");
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--disable-browser-side-navigation");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		}
		capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		return capabilities;
	}

}

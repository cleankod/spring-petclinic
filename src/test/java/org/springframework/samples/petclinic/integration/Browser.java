package org.springframework.samples.petclinic.integration;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.Response;

public class Browser {

	public static WebDriver createDriver() {
		setupDriver();
		ChromeDriver chromeDriver = new ChromeDriver(new ChromeDriverService.Builder().withSilent(true).build(),
				chromeOptions());
		chromeDriver.manage().window().setSize(new Dimension(1024, 768));
		chromeDriver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);

		Map<String, ? extends Serializable> slowConnectionSettings = Map.of("offline", false, "latency", 5,
				"download_throughput", 1024 * 20, "upload_throughput", 1024 * 20);
		CommandExecutor executor = chromeDriver.getCommandExecutor();
		try {
			Response response = executor.execute(new Command(chromeDriver.getSessionId(), "setNetworkConditions",
					Map.of("network_conditions", slowConnectionSettings)));
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

		return chromeDriver;
	}

	private static void setupDriver() {
		WebDriverManager.chromedriver().timeout(60).setup();
	}

	private static ChromeOptions chromeOptions() {
		final ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("enable-automation");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.addArguments("--disable-browser-side-navigation");
		chromeOptions.addArguments("--lang=en");
		return chromeOptions;
	}

}

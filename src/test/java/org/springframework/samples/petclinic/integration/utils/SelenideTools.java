package org.springframework.samples.petclinic.integration.utils;

import com.codeborne.selenide.Selenide;

public class SelenideTools {
	public static void openUrl(String url) {
		Selenide.open(url);
	}
}

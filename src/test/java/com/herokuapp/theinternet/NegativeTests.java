package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {
	@Test
	public void IncorrectUserName() {
		// driver
		System.setProperty("webdriver.chome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// open the site
		driver.get("http://the-internet.herokuapp.com/login");

		// Enter incorrect username and correct password
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));

		username.sendKeys("incorrect");
		password.sendKeys("SuperSecretPassword!");

		// Click Login Button
		WebElement logInButton = driver.findElement(By.xpath("//button[@class ='radius']"));
		logInButton.click();

		// Assert
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String actualErrorText = errorMessage.getText();
		String expectedErrorText = "Your username is invalid!";
		Assert.assertTrue(errorMessage.isDisplayed(), "The error message isn't displayed");
		Assert.assertTrue(actualErrorText.contains(expectedErrorText),
				"Incorrect error message text. \nActualErrorMessage" + actualErrorText + "\nExpectedErrorMessage"
						+ expectedErrorText);

		// Close the browser
		driver.quit();

	}
	
	@Test
	public void IncorrectPassword() {
		// driver
		System.setProperty("webdriver.chome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// open the site
		driver.get("http://the-internet.herokuapp.com/login");

		// Enter incorrect username and correct password
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));

		username.sendKeys("tomsmith");
		password.sendKeys("incorrect");

		// Click Login Button
		WebElement logInButton = driver.findElement(By.xpath("//button[@class ='radius']"));
		logInButton.click();

		// Assert
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String actualErrorText = errorMessage.getText();
		String expectedErrorText = "Your password is invalid!";
		Assert.assertTrue(errorMessage.isDisplayed(), "The error message isn't displayed");
		Assert.assertTrue(actualErrorText.contains(expectedErrorText),
				"Incorrect error message text. \nActualErrorMessage" + actualErrorText + "\nExpectedErrorMessage"
						+ expectedErrorText);

		// Close the browser
		driver.quit();

	}

}

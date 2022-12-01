package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {

	@Test
	public void loginTest() {

		System.out.println("Start test");

		// Create Driver
		System.setProperty("webdriver.chome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		// open test page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("page is opened");

		// enter username

		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");

		// enter password

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");

		// click login button

		WebElement logInButton = driver.findElement(By.xpath("//button[@class ='radius']"));
		logInButton.click();
		sleep(2000);

		// verifications

		// new url
		String expectedUrl = "http://the-internet.herokuapp.com/secure";
		String actualUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualUrl, expectedUrl, "Actual urs is not the same with expected url. ");

		// logout button is visible

		WebElement logOutButton = driver.findElement(By.xpath("//a[@class = 'button secondary radius']"));
		Assert.assertTrue(logOutButton.isDisplayed(), "the LogOut Button is not visible");

		// successful login message
		WebElement succussMessage = driver.findElement(By.xpath("//div[@id = 'flash']"));
		String expectedMessage = "You logged into a secure area!";
		String actualMessage = succussMessage.getText();
		// Assert.assertEquals( actualMessage, expectedMessage, "Actual message is not
		// the same with expected message. ");
		Assert.assertTrue(actualMessage.contains(expectedMessage),
				"Actual message does not contain  expected message. \nActual Message: " + actualMessage
						+ "\nExpectedMessage):" + expectedMessage);

		// Close the browser
		driver.quit();

	}

	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

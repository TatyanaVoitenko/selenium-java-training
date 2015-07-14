package ru.st.selenium;

import static org.junit.Assert.fail;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AddFilm extends ru.st.selenium.pages.TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test

	public void testAddFilm() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.get(baseUrl + "/php4dvd/");
		WebElement userNameField = driver.findElement(By.id("username"));
		userNameField.clear();
		userNameField.sendKeys("admin");
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
		passwordField.sendKeys("admin");
		driver.findElement(By.name("submit")).click();
		
		driver.findElement(By.xpath("//a[contains(@href,\"./?go=add\")]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));
		driver.findElement(By.name("name")).sendKeys("My First Film");
		driver.findElement(By.name("aka")).sendKeys("hfffg");
		driver.findElement(By.name("year")).sendKeys("2015");
		driver.findElement(By.name("duration")).sendKeys("1000");
		driver.findElement(By.name("rating")).sendKeys("10");
		driver.findElement(By.id("own_no")).click();
		driver.findElement(By.id("seen_no")).click();
		driver.findElement(By.id("loaned_no")).click();
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("movie")));

		driver.findElement(By.linkText("Log out")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	@Test
	
	public void testAddFilm_Negative() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get(baseUrl + "/php4dvd/");
		WebElement userNameField = driver.findElement(By.id("username"));
		userNameField.clear();
		userNameField.sendKeys("admin");
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
		passwordField.sendKeys("admin");
		driver.findElement(By.name("submit")).click();

		
		driver.findElement(By.xpath("//a[contains(@href,\"./?go=add\")]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));

		
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(""); //enter empty name
		driver.findElement(By.name("aka")).clear();
		driver.findElement(By.name("aka")).sendKeys("hfffg");
		driver.findElement(By.name("year")).clear();
		driver.findElement(By.name("year")).sendKeys("2015");
		driver.findElement(By.name("duration")).clear();
		driver.findElement(By.name("duration")).sendKeys("1000");
		driver.findElement(By.name("rating")).clear();
		driver.findElement(By.name("rating")).sendKeys("10");
		driver.findElement(By.id("own_no")).click();
		driver.findElement(By.id("seen_no")).click();
		driver.findElement(By.id("loaned_no")).click();
		driver.findElement(By.id("submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='error' and @for='name']")));

		driver.findElement(By.linkText("Log out")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		// storeLocation | movieAddress |
		String movieAddress = driver.getCurrentUrl();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
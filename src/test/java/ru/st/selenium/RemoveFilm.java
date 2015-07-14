package ru.st.selenium;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class RemoveFilm extends ru.st.selenium.pages.TestBase {
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Test
	public void testRemoveFilm() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.get(baseUrl + "/php4dvd/");
		WebElement userNameField = driver.findElement(By.id("username"));
		userNameField.clear();
		userNameField.sendKeys("admin");
		WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
		passwordField.sendKeys("admin");
		driver.findElement(By.name("submit")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[4]/div/div[2]")));
		driver.findElement(By.xpath("//a[4]/div/div[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'delete.png')]")));
		driver.findElement(By.xpath("//img[contains(@src,'delete.png')]")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='results']")));
	    
	    
	    driver.findElement(By.linkText("Log out")).click();
		alert = driver.switchTo().alert();
		alert.accept();
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
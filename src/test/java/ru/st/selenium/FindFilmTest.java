package ru.st.selenium;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.st.selenium.LoginTest;

public class FindFilmTest extends ru.st.selenium.pages.TestBase{

	@Test
	  public void findFilm() throws Exception {
		
	    driver.get(baseUrl + "/php4dvd/");
	    WebElement userNameField = driver.findElement(By.id("username"));
		userNameField.clear();
	    userNameField.sendKeys("admin");
	    WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
	    passwordField.sendKeys("admin");
	    driver.findElement(By.name("submit")).click();
	    WebDriverWait wait = new WebDriverWait(driver,10);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
	   WebElement search_field = driver.findElement(By.id("q"));
	   search_field.sendKeys(Keys.CONTROL + "a");
	   Thread.sleep(200);
	   search_field.sendKeys(Keys.DELETE);
	   search_field.sendKeys("big game");
	   search_field.sendKeys(Keys.ENTER);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("movie_cover")));
	   String  title = driver.findElement(By.className("title")).getText();
       Assert.assertEquals(title, "Big game"); 
       List<WebElement> elements =driver.findElements(By.xpath("//a[contains(@href,'./?go=movie&id=')]"));
       int films_count = elements.size();
       Assert.assertEquals(films_count, 1);
       driver.findElement(By.linkText("Log out")).click();
       Alert alert = driver.switchTo().alert();
       alert.accept();

	   
	}
	
	@Test
	  public void Film_is_not_found() throws Exception {
		
	    driver.get(baseUrl + "/php4dvd/");
	    WebElement userNameField = driver.findElement(By.id("username"));
		userNameField.clear();
	    userNameField.sendKeys("admin");
	    WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
	    passwordField.sendKeys("admin");
	    driver.findElement(By.name("submit")).click();
	    WebDriverWait wait = new WebDriverWait(driver,10);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
	   WebElement search_field = driver.findElement(By.id("q"));
	   search_field.sendKeys(Keys.CONTROL + "a");
	   Thread.sleep(200);
	   search_field.sendKeys(Keys.DELETE);

	   search_field.sendKeys("Rob");
	   search_field.sendKeys(Keys.ENTER);
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("results")));
	   String  title = driver.findElement(By.className("content")).getText();
       Assert.assertEquals(title, "No movies where found."); 
       driver.findElement(By.linkText("Log out")).click();
       Alert alert = driver.switchTo().alert();
       alert.accept();

	   
	}
	
}

package ru.st.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
		//login
		WebDriverWait wait = new WebDriverWait(driver,10);
	    driver.get(baseUrl + "/php4dvd/");
	    WebElement userNameField = driver.findElement(By.id("username"));
		userNameField.clear();
	    userNameField.sendKeys("admin");
	    WebElement passwordField = driver.findElement(By.name("password"));
		passwordField.clear();
	    passwordField.sendKeys("admin");
	    driver.findElement(By.name("submit")).click();
	    
	    //clare search field
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
	    WebElement search_field = driver.findElement(By.id("q"));
		search_field.sendKeys(Keys.CONTROL + "a");
		Thread.sleep(200);
		search_field.sendKeys(Keys.DELETE);
		search_field.sendKeys(Keys.ENTER); 

	   //count how many films contains page before search is performed
	   List<WebElement> elements_before_search =driver.findElements(By.xpath("//a[contains(@href,'./?go=movie&id=')]"));
	   int films_count_before_search = elements_before_search.size(); 
	   
	   //enter search word into search field
	   search_field.sendKeys("game");
	   search_field.sendKeys(Keys.ENTER);
	
	   String  title = driver.findElement(By.xpath("//div[@id='content']")).getText();
	   System.out.println(title);
	   title = driver.findElement(By.xpath("//div[@id='content']")).getText();
	   System.out.println(title);
	   //count how many films contains page after search is performed
	   List<WebElement> elements_after_search=driver.findElements(By.xpath("//a[contains(@href,'./?go=movie&id=')]"));
       int films_count_after_search = elements_after_search.size(); 
      
      // Search does not work If number of films on page is not changed  
       Assert.assertNotEquals(films_count_after_search, films_count_before_search);   
       
       driver.findElement(By.linkText("Log out")).click();
       Alert alert = driver.switchTo().alert();
       alert.accept();

	   
	}
	
	//@Test
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

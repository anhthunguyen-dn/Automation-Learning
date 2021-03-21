package automationfc.com;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.thread.ThreadTimeoutException;

public class Topic_03_Selenium_API_Element1 {
	WebDriver driver;
	WebElement element;
    
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	@Test
	public void TC_01_IsDisplayed() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		boolean emailTextboxStatus = driver.findElement(By.xpath("//input[@type='email']")).isDisplayed();
		  if (emailTextboxStatus == true) {
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
		    System.out.println("Email textbox is not displayed");
		}
		boolean educationTextboxStatus = driver.findElement(By.xpath("//label[@for='edu']")).isDisplayed();
		  if (educationTextboxStatus == true) {
			System.out.println("Education textbox is displayed");
		} else {
		    System.out.println("Education textbox is not displayed");
		}
		boolean AgeUnder18 = driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed();
		  if (AgeUnder18 == true) {
			System.out.println("AgeUnder18 textbox is displayed");
		} else {
		    System.out.println("AgeUnder18 textbox is not displayed");
		}
	}

	
	@Test
	public void TC_02_IsEnable() throws InterruptedException {
		boolean under18Status = driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed();
		if (under18Status == true) {
			driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled();
			System.out.println("AgeUnder18RadioButton is enabled");
		} else {
		System.out.println("AgeUnder18RadioButton is disabled");
		}
	}
	
	@Test
	public void TC_03_IsSelected() throws InterruptedException {
		driver.findElement(By.id("under_18")).click();
		driver.findElement(By.id("java")).click();
		assertTrue(driver.findElement(By.id("under_18")).isSelected());
		assertTrue(driver.findElement(By.id("java")).isSelected());
		System.out.println("Java is selected");
		driver.findElement(By.id("java")).click();
		assertFalse(driver.findElement(By.id("java")).isSelected());
		System.out.println("Java is de-selected");
		driver.quit();
			
	}
	
	@Test
	public void TC_04_Validated () throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("automationfc");
		/*lowercase*/
		driver.findElement(By.id("new_password")).sendKeys("auto");
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		driver.findElement(By.id("new_password")).clear();
		/*uppercase*/
		driver.findElement(By.id("new_password")).sendKeys("AUTO");
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		driver.findElement(By.id("new_password")).clear();
		/*number*/
		driver.findElement(By.id("new_password")).sendKeys("15");
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		driver.findElement(By.id("new_password")).clear();
		/*special*/
		driver.findElement(By.id("new_password")).sendKeys("@");
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		driver.findElement(By.id("new_password")).clear();
		/*8-char*/
		driver.findElement(By.id("new_password")).sendKeys("Auto1234");
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		assertFalse(driver.findElement(By.id("create-account")).isEnabled());
		driver.findElement(By.id("new_password")).clear();
		/*Vadidated*/
		driver.findElement(By.id("new_password")).sendKeys("Auto@1234");
		Thread.sleep(1000);
		assertTrue(driver.findElement(By.id("create-account")).isEnabled());
		driver.findElement(By.id("new_password")).clear();
		//Selected
		driver.findElement(By.id("marketing_newsletter")).click();
		assertTrue(driver.findElement(By.id("marketing_newsletter")).isSelected());
		driver.findElement(By.id("marketing_newsletter")).click();
		assertFalse(driver.findElement(By.id("marketing_newsletter")).isSelected());
		
	}
	
		
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
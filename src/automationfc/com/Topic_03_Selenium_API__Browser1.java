package automationfc.com;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_API__Browser1 {
	WebDriver driver;
	WebElement element;
    
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Verify_Url() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.quit();
		
	}

	@Test
	public void TC_02_Verify_Title() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		driver.quit();
		
	}
	
	@Test
	public void TC_03_Nagative_function() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		driver.quit();
		
	}
	
	@Test
	public void TC_04_Get_Page_Source_code() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		driver.quit();
	}
	
	@Test
	public void TC_05_Check_IsDisplayed() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
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
	public void TC_06_Check_IsEnable() throws InterruptedException {
		boolean under18Status = driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed();
		if (under18Status == true) {
			driver.findElement(By.xpath("//input[@id='under_18']")).click();
			System.out.println("Email textbox is displayed");
		} else {
		System.out.println("Email textbox is not displayed");
		}
		driver.quit();
	}
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
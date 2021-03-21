package automationfc.com;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_API_Browser_Element {
	WebDriver driver;
    
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
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
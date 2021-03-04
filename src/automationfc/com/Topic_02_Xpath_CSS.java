package automationfc.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_CSS {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Login_with_Empty_Email_and_Pass(){
		// Login with empty Email and Password
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.findElement(By.xpath("//div[@id='advice-required-entry-email' and text()='This is a required field.']")).isDisplayed();
		driver.findElement(By.xpath("//div[@id='advice-required-entry-pass' and text()='This is a required field.']")).isDisplayed();
	}

	@Test
	public void TC_02_Login_with_Invalid_Email(){
		// Stay in Login Page
		// Login with empty Email and Password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@1234");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.findElement(By.xpath("//div[contains(text(),'enter a valid email')]")).isDisplayed();
	}
	
	@Test
	public void TC_03_Login_with_Pass_less_than_min_number_characters(){
		// Stay in Login Page
		// Login with empty Email and Password
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("anhthung1803@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.findElement(By.xpath("//div[@id='advice-validate-password-pass'][contains(string(),'or more characters without')]")).isDisplayed();
	}
	
	@Test
	public void TC_04_Login_with_incorrect_email_or_pass(){
		// Stay in Login Page
		// Login with empty Email and Password
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("anhthung1803@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456789");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.findElement(By.xpath("//span[contains(.,'Invalid login')]")).isDisplayed();
	}
	
	
	
	
	
	
	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
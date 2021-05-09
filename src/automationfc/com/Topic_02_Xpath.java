package automationfc.com;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_02_Xpath {
	WebDriver driver;
    Random rand = new Random();
    String random_email = ("automation" + rand.nextInt(123456789) + "@gmail.com");
    
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
	}

	@Test
	public void TC_01_Login_with_Empty_Email_and_Pass() throws InterruptedException {
		// Login with empty Email and Password
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");
		Thread.sleep(5000);
	}

	@Test
	public void TC_02_Login_with_Invalid_Email() throws InterruptedException {
		// Stay in Login Page
		// Login with invalid Email
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1234@1234");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'enter a valid email')]")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
		Thread.sleep(5000);
	}
	
	@Test
	public void TC_03_Login_with_Pass_less_than_min_number_characters() throws InterruptedException {
		// Stay in Login Page
		// Login with password less than minimum number characters
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(random_email);
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass'][contains(string(),'or more characters without')]")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
		Thread.sleep(5000);
	}
	
	@Test
	public void TC_04_Login_with_incorrect_email_or_pass() throws InterruptedException {
		// Stay in Login Page
		// Login with incorrect email or pass
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(random_email);
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456789");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[contains(.,'Invalid login')]")).getText(),"Invalid login or password.");
		Thread.sleep(5000);
	}
	
	@Test
	public void TC_05_Create_a_new_account() throws InterruptedException {
		// Stay in Login Page
		// Create a new account
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='pass']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Anh");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Thu");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Nguyen");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(random_email);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456789aA@");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456789aA@");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()]")).getText(),"Thank you for registering with Main Website Store.");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains("Anh Thu Nguyen"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains(random_email));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']//li[last()]/child::a")).click();
		driver.get("http://live.demoguru99.com/index.php/");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@class='welcome-msg']")).getText(),"Default welcome msg!");
		Thread.sleep(5000);
		
	}
	
	@Test
	public void TC_06_Log_in_with_valid_email_password() throws InterruptedException {
		// Log in with valid email and password
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath(".//div[@class='footer']//a[@title='My Account']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(random_email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456789aA@");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		driver.get("http://live.demoguru99.com/index.php/customer/account/index/");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='dashboard']/descendant::h1")).getText(),"MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']/p/strong[text()]")).getText(),"Hello, Anh Thu Nguyen!");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains("Anh Thu Nguyen"));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']/p")).getText().contains(random_email));
		Thread.sleep(5000);
				
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
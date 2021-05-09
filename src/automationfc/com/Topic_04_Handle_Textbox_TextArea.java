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

public class Topic_04_Handle_Textbox_TextArea {
	WebDriver driver;
	String LoginPageUrl, UserID, Password;
	String name, dob, addr, city, state, pin, phone, email;
	String CustomerID;
	By CustomerNameTextBox = By.name("name");
	By DateOfBirthTextBox = By.name("dob");
	By AddressTextBox = By.name("addr");
	By CityTextBox = By.name("city");
	By StateTextBox = By.name("state");
	By PINTextBox = By.name("pinno");
	By PhoneTextBox = By.name("telephoneno");
	By EmailTextBox = By.name("emailid");
	By PasswordTextBox = By.name("password");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		
		name = "Anh Thu";
		dob = "2010-01-03";
		addr = "Da Nang";
		city = "Da Nang";
		state = "Da Nang";
		pin = "123456";
		phone = "0987654321";
		email = "anhthunguyen.tester@gmail.com";
	}

	@Test
	public void TC_01_Get_username_pass_to_register() {
		LoginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.get("http://demo.guru99.com/");
		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		UserID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		Password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
		
	}
	
	@Test	
	public void TC_02_Login_Sucess() {
		driver.get(LoginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(UserID);
		driver.findElement(By.name("password")).sendKeys(Password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + UserID + "']")).isDisplayed());
		
	}
	
	@Test	
	public void TC_03_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		driver.findElement(CustomerNameTextBox).sendKeys(name);
		driver.findElement(DateOfBirthTextBox).sendKeys(dob);
		driver.findElement(AddressTextBox).sendKeys(addr);
		driver.findElement(CityTextBox).sendKeys(city);
		driver.findElement(StateTextBox).sendKeys(state);
		driver.findElement(PINTextBox).sendKeys(pin);
		driver.findElement(PhoneTextBox).sendKeys(phone);
		driver.findElement(EmailTextBox).sendKeys(email);
		driver.findElement(PasswordTextBox).sendKeys(Password);
		driver.findElement(By.name("sub")).click();
		Thread.sleep(2000);
		//verify customer information
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']following-sibling::td")).getText(), dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']following-sibling::td")).getText(), addr);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']following-sibling::td")).getText(), phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']following-sibling::td")).getText(), email);
		
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']following-sibling::td")).getText();
	}
	
	@Test	
	public void TC_04_Edit_Customer() throws InterruptedException {
		
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	}

	@AfterClass
	public void afterClass() {
		
	}

}
package automationfc.com;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
    WebDriver driver;
	Select select;
	String firstName, lastName, emailAddress, companyName, password, Register;
	String date, month, year;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		firstName = "John";
		lastName = "Wick";
		companyName = "Hollywood";
		password = "123456abc";

		date = "25";
		month = "September";
		year = "1988";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	 @Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/");
		/* 1 - Mở ra trang Resgister */
		driver.findElement(By.cssSelector(".ico-register")).click();

		/* 2 - Điền thông tin vào các field required */
		checkToCheckboxOrRadio(By.cssSelector("#gender-male"));
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Khởi tạo biến select để thao tác với dropdown Date
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		Assert.assertEquals(select.getOptions().size(), 32);

		// Sử dụng Index: Chọn ngày 3
		// select.selectByIndex(4);
		// sleepInSecond(5);

		// Sử dụng value: Chọn ngày 15
		// select.selectByValue("15");
		// sleepInSecond(5);

		// Sử dụng text: Chọn ngày số 30 --> Nên dùng text
		// select.selectByVisibleText("30");
		// sleepInSecond(5);

		// Khởi tạo biến select để thao tác với dropdown Month
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText(month); // ** dùng nhiều nhất
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month); // ** dùng nhều nhất
		Assert.assertEquals(select.getOptions().size(), 13);

		// Kiểm tra dropdown này không hỗ trợ multiple
		// Assert.assertFalse(select.isMultiple()); // * ít dùng

		// Lấy ra tất cả các item trong dropdown đó
		// List<WebElement> allItem = select.getOptions(); //* ít dùng

		// Kiểm tra xem số lượng item có bằng mong muốn hay không
		// Assert.assertEquals(allItem.size(), 13);

		// bỏ chọn hết tất cả 1 lượt
		// select.deselectAll();

		// Trả về text đã chọn thành công
		// select.getFirstSelectedOption().getText();

		// Khởi tạo biến select để thao tác với dropdown Year
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);

		emailAddress = "johnwick" + getRandomNumber() + "@hotmail.com";
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);

		checkToCheckboxOrRadio(By.id("Newsletter"));

		driver.findElement(By.id("Password")).sendKeys(password);
		sleepInSecond(5);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		

		/* 3 - Đăng ký */
		driver.findElement(By.cssSelector("#register-button")).click();
		sleepInSecond(5);
       	/* 4 - Kiểm tra xuất hiện message đăng ký thành công */
		//Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(),"Your registration completed");
		driver.findElement(By.xpath("//div[@class='page-body']//div[@class='result']")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-body']//div[@class='result']")).getText(), Register);
		/* 5 - Vào trang My Account */
		driver.findElement(By.cssSelector(".ico-account")).click();
		

		// Sau khi click xong nó đã chuyển qua 1 trang html khác rồi nên bắt buộc phải
		// tìm element lại -> lỗi -> không tìm thấy element
		// Refresh/ load lại trang -> html update lại -> bắt buộc selenium phải tìm lại
		// element

		/* 6 - Kiểm tra đúng với thông tin đã đăng ký */
		Assert.assertTrue(driver.findElement(By.cssSelector("#gender-male")).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("#Newsletter")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);
		Assert.assertEquals(select.getOptions().size(), 32);

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		Assert.assertEquals(select.getOptions().size(), 13);

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		Assert.assertEquals(select.getOptions().size(), 112);

		driver.findElement(By.cssSelector(".ico-logout")).click();
	}

	private String contains(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Test
	public void TC_02_Multiple_Select() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Kiểm tra job role 1 không hỗ trợ multiple
		select = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(select.isMultiple());

		// Chọn Moblie Testing = selectByVisibleText
		select.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");

		// Chọn Manual Testing = selectByvalue
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");

		// Chọn Funtional UI Testing = selectByIndex
		select.selectByIndex(9);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");

		// Kiểm tra job role 1 có đủ 10 giá trị
		Assert.assertEquals(select.getOptions().size(), 10);

		select = new Select(driver.findElement(By.id("job2")));

		ArrayList<String> allItemText = new ArrayList<String>();
		allItemText.add("Automation");
		allItemText.add("Mobile");
		allItemText.add("Desktop");

		// Chọn 3 item
		for (String item : allItemText) {
			select.selectByVisibleText(item);
		}
		sleepInSecond(5);

		// Kiểm tra job role 2 hỗ trợ multiple
		Assert.assertTrue(select.isMultiple());


		// Kiểm tra đã chọn đúng 3 item thành công
		List<WebElement> allSelectedItems = select.getAllSelectedOptions();
		ArrayList<String> allsSelectedText = new ArrayList<String>();

		for (WebElement item : allSelectedItems) {
			allsSelectedText.add(item.getText());
		}

		Assert.assertEquals(allsSelectedText.size(), 3);
		Assert.assertEquals(allsSelectedText, allItemText);

	}
	

	public void checkToCheckboxOrRadio(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
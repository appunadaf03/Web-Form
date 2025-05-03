package webForm_Project;

import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WebForm {

	static WebDriver driver;
	
	@BeforeTest()
	public  void system_driver() {
		
		
		driver = new ChromeDriver();
		driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
		driver.manage().window().maximize();
		screenshot("Home");

	}
	
	
	@Test(priority=0)
	public void test_input() {
		driver.findElement(By.id("my-text-id")).sendKeys("Jhon");
		screenshot("Text");
	}
	
	@Test(priority=1)
	public void password() throws InterruptedException{
		//Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("Xyz@123");
		screenshot("pwd");
	}
	
	@Test(priority=2)
	public void textarea()
	{
		driver.findElement(By.xpath("//textarea[@class=\"form-control\"]")).sendKeys("import org.openqa.selenium.chrome.ChromeDriver;\r\n"
				+ "import org.testng.annotations.BeforeTest;\r\n"
				+ "import org.testng.annotations.Test;");
		screenshot("textarea");
	}
	
	@Test(priority=3)
	public void dropdown() {
		
		Select sdp = new Select(driver.findElement(By.xpath("//select[@name=\"my-select\"]")));
		sdp.selectByIndex(1);
	}
	
	@Test(priority=4)
	public void dropdow_datalist() throws InterruptedException {
		
		/*WebElement src = driver.findElement(By.xpath("//input[@list=\"my-options\"]"));
		Actions action = new Actions(driver);
		action.click(src).build().perform();
		Thread.sleep(5000);
		WebElement hover = driver.findElement(By.xpath("//option[@value=\"New York\"]"));
		Thread.sleep(5000);
		action.click(hover).build().perform();  */
		
		//driver.findElement(By.xpath("//datalist[@id=\"my-options\"]/option[@value=\"New York\"]")).click();
		
		// Find the input and type part of the value
      /*  WebElement input = driver.findElement(By.name("my-datalist"));
        input.sendKeys("N");
        Thread.sleep(1000);
        input.sendKeys("e");
        Thread.sleep(1000);
        input.sendKeys("w");
        Thread.sleep(1000);

        Actions action = new Actions(driver);
        WebElement hover = driver.findElement(By.xpath("//datalist[@id='my-options']/option[@value='New York']"));
        action.click(hover).build().perform() */
        
        // Wait and find the matching option (for demonstration; not required unless using JS suggestion list)
        //WebElement option = driver.findElement(By.xpath("//datalist[@id='my-options']/option[@value='New York']"));  
		//option.click();
		String optionToselect = "New York";
		int count=0;
		driver.findElement(By.name("my-datalist")).sendKeys("New York");
		Thread.sleep(3000);
		
		List<WebElement> option_list=driver.findElements(By.xpath("//datalist[@id=\"my-options\"]"));
		
       for( WebElement ele:option_list) {
    	   String current_option=ele.getText();
    	   if(current_option.contains(optionToselect)) {
    		   ele.click();
    		   count++;
    		   break;
    	   }
       }
       if(count!=0) {
    	   System.out.println(optionToselect + " has been selected in the DD");
       }
       else {
    	   System.out.println("option you want to selecte is not availbale in the DD");
       }
	}
	
	@Test(priority=6)
	public void choose_file() throws InterruptedException, AWTException{
		//Thread.sleep(3000);
		//1. This is the  sendkeys()  method
		//driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\appun\\Downloads\\src.png");
		
		//2. This is the javascriptexecutor method
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//input[@type='file']")));
	
		
		// Alternative for the javascriptexecutor method
		/*WebElement fileInput = driver.findElement(By.xpath("//input[@name='my-file']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", fileInput);*/

		//File Path Selection, Copying file path into clipboard ctrl + c
		StringSelection filePathSelection = new StringSelection("C:\\Users\\appun\\Downloads\\src.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePathSelection, null);
		Thread.sleep(3000);
		
		//Robot Class, Ctrl + V
		Robot rm =new Robot();
		rm.keyPress(KeyEvent.VK_CONTROL);
		rm.keyPress(KeyEvent.VK_V);
		rm.keyRelease(KeyEvent.VK_V);
		rm.keyRelease(KeyEvent.VK_CONTROL);
		
		//Now to Click on Enter Button
		rm.keyPress(KeyEvent.VK_ENTER);
	}
	
	@Test(priority=9)
	public void color_pick() {
		WebElement colour = driver.findElement(By.xpath("//input[@name='my-colors']"));
		colour.sendKeys("#F20D2F");
		
		// Have to work on this more in coming days 
	}
	
	
	
	@Test(priority=5)
	public void checked() {
		driver.findElement(By.id("my-check-2")).click();
		driver.findElement(By.id("my-radio-2")).click();
	}
	
	
	@Test(priority=6)
	public void calender() {
		driver.findElement(By.xpath(" //input[@name='my-date']")).click();
		
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@data-date=\"1744329600000\"]")));
		String aMonth = driver.findElement(By.className("datepicker-switch")).getText();
		
		
		while(!(aMonth.equals("July 2026"))){
			driver.findElement(By.className("next")).click();
			
			aMonth = driver.findElement(By.className("datepicker-switch")).getText();
		}driver.findElement(By.xpath("//td[@class='day'][normalize-space()='1']")).click();
		System.out.println(aMonth);
	
	}
	
	@Test(priority=7)
	public void range() {
		WebElement hover = driver.findElement(By.xpath("//input[@class=\"form-range\"]"));
		Actions action = new Actions(driver);
		action.dragAndDropBy(hover, -100,125).perform();
	
	}
	
	@Test(priority=8)
	public void submit() {
		driver.findElement(By.xpath("//button[@type='submit']")).click();	}
	
	
	public void screenshot(String firstname) {
		try {
			File src =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("./Snaaps/"+firstname+".png"));
		}
		catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	
	
	
	@AfterTest
	public void system_logout() throws InterruptedException{
		Thread.sleep(10000);
		driver.close();
	}
}

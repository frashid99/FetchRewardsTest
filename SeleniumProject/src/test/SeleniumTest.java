package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {
	public static void main(String[] args) {
		//Setting up Chrome driver and navigating to the website
		System.setProperty("webdriver.chrome.driver","C:\\Users\\faraz\\OneDrive\\Desktop\\ChromeDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://ec2-54-208-152-154.compute-1.amazonaws.com/");
		driver.manage().window().maximize();
		
		insertValues(driver);
		
		//Weigh it
		driver.findElement(By.xpath("//button[normalize-space()='Weigh']")).click();

		//Wait for comparator operator instead of "?"
		WebDriverWait wait = new WebDriverWait(driver, 3);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[normalize-space()='?']")));
		
		//Set comparator operator to a variable
		String result = driver.findElement(By.id("reset")).getText();
		
		findFakeBar(result, driver, wait);
		printAlert(driver);
		printWeighings(driver, wait);
	}
	
	//Looks for fake bar depending on operator
	public static void findFakeBar(String result, WebDriver driver, WebDriverWait wait) {
		if(result.equals("=")) {
			ifFirstWeighEqual(driver, wait);
		}
		else if(result.equals("<")) {
			ifFirstWeighLessThan(driver, wait);
		}else {
			ifFirstWeighGreaterThan(driver, wait);
		}
	}
	
	//Insert values into left and right bowl
	public static void insertValues(WebDriver driver) {
		driver.findElement(By.xpath("//input[@id='left_0']")).sendKeys("0");
		driver.findElement(By.xpath("//input[@id='left_1']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='left_2']")).sendKeys("2");
						
		driver.findElement(By.xpath("//input[@id='right_0']")).sendKeys("3");
		driver.findElement(By.xpath("//input[@id='right_1']")).sendKeys("4");
		driver.findElement(By.xpath("//input[@id='right_2']")).sendKeys("5");	
	}
	
	//If bowls are equal then last three numbers contains fake bar
	public static void ifFirstWeighEqual(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
		
		driver.findElement(By.xpath("//input[@id='left_0']")).sendKeys("0");
		driver.findElement(By.xpath("//input[@id='left_1']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='left_2']")).sendKeys("6");
		
		driver.findElement(By.xpath("//input[@id='right_0']")).sendKeys("3");
		driver.findElement(By.xpath("//input[@id='right_1']")).sendKeys("4");
		driver.findElement(By.xpath("//input[@id='right_2']")).sendKeys("7");
		
		equalSecondWeigh(driver, wait);
	}
	
	//Check if left or right side is less in weight otherwise it's the last number
	public static void equalSecondWeigh(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//button[normalize-space()='Weigh']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[normalize-space()='?']")));
		
		String result2 = driver.findElement(By.id("reset")).getText();
		
		if(result2.equals("<")) {
			driver.findElement(By.xpath("//button[normalize-space()='6']")).click();
		}else if(result2.equals(">")) {
			driver.findElement(By.xpath("//button[normalize-space()='7']")).click();
		}else {
			driver.findElement(By.xpath("//button[normalize-space()='8']")).click();
		}
	}
	
	//If left bowl weighs less then first three numbers contain fake bar
	public static void ifFirstWeighLessThan(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
		
		driver.findElement(By.xpath("//input[@id='left_0']")).sendKeys("0");
		driver.findElement(By.xpath("//input[@id='left_1']")).sendKeys("3");
		driver.findElement(By.xpath("//input[@id='left_2']")).sendKeys("4");
		
		driver.findElement(By.xpath("//input[@id='right_0']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='right_1']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@id='right_2']")).sendKeys("6");
		
		lessThanSecondWeigh(driver, wait);
	}
	
	//Check if left or right side is less in weight otherwise it's the last number from the first three
	public static void lessThanSecondWeigh(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//button[normalize-space()='Weigh']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[normalize-space()='?']")));
		
		String result3 = driver.findElement(By.id("reset")).getText();
		
		if(result3.equals("<")) {
			driver.findElement(By.xpath("//button[normalize-space()='0']")).click();
		}else if(result3.equals(">")) {
			driver.findElement(By.xpath("//button[normalize-space()='1']")).click();
		}else {
			driver.findElement(By.xpath("//button[normalize-space()='2']")).click();
		}
	}
	
	//If right bowl weighs less then middle three numbers contain the fake bar
	public static void ifFirstWeighGreaterThan(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
		
		driver.findElement(By.xpath("//input[@id='left_0']")).sendKeys("3");
		driver.findElement(By.xpath("//input[@id='left_1']")).sendKeys("1");
		driver.findElement(By.xpath("//input[@id='left_2']")).sendKeys("2");
		
		driver.findElement(By.xpath("//input[@id='right_0']")).sendKeys("4");
		driver.findElement(By.xpath("//input[@id='right_1']")).sendKeys("6");
		driver.findElement(By.xpath("//input[@id='right_2']")).sendKeys("7");
		
		greaterThanSecondWeigh(driver, wait);
	}
	
	//Check if left or right side is less in weight otherwise it's the last number from the middle three
	public static void greaterThanSecondWeigh(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.xpath("//button[normalize-space()='Weigh']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[normalize-space()='?']")));
		
		String result4 = driver.findElement(By.id("reset")).getText();
		
		if(result4.equals("<")) {
			driver.findElement(By.xpath("//button[normalize-space()='3']")).click();
		}else if(result4.equals(">")) {
			driver.findElement(By.xpath("//button[normalize-space()='4']")).click();
		}else {
			driver.findElement(By.xpath("//button[normalize-space()='5']")).click();
		}
	}
	
	//Print out weighings and size
	public static void printWeighings(WebDriver driver, WebDriverWait wait) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[normalize-space()='?']")));
		List<WebElement> list = driver.findElements(By.tagName("li"));
		System.out.println("Weighings:");
		for(WebElement item : list) {
			System.out.println(item.getText());
		}
		System.out.println("---------------");
		System.out.println("Number of weighings: " + list.size());
	}
	
	//Print victory message
	public static void printAlert(WebDriver driver) {
		System.out.println();
		System.out.println(driver.switchTo().alert().getText());
		System.out.println("---------------");
		driver.switchTo().alert().accept();
	}
	
}

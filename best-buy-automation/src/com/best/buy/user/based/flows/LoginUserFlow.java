package com.best.buy.user.based.flows;

import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUserFlow {
	public static WebDriver driver = null;
	@SuppressWarnings("deprecation")
	public static void main(String args[]) {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.navigate().to("https://www.bestbuy.com/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println(title);

		///////////////////////////
		// 1. Navigate to Bestbuy//
		///////////////////////////
		WebElement category = driver.findElement(By.cssSelector("img[src='https://www.bestbuy.com/~assets/bby/_intl/landing_page/images/maps/usa.svg'][alt='United States']"));
		Actions action = new Actions(driver);
		action.click(category).perform();

		////////////////////////////////
		// 2. Navigate to Log In User //
		////////////////////////////////
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement accountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Account']")));
		accountButton.click();
	
		WebElement signIn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='c-button c-button-secondary c-button-sm sign-in-btn']")));
		signIn.click();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		// Enter user input into the input element
		System.out.print("Enter your emai: ");
		String email = scanner.nextLine();
		WebElement emailInputElement = driver.findElement(By.id("fld-e"));
		emailInputElement.sendKeys(email);

		// Enter user input into the input element
		System.out.print("Enter your password: ");
		String password = scanner.nextLine();
		WebElement passwordInputElement = driver.findElement(By.id("fld-p1"));
		passwordInputElement.sendKeys(password);

		WebElement button = driver.findElement(By.xpath("//button[@data-track='Sign In']"));
		button.click();
	}
}

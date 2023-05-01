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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserInteractionBasedCheckOutFlow {
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


		///////////////////////////
		// 2. Search for any item//
		///////////////////////////
		WebElement searchInput = driver.findElement(By.id("gh-search-input"));
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter search term: ");
		String searchTerm = scanner.nextLine();
		// Enter user input into search input field and submit
		searchInput.sendKeys(searchTerm);
		searchInput.submit();


		///////////////////////////////////////////////////////
		// 3. Sort the search results with the sort dropdown //
		///////////////////////////////////////////////////////
		String[] options = {"Best Match", "Best Selling", "Best Discount", "Price Low to High", "Price High to Low", "Customer Rating", "New Arrivals", "Brand A-Z", "Brand Z-A"};
		System.out.println("Select a sorting option:");
		for (int i = 0; i < options.length; i++) {
			System.out.println((i+1) + ". " + options[i]);
		}
		scanner = new Scanner(System.in);
		int choice = 0;
		while (choice < 1 || choice > options.length) {
			System.out.print("Enter your choice (1-" + options.length + "): ");
			choice = scanner.nextInt();
			if (choice < 1 || choice > options.length) {
				System.out.println("Invalid choice. Please enter a number between 1 and " + options.length + ".");
			}
		}
		String sortBy = options[choice-1];
		WebElement dropdown = driver.findElement(By.id("sort-by-select"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(sortBy);


		/////////////////////////////////////////////////
		//4. Apply a price range to the search results //
		/////////////////////////////////////////////////
		// Take input for minimum value
		scanner = new Scanner(System.in);
		System.out.print("Enter minimum value: ");
		String minPrice = scanner.nextLine();
		// Find the minimum value input element by ID
		WebElement minInput = driver.findElement(By.id("min-currentprice_facet-input"));
		// Clear the input field and enter the minimum value
		minInput.clear();
		minInput.sendKeys(minPrice);
		scanner = new Scanner(System.in);
		// Take input for maximum value
		System.out.print("Enter maximum value: ");
		String maxPrice = scanner.nextLine();
		// Find the maximum value input element by ID
		WebElement maxInput = driver.findElement(By.id("max-currentprice_facet-input"));
		// Clear the input field and enter the maximum value
		maxInput.clear();
		maxInput.sendKeys(maxPrice);
		if (null != minPrice && null != maxPrice) {
			WebElement svgElement = driver.findElement(By.className("svg-size-s"));
			// Click on the SVG element
			svgElement.click();
		}
		
		//////////////////////////////////////////////////
		//5. Select an item and go to cart and checkout //
		//////////////////////////////////////////////////
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-sku-id='6202105']")));
		button.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement goToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-track='Attach Modal: Go to cart']")));
		goToCartButton.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
		checkoutButton.click();
	}
}

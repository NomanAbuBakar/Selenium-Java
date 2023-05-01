package com.best.buy.pre.set.flows;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContinueShoppingCheckOutFlow {
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
		searchInput.sendKeys("Samsung TV");
		searchInput.submit();


		///////////////////////////////////////////////////////
		// 3. Sort the search results with the sort dropdown //
		///////////////////////////////////////////////////////
		String[] options = {"Best Match", "Best Selling", "Best Discount", "Price Low to High", "Price High to Low", "Customer Rating", "New Arrivals", "Brand A-Z", "Brand Z-A"};
		String sortBy = options[4-1];
		WebElement dropdown = driver.findElement(By.id("sort-by-select"));
		Select select = new Select(dropdown);
		select.selectByVisibleText(sortBy);


		///////////////////////////////////////////////////////
		//4. Apply a price range to the search results //
		///////////////////////////////////////////////////////
		WebElement minInput = driver.findElement(By.id("min-currentprice_facet-input"));
		// Clear the input field and enter the minimum value
		minInput.clear();
		minInput.sendKeys("500");
		WebElement maxInput = driver.findElement(By.id("max-currentprice_facet-input"));
		// Clear the input field and enter the maximum value
		maxInput.clear();
		maxInput.sendKeys("2000");
		WebElement svgElement = driver.findElement(By.className("svg-size-s"));
		svgElement.click();
		
		
		//////////////////////////////////////////////////////////
		//5. Select an item and continue shopping then checkout //
		//////////////////////////////////////////////////////////
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-sku-id='6202105']")));
		button.click();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement goToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("continue-shopping")));
		goToCartButton.click();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cartLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart-label")));
		cartLabel.click();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement checkoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
		checkoutButton.click();
	}
}

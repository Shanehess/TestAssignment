package test.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class SearchReview {
	private AndroidDriver<WebElement> driver;
	
	@FindBy(xpath="//*[@resource-id=\"com.expedia.bookings:id/search_btn\"]")
	private WebElement search;
	
	public SearchReview(AndroidDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		new WebDriverWait(this.driver, 10).until(ExpectedConditions.elementToBeClickable(search));
	}
	
	public SearchResults clickSearch(){
		this.search.click();
		return new SearchResults(driver);
	}
}

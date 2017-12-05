package test.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Item {
	private AndroidDriver<WebElement> driver;
	
	@FindBy(xpath="//*[@resource-id=\"com.expedia.bookings:id/hotel_star_rating_bar\"]")
	private WebElement starbar;
	
	@FindBy(xpath="//*[@content-desc=\"Back\"]")
	private WebElement back;
	
	public Item(AndroidDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		new WebDriverWait(this.driver, 10).until(ExpectedConditions.elementToBeClickable(starbar));
	}
	public String getStars(){
		return starbar.getAttribute("name");
	}
	public SearchResults clickBack() {
		this.back.click();
		return new SearchResults(driver);
	}
}

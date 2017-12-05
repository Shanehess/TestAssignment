package test.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Search {
	private AndroidDriver<WebElement> driver;
	
	@FindBy(xpath="//*[@content-desc=\"Hotels Button\"]")
	private WebElement hotelsButton;
	@FindBy(xpath="//*[@text=\"Shop\"]")
	private WebElement shop;	
	
	public Search(AndroidDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		try
		{
			new WebDriverWait(this.driver, 10).until(ExpectedConditions.elementToBeClickable(hotelsButton));
		}
		catch(Exception e)
		{
			this.clickShop();
			new WebDriverWait(this.driver, 10).until(ExpectedConditions.elementToBeClickable(hotelsButton));
		}
	}
	public void clickShop(){
		this.shop.click();
	}
	public SearchOption clickHotels(){
		hotelsButton.click();
		return new SearchOption(driver);
	}
	public SearchResults searchHotels(String startDate, String endDate) {
		SearchOption searchOption = this.clickHotels();
		searchOption.clickCurLocation();
		searchOption.clickDate(startDate);
		searchOption.clickDate(endDate);
		SearchReview searchReview = searchOption.clickDone();
		return searchReview.clickSearch();
	}
}

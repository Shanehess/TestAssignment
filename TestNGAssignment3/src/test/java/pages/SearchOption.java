package test.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class SearchOption {
	private AndroidDriver<WebElement> driver;
	
	@FindBy(xpath="//*[@text=\"Current Location\"]")
	private WebElement curLocation;
	
	@FindBy(xpath="//*[@resource-id=\"android:id/button1\"]")
	private WebElement done;
	
	public SearchOption(AndroidDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		new WebDriverWait(this.driver, 10).until(ExpectedConditions.elementToBeClickable(curLocation));
	}
	
	public void clickCurLocation(){
		this.curLocation.click();
	}
	
	public void clickDate(String date)
	{
		driver.findElementByXPath("//*[@content-desc=\""+date+"\"]").click();
	}
	public SearchReview clickDone(){
		this.done.click();
		return new SearchReview(driver);
	}
}

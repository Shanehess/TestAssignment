package test.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class PopUp {
	private AndroidDriver<WebElement> driver;
	
	@FindBy(xpath="//*[@resource-id=\"com.expedia.bookings:id/button_next\"]")
	private WebElement next;
	
	@FindBy(xpath="//*[@resource-id=\"com.expedia.bookings:id/button_final\"]")
	private WebElement finalButton;
	
	public PopUp(AndroidDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		new WebDriverWait(this.driver, 3).until(ExpectedConditions.elementToBeClickable(next));
	}
	public void clickNext(){
		next.click();
	}
	public void clickFinal(){
		finalButton.click();
	}
	public void closePopUp(){
		this.clickNext();
		this.clickNext();
		this.clickFinal();
	}
}

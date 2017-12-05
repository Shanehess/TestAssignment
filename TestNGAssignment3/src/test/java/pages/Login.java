package test.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Login {
	private AndroidDriver<WebElement> driver;
	
	@FindBy(xpath="//*[@text=\"Email Address \"]")
	private WebElement email;
	
	@FindBy(xpath="//*[@text=\"Password \"]")
	private WebElement password;
	
	//@FindBy(xpath="//*[@resource-id=\"com.expedia.bookings:id/sign_in_button\"]")
	@FindBy(className = "q")
	private WebElement login;
	
	public Login(AndroidDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		new WebDriverWait(this.driver, 10).until(ExpectedConditions.elementToBeClickable(email));
	}
	public void setEmail(String username)
	{
		this.email.sendKeys(username);
	}
	public void setPassword(String pwd)
	{
		this.password.sendKeys(pwd);
	}
	public Search clickLogin()
	{
		login.click();
		return new Search(driver);
	}
	public Search login(String username,String pwd)
	{
		this.setEmail(username);
		this.setPassword(pwd);
		return this.clickLogin();
	}


}

package test.java.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import test.java.utilities.Utilities;

public class SearchResults {
	private AndroidDriver<WebElement> driver;
	
	
	@FindBy(xpath="//*[@resource-id=\"com.expedia.bookings:id/list_view\"]")
	private WebElement list;

	
	@FindBy(xpath="//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]")
	private WebElement firstItem;
	
	
	
	@FindBy(xpath="//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.support.v7.widget.RecyclerView[1]/android.widget.FrameLayout[3]/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.RelativeLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.TextView[1]")
	private WebElement thirdPrice;	
	
	public SearchResults(AndroidDriver<WebElement> driver){
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		new WebDriverWait(this.driver, 15).until(ExpectedConditions.elementToBeClickable(firstItem));
	}
	
	public Item clickFirst(){
		this.firstItem.click();
		return new Item(driver);
	}
	
	public String getPrice(){
		boolean loop = true;
		int count = 0;
		while(loop)
		{
			try{
				Utilities.scroll1(driver);
				loop = false;
				return this.thirdPrice.getText();
			}
			catch(Exception e)
			{
				if(count < 3)
				{
					System.out.println("Attempting to scroll this is the "+count++ +"time");
				}
				else
				{
					loop = false;
				}
			}
		}	
		return null;
	}

	public String getStars() {
		Item item = this.clickFirst();
		String temp = item.getStars();
		item.clickBack();
		return temp;
	}
}

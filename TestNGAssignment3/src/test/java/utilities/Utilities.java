package test.java.utilities;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Utilities {
	public static void scroll1(AndroidDriver<WebElement> driver){
		TouchAction touchAction66 = new TouchAction(driver);
		touchAction66.press(687,2259).waitAction(1).moveTo(675,1661).release();
		driver.performTouchAction(touchAction66);
	}
	public static void clickBack(AndroidDriver<WebElement> driver)
	{
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("keySequence", "BACK");
		driver.executeScript("mobile:presskey", params1);
	}
}

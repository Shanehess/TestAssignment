package test.java.test;

import org.testng.annotations.Test;

import com.perfecto.reportium.test.TestContext;

import test.java.pages.Login;
import test.java.pages.PopUp;
import test.java.pages.Search;
import test.java.pages.SearchResults;
import test.java.utilities.BaseTest;

public class Test3 extends BaseTest {
	@Test
	public void Assignment3() {
		reportiumClient.testStart("Assignment3", new TestContext());
		driver.launchApp();
		reportiumClient.stepStart("Login");
		
		try {
			new PopUp(driver).closePopUp();
		} catch (Exception e) {
			System.out.println("No popUp. Continuing.");
		}
		Login login = new Login(driver);
		Search search = login.login("shaneh@perfectomobile.com","Perfecto123");
		reportiumClient.stepEnd("Login");
		
		reportiumClient.stepStart("Search Hotels");
		SearchResults results = search.searchHotels("Saturday, October 28","Saturday, November 4");
		reportiumClient.stepEnd("Search Hotels");
		
		reportiumClient.stepStart("Get Stars");
		System.out.println(results.getStars());
		reportiumClient.stepEnd("Get Stars");
		
		reportiumClient.stepStart("Get Price");
		System.out.println(results.getPrice());
		reportiumClient.stepEnd("Get Price");

	}
}

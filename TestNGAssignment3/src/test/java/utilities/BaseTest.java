package test.java.utilities;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.exception.ReportiumException;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.result.TestResultFactory;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {
	protected String browserName = "mobileOS";
	protected DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
	protected AndroidDriver<WebElement> driver;
	
	protected String app = "PRIVATE:Application/Expedia Hotels Flights Cars_v17.22.0_apkpure.com.apk";
	protected String appName = "com.expedia.bookings";
	protected ReportiumClient reportiumClient;	
	
	
	public ReportiumClient createReportInstance() {
		PerfectoExecutionContext perfectoExecutionContext = new PerfectoExecutionContext
				.PerfectoExecutionContextBuilder()
				.withProject(new Project("TestProject", "1.0")).withJob(new Job("Assignment", 45))
				.withContextTags("TestNGAssignment").withWebDriver(driver).build();
		ReportiumClient reportiumClient = new ReportiumClientFactory()
				.createPerfectoReportiumClient(perfectoExecutionContext);
		return reportiumClient;
	}

	@BeforeTest
	@Parameters ({"deviceName","host","user","password"})
	public void beforeTest(String device,String host,String user, String password) throws IOException {
		capabilities.setCapability("user", user);
		capabilities.setCapability("password", password);
		capabilities.setCapability("deviceName", device);
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("app", app);
		capabilities.setCapability("appPackage", appName);
		capabilities.setCapability("autoInstrument", true);
		capabilities.setCapability("fullReset", true);
		EclipseConnector.setExecutionIdCapability(capabilities, host);
		driver = new AndroidDriver<WebElement>(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.installApp(app);
	}

	@BeforeMethod
	public void beforeMethod() {
		reportiumClient = createReportInstance();
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) {
		int status = testResult.getStatus();
		switch (status) {
		case ITestResult.FAILURE:
			reportiumClient.testStop(TestResultFactory.createFailure("An error occurred", testResult.getThrowable()));
			break;
		case ITestResult.SUCCESS_PERCENTAGE_FAILURE:
		case ITestResult.SUCCESS:
			reportiumClient.testStop(TestResultFactory.createSuccess());
			break;
		case ITestResult.SKIP:
			// Ignore
			break;
		default:
			throw new ReportiumException("Unexpected status " + status);
		}
	}

	@AfterTest
	public void afterTest() throws IOException {
		Map<String, Object> params47 = new HashMap<String, Object>();
		params47.put("name", appName);
		driver.executeScript("mobile:application:clean", params47);
			
		// re-attempt driver clean up in case of massive failure
		driver.quit();

	}
}

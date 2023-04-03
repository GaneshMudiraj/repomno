package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	Logger log;

	public WebDriver driver;
	
	@BeforeMethod
	public void openApplication() throws IOException {

		log = LogManager.getLogger(LoginTest.class.getName());

		driver = intializeDriver();
		log.debug("Browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("Navigated to application URL");
	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {

		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on the My Account dropdown");
		landingPage.loginOption().click();
		log.debug("Clicked on login option");

		Thread.sleep(3000);

		LoginPage loginpage = new LoginPage(driver);
		loginpage.emailAddressField().sendKeys(email);
		log.debug("Email address got entered");
		loginpage.passwordField().sendKeys(password);
		log.debug("Password got entered");
		loginpage.loginButton().click();
		log.debug("Clicked on login button");

		AccountPage accountpage = new AccountPage(driver);

		String actualResult;

		try {
			accountpage.editAccountInformationOption().isDisplayed();
			actualResult = "Successfull";
			log.debug("User got logged in");

		} catch (Exception e) {

			log.debug("User didn't log in");
			actualResult = "Failure";

		}

		Assert.assertEquals(actualResult, expectedResult);

		log.info("Login Test got passed");

	}

	

	@AfterMethod
	public void closure() {

		driver.close();
		log.debug("Browser got closed");

	}

	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = {{ "Seleniumtraining1234@gmail.com", "selenium143", "Successfull" }};

		return data;
	}

}
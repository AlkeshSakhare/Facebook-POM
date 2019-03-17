package com.facebook.TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.facebook.TestBase.TestBase;
import com.facebook.TestPages.LoginPage;
import com.facebook.utils.TestUtil;

public class LoginPageTest extends TestBase {
	public LoginPageTest() {
		super();
	}

	LoginPage loginPage;

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}

	@DataProvider
	public Object[][] readLoginData() {
		return TestUtil.getTestData("login");
	}

	// @Test()
	// public void verifyLogin() {
	// String username = properties.getProperty("username");
	// String password = properties.getProperty("password");
	// loginPage.login(username, password);
	// ngWebDriver.waitForAngularRequestsToFinish();
	// }

	@Test(dataProvider = "readLoginData")
	public void verifymultipleLogin(String username, String password) {
		loginPage.login(username, password);
		// ngWebDriver.waitForAngularRequestsToFinish();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

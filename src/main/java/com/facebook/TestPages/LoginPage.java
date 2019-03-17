package com.facebook.TestPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.facebook.TestBase.TestBase;

public class LoginPage extends TestBase {

	@FindBy(id = "email")
	WebElement useridtxt;

	@FindBy(id = "pass")
	WebElement passwordtxt;

	@FindBy(xpath = "//input[@value='Log In']")
	WebElement loginbtn;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void login(String userid, String password) {
		useridtxt.clear();
		useridtxt.sendKeys(userid);
		passwordtxt.clear();
		passwordtxt.sendKeys(password);
		loginbtn.click();
	}

}

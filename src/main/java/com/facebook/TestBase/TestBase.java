package com.facebook.TestBase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.facebook.utils.TestUtil;
import com.facebook.utils.WebEventListener;
import com.paulhammant.ngwebdriver.NgWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static String browser;
	public static Properties properties;
	public static NgWebDriver ngWebDriver;

	public TestBase() {

		try {
			properties = new Properties();
			properties.load(new FileReader(
					System.getProperty("user.dir") + "/src/main/java/com/facebook/Config/config.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialization() {

		browser = properties.getProperty("browser");
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

			break;
		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			break;
		}
		EventFiringWebDriver e_driver = new EventFiringWebDriver(driver);
		WebEventListener eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		ngWebDriver = new NgWebDriver((JavascriptExecutor) driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("url"));

	}

}

package SeleniumTest;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class SysTest001QicFixC402RegistrationSunny001Test {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void sysTest001QicFixC402RegistrationSunny001() {
    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
    driver.manage().window().setSize(new Dimension(788, 824));
    driver.findElement(By.cssSelector("div:nth-child(1) .btn")).click();
    driver.findElement(By.name("fname")).click();
    driver.findElement(By.name("fname")).sendKeys("Steven");
    driver.findElement(By.name("lname")).click();
    driver.findElement(By.name("lname")).sendKeys("Alfonso");
    driver.findElement(By.name("dob")).click();
    driver.findElement(By.name("dob")).sendKeys("11/6/1990");
    driver.findElement(By.name("phone")).click();
    driver.findElement(By.name("phone")).sendKeys("555-555-5555");
    driver.findElement(By.name("street_address")).click();
    driver.findElement(By.name("street_address")).sendKeys("555 W 28th ST");
    driver.findElement(By.name("city")).click();
    driver.findElement(By.name("city")).sendKeys("Miami");
    {
      WebElement dropdown = driver.findElement(By.name("state"));
      dropdown.findElement(By.xpath("//option[. = 'Florida']")).click();
    }
    driver.findElement(By.name("zipcode")).click();
    driver.findElement(By.name("zipcode")).sendKeys("33126");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).sendKeys("steven.a@email.com");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).sendKeys("12345");
    driver.findElement(By.cssSelector("label > input")).click();
    driver.findElement(By.cssSelector(".btn")).click();
  }
}


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
public class SysTest001QicFixCT401ClientTowLoginSunny001Test {
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
  public void sysTest001QicFixCT401ClientTowLoginSunny001() {
    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
    driver.manage().window().setSize(new Dimension(788, 824));
    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
  }
}
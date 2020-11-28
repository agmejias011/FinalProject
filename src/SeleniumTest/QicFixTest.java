package SeleniumTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class QicFixTest {
	
	public QicFixTest()
	{
		  System.setProperty("webdriver.chrome.driver",
		           "C:/Users/agonz/eclipse-workspace/ProjectCEN4072/chromedriver.exe");
	}
	
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
	  public void sysTest001QicFixC310ListofTowersRainy001() {
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(792, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.cssSelector("li:nth-child(4) > a")).click();
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
	  
	  @Test
	  public void sysTest001QicFixC402RegistrationSunny002() {
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    driver.findElement(By.cssSelector("div:nth-child(2) > .form-signin:nth-child(1) > .btn")).click();
	    driver.findElement(By.cssSelector(".form-horizontal")).click();
	    driver.findElement(By.name("fname")).click();
	    driver.findElement(By.name("fname")).sendKeys("Miguel");
	    driver.findElement(By.name("lname")).click();
	    driver.findElement(By.name("lname")).sendKeys("Miller");
	    driver.findElement(By.name("dob")).click();
	    driver.findElement(By.name("dob")).sendKeys("11/6/1990");
	    driver.findElement(By.name("phone")).click();
	    driver.findElement(By.name("phone")).sendKeys("444-444-4444");
	    driver.findElement(By.name("street_address")).click();
	    driver.findElement(By.name("street_address")).sendKeys("575 W 2nd ST");
	    driver.findElement(By.name("city")).click();
	    driver.findElement(By.name("city")).sendKeys("Miami");
	    {
	      WebElement dropdown = driver.findElement(By.name("state"));
	      dropdown.findElement(By.xpath("//option[. = 'Florida']")).click();
	    }
	    driver.findElement(By.name("zipcode")).click();
	    driver.findElement(By.name("zipcode")).sendKeys("33172");
	    driver.findElement(By.name("email")).click();
	    driver.findElement(By.name("email")).sendKeys("miller.m@email.com");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("12345");
	    driver.findElement(By.cssSelector(".form-group:nth-child(12) .form-control")).click();
	    driver.findElement(By.cssSelector(".form-group:nth-child(12) .form-control")).sendKeys("ABCD");
	    driver.findElement(By.cssSelector(".form-group:nth-child(13) .form-control")).click();
	    driver.findElement(By.cssSelector(".form-group:nth-child(13) .form-control")).sendKeys("10");
	    driver.findElement(By.cssSelector(".checkbox > label")).click();
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.cssSelector("html")).click();
	  }
	  
	  @Test
	  public void sysTest001QicFixCT401ClientTowLoginSunny001() {
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
	    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	  }
	  
	  @Test
	  public void sysTest001QicFixCT401ClientTowLoginSunny002() {	
			
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(786, 824));
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.id("inputPassword")).click();
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	  }
	  
	  @Test
	  public void sysTest001QicFixCT404ClientTowLoginSunny001() {
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
	    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.name("btnLogout")).click();
	    driver.findElement(By.linkText("Go back to main page.")).click();
	  }
	  
	  @Test
	  public void sysTest001QicFixCT404ClientTowLoginSunny002() {
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(790, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.name("btnLogout")).click();
	  }
	  
	  public void sysTest001QicFixT201EditProfileRainy001() {
		    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
		    driver.manage().window().setSize(new Dimension(790, 824));
		    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
		    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
		    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
		    driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();
		    driver.findElement(By.name("phone")).click();
		    driver.findElement(By.name("phone")).sendKeys("555-666-4444");
		    driver.findElement(By.cssSelector(".btn")).click();
		    driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();
		  }
	  
	  @Test
	  public void sysTest001QicFixT202ViewRatingRainy001() {
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(792, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
	    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();
	  }

}

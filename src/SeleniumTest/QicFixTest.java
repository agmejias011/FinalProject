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
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andres Gonzalez
 * @author Peter J. Clarke Date: 10/28/2020 Purpose: System Test cases for the
 *         QicFix System
 *
 */

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
	  
	  /**
		 * ID: SysTest-001-QicFix-C-310-List of Towers-Rainy001
		 * Purpose: Displaying a list of towers from a logged in Client
		 * Preconditions: - For initial state of the database see Fig 4.1.2.1
		 *				  - The System is functioning properly
		 *				  - Tower user must be registered and in the database before being able to log in
		 *				  - Tower user has the application open
		 *				  - The user is signed in with the information:
		 *				      Email: user2@email.com
		 * 	      	          password: 5N3nwfHUPAsbaNez
		 * 				  - User browses Menu > List Tower
		 * Input: None
		 * Expected Output: List of towers are displayed
		 */
	  @Test
	  public void sysTest001QicFixC310ListofTowersRainy001() {
		  //Preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(792, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.cssSelector("li:nth-child(4) > a")).click();
	   
	    //checking for not an empty list
	    List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/tr"));
	    
	    //input'
	    
	    //expected output
	    assertTrue(list.size() > 0);
	  }	  
	 
	  
	  /**
		 * ID: SysTest-001-QicFix-C-402-Registration-Sunny001 
		 * Purpose: Evaluate functionality for a new client to register in the application
		 * Preconditions: 1-For initial state of the database see Fig 4.1.2.1
		 *				  2- The System is functioning properly
		 *				  3- User has the application open and clicks on Register Client 
		 * Input: 
		 *					First Name:  Steven
		 *				    Last Name: Alfonso
		 *					Date of Birth: 11/6/1990
		 *					Phone: 555-555-5555
		 *					Address: 555 W 28th ST
		 *					City: Miami
		 *					State: Florida
		 *					Zipcode: 33126
		 *					Email: steven.5@email.com
		 *					Password: 12345
		 *
		 *					Clicked and accept the terms. Click Register
	     *
		 * Expected Output: New Client user is successfully registered and logged in.
		 */
	  @Test
	  public void sysTest001QicFixC402RegistrationSunny001() {
		  //Preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    driver.findElement(By.cssSelector("div:nth-child(1) .btn")).click();
	    
	    //input
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
	    driver.findElement(By.name("email")).sendKeys("steven.5@email.com");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("12345");
	    driver.findElement(By.cssSelector("label > input")).click();
	    driver.findElement(By.cssSelector(".btn")).click();
	    
	   //Expected output
	    boolean contains = driver.getPageSource().contains("Logged as Steven Alfonso");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  
	  
	  
	  /**
		 * ID: SysTest-001-QicFix-C-402-Registration-Sunny002 
		* Purpose: Evaluate functionality for a new client to register in the application
		 * Preconditions: 1-For initial state of the database see Fig 4.1.2.1
		 *				  2- The System is functioning properly
		 *				  3- User has the application open and clicks on Register Client 
		 * Input: 
		 *					First Name:  Miguel
		 *				    Last Name: Miller
		 *					Date of Birth: 11/6/1990
		 *					Phone: 444-444-4444
		 *					Address: 575 W 2nd ST
		 *					City: Miami
		 *					State: Florida
		 *					Zipcode: 33172
		 *					Email: miller.5@email.com
		 *					Password: 12345
		 *					Permit Number: ABCD
		 *					Price per Mile: 10
		 *					
		 *
		 *					Clicked and accept the terms. Click Register
	     *
		 * Expected Output: New Tower user is successfully registered and logged in.
		 */
	  
	  @Test
	  public void sysTest001QicFixC402RegistrationSunny002() {
		  //Preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    driver.findElement(By.cssSelector("div:nth-child(2) > .form-signin:nth-child(1) > .btn")).click();
	    driver.findElement(By.cssSelector(".form-horizontal")).click();
	    
	    //Input
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
	    driver.findElement(By.name("email")).sendKeys("miller.5@email.com");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("12345");
	    driver.findElement(By.cssSelector(".form-group:nth-child(12) .form-control")).click();
	    driver.findElement(By.cssSelector(".form-group:nth-child(12) .form-control")).sendKeys("ABCD");
	    driver.findElement(By.cssSelector(".form-group:nth-child(13) .form-control")).click();
	    driver.findElement(By.cssSelector(".form-group:nth-child(13) .form-control")).sendKeys("10");
	    driver.findElement(By.cssSelector(".checkbox > label")).click();
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.cssSelector("html")).click();
	    	  
	  //Expected output
	    boolean contains = driver.getPageSource().contains("Logged as Miguel Miller");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  
	  
	  /**
		 * ID: SysTest-001-QicFix-C-402-Registration-Sunny003
		 * Purpose: Evaluate that the system does not register a client that has already been registered
		 * Preconditions: 1-For initial state of the database see Fig 4.1.2.1
		 *				  2- The System is functioning properly
		 *				  3- User has the application open and clicks on Register Client 
		 * Input: 
		 *					First Name:  Steven
		 *				    Last Name: Alfonso
		 *					Date of Birth: 11/6/1990
		 *					Phone: 555-555-5555
		 *					Address: 555 W 28th ST
		 *					City: Miami
		 *					State: Florida
		 *					Zipcode: 33126
		 *					Email: steven.2@email.com
		 *					Password: 12345
		 *
		 *					Clicked and accept the terms. Click Register
	     *
		 * Expected Output: The client is not registered
		 */
	  @Test
	  public void sysTest001QicFixC402RegistrationSunny003() {
		  //Preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    driver.findElement(By.cssSelector("div:nth-child(1) .btn")).click();
	    
	    //input
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
	    driver.findElement(By.name("email")).sendKeys("steven.2@email.com");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("12345");
	    driver.findElement(By.cssSelector("label > input")).click();
	    driver.findElement(By.cssSelector(".btn")).click();
	    
	   //Expected output
	    boolean contains = driver.getPageSource().contains("Logged as Steven Alfonso");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertFalse(contains);
	  }
	  
	  /**
		 * ID: SysTest-001-QicFix-CT-401-Client/Tow Log in-Sunny001 
		 * Purpose: Evaluate functionality for tower user log in into the application
		 * Preconditions: 
		 *		 1-	For initial state of the database see Fig 4.1.2.1
		 *		 2-	The System is functioning properly
		 *	 	 3-	Tower user must be registered and in the database before being able to log in
		 *		 4-	Tower user has the application open

		 * Input: 
		 * 			Email: user2@email.com
		 * 	 		password: 5N3nwfHUPAsbaNez
         *
		 * Expected Output: Tower user successfully logs in
		 */
	  @Test
	  public void sysTest001QicFixCT401ClientTowLoginSunny001() {
		  //preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    //input
	    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
	    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    //expected output
	    boolean contains = driver.getPageSource().contains("Logged as");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  
	  /**
		 * ID: SysTest-001-QicFix-CT-401-Client/Tow Log in-Sunny002 
		 * Purpose: Evaluate functionality for client user log in into the application
		 * Preconditions: 
		 *		 1-	For initial state of the database see Fig 4.1.2.1
		 *		 2-	The System is functioning properly
		 *	 	 3-	client user must be registered and in the database before being able to log in
		 *		 4-	client user has the application open

		 * Input: 
		 * 			Email: user1@email.com
		 * 	 		password: ge6SgZ3rbEFs95Cg
       *
		 * Expected Output: client user successfully logs in
		 */
	  @Test
	  public void sysTest001QicFixCT401ClientTowLoginSunny002() {	
			
		  //preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(786, 824));
	    //input
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.id("inputPassword")).click();
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    //expected output
	    boolean contains = driver.getPageSource().contains("Logged as");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  
	  /**
		 * ID: SysTest-001-QicFix-CT-401-Client/Tow Log in-Sunny003 
		 * Purpose: Evaluate functionality for client user log not able to log in with false credentials
		 * Preconditions: 
		 *		 1-	For initial state of the database see Fig 4.1.2.1
		 *		 2-	The System is functioning properly
		 *	 	 3-	client user must be registered and in the database before being able to log in
		 *		 4-	client user has the application open

		 * Input: 
		 * 			Email: user1@email.com
		 * 	 		password: ge6SgZ3rbEF
     *
		 * Expected Output: client user cannot log in
		 */
	  @Test
	  public void sysTest001QicFixCT401ClientTowLoginSunny003() {	
			
		  //preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(786, 824));
	    //input
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.id("inputPassword")).click();
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEF");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    //expected output
	    boolean contains = driver.getPageSource().contains("Logged as");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertFalse(contains);
	  }
	  
	  /**
		 * ID: SysTest-001-QicFix-CT-404-Tower Log out-Sunny001 
		 * Purpose: Evaluate functionality for tower user log out from the application
		 * Preconditions:		
		 *		1-	For initial state of the database see Fig 4.1.2.1
		 *		2-	The System is functioning properly
		 *		3-	Tower user must be registered and in the database before being logged in.
		 *		4-	 Tower user is already logged in into the system.
		 *		5-	Tower user clicks log out
	     *
		 * Input: none
		 * Expected Output: Tower user successfully logs out
		 */
	  @Test
	  public void sysTest001QicFixCT404TowerLogoutSunny001() {
		  //preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(788, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
	    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.name("btnLogout")).click();
	   
	    //input
	    
	    //expected output
	    boolean contains = driver.getPageSource().contains("Successful Logout");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  
	  /**
		 * ID: SysTest-001-QicFix-CT-404-Client Log out-Sunny001 
		 * Purpose: Evaluate functionality for client user log out from the application
		 * Preconditions:		
		 *		1-	For initial state of the database see Fig 4.1.2.1
		 *		2-	The System is functioning properly
		 *		3-	Client user must be registered and in the database before being logged in.
		 *		4-	Client user is already logged in into the system.
		 *		5-	Client user clicks log out
	     *
		 * Input: none
		 * Expected Output: Client user successfully logs out
		 */
	  @Test
	  public void sysTest001QicFixCT404ClientLogoutSunny002() {
	    //preconditions
		  driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(790, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.name("btnLogout")).click();
	  
	    //input
	    
	    //expected output
	    boolean contains = driver.getPageSource().contains("Successful Logout");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  
	  /**
		 * ID: SysTest-001-QicFix-T-201-Edit Profile-Rainy001 
		 * Purpose: Evaluate functionality by editing tower’s profile
		 * Preconditions: 
		 *		1-	For initial state of the database see Fig 4.1.2.1
		 *		2-	The System is functioning properly
		 *		3-	Tower user is already logged in to the system
		 *		Email: user2@email.com
		 *		password: 5N3nwfHUPAsbaNez
		 *
		 * Input: 
		 * 		Date of Birth: 11/05/1967
		 *		Phone: 563-568-5684
		 *  	Address: 563 W 1st St
	 	 *  	City: Miami
		 *  	State: Fl
		 *  	Zipcode: 33122
		 *
		 * Expected Output: Profile is updated with the new information
		 */
	  @Test
	  public void sysTest001QicFixT201EditProfileRainy001() {
		  //preconditions
		    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
		    driver.manage().window().setSize(new Dimension(790, 824));
		    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
		    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
		    //input
		    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
		    driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();
		    driver.findElement(By.name("phone")).click();
		    driver.findElement(By.name("phone")).sendKeys("555-666-4444");
		    driver.findElement(By.cssSelector(".btn")).click();
		    driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();
		    
		    //expected output
		    boolean contains = driver.getPageSource().contains("555-666-4444");
		    System.out.print(driver.getPageSource());
		    	   	   
		    assertTrue(contains);
		  }
	  
	  /**
		 * ID: SysTest-001-QicFix-T-201-Edit Profile-Rainy002
		 * Purpose: Evaluate functionality by editing client's profile
		 * Preconditions: 
		 *		1-	For initial state of the database see Fig 4.1.2.1
		 *		2-	The System is functioning properly
		 *		3-	Tower user is already logged in to the system
		 *		Email: user1@email.com
		 *		password: ge6SgZ3rbEFs95Cg
		 *
		 * Input: 
		 * 		First Name: John
		 *
		 * Expected Output: Profile is updated with the new information
		 */	  
	  @Test
	  public void sysTest001QicFixT201EditProfileRainy002() {
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(794, 824));
	    driver.findElement(By.id("inputEmail")).click();
	    driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");
	    driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();
	    driver.findElement(By.name("fname")).click();
	    driver.findElement(By.name("fname")).click();
	    {
	      WebElement element = driver.findElement(By.name("fname"));
	      Actions builder = new Actions(driver);
	      builder.doubleClick(element).perform();
	    }
	    driver.findElement(By.name("fname")).sendKeys("John");
	    driver.findElement(By.cssSelector(".btn")).click();
	    
	  //expected output
	    boolean contains = driver.getPageSource().contains("Logged as John");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  
	  
	  
	  /**
		 * ID: SysTest-001-QicFix-T-202-View Rating-Rainy001
		 * Purpose: Viewing rating and comments as a tower user
		 * Preconditions: 
		 *		1-	For initial state of the database see Fig 4.1.2.1
		 *		2-	The System is functioning properly
		 *		3-	The tower user is registered and stored in the database
		 *
		 * Input: none
		 * Expected Output: Ratings and comments are displayed
		 */
	  @Test
	  public void sysTest001QicFixT202ViewRatingRainy001() {
		  //preconditions
	    driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
	    driver.manage().window().setSize(new Dimension(792, 824));
	    driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
	    driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
	    driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();
	    //input
	    
	    //expected output
	    boolean contains = driver.getPageSource().contains("Ratings");
	    System.out.print(driver.getPageSource());
	    	   	   
	    assertTrue(contains);
	  }
	  


}

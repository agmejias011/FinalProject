package SeleniumTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import java.util.ArrayList;
import java.util.List;

public class QicFixTest_Zachary_Garcia {
	
	public QicFixTest_Zachary_Garcia()
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
     * Beginning of Zachary Garcia's test cases
     */


    /**
     * Zachary Test Case 1
     * ID: sysTest001QicFixCT105ClientFailsFiveTimesToLoginRainy001 
     * Purpose: Evaluate login functionality for client 
     * Preconditions: 
     *		-	Client's system is working
     *		-	Client is already registered
     * Input: 
     *		-	Email: user1@email.com
     *		-	password: wrongpassword
     *
     * Expected Output: 
     *		-	The System is not functioning properly because it allows the user to attempt more than
     *			5 failed logins
     */
    @Test
    public void sysTest001QicFixCT105ClientFailsFiveTimesToLoginRainy001() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        boolean attemptedMoreThanFiveLogins = false;
        for (int i = 0; i < 6; i++) {
            //input
            driver.findElement(By.id("inputPassword")).sendKeys("wrongpassword");
            driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");
            driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();

            //attempt more than 5 logins
            boolean contains = driver.getPageSource().contains("Logged as");
            if (i > 4 && !contains)
                attemptedMoreThanFiveLogins = true;
        }
        assertTrue(attemptedMoreThanFiveLogins);
    }


    /**
     * Zachary Test Case 2
     * ID: sysTest002QicFixT201EditProfileRainy002 
     * Purpose: Evaluate the functionality of edit profile for Tower
     * Preconditions: 
     *		- Tower's system is working
     *		- Tower has an existing profile
     * Input: 
     *		-	First Name: Zachary
     *		-	Last Name: Garcia
     *
     * Expected Output: Tower receives message indicating successful profile update
     * 					In this case it would be the changed name after update
     */
    @Test
    public void sysTest002QicFixT201EditProfileRainy002() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        driver.findElement(By.id("inputPassword")).sendKeys("5N3nwfHUPAsbaNez");
        driver.findElement(By.id("inputEmail")).sendKeys("user2@email.com");

        //login
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();

        //edit profile button
        driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();

        //clear first name and enter Zachary
        driver.findElement(By.name("fname")).click();
        driver.findElement(By.name("fname")).clear();
        driver.findElement(By.name("fname")).sendKeys("Zachary");

        //clear last name and enter Garcia
        driver.findElement(By.name("lname")).click();
        driver.findElement(By.name("lname")).clear();
        driver.findElement(By.name("lname")).sendKeys("Garcia");

        //submit changes
        driver.findElement(By.cssSelector(".btn")).click();

        //expected output
        boolean contains = driver.getPageSource().contains("Logged as Zachary Garcia");

        assertTrue(contains);
    }

    /**
     * Zachary Test Case 3
     * ID: sysTest003QicFixC310DisplayOrderedListofTowersSunny001
     * Purpose: Evaluate functionality for a client listing towers
     * Preconditions: 
     * 		- Client is able to login
     * 		- Client is able to view list of towers
     * Input: None
     * Expected Output: Client receives a list of towers
     */
    @Test
    public void sysTest003QicFixC310DisplayOrderedListofTowersSunny001() {
        //open window
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));

        //input email and password
        driver.findElement(By.id("inputPassword")).sendKeys("ge6SgZ3rbEFs95Cg");
        driver.findElement(By.id("inputEmail")).sendKeys("user1@email.com");

        //login
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        driver.findElement(By.cssSelector("li:nth-child(4) > a")).click();

        //check for zero towers because this user has no towers
        List<WebElement > list = driver.findElements(By.xpath("//table/tbody/tr/tr"));

        //expected output
        assertTrue(list.size() == 0);
    }


    /**
     * Zachary Test Case 4
     * ID: SysTest004QicFixT403RegistrationSunny002 
     * Purpose: Evaluate functionality for a new client to register in the application
     * Preconditions: 1-For initial state of the database see Fig 4.1.2.1
     *				  2- The System is functioning properly
     *				  3- User has the application open and clicks on Register Client 
     * Input: 
     * 		- First Name: Zachary
     * 		- Last Name: Garcia
     * 		- State: Alabama
     * 		- Zip Code: 33024
     * 		- Email: zgarc026@fiu.edu
     * 		- Password: randopassword
     *
     *					Clicked and accept the terms. Click Register
     *
     * Expected Output: New Client user is successfully registered and logged in.
     */
    @Test
    public void SysTest004QicFixT403RegistrationSunny002() {
        //open the window
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));

        //click register tower option
        driver.findElement(By.xpath("//button[contains(text(), 'Register Tower')]")).click();

        //input
        driver.findElement(By.name("fname")).click();
        driver.findElement(By.name("fname")).sendKeys("Zachary");
        driver.findElement(By.name("lname")).click();
        driver.findElement(By.name("lname")).sendKeys("Garcia"); {
            WebElement dropdown = driver.findElement(By.name("state"));
            dropdown.findElement(By.xpath("//option[. = 'Alabama']")).click();
        }
        driver.findElement(By.name("zipcode")).click();
        driver.findElement(By.name("zipcode")).sendKeys("33024");
        driver.findElement(By.name("email")).click();


        driver.findElement(By.name("email")).sendKeys("zgarc0261@fiu.edu");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("randopassword");

        //accept terms and agreement
        driver.findElement(By.cssSelector("label > input")).click();

        //register
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();

        //Expected output
        boolean contains = driver.getPageSource().contains("Logged as Zachary Garcia");
        System.out.print(driver.getPageSource());

        //false because user already exists
        assertFalse(contains);
    }



    /**
	     * Zachary Test Case 5
		 * ID: SysTest005QicFixCT401ClientTowLoginSunny003
		 * Purpose: Evaluate login functionality
		 * Preconditions: 
		 *		- Client/Tower's system is working
		 *		- Client/Tower has an existing profile

		 * Input: 
		 * 			Email: user3@email.com
		 * 	 		password: 3JmNeQBaUrfY26aN
         *
		 * Expected Output: Successful login
		 */
    @Test
    public void SysTest005QicFixCT401ClientTowLoginSunny003() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        //input
        driver.findElement(By.id("inputPassword")).sendKeys("3JmNeQBaUrfY26aN");
        driver.findElement(By.id("inputEmail")).sendKeys("user3@email.com");

        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        //expected output
        boolean contains = driver.getPageSource().contains("Logged as");
        System.out.print(driver.getPageSource());

        assertTrue(contains);
    }


    /**
	     * Zachary Test Case 6
		 * ID: SysTest006QicFixCT404ClientTowLogoutSunny004 
		 * Purpose: Evaluate logout functionality
		 * Preconditions: 
		 *		- Client/Tower's system is working
		 *		- Client/Tower has an existing profileworking
		 *		- Client/Tower able to log in

		 * Input: 
		 * 			Email: user3@email.com
		 * 	 		password: 3JmNeQBaUrfY26aN
         *
		 * Expected Output: Tower user successfully logs in
		 */
    @Test
    public void SysTest006QicFixCT404ClientTowLogoutSunny004() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        //input
        driver.findElement(By.id("inputPassword")).sendKeys("3JmNeQBaUrfY26aN");
        driver.findElement(By.id("inputEmail")).sendKeys("user3@email.com");
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();


        driver.findElement(By.xpath("//input[@value=\"Logout\"]")).click();
        //expected output
        boolean contains = driver.getPageSource().contains("Successful Logout");
        System.out.print(driver.getPageSource());

        assertTrue(contains);
    }



    /**
	     * Zacharys Test Case 7
		 * ID: sysTest007QicFixCT401ClientTowLoginRainy003 
		 * Purpose: Evaluate functionality for tower user log in into the application
		 * Preconditions: 
		 *		- Client/Tower's system is working
		 *		- Client/Tower has an existing profile

		 * Input: 
		 * 			Email: user3@email.com
		 * 	 		password: wrongpassword
         *
		 * Expected Output: Client/Tower unable to login
		 */
    @Test
    public void sysTest007QicFixCT401ClientTowLoginRainy003() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        //input
        driver.findElement(By.id("inputPassword")).sendKeys("wrongpassword");
        driver.findElement(By.id("inputEmail")).sendKeys("user3@email.com");
        //	    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        //expected output
        boolean contains = driver.getPageSource().contains("Logged as");
        System.out.print(driver.getPageSource());

        assertFalse(contains);
    }


    /**
//	     * Zachary Test Case 8
		 * ID: SysTest008QicFixC402ClientLoginRainy004 
		 * Purpose: Evaluate login functionality with faulty input data
		 * Preconditions: 
		 * 		- User's system works
		 * 		- User has preexisting profile
		 * Input: 
		 * 			Email: jdoe@email.com
		 * 	 		password: ''
	     *
		 * Expected Output: New Client user is successfully registered and logged in.
		 */
    @Test
    public void SysTest008QicFixC402ClientLoginRainy004() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        //input
        driver.findElement(By.id("inputPassword")).sendKeys("");
        driver.findElement(By.id("inputEmail")).sendKeys("jdoe@email.com");
        //		    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        //expected output
        boolean contains = driver.getPageSource().contains("Logged as");
        System.out.print(driver.getPageSource());

        assertFalse(contains);
    }

    /**
     * Zacharys Test 9
     * ID: SysTest009QicFixT201EditProfileRainy005 
     * Purpose: Evaluate functionality for a new client to register in the application
     * Preconditions: 
     * 			- Tower has existing profile
     * 			- Tower's system works
     * Input: 
     *			- First Name: Zachary
     *			- Last Name: Garcia
     *			- State: Alabama
     *			- Zip Code: 33024
     *			- Password: ''
     *			- Email: ''
     *
     * Expected Output: User should receive error of invalid input due to empty password
     */
    @Test
    public void SysTest009QicFixT201EditProfileRainy005() {
        //open the window
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));

        //click register tower option
        driver.findElement(By.xpath("//button[contains(text(), 'Register Tower')]")).click();

        //input
        driver.findElement(By.name("fname")).click();
        driver.findElement(By.name("fname")).sendKeys("Zachary");
        driver.findElement(By.name("lname")).click();
        driver.findElement(By.name("lname")).sendKeys("Garcia"); {
            WebElement dropdown = driver.findElement(By.name("state"));
            dropdown.findElement(By.xpath("//option[. = 'Alabama']")).click();
        }
        driver.findElement(By.name("zipcode")).click();
        driver.findElement(By.name("zipcode")).sendKeys("33024");
        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.name("password")).click();

        //input of empty password
        driver.findElement(By.name("password")).sendKeys("");

        //accept terms and agreement
        driver.findElement(By.cssSelector("label > input")).click();

        //register
        driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();

        //Expected output
        boolean contains = driver.getPageSource().contains("Logged as Zachary Garcia");
        System.out.print(driver.getPageSource());

        assertFalse(contains);
    }


    /**
     * Zacharys Test 10
     * ID: sysTest010QicFixT201EditProfileSunny005 
     * Purpose: Evaluate the functionality of edit profile for Tower
     * Preconditions: 
     *		- Tower has an existing profile
     * Input: 
     *		-	First Name: ''
     *		-	Last Name: ''
     *
     * Expected Output: Client unable to save edited profile because of empty first name
     * 					and empty last name
     */
    @Test
    public void sysTest010QicFixT201EditProfileSunny005() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        driver.findElement(By.id("inputPassword")).sendKeys("PPzQMCFUp46qr8X3");
        driver.findElement(By.id("inputEmail")).sendKeys("user6@email.com");
        //login
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();

        //edit profile button
        driver.findElement(By.cssSelector("li:nth-child(2) > a")).click();

        //clear first name and enter Zachary
        driver.findElement(By.name("fname")).click();
        driver.findElement(By.name("fname")).clear();
        driver.findElement(By.name("fname")).sendKeys("");

        //clear last name and enter Garcia
        driver.findElement(By.name("lname")).click();
        driver.findElement(By.name("lname")).clear();
        driver.findElement(By.name("lname")).sendKeys("");

        //submit changes
        driver.findElement(By.cssSelector(".btn")).click();

        //expected output
        boolean contains = driver.getPageSource().contains("Logged as Zachary Garcia");

        assertFalse(contains);
    }



    /**
     * Zacharys Test 11
     * ID: sysTest011QicFixCT401ClientTowLoginSunny006 
     * Purpose: Evaluate login functionality for client 
     * Preconditions: 
     *		-	User has existing username and password and is able to login
     * Input: 
     *		-	Email: user6@email.com
     *		-	password: wrongpassword
     *
     * Expected Output: 
     *		-	Tower successfully logs in
     */
    @Test
    public void sysTest011QicFixCT401ClientTowLoginSunny006() {
        //preconditions
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));
        //input
        driver.findElement(By.id("inputPassword")).sendKeys("PPzQMCFUp46qr8X3");
        driver.findElement(By.id("inputEmail")).sendKeys("user6@email.com");
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();

        //attempt more than 5 logins
        boolean contains = driver.getPageSource().contains("Logged as");
        assertTrue(contains);
    }

    /**
     * Zacharys Test 12
     * ID: sysTest012QicFixC310ListofTowersRainy006
     * Purpose: Evaluate tower list functionality
     * Preconditions: 
     * 		- Client is able to login
     * 		- Client is able to view list of towers
     * Input: 
     * 		- Email: user5@email.com
     * 		- password: Vy6c946nyAhDJ3Q9
     * Expected Output: Client receives an empty list of towers
     */
    @Test
    public void sysTest012QicFixC310ListofTowersRainy006() {
        //open window
        driver.get("http://cen4072-fa20.cs.fiu.edu:8080/QicFixSystem-Geordi/login.jsp");
        driver.manage().window().setSize(new Dimension(1000, 1000));

        //input email and password
        driver.findElement(By.id("inputPassword")).sendKeys("Vy6c946nyAhDJ3Q9");
        driver.findElement(By.id("inputEmail")).sendKeys("user5@email.com");

        //login
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        driver.findElement(By.cssSelector("li:nth-child(4) > a")).click();

        //check for zero towers because this user has no towers
        List<WebElement> list = driver.findElements(By.xpath("//table/tbody/tr/tr"));

        //expected output
        assertTrue(list.size() == 0);
    }

}

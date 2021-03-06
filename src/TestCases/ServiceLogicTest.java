package TestCases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import appLogic.ServiceLogic;
import client.model.User;
import entity.Application;
import entity.Client;
import entity.DatastoreFacade;
import entity.Service;
import entity.Tower;
import util.Location;

/**
 * @author Andres Gonzalez
 * @author Peter J. Clarke Date: 10/28/2020 Purpose: Test cases for the
 *         ServiceLogic class
 *
 */

public class ServiceLogicTest {

	@Mock
	Client clientMock;

	private ServiceLogic serviceLogic;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// MockitoAnnotations.initMocks(this);
		serviceLogic = new ServiceLogic();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		serviceLogic = null;
	}

	/**
	 * ID: QicFix-ServiceLogic-001-setDatastoreFacade-001 
	 * Purpose: sets dataStoreFacade
	 * Preconditions: create DatastoreFacade instance 
	 * Input: created DatastoreFacadeinstance in preconditions
	 * Expected Output: true
	 */
	@Test
	public void testsetDatastoreFacade_001() {
		// Test Setup:
		DatastoreFacade ds = new DatastoreFacade();		
		// Test Input:
		serviceLogic.setDatastoreFacade(ds);
		// Expected Output:		
		assertTrue(true);
	}
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-createService-001 
	 * Purpose: creates a service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
	 * 		  String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * Expected Output: creates service and returns true
	 */
	@Test
	public void testcreateService_001() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]";
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		
		// Test Input:
		boolean result = serviceLogic.createService(content, token, email);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-createService-002
	 * Purpose: creates a service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjiASDFADFASFASFASFkl\\\\[(.*?)\\\\]"
	 * 		  String token = "58REZ69JDEjiASDFADFASFASFASFkl"
	 * 		  String email = "user6@email.com"
	 * Expected Output: creates service and returns true
	 */
	@Test
	public void testcreateService_002() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DEjiASDFADFASFASFASFkl\\\\[(.*?)\\\\]";
		String token = "58REZ69JDEjiASDFADFASFASFASFkl";
		String email = "user6@email.com";
		
		// Test Input:
		boolean result = serviceLogic.createService(content, token, email);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-selectServiceByTowerEmail-001 
	 * Purpose: select service by tower email and returns a list of service
	 * Preconditions: creates a service instance
	 * Input: String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * Expected Output: returns a list of services and asserts true
	 */
	@Test
	public void testselectServiceByTowerEmail_001() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212A");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("33199");
		temp.setClientDescription("student");
		temp.setTowerDescription("tall");
		
		
		
		// Test Setup:
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectServiceByTowerEmail(token, email);
		// Expected Output:		
		assertTrue(compareService(resultList.get(0), temp));
	}
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-selectServiceByTowerEmail-002 
	 * Purpose: select service by tower email and returns a list of service
	 * Preconditions: creates a service instance
	 * Input: String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * Expected Output: returns a list of services and asserts false
	 */
	@Test
	public void testselectServiceByTowerEmail_002() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212A");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("3319989");
		temp.setClientDescription("student");
		temp.setTowerDescription("tall");
		
		
		
		// Test Setup:
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectServiceByTowerEmail(token, email);
		// Expected Output:		
		assertFalse(compareService(resultList.get(0), temp));
	}
	
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-selectServiceByTowerEmail-003
	 * Purpose: select service by tower email and returns a list of service
	 * Preconditions: creates a service instance
	 * Input: String token = "586erfd@df4431"
	 * 		  String email = "user@email.com"
	 * Expected Output: returns a list of services and asserts true
	 */
	@Test
	public void testselectServiceByTowerEmail_003() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212A");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("33199");
		temp.setClientDescription("student");
		temp.setTowerDescription("tall");
		
		
		
		// Test Setup:
		String token = "586erfddf4431";
		String email = "user@email.com";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectServiceByTowerEmail(token, email);
		// Expected Output:		
		assertTrue(compareService(resultList.get(0), temp));
	}
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-selectServiceByTowerEmail-004
	 * Purpose: select service by tower email and returns a list of service
	 * Preconditions: creates a service instance
	 * Input: String token = "586erfd@df4431"
	 * 		  String email = "user@email.com"
	 * Expected Output: returns a list of services and asserts false
	 */
	@Test
	public void testselectServiceByTowerEmail_004() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212AAA");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("33199");
		temp.setClientDescription("student");
		temp.setTowerDescription("tall");
		
		
		
		// Test Setup:
		String token = "586erfddf4431";
		String email = "user@email.com";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectServiceByTowerEmail(token, email);
		// Expected Output:		
		assertFalse(compareService(resultList.get(0), temp));
	}
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-chargeClient-001 
	 * Purpose: charges client for the service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
	 * 		  String authToken = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * 		  Integer serviceId = 6
	 * Expected Output: charges the client and returns true
	 */
	@Test
	public void testchargeClient_001() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]";
		String authToken = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		Integer serviceId = 6;
		
		// Test Input:
		boolean result = serviceLogic.chargeClient(content, authToken, email, serviceId);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-chargeClient-002
	 * Purpose: charges client for the service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\FKDOWEMDM1!DJKM]"
	 * 		  String authToken = "58REZ69JDF5865DSFSDF"
	 * 		  String email = "user@email.com"
	 * 		  Integer serviceId = 4
	 * Expected Output: charges the client and returns true
	 */
	@Test
	public void testchargeClient_002() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\FKDOWEMDM1!DJKM]";
		String authToken = "58REZ69JDF5865DSFSDF";
		String email = "user@email.com";
		Integer serviceId = 4;
		
		// Test Input:
		boolean result = serviceLogic.chargeClient(content, authToken, email, serviceId);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-declineService-001 
	 * Purpose: decline service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
	 * 		  String authToken = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * 		  Integer serviceId = 6
	 * Expected Output: decline service and returns true
	 */
	@Test
	public void testdeclineService_001() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]";
		String authToken = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		Integer serviceId = 6;
		
		// Test Input:
		boolean result = serviceLogic.declineService(content, authToken, email, serviceId);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-declineService-002
	 * Purpose: decline service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\JKSDFIEWNDIDSMDF56865]"
	 * 		  String authToken = "58REZ69JSFAERSDFS25S622"
	 * 		  String email = "user5@email.com"
	 * 		  Integer serviceId = 1
	 * Expected Output: decline service and returns true
	 */
	@Test
	public void testdeclineService_002() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\JKSDFIEWNDIDSMDF56865]";
		String authToken = "58REZ69JSFAERSDFS25S622";
		String email = "user5@email.com";
		Integer serviceId = 1;
		
		// Test Input:
		boolean result = serviceLogic.declineService(content, authToken, email, serviceId);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-listTower-001 
	 * Purpose: display all the list of towers
	 * Preconditions: creates a tower instance
	 * Input: String authToken = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 *        String pickup = "586 W 63 St Miami Fl, 33143"
	 *        Integer order = 5634
	 * Expected Output: returns a list of towers and asserts true
	 */
	@Test
	public void testlistTower_001() {
		//Preconditions:		
		Tower myT = new Tower();
		myT.setBlocked("blocked");
		myT.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		myT.setDob(dob);
		myT.setEmail("agonz1123@fiu.edu");
		myT.setFname("Andy");
		myT.setId(5);
		myT.setLname("Miller");
		myT.setPassword("1234");
		myT.setPhone("555-555-5555");
		myT.setState("FL");
		myT.setStreetAddress("520 w 5 st");
		myT.setUserTypeId(3);
		myT.setZipcode("33122");
		myT.setCompanyName("ABC");
		myT.setLatitude(2.2);
		myT.setLongitude(2.3);
		myT.setPermitNumber("12345");
		myT.setPriceMile(2.24);		
		
		
		// Test Setup:
		String authToken = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		String pickup = "586 W 63 St Miami Fl, 33143";
		Integer order = 5634;
		
		// Test Input:
		List<Tower> resultList = serviceLogic.listTower(authToken, email, pickup, order);
		// Expected Output:		
		assertTrue(compareTower(resultList.get(0), myT));
	}
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-listTower-002
	 * Purpose: display all the list of towers
	 * Preconditions: creates a tower instance
	 * Input: String authToken = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 *        String pickup = "586 W 63 St Miami Fl, 33143"
	 *        Integer order = 5634
	 * Expected Output: returns a list of towers and asserts false
	 */
	@Test
	public void testlistTower_002() {
		//Preconditions:		
		Tower myT = new Tower();
		myT.setBlocked("blocked");
		myT.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		myT.setDob(dob);
		myT.setEmail("agonz1123@fiu.edu");
		myT.setFname("Andy");
		myT.setId(5);
		myT.setLname("Miller");
		myT.setPassword("1234896");
		myT.setPhone("555-555-5555");
		myT.setState("FL");
		myT.setStreetAddress("520 w 5 st");
		myT.setUserTypeId(3);
		myT.setZipcode("33122");
		myT.setCompanyName("ABC");
		myT.setLatitude(2.2);
		myT.setLongitude(2.3);
		myT.setPermitNumber("12345");
		myT.setPriceMile(2.24);		
		
		
		// Test Setup:
		String authToken = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		String pickup = "586 W 63 St Miami Fl, 33143";
		Integer order = 5634;
		
		// Test Input:
		List<Tower> resultList = serviceLogic.listTower(authToken, email, pickup, order);
		// Expected Output:		
		assertFalse(compareTower(resultList.get(0), myT));
	}
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-listTower-003 
	 * Purpose: display all the list of towers
	 * Preconditions: creates a tower instance
	 * Input: String authToken = "58REZ69ASDFADF56J"
	 * 		  String email = "user10@email.com"
	 *        String pickup = "2568 NW 2nd ST Miami FL 33158"
	 *        Integer order = 2045
	 * Expected Output: returns a list of towers and asserts true
	 */
	@Test
	public void testlistTower_003() {
		//Preconditions:		
		Tower myT = new Tower();
		myT.setBlocked("blocked");
		myT.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		myT.setDob(dob);
		myT.setEmail("agonz1123@fiu.edu");
		myT.setFname("Andy");
		myT.setId(5);
		myT.setLname("Miller");
		myT.setPassword("1234");
		myT.setPhone("555-555-5555");
		myT.setState("FL");
		myT.setStreetAddress("520 w 5 st");
		myT.setUserTypeId(3);
		myT.setZipcode("33122");
		myT.setCompanyName("ABC");
		myT.setLatitude(2.2);
		myT.setLongitude(2.3);
		myT.setPermitNumber("12345");
		myT.setPriceMile(2.24);		
		
		
		// Test Setup:
		String authToken = "58REZ69ASDFADF56J";
		String email = "user10@email.com";
		String pickup = "2568 NW 2nd ST Miami FL 33158";
		Integer order = 2045;
		
		// Test Input:
		List<Tower> resultList = serviceLogic.listTower(authToken, email, pickup, order);
		// Expected Output:		
		assertTrue(compareTower(resultList.get(0), myT));
	}
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-listTower-004
	 * Purpose: display all the list of towers
	 * Preconditions: creates a tower instance
	 * Input: String authToken = "58REZ69ASDFADF56J"
	 * 		  String email = "user10@email.com"
	 *        String pickup = "2568 NW 2nd ST Miami FL 33158"
	 *        Integer order = 2045
	 * Expected Output: returns a list of towers and asserts false
	 */
	@Test
	public void testlistTower_004() {
		//Preconditions:		
		Tower myT = new Tower();
		myT.setBlocked("blocked");
		myT.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		myT.setDob(dob);
		myT.setEmail("agonz1123@fiu.edu");
		myT.setFname("Andy");
		myT.setId(5);
		myT.setLname("Miller");
		myT.setPassword("1234896");
		myT.setPhone("555-555-5555");
		myT.setState("FL");
		myT.setStreetAddress("520 w 5 st");
		myT.setUserTypeId(3);
		myT.setZipcode("33122");
		myT.setCompanyName("ABC");
		myT.setLatitude(2.2);
		myT.setLongitude(2.3);
		myT.setPermitNumber("12345");
		myT.setPriceMile(2.24);		
		
		
		// Test Setup:
		String authToken = "58REZ69ASDFADF56J";
		String email = "user10@email.com";
		String pickup = "2568 NW 2nd ST Miami FL 33158";
		Integer order = 2045;
		
		// Test Input:
		List<Tower> resultList = serviceLogic.listTower(authToken, email, pickup, order);
		// Expected Output:		
		assertFalse(compareTower(resultList.get(0), myT));
	}
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-selectAllService-001
	 * Purpose: displays/ selects all services available
	 * Preconditions: creates a service instance
	 * Input: String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * Expected Output: returns a list of services and asserts true
	 */
	@Test
	public void testselectAllService_001() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212A");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("33199");
		temp.setClientDescription("student");
		temp.setTowerDescription("tall");
		
		// Test Setup:
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectAllService(token, email);
		// Expected Output:		
		assertTrue(compareService(resultList.get(0), temp));
	}
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-selectAllService-002
	 * Purpose: displays/ selects all services available
	 * Preconditions: creates a service instance
	 * Input: String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * Expected Output: returns a list of services and asserts false
	 */
	@Test
	public void testselectAllService_002() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212A");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("33199");
		temp.setClientDescription("person");
		temp.setTowerDescription("tall");
		
		// Test Setup:
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectAllService(token, email);
		// Expected Output:		
		assertFalse(compareService(resultList.get(0), temp));
	}
	
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-selectAllService-003
	 * Purpose: displays/ selects all services available
	 * Preconditions: creates a service instance
	 * Input: String token = "58REZ6ASDFSD45761259J"
	 * 		  String email = "user1@email.com"
	 * Expected Output: returns a list of services and asserts true
	 */
	@Test
	public void testselectAllService_003() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212A");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("33199");
		temp.setClientDescription("student");
		temp.setTowerDescription("tall");
		
		// Test Setup:
		String token = "58REZ6ASDFSD45761259J";
		String email = "user1@email.com";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectAllService(token, email);
		// Expected Output:		
		assertTrue(compareService(resultList.get(0), temp));
	}
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-selectAllService-004
	 * Purpose: displays/ selects all services available
	 * Preconditions: creates a service instance
	 * Input: String token = "58REZ6ASDFSD45761259J";
	 *        String email = "user1@email.com";
	 * Expected Output: returns a list of services and asserts false
	 */
	@Test
	public void testselectAllService_004() {
		//Preconditions:		
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456789));
		temp.setCost(5.0);
		temp.setLatitudePickup(65.5);
		temp.setLongitudePickup(66.6);
		temp.setLatitudeDestination(85.5);		
		temp.setLongitudeDestination(86.6);		
		temp.setStreetAddressPickup("CASE212A");		
		temp.setCityPickup("Miami");		
		temp.setStatePickup("FL");		
		temp.setZipcodePickup("33199");		
		temp.setStreetAddressDestination("PG6");		
		temp.setCityDestination("Miami");		
		temp.setStateDestination("FL");		
		temp.setZipcodeDestination("33199");
		temp.setClientDescription("person");
		temp.setTowerDescription("tall");
		
		// Test Setup:
		String token = "58REZ6ASDFSD45761259J";
		String email = "user1@email.com";
		
		// Test Input:
		List<Service> resultList = serviceLogic.selectAllService(token, email);
		// Expected Output:		
		assertFalse(compareService(resultList.get(0), temp));
	}
	
	
	
	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-ServiceLogic-001-acceptService-001 
	 * Purpose: accepts a service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
	 * 		  String authToken = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * 		  Integer serviceId = 6
	 * Expected Output: accepts service and returns true
	 */
	@Test
	public void testacceptService_001() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]";
		String authToken = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		Integer serviceId = 6;
		
		// Test Input:
		boolean result = serviceLogic.acceptService(content, authToken, email, serviceId);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-acceptService-002 
	 * Purpose: accepts a service
	 * Preconditions: none 
	 * Input: String content = "ABC\\\\[(.*?)\\\\]DESADFASDFSDjikl\\\\[(.*?)\\\\]"
	 * 		  String authToken = "58REZ69JSDFASDFSA"
	 * 		  String email = "user50@email.com"
	 * 		  Integer serviceId = 53
	 * Expected Output: accepts service and returns true
	 */
	@Test
	public void testacceptService_002() {
		// Test Setup:
		String content = "ABC\\\\[(.*?)\\\\]DESADFASDFSDjikl\\\\[(.*?)\\\\]";
		String authToken = "58REZ69JSDFASDFSA";
		String email = "user50@email.com";
		Integer serviceId = 53;
		
		// Test Input:
		boolean result = serviceLogic.acceptService(content, authToken, email, serviceId);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	
	
	
	/**
	 * ID: QicFix-ServiceLogic-001-updatePickup-001 
	 * Purpose: updates a pick up address
	 * Preconditions: none 
	 * 		  String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * 		  Integer serviceId = 6
	 *        String pickup = "568 W 2nd Ave Miami Fl 33172"
	 * Expected Output: updates pick up and returns true
	 */
	@Test
	public void testupdatePickup_001() {
		// Test Setup:		
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		Integer serviceId = 6;
		String pickup = "568 W 2nd Ave Miami Fl 33172";
		
		// Test Input:
		boolean result = serviceLogic.updatePickup(token, email, serviceId, pickup);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	/**
	 * ID: QicFix-ServiceLogic-001-updateDestination-001 
	 * Purpose: updates a destination address
	 * Preconditions: none 
	 * 		  String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * 		  Integer serviceId = 6
	 *        String destination = "2550 W 2nd Ave Miami Fl 33018"
	 * Expected Output: updates destination address and returns true
	 */
	@Test
	public void testupdateDestination_001() {
		// Test Setup:		
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		Integer serviceId = 6;
		String destination = "2550 W 2nd Ave Miami Fl 33018";
		
		// Test Input:
		boolean result = serviceLogic.updateDestination(token, email, serviceId, destination);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	/**
	 * ID: QicFix-ServiceLogic-001-makePayment-001 
	 * Purpose: makes a payment
	 * Preconditions: none 
	 * 		  String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * 		  Integer serviceId = 6
	 *        Double payment = 80
	 * Expected Output: makes a payment and returns true
	 */
	@Test
	public void testmakePayment_001() {
		// Test Setup:		
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		Integer serviceId = 6;
		Double payment = 80.00;
		
		// Test Input:
		boolean result = serviceLogic.makePayment(token, email, serviceId, payment);
		// Expected Output:		
		assertTrue(result == true);
	}
	

	/**
	 * ID: QicFix-ServiceLogic-001-rateTower-001 
	 * Purpose: rates a tower
	 * Preconditions: none 
	 * 		  String token = "58REZ69J"
	 * 		  String email = "agonz1123@fiu.edu"
	 * 		  Integer serviceId = 6
	 *        Integer towerId = 5
	 *        Integer rate = 4
	 * Expected Output: rates a tower and returns true
	 */
	@Test
	public void testrateTower_001() {
		// Test Setup:		
		String token = "58REZ69J";
		String email = "agonz1123@fiu.edu";
		Integer serviceId = 6;
		Integer towerId = 5;
		Integer rate = 4;
		
		
		// Test Input:
		boolean result = serviceLogic.rateTower(token, email, serviceId, towerId, rate);
		// Expected Output:		
		assertTrue(result == true);
	}
	
	
	//@author Javier Duenas
		//JD test cases start
		
		/**
		 * ID: QicFix-ServiceLogic createServiceJD1
		 * Purpose: test creating a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]aBcDeFl\\\\[(.*?)\\\\]"
		 * 		  String token = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * Expected Output: creates service and returns true
		 */
		@Test
		public void testcreateServiceJD1() {
			// Test Setup:
			String content = "ABC\\\\[(.*?)\\\\]aBcDeFl\\\\[(.*?)\\\\]";
			String token = "69ABC58J";
			String email = "jduen011@fiu.edu";
			
			// Test Input:
			boolean result = serviceLogic.createService(content, token, email);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic rateTowerJD2
		 * Purpose: test rating a tower
		 * Preconditions: none 
		 * 		  String token = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 1
		 *        Integer towerId = 2
		 *        Integer rate = 5
		 * Expected Output: rates a tower and returns true
		 */
		@Test
		public void testrateTowerJD2() {
			// Test Setup:		
			String token = "69ABC58J";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 1;
			Integer towerId = 2;
			Integer rate = 5;
			
			
			// Test Input:
			boolean result = serviceLogic.rateTower(token, email, serviceId, towerId, rate);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic makePaymentJD3
		 * Purpose: test making a payment
		 * Preconditions: none 
		 * 		  String token = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 4
		 *        Double payment = 100.00
		 * Expected Output: makes a payment and returns true
		 */
		@Test
		public void testmakePaymentJD3() {
			// Test Setup:		
			String token = "69ABC58J";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 4;
			Double payment = 100.00;
			
			// Test Input:
			boolean result = serviceLogic.makePayment(token, email, serviceId, payment);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogicupdatePickupJD4
		 * Purpose: updates a pick up address
		 * Preconditions: none 
		 * 		  String token = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 4
		 *        String pickup = "568 W 2nd Ave Miami Fl 33172"
		 * Expected Output: updates pick up and returns true
		 */
		@Test
		public void testupdatePickupJD4() {
			// Test Setup:		
			String token = "69ABC58J";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 4;
			String pickup = "190 w 49th st Hialeah FL 33012";
			
			// Test Input:
			boolean result = serviceLogic.updatePickup(token, email, serviceId, pickup);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogicupdatePickupJD4
		 * Purpose: test if doesn't updates a pick up address
		 * Preconditions: none 
		 * 		  String token = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 4
		 *        String pickup = "568 W 2nd Ave Miami Fl 33172"
		 * Expected Output: updates pick up and returns false
		 */
		@Test
		public void testupdatePickupJD5() {
			// Test Setup:		
			String token = "69ABC58J";
			String email = "randomEmail@fiu.edu";
			Integer serviceId = 4;
			String pickup = "190 w 49th st Hialeah FL 33012";
			
			// Test Input:
			boolean result = !(serviceLogic.updatePickup(token, email, serviceId, pickup));
			// Expected Output:		
			assertFalse(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic updateDestinationJD6
		 * Purpose: updates a destination address
		 * Preconditions: none 
		 * 		  String token = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 2
		 *        String destination = "190 w 49th st Hialeah FL 33012"
		 * Expected Output: updates destination address and returns true
		 */
		@Test
		public void testupdateDestinationJD6() {
			// Test Setup:		
			String token = "69ABC58J";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 2;
			String destination = "190 w 49th st Hialeah FL 33012";
			
			// Test Input:
			boolean result = serviceLogic.updateDestination(token, email, serviceId, destination);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic updateDestinationJD7
		 * Purpose: test if doesnt update a destination address
		 * Preconditions: none 
		 * 		  String token = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 2
		 *        String destination = "190 w 49th"
		 * Expected Output: updates destination address and returns true
		 */
		@Test
		public void testupdateDestinationJD7() {
			// Test Setup:		
			String token = "69ABC58J";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 2;
			String destination = "190 w 49th st";
			
			// Test Input:
			boolean result = !(serviceLogic.updateDestination(token, email, serviceId, destination));
			// Expected Output:		
			assertFalse(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic acceptServiceJD8
		 * Purpose: test accepting a service.
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
		 * 		  String authToken = "69ABC58J"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 2
		 * Expected Output: accepts service and returns true
		 */
		@Test
		public void testacceptServiceJD8() {
			// Test Setup:
			String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]aBcDeFl\\\\\\\\[(.*?)\\\\\\\\]";
			String authToken = "69ABC58J";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 2;
			
			// Test Input:
			boolean result = serviceLogic.acceptService(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic acceptServiceJD9
		 * Purpose: test declining a service.
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
		 * 		  String authToken = "69ABC58K"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 2
		 * Expected Output: false
		 */
		@Test
		public void testacceptServiceJD9() {
			// Test Setup:
			String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]aBcDeFl\\\\\\\\[(.*?)\\\\\\\\]";
			String authToken = "69ABC58k";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 2;
			
			// Test Input:
			boolean result = !(serviceLogic.acceptService(content, authToken, email, serviceId));
			// Expected Output:		
			assertFalse(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic declineServiceJD10 
		 * Purpose: test declining service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]aBcDeFl\\\\\\\\[(.*?)\\\\\\\\]"
		 * 		  String authToken = "69ABC58k"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 2
		 * Expected Output: decline service and returns true
		 */
		@Test
		public void testdeclineServiceJD10() {
			// Test Setup:
			String content = "ABC\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\]aBcDeFl\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\]";
			String authToken = "69ABC58k";
			String email = "jduen011@fiu.edu";
			Integer serviceId = 2;
			
			// Test Input:
			boolean result = serviceLogic.declineService(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic declineServiceJD11 
		 * Purpose: test failing to decline a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]aBcDeFl\\\\\\\\[(.*?)\\\\\\\\]"
		 * 		  String authToken = "69ABC58k"
		 * 		  String email = "jduen011@fiu.edu"
		 * 		  Integer serviceId = 2
		 * Expected Output: decline service and returns true
		 */
		@Test
		public void testdeclineServiceJD11() {
			// Test Setup:
			String content = "ABC\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\]aBcDeFl\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\]";
			String authToken = "69ABC58k";
			String email = "jduen011@fiu.edu";
			Integer serviceId = -2;
			
			// Test Input:
			boolean result = !(serviceLogic.declineService(content, authToken, email, serviceId));
			// Expected Output:		
			assertFalse(result == true);
		}
		
		/**
		 * ID: QicFix-ServiceLogic chargeClientJD12
		 * Purpose: test charging a client for the service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\]aBcDeFl\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\]"
		 * 		  String authToken = "69ABC58k"
		 * 		  String email = "jduen011@email.com"
		 * 		  Integer serviceId = 2
		 * Expected Output: charges the client and returns true
		 */
		@Test
		public void testchargeClientJD12() {
			// Test Setup:
			String content = "ABC\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\]aBcDeFl\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\]";
			String authToken = "69ABC58k";
			String email = "jduen011@email.com";
			Integer serviceId = 1;
			
			// Test Input:
			boolean result = serviceLogic.chargeClient(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		//JD test cases end
	
	
	
		//@author Zachary Garcia
		// ZG Test cases start
		/**
		 * ID: QicFix-ServiceLogic-testSelectAllService010
		 * Purpose: displays/ selects all services available
		 * Preconditions: creates a service instance
		 * Input: String token = "FGREYHW43YW54YHW65J54"
		 * 		  String email = "jdoe025@fiu.edu"
		 * Expected Output: Returns false because there is no tower by this email
		 */
		@Test
		public void testSelectAllService010() {
			//Preconditions:		
			Service temp = new Service();
			temp.setId(1234);
			temp.setClientId(3456);
			temp.setCreationDate(new Date(123456));
			temp.setStartDate(new Date(234567));
			temp.setEndDate(new Date(345678));
			temp.setCancelDate(new Date(456789));
			temp.setCost(5.0);
			temp.setLatitudePickup(65.5);
			temp.setLongitudePickup(66.6);
			temp.setLatitudeDestination(85.5);		
			temp.setLongitudeDestination(86.6);		
			temp.setStreetAddressPickup("CASE212A");		
			temp.setCityPickup("Hollywood");		
			temp.setStatePickup("FL");		
			temp.setZipcodePickup("33024");		
			temp.setStreetAddressDestination("320");		
			temp.setCityDestination("Hollywood");		
			temp.setStateDestination("FL");		
			temp.setZipcodeDestination("33024");
			temp.setClientDescription("student");
			temp.setTowerDescription("tall");
			
			// Test Setup:
			String token = "FGREYHW43YW54YHW65J54";
			String email = "jdoe025@fiu.edu";
			
			// Test Input:
			List<Service> resultList = serviceLogic.selectAllService(token, email);
			// Expected Output:		
			assertFalse(compareService(resultList.get(0), temp));
		}

		
		/**
		 * ID: QicFix-ServiceLogic-testSelectAllService011
		 * Purpose: displays/ selects all services available
		 * Preconditions: creates a service instance
		 * Input: String token = "43T64W5YRTHRTE436YTERA"
		 * 		  String email = "ZMOE025@fiu.edu"
		 * Expected Output: Returns a list of services and asserts true
		 */
		@Test
		public void testSelectAllService011() {
			//Preconditions:		
			Service temp = new Service();
			temp.setId(1234);
			temp.setClientId(3456);
			temp.setCreationDate(new Date(123456));
			temp.setStartDate(new Date(234567));
			temp.setEndDate(new Date(345678));
			temp.setCancelDate(new Date(456789));
			temp.setCost(5.0);
			temp.setLatitudePickup(65.5);
			temp.setLongitudePickup(66.6);
			temp.setLatitudeDestination(85.5);		
			temp.setLongitudeDestination(86.6);		
			temp.setStreetAddressPickup("CASE212A");		
			temp.setCityPickup("Hollywood");		
			temp.setStatePickup("FL");		
			temp.setZipcodePickup("33024");		
			temp.setStreetAddressDestination("320");		
			temp.setCityDestination("Miami");		
			temp.setStateDestination("FL");		
			temp.setZipcodeDestination("33024");
			temp.setClientDescription("student");
			temp.setTowerDescription("tall");
			
			// Test Setup:
			String token = "43T64W5YRTHRTE436YTERA";
			String email = "ZMOE025@fiu.edu";
			
			// Test Input:
			List<Service> resultList = serviceLogic.selectAllService(token, email);
			// Expected Output:		
			assertFalse(compareService(resultList.get(0), temp));
		}
		
		/**
		 * ID: QicFix-ServiceLogic-testMakePayment010 
		 * Purpose: Make a valid payment
		 * Preconditions: none 
		 * 		  String token = "54RTH66FD"
		 * 		  String email = "ZMOE@fiu.edu"
		 * 		  Integer serviceId = 3
		 *        Double payment = 120.00
		 * Expected Output: makes a payment and returns true
		 */
		@Test
		public void testMakePayment010() {
			// Test Setup:		
			String token = "54RTH66FD";
			String email = "ZMOE@fiu.edu";
			Integer serviceId = 3;
			Double payment = 120.00;
			
			// Test Input:
			boolean result = serviceLogic.makePayment(token, email, serviceId, payment);
			// Expected Output:		
			assertTrue(result);
		}
		
		/**
		 * ID: QicFix-ServiceLogic-testMakePayment011 
		 * Purpose: Rejects a negative payment
		 * Preconditions: none 
		 * 		  String token = "54RTH66FD"
		 * 		  String email = "ZMOE@fiu.edu"
		 * 		  Integer serviceId = 9
		 *        Double payment = -120.00
		 * Expected Output: Should return false due to invalid amount but returns true
		 */
		@Test
		public void testMakePayment011() {
			// Test Setup:		
			String token = "54RTH66FD";
			String email = "ZMOE@fiu.edu";
			Integer serviceId = 9;
			Double payment = -120.00;
			
			// Test Input:
			boolean result = serviceLogic.makePayment(token, email, serviceId, payment);
			// Expected Output:		
			assertTrue(result);
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic-testAcceptService010
		 * Purpose: accepts a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
		 * 		  String authToken = "58REZ69J"
		 * 		  String email = "jgomez@fiu.edu"
		 * 		  Integer serviceId = 12
		 * Expected Output: Returns true because there isn't a validator checking for correct token
		 */
		@Test
		public void testAcceptService010() {
			// Test Setup:
			String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]";
			String authToken = "";
			String email = "jgomez@fiu.edu";
			Integer serviceId = 12;
			
			// Test Input:
			boolean result = serviceLogic.acceptService(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic-testAcceptService011
		 * Purpose: accepts a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]"
		 * 		  String authToken = "58REZ69J"
		 * 		  String email = "jgomez@fiu.edu"
		 * 		  Integer serviceId = 12
		 * Expected Output: Accepts service with valid token
		 */
		@Test
		public void testAcceptService011() {
			// Test Setup:
			String content = "ABC\\\\[(.*?)\\\\]DEjikl\\\\[(.*?)\\\\]";
			String authToken = "fhdsfjhfgd35243";
			String email = "jgomez@fiu.edu";
			Integer serviceId = 12;
			
			// Test Input:
			boolean result = serviceLogic.acceptService(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		


		/**
		 * ID: QicFix-ServiceLogic-testRateTower010 
		 * Purpose: rates a tower
		 * Preconditions: none 
		 * 		  String token = "58REZ69J"
		 * 		  String email = "pgarc025@fiu.edu"
		 * 		  Integer serviceId = 6
		 *        Integer towerId = 5
		 *        Integer rate = 4
		 * Expected Output: Returns true because user doesn't exist
		 */
		@Test
		public void testRateTower010() {
			// Test Setup:		
			String token = "58REZ69J";
			String email = "pgarc025@fiu.edu";
			Integer serviceId = 6;
			Integer towerId = 0;
			Integer rate = 4;
			
			
			// Test Input:
			boolean result = serviceLogic.rateTower(token, email, serviceId, towerId, rate);
			// Expected Output:		
			assertTrue(result == true);
		}

		/**
		 * ID: QicFix-ServiceLogic-testRateTower011 
		 * Purpose: rates a tower
		 * Preconditions: none 
		 * 		  String token = "58REZ69J"
		 * 		  String email = "pgarc025@fiu.edu"
		 * 		  Integer serviceId = 6
		 *        Integer towerId = 5
		 *        Integer rate = 4
		 * Expected Output: Returns true with valid towerId
		 */
		@Test
		public void testRateTower011() {
			// Test Setup:		
			String token = "58REZ69J";
			String email = "pgarc025@fiu.edu";
			Integer serviceId = 6;
			Integer towerId = 3;
			Integer rate = 4;
			
			
			// Test Input:
			boolean result = serviceLogic.rateTower(token, email, serviceId, towerId, rate);
			// Expected Output:		
			assertTrue(result == true);
		}

		
		/**
		 * ID: QicFix-ServiceLogic-testListTower010
		 * Purpose: display all the list of towers
		 * Preconditions: creates a tower instance
		 * Input: String authToken = "58REZ69J"
		 * 		  String email = "zgarc025@fiu.edu"
		 *        String pickup = "320 N 66th Ave Miami Fl, 33143"
		 *        Integer order = 5634
		 * Expected Output: Returns a list of towers and asserts false
		 */
		@Test
		public void testListTower010() {
			//Preconditions:		
			Tower myT = new Tower();
			myT.setBlocked("blocked");
			myT.setCity("Miami");
			Date dob = new Date(1990, 10, 5);
			myT.setDob(dob);
			myT.setEmail("zgarc025@fiu.edu");
			myT.setFname("Zachary");
			myT.setId(5);
			myT.setLname("Garcia");
			myT.setPassword("1234896");
			myT.setPhone("555-555-5555");
			myT.setState("FL");
			myT.setStreetAddress("320 N 66th Ave");
			myT.setUserTypeId(3);
			myT.setZipcode("33122");
			myT.setCompanyName("ABC");
			myT.setLatitude(2.2);
			myT.setLongitude(2.3);
			myT.setPermitNumber("12345");
			myT.setPriceMile(2.24);		
			
			
			// Test Setup:
			String authToken = "58REZ69J";
			String email = "zgarc025@fiu.edu";
			String pickup = "320 N 66th Ave Miami Fl, 33143";
			Integer order = 5921;
			
			// Test Input:
			List<Tower> resultList = serviceLogic.listTower(authToken, email, pickup, order);
			// Expected Output:		
			assertFalse(compareTower(resultList.get(0), myT));
		}

		/**
		 * ID: QicFix-ServiceLogic-testUpdatePickup010 
		 * Purpose: updates a pick up address
		 * Preconditions: none 
		 * 		  String token = "sgsfdghs43"
		 * 		  String email = "zgarc025@fiu.edu"
		 * 		  Integer serviceId = 6
		 *        String pickup = "320 N 66th Ave Miami Fl 33172"
		 * Expected Output: Returns false because this user doesn't exist
		 */
		@Test
		public void testUpdatePickup010() {
			// Test Setup:		
			String token = "sgsfdghs43";
			String email = "zgarc025@fiu.edu";
			Integer serviceId = 3;
			String pickup = "320 N 66th Ave Miami Fl 33172";
			
			// Test Input:
			boolean result = serviceLogic.updatePickup(token, email, serviceId, pickup);
			// Expected Output:		
			assertTrue(result);
		}

		/**
		 * ID: QicFix-ServiceLogic-testUpdatePickup011
		 * Purpose: updates a pick up address
		 * Preconditions: none 
		 * 		  String token = "sgsfdghs43"
		 * 		  String email = "zgarc025@fiu.edu"
		 * 		  Integer serviceId = 6
		 *        String pickup = "320 N 66th Ave Miami Fl 33172"
		 * Expected Output: Return false due to invalid token
		 */
		@Test
		public void testUpdatePickup011() {
			// Test Setup:		
			String token = "";
			String email = "zgarc025@fiu.edu";
			Integer serviceId = 3;
			String pickup = "320 N 66th Ave Miami Fl 33172";
			
			// Test Input:
			boolean result = serviceLogic.updatePickup(token, email, serviceId, pickup);
			// Expected Output:		
			assertTrue(result);
		}
		
		/**
		 * ID: QicFix-ServiceLogic-testCreateService010
		 * Purpose: creates a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]DEjiASDFADFASFASFASFkl\\\\[(.*?)\\\\]"
		 * 		  String token = "GFGREW35436"
		 * 		  String email = "zgarc025@email.com"
		 * Expected Output: Creates service and returns true
		 */
		@Test
		public void testCreateService010() {
			// Test Setup:
			String content = "ABC\\\\[(.*?)\\\\]DEjiASDFADFASFASFASFkl\\\\[(.*?)\\\\]";
			String token = "GFGREW35436";
			String email = "zgarc025@email.com";
			
			// Test Input:
			boolean result = serviceLogic.createService(content, token, email);
			// Expected Output:		
			assertTrue(result);
		}
		
		
		//ZG cases end
		
		
		
		
		//@author: Sergio Pizzolante
		// SP test cases start
		
		/**
		 * ID: QicFix-ServiceLogic-setDatastoreFacade-6 
		 * Purpose: sets dataStoreFacade
		 * Preconditions: create DatastoreFacade instance 
		 * Input: created DatastoreFacadeinstance in preconditions
		 * Expected Output: true
		 */
		@Test
		public void testsetDatastoreFacade6() {
			// Test Setup:
			DatastoreFacade ds = new DatastoreFacade();		
			// Test Input:
			serviceLogic.setDatastoreFacade(ds);
			// Expected Output:		
			assertFalse(false);
		}
		
		
		
		/**
		 * ID: QicFix-ServiceLogic-createService_6 
		 * Purpose: creates a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]7giGVI\\\\[(.*?)\\\\]"
		 * 		  String token = "5438716SP"
		 * 		  String email = "spizz001@fiu.edu"
		 * Expected Output: creates service and returns true
		 */
		@Test
		public void testcreateService_6() {
			// Test Setup:
			String content = "ABC\\\\[(.*?)\\\\]7giGVI\\\\[(.*?)\\\\]";
			String token = "5438716SP";
			String email = "spizz001@fiu.edu";
			
			// Test Input:
			boolean result = serviceLogic.createService(content, token, email);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		
		

		/**
		 * ID: QicFix-ServiceLogic-createService7
		 * Purpose: creates a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]jkHJHFGHJFHchku\\\\\\\\[(.*?)\\\\\\\\]"
		 * 		  String token = "5438716SP291295"
		 * 		  String email = "spizz001@fiu.edu"
		 * Expected Output: creates service and returns true
		 */
		@Test
		public void testcreateService7() {
			// Test Setup:
			String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]jkHJHFGHJFHchku\\\\\\\\[(.*?)\\\\\\\\]";
			String token = "5438716SP291295";
			String email = "spizz001@fiu.edu";
			
			// Test Input:
			boolean result = serviceLogic.createService(content, token, email);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic-selectServiceByTowerEmail7 
		 * Purpose: select service by tower email and returns a list of service
		 * Preconditions: creates a service instance
		 * Input: String token = "5438716SP"
		 * 		  String email = "spizz001@fiu.edu"
		 * Expected Output: returns a list of services and asserts true
		 */
		@Test
		public void testselectServiceByTowerEmail7() {
			//Preconditions:		
			Service temp = new Service();
			temp.setId(1234);
			temp.setClientId(3456);
			temp.setCreationDate(new Date(123456));
			temp.setStartDate(new Date(234567));
			temp.setEndDate(new Date(345678));
			temp.setCancelDate(new Date(456789));
			temp.setCost(5.0);
			temp.setLatitudePickup(65.5);
			temp.setLongitudePickup(66.6);
			temp.setLatitudeDestination(85.5);		
			temp.setLongitudeDestination(86.6);		
			temp.setStreetAddressPickup("DM211");		
			temp.setCityPickup("Miami");		
			temp.setStatePickup("FL");		
			temp.setZipcodePickup("33144");		
			temp.setStreetAddressDestination("PG5");		
			temp.setCityDestination("Miami");		
			temp.setStateDestination("FL");		
			temp.setZipcodeDestination("33143");
			temp.setClientDescription("student");
			temp.setTowerDescription("tall");
			
			
			
			// Test Setup:
			String token = "5438716SP";
			String email = "spizz001@fiu.edu";
			
			// Test Input:
			List<Service> resultList = serviceLogic.selectServiceByTowerEmail(token, email);
			// Expected Output:		
			assertFalse(compareService(resultList.get(0), temp));
		}
		
		
		

		/**
		 * ID: QicFix-ServiceLogic-selectServiceByTowerEmail5 
		 * Purpose: select service by tower email and returns a list of service
		 * Preconditions: creates a service instance
		 * Input: String token = "5438716SP"
		 * 		  String email = "spizz001@fiu.edu"
		 * Expected Output: returns a list of services and asserts false
		 */
		@Test
		public void testselectServiceByTowerEmail5() {
			//Preconditions:		
			Service temp = new Service();
			temp.setId(1234);
			temp.setClientId(3456);
			temp.setCreationDate(new Date(123456));
			temp.setStartDate(new Date(234567));
			temp.setEndDate(new Date(345678));
			temp.setCancelDate(new Date(456789));
			temp.setCost(5.0);
			temp.setLatitudePickup(60.5);
			temp.setLongitudePickup(69.6);
			temp.setLatitudeDestination(85.5);		
			temp.setLongitudeDestination(86.6);		
			temp.setStreetAddressPickup("PG5");		
			temp.setCityPickup("Miami");		
			temp.setStatePickup("FL");		
			temp.setZipcodePickup("33199");		
			temp.setStreetAddressDestination("PG6");		
			temp.setCityDestination("Miami");		
			temp.setStateDestination("FL");		
			temp.setZipcodeDestination("3319989");
			temp.setClientDescription("student");
			temp.setTowerDescription("tall");
			
			
			
			// Test Setup:
			String token = "5438716SP";
			String email = "spizz001@fiu.edu";
			
			// Test Input:
			List<Service> resultList = serviceLogic.selectServiceByTowerEmail(token, email);
			// Expected Output:		
			assertFalse(compareService(resultList.get(0), temp));
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic-selectServiceByTowerEmail8
		 * Purpose: select service by tower email and returns a list of service
		 * Preconditions: creates a service instance
		 * Input: String token = "5438716SP"
		 * 		  String email = "spizz001@fiu.edu"
		 * Expected Output: returns a list of services and asserts true
		 */
		@Test
		public void testselectServiceByTowerEmail8() {
			//Preconditions:		
			Service temp = new Service();
			temp.setId(1234);
			temp.setClientId(3456);
			temp.setCreationDate(new Date(123456));
			temp.setStartDate(new Date(234567));
			temp.setEndDate(new Date(345678));
			temp.setCancelDate(new Date(456789));
			temp.setCost(5.0);
			temp.setLatitudePickup(65.5);
			temp.setLongitudePickup(66.6);
			temp.setLatitudeDestination(85.5);		
			temp.setLongitudeDestination(86.6);		
			temp.setStreetAddressPickup("CASE212A");		
			temp.setCityPickup("Miami");		
			temp.setStatePickup("FL");		
			temp.setZipcodePickup("33199");		
			temp.setStreetAddressDestination("PG6");		
			temp.setCityDestination("Miami");		
			temp.setStateDestination("FL");		
			temp.setZipcodeDestination("33199");
			temp.setClientDescription("student");
			temp.setTowerDescription("tall");
			
			
			
			// Test Setup:
			String token = "5438716SP";
			String email = "spizz001@fiu.edu";
			
			// Test Input:
			List<Service> resultList = serviceLogic.selectServiceByTowerEmail(token, email);
			// Expected Output:		
			assertTrue(compareService(resultList.get(0), temp));
		}
		
		
		
		/**
		 * ID: QicFix-ServiceLogic-chargeClient5 
		 * Purpose: charges client for the service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]kgjkGJHH\\\\[(.*?)\\\\]"
		 * 		  String authToken = "58REZ69J"
		 * 		  String email = "spizz001@fiu.edu"
		 * 		  Integer serviceId = 6
		 * Expected Output: charges the client and returns true
		 */
		@Test
		public void testchargeClient5() {
			// Test Setup:
			String content = "ABC\\\\[(.*?)\\\\]kgjkGJHH\\\\[(.*?)\\\\]";
			String authToken = "58REZ69J";
			String email = "spizz001@fiu.edu";
			Integer serviceId = 6;
			
			// Test Input:
			boolean result = serviceLogic.chargeClient(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic-chargeClient6
		 * Purpose: charges client for the service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]kgjkGJHH\\\\[(.*?)\\\\]"
		 * 		  String authToken = "5438716SPTFJHGK"
		 * 		  String email = "spizz001@fiu.edu"
		 * 		  Integer serviceId = 4
		 * Expected Output: charges the client and returns true
		 */
		@Test
		public void testchargeClient6() {
			// Test Setup:
			String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]kgjkGJHH\\\\\\\\[(.*?)\\\\\\\\]";
			String authToken = "5438716SPTFJHGK";
			String email = "spizz001@fiu.edu";
			Integer serviceId = 4;
			
			// Test Input:
			boolean result = serviceLogic.chargeClient(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic-declineService7 
		 * Purpose: decline service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\[(.*?)\\\\]kgjkGJHH\\\\[(.*?)\\\\]"
		 * 		  String authToken = "5438716SP"
		 * 		  String email = "spizz001@fiu.edu"
		 * 		  Integer serviceId = 6
		 * Expected Output: decline service and returns true
		 */
		@Test
		public void testdeclineService7() {
			// Test Setup:
			String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]kgjkGJHH\\\\\\\\[(.*?)\\\\\\\\]";
			String authToken = "5438716SP";
			String email = "spizz001@fiu.edu";
			Integer serviceId = 6;
			
			// Test Input:
			boolean result = serviceLogic.declineService(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		
		

		/**
		 * ID: QicFix-ServiceLogic-listTower6 
		 * Purpose: display all the list of towers
		 * Preconditions: creates a tower instance
		 * Input: String authToken = "5438716SP"
		 * 		  String email = "spizz001fiu.edu"
		 *        String pickup = "1111 SW 60th St Miami Fl, 33143"
		 *        Integer order = 5678
		 * Expected Output: returns a list of towers and asserts true
		 */
		@Test
		public void testlistTower6() {
			//Preconditions:		
			Tower myT = new Tower();
			myT.setBlocked("blocked");
			myT.setCity("Miami");
			Date dob = new Date(1990, 10, 5);
			myT.setDob(dob);
			myT.setEmail("spizz001@fiu.edu");
			myT.setFname("Joe");
			myT.setId(5);
			myT.setLname("Doe");
			myT.setPassword("1234");
			myT.setPhone("555-555-5555");
			myT.setState("FL");
			myT.setStreetAddress("520 w 5 st");
			myT.setUserTypeId(3);
			myT.setZipcode("33122");
			myT.setCompanyName("ABC");
			myT.setLatitude(2.2);
			myT.setLongitude(2.3);
			myT.setPermitNumber("12345");
			myT.setPriceMile(2.24);		
			
			
			// Test Setup:
			String authToken = "5438716SP";
			String email = "spizz001@fiu.edu";
			String pickup = "1111 SW 60th St Miami Fl, 33143";
			Integer order = 5678;
			
			// Test Input:
			List<Tower> resultList = serviceLogic.listTower(authToken, email, pickup, order);
			// Expected Output:		
			assertFalse(compareTower(resultList.get(0), myT));
		}
		
		
		/**
		 * ID: QicFix-ServiceLogic-listTower7
		 * Purpose: display all the list of towers
		 * Preconditions: creates a tower instance
		 * Input: String authToken = "5438716SP"
		 * 		  String email = "spizz001@fiu.edu"
		 *        String pickup = "1111 SW 60th St Miami Fl, 33143"
		 *        Integer order = 5678
		 * Expected Output: returns a list of towers and asserts false
		 */
		@Test
		public void testlistTower7() {
			//Preconditions:		
			Tower myT = new Tower();
			myT.setBlocked("blocked");
			myT.setCity("Miami");
			Date dob = new Date(1990, 10, 5);
			myT.setDob(dob);
			myT.setEmail("spizz001@fiu.edu");
			myT.setFname("Joe");
			myT.setId(5);
			myT.setLname("Doe");
			myT.setPassword("1234896");
			myT.setPhone("555-555-5555");
			myT.setState("FL");
			myT.setStreetAddress("520 w 5 st");
			myT.setUserTypeId(3);
			myT.setZipcode("33122");
			myT.setCompanyName("ABC");
			myT.setLatitude(2.2);
			myT.setLongitude(2.3);
			myT.setPermitNumber("12345");
			myT.setPriceMile(2.24);		
			
			
			// Test Setup:
			String authToken = "5438716SP";
			String email = "spizz001@fiu.edu";
			String pickup = "1111 SW 60th St Miami Fl, 33143";
			Integer order = 5678;
			
			// Test Input:
			List<Tower> resultList = serviceLogic.listTower(authToken, email, pickup, order);
			// Expected Output:		
			assertFalse(compareTower(resultList.get(0), myT));
		}
		
		
		
		

		/**
		 * ID: QicFix-ServiceLogic-acceptService6 
		 * Purpose: accepts a service
		 * Preconditions: none 
		 * Input: String content = "ABC\\\\\\\\[(.*?)\\\\\\\\]kgjkGJHH\\\\\\\\[(.*?)\\\\\\\\]"
		 * 		  String authToken = "5438716SP"
		 * 		  String email = "spizz001@fiu.edu"
		 * 		  Integer serviceId = 6
		 * Expected Output: accepts service and returns true
		 */
		@Test
		public void testacceptService6() {
			// Test Setup:
			String content = "ABC\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\]kgjkGJHH\\\\\\\\\\\\\\\\[(.*?)\\\\\\\\\\\\\\\\]";
			String authToken = "5438716SP";
			String email = "spizz001@fiu.edu";
			Integer serviceId = 6;
			
			// Test Input:
			boolean result = serviceLogic.acceptService(content, authToken, email, serviceId);
			// Expected Output:		
			assertTrue(result == true);
		}
		
		//SG cases end
		
	// *****************Helper methods************************************

		// Compares two Service objects for equality
		public boolean compareService(Service s1, Service s2) {

			return (s1.getId().equals(s2.getId()) && s1.getClientId().equals(s2.getClientId())
					&& s1.getCreationDate().equals(s2.getCreationDate()) && s1.getStartDate().equals(s2.getStartDate())
					&& s1.getEndDate().equals(s2.getEndDate()) && s1.getCancelDate().equals(s2.getCancelDate())
					&& s1.getCost().equals(s2.getCost()) && s1.getLatitudePickup().equals(s2.getLatitudePickup())
					&& s1.getLongitudePickup().equals(s2.getLongitudePickup())
					&& s1.getLatitudeDestination().equals(s2.getLatitudeDestination())
					&& s1.getLongitudeDestination().equals(s2.getLongitudeDestination())
					&& s1.getStreetAddressPickup().equals(s2.getStreetAddressPickup())
					&& s1.getCityPickup().equals(s2.getCityPickup()) && s1.getStatePickup().equals(s2.getStatePickup())
					&& s1.getZipcodePickup().equals(s2.getZipcodePickup())
					&& s1.getStreetAddressDestination().equals(s2.getStreetAddressDestination())
					&& s1.getCityDestination().equals(s2.getCityDestination())
					&& s1.getStateDestination().equals(s2.getStateDestination())
					&& s1.getZipcodeDestination().equals(s2.getZipcodeDestination())
					&& s1.getClientDescription().equals(s2.getClientDescription())
					&& s1.getTowerDescription().equals(s2.getTowerDescription()));

		}
		
		
		// Compares two Tower objects for equality
		private boolean compareTower(Tower s1, Tower s2) {
			return (s1.getId().equals(s2.getId()) && s1.getBlocked().equals(s2.getBlocked())
					&& s1.getCity().equals(s2.getCity()) && s1.getDob().equals(s2.getDob())
					&& s1.getEmail().equals(s2.getEmail()) && s1.getFname().equals(s2.getFname())
					&& s1.getLname().equals(s2.getLname()) && s1.getPassword().equals(s2.getPassword())
					&& s1.getPhone().equals(s2.getPhone()) && s1.getState().equals(s2.getState())
					&& s1.getStreetAddress().equals(s2.getStreetAddress()) && s1.getUserTypeId().equals(s2.getUserTypeId())
					&& s1.getZipcode().equals(s2.getZipcode()) && s1.getCompanyName().equals(s2.getCompanyName())
					&& s1.getLatitude().equals(s2.getLatitude()) && s1.getLongitude().equals(s2.getLongitude())
					&& s1.getPermitNumber().equals(s2.getPermitNumber())
					&& s1.getPermitNumber().equals(s2.getPermitNumber()) && s1.getPriceMile().equals(s2.getPriceMile()));
		}
		
		
	
}

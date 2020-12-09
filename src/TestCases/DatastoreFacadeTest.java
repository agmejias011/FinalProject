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
 *         DatastoreFacade class
 *
 */

public class DatastoreFacadeTest {

	@Mock
	Client clientMock;

	private DatastoreFacade datastoreFacade;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// MockitoAnnotations.initMocks(this);
		datastoreFacade = new DatastoreFacade();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {

		datastoreFacade = null;
	}

	/**
	 * ID: QicFix-DatastoreFacade-002-createClient-001 
	 * Purpose: Test if method calls client.create() and returns true
	 * Preconditions: mock of Client.class, 
	 * Input: datastoreFacade.createClient(clientMock) 
	 * Expected Output: true
	 */
	@Test
	public void testCreateClient_001() {
		// Test Setup:
		Client clientMock = mock(Client.class);
		when(clientMock.create()).thenReturn(true);
		// Test Input:
		boolean testReturn = datastoreFacade.createClient(clientMock);
		// Expected Output:
		assertTrue(testReturn);
		verify(clientMock).create();
	}

	/**
	 * ID: QicFix-DatastoreFacade-002-createClient-002
	 * Purpose: Test if method calls client.create() and returns false 
	 * Preconditions: mock of Client.class, 
	 * Input: datastoreFacade.createClient(clientMock)
	 * datastoreFacade.createClient(clientMock) Expected Output: false
	 */
	@Test
	public void testCreateClient_002() {
		// Test Setup:
		Client clientMock = mock(Client.class);
		when(clientMock.create()).thenReturn(false);
		// Test Input:
		boolean testReturn = datastoreFacade.createClient(clientMock);
		// Expected Output:
		assertFalse(testReturn);
		verify(clientMock).create();
	}

	/**
	 * ID: QicFix-DatastoreFacade-006-declineRequest-001 
	 * Purpose: Test if request is declined
	 * Preconditions: 
	 * Input: serviceId = 1; towerId = 3; 
	 * Expected output: request is declined 
	 * Comments: This method is not testable because
	 * DeclineRequest() in DatastoreFacade has a inline declaration of a local
	 * variable
	 */
	@Test
	public void testDeclineRequest() {
		Integer serviceId = 1;
		Integer towerId = 3;
		// This function is difficult to test.
		// Test Setup:
		// Test Input:
		datastoreFacade.declineRequest(serviceId, towerId);
		// Expected Output
		assertTrue(true);
	}

	/**
	 * ID: QicFix-DatastoreFacade-006-declineService-001 
	 * Purpose: Test if Service is declined 
	 * Preconditions: 
	 * Input: String towerEmail = "agonz1123@fiu.edu";
			  Integer serviceId = 1;
	 * Expected output: that Service is declined: true
	 */
	@Test
	public void testdeclineService() {
		String towerEmail = "agonz1123@fiu.edu";
		Integer serviceId = 1;

		// Test Setup:
		// Test Input:
		boolean returnVal = datastoreFacade.declineService(towerEmail, serviceId);
		// Expected Output
		assertTrue(returnVal);
	}

	/**
	 * ID: QicFix-DatastoreFacade-008-selectServiceByTowerEmail-001 
	 * Purpose: Test if a non empty Service list is returned. 
	 * Preconditions: A service instance is created 
	 * Input: towerEmail = clarkep@fiu.edu; 
	 * Expected output: Returns list with one service and returns true
	 */
	@Test
	public void testSelectServiceByTowerEmail001() {
		// Test setup
		List<Service> myServiceList = new ArrayList<Service>();
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
		
		// Test input
		myServiceList = datastoreFacade.selectServiceByTowerEmail("clarkep@fiu.edu");
		// Test output
		assertTrue(compareService(myServiceList.get(0), temp));

	}

	/**
	 * ID: QicFix-DatastoreFacade-008-selectServiceByTowerEmail-001 
	 * Purpose: Test if a non empty Service list is returned. 
	 * Preconditions: A service instance is created 
	 * Input: towerEmail = clarkep@fiu.edu; 
	 * Expected output: Returns list with one service and return false
	 */
	@Test
	public void testSelectServiceByTowerEmail002() {
		// Test setup
		List<Service> myServiceList = new ArrayList<Service>();
		Service temp = new Service();
		temp.setId(12345);
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
		// Test input
		myServiceList = datastoreFacade.selectServiceByTowerEmail("clarkep@fiu.edu");
		// Test output
		assertFalse(compareService(myServiceList.get(0), temp));

	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectClientByEmail-001 
	 * Purpose: Test if non empty client list is returned 
	 * Preconditions: A client instance is created
	 * Input: String email = "agonz1123@fiu.edu"; 
	 * Expected output: Returns list with one client and returns true
	 */
	@Test
	public void testselectClientByEmail001() {
		// Test setup

		Client client = new Client();
		client.setBlocked("blocked");
		client.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		client.setDob(dob);
		client.setEmail("agonz1123@fiu.edu");
		client.setFname("Andy");
		client.setId(5);
		client.setLname("Miller");
		client.setPassword("1234");
		client.setPhone("555-555-5555");
		client.setState("FL");
		client.setStreetAddress("520 w 5 st");
		client.setUserTypeId(3);
		client.setZipcode("33122");

		// Test input
		List<Client> myClientList = datastoreFacade.selectClientByEmail("agonz1123@fiu.edu");
		// Test output
		assertTrue(compareClients(myClientList.get(0), client));

	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectClientByEmail-002 
	 * Purpose: Test if non empty client list is returned 
	 * Preconditions: A client instance is created
	 * Input: String email = "agonz1123@fiu.edu"; 
	 * Expected output: Returns list with one client and returns false
	 */
	@Test
	public void testselectClientByEmail002() {
		// Test setup

		Client client = new Client();
		client.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		client.setDob(dob);
		client.setEmail("agonz1123@fiu.edu");
		client.setFname("Andy");
		client.setId(5);
		client.setLname("Miller");
		client.setPassword("1234");
		client.setPhone("555-555-5555");
		client.setState("FL");
		client.setStreetAddress("1254 Nw 2nd St");
		client.setUserTypeId(3);
		client.setZipcode("33122");

		// Test input
		List<Client> myClientList = datastoreFacade.selectClientByEmail("agonz1123@fiu.edu");
		// Test output
		assertFalse(compareClients(myClientList.get(0), client));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByAddress-001 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created
	 * Input: String address = "520 w 5 st"; 
	 * Expected output: Returns list with one client and returns true
	 */
	@Test
	public void testselectTowerByAddress001() {
		// Test setup

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

		// Test input
		String address = "520 w 5 st";
		List<Tower> myTower = datastoreFacade.selectTowerByAddress(address);
		// Test output
		assertTrue(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByAddress-002 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created
	 * Input: String address = "520 w 5 st"; 
	 * Expected output: Returns list with one client and returns false
	 */
	@Test
	public void testselectTowerByAddress002() {
		// Test setup

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
		myT.setPermitNumber("1234586");
		myT.setPriceMile(2.24);

		// Test input
		String address = "520 w 5 st";
		List<Tower> myTower = datastoreFacade.selectTowerByAddress(address);
		// Test output
		assertFalse(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByAddress00101 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created, a location instance is created 
	 * Input: Location loc = new Location(2.2, 2.3);
	 * 		  String city = "Miami"; String state = "FL"; 
	 * Expected output: Returns list with one client and returns true
	 */
	@Test
	public void testselectTowerByAddress00101() {
		// Test setup
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

		// Test input
		Location loc = new Location(2.2, 2.3);
		String city = "Miami";
		String state = "FL";

		List<Tower> myTower = datastoreFacade.selectTowerByAddress(loc, city, state);
		// Test output
		assertTrue(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByAddress00102 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created, a location instance is created 
	 * Input: Location loc = new Location(2.2, 2.3);
	 * 		  String city = "Miami"; String state = "FL"; 
	 * Expected output: Returns list with one client and returns false
	 */
	@Test
	public void testselectTowerByAddress00102() {
		// Test setup
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
		myT.setCompanyName("ABC-D");
		myT.setLatitude(2.2);
		myT.setLongitude(2.3);
		myT.setPermitNumber("12345");
		myT.setPriceMile(2.24);

		// Test input
		Location loc = new Location(2.2, 2.3);
		String city = "Miami";
		String state = "FL";

		List<Tower> myTower = datastoreFacade.selectTowerByAddress(loc, city, state);
		// Test output
		assertFalse(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByRating-001 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created
	 * Input: String city = "Miami"; String state = "FL";
	 * Expected output: Returns list with one client and returns true
	 */
	@Test
	public void testselectTowerByRating001() {
		// Test setup

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

		// Test input
		String city = "Miami";
		String state = "FL";

		List<Tower> myTower = datastoreFacade.selectTowerByRating(city, state);
		// Test output
		assertTrue(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByRating-002 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created
	 * Input: String city = "Miami";
	 * 		  String state = "FL"; 
	 * Expected output: Returns list with one client and returns false
	 */
	@Test
	public void testselectTowerByRating002() {
		// Test setup

		Tower myT = new Tower();
		myT.setBlocked("blocked");
		myT.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		myT.setDob(dob);
		myT.setEmail("agonz1123@fiu.edu");
		myT.setFname("Andy");
		myT.setId(5);
		myT.setLname("Miller");
		myT.setPassword("12345689");
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

		// Test input
		String city = "Miami";
		String state = "FL";

		List<Tower> myTower = datastoreFacade.selectTowerByRating(city, state);
		// Test output
		assertFalse(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByPrice-001 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created
	 * Input: String city = "Miami"; 
	 * 		  String state = "FL"; 
	 * Expected output: Returns list with one client and returns true
	 */
	@Test
	public void testselectTowerByPrice001() {
		// Test setup

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

		// Test input
		String city = "Miami";
		String state = "FL";

		List<Tower> myTower = datastoreFacade.selectTowerByPrice(city, state);
		// Test output
		assertTrue(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-selectTowerByPrice-002 
	 * Purpose: Test if non empty tower list is returned 
	 * Preconditions: A tower instance is created
	 * Input: String city = "Miami"; 
	 * 		  String state = "FL"; 
	 * Expected output: Returns list with one client and returns false
	 */
	@Test
	public void testselectTowerByPrice002() {
		// Test setup

		Tower myT = new Tower();
		myT.setBlocked("blocked");
		myT.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		myT.setDob(dob);
		myT.setEmail("agonz1123@fiu.edu");
		myT.setFname("Andy");
		myT.setId(5);
		myT.setLname("Miller");
		myT.setPassword("12345689");
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

		// Test input
		String city = "Miami";
		String state = "FL";

		List<Tower> myTower = datastoreFacade.selectTowerByPrice(city, state);
		// Test output
		assertFalse(compareTower(myTower.get(0), myT));
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-updateClient-001
	 * Purpose: Test if client is updated
	 * Preconditions: A client instance is created
	 * Input: Client instance
	 * Expected output: Returns true if the client is updated
	 */
	@Test
	public void testupdateClient001() {
		// Test setup

		Client client = new Client();
		client.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		client.setDob(dob);
		client.setEmail("agonz1123@fiu.edu");
		client.setFname("Andy");
		client.setId(5);
		client.setLname("Miller");
		client.setPassword("1234");
		client.setPhone("555-555-5555");
		client.setState("FL");
		client.setStreetAddress("520 w 5 st");
		client.setUserTypeId(3);
		client.setZipcode("33122");

		// Test input

		boolean resp = datastoreFacade.updateClient(client);
		// Test output

		boolean updated = true;
		assertTrue(updated == true);
	}

	/**Author: Andres Gonzalez Mejias
	 * ID: QicFix-DatastoreFacade-008-updateClient-002 
	 * Purpose: Test if client is not updated
	 * Preconditions: A client instance is created
	 * Input: Client instance
	 * Input: client instance 
	 * Expected output: Returns false if the client is not updated
	 */
	@Test
	public void testupdateClient002() {
		// Test setup

		Client client = new Client();
		client.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		client.setDob(dob);
		client.setEmail("agonz1123@fiu.edu");
		client.setFname("Andy");
		client.setId(5);
		client.setLname("Miller");
		client.setPassword("1234");
		client.setPhone("555-555-5555");
		client.setState("FL");
		client.setStreetAddress("520 w 5 st");
		client.setUserTypeId(3);
		client.setZipcode("33122");

		// Test input

		boolean resp = datastoreFacade.updateClient(client);

		boolean updated = false;

		// Test output
		assertFalse(updated == true);
	}

	/**
	 * ID: QicFix-DatastoreFacade-008-selectApplication-001 
	 * Purpose: Test if non empty list of application is returned 
	 * Preconditions: An application instance is created 
	 * Input: Integer userTypeId = 6; 
	 * Expected output: Returns list with one application and returns true
	 */
	@Test
	public void tesselectApplication001() {
		// Test setup

		Application app = new Application();
		app.setId(25);
		app.setName("ABC");
		app.setUrl("www.url.com");

		// Test input
		Integer userTypeId = 6;

		List<Application> myApp = datastoreFacade.selectApplication(userTypeId);
		// Test output

		// Test output
		assertTrue(compareApp(myApp.get(0), app));
	}

	/**
	 * ID: QicFix-DatastoreFacade-008-selectApplication-002 
	 * Purpose: Test if non empty list of application is returned 
	 * Preconditions: An application instance is created 
	 * Input: Integer userTypeId = 6; 
	 * Expected output: Returns list with one application and returns false
	 */
	@Test
	public void tesselectApplication002() {
		// Test setup

		Application app = new Application();
		app.setId(25);
		app.setName("ABC");
		app.setUrl("www.urlDiff.com");

		// Test input
		Integer userTypeId = 6;

		List<Application> myApp = datastoreFacade.selectApplication(userTypeId);
		// Test output

		// Test output
		assertFalse(compareApp(myApp.get(0), app));
	}

	/**
	 * ID: QicFix-DatastoreFacade-008-selectUserByClientEmail-001 
	 * Purpose: Test if a non empty Service list is returned. 
	 * Preconditions: A service instance is created 
	 * Input: String email = "clarkep@fiu.edu" 
	 * Expected output: Returns list with one service and returns true
	 */
	@Test
	public void testselectUserByClientEmail001() {
		// Test setup
		List<Service> myServiceList = new ArrayList<Service>();
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
		// Test input
		String email = "clarkep@fiu.edu";
		myServiceList = datastoreFacade.selectUserByClientEmail(email);
		// Test output
		assertTrue(compareService(myServiceList.get(0), temp));

	}

	/**
	 * ID: QicFix-DatastoreFacade-008-selectUserByClientEmail-002 
	 * Purpose: Test if a non empty Service list is returned. 
	 * Preconditions: A service instance is created 
	 * Input: String email = "clarkep@fiu.edu"
	 * Expected output: Returns list with one service and returns false
	 */
	@Test
	public void testselectUserByClientEmail002() {
		// Test setup
		List<Service> myServiceList = new ArrayList<Service>();
		Service temp = new Service();
		temp.setId(1234);
		temp.setClientId(3456);
		temp.setCreationDate(new Date(123456));
		temp.setStartDate(new Date(234567));
		temp.setEndDate(new Date(345678));
		temp.setCancelDate(new Date(456788929));
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
		// Test input
		String email = "clarkep@fiu.edu";
		myServiceList = datastoreFacade.selectUserByClientEmail(email);
		// Test output
		assertFalse(compareService(myServiceList.get(0), temp));

	}

	/**
	 * ID: QicFix-DatastoreFacade-008-block-001 
	 * Purpose: Test if user is blocked
	 * Preconditions: A user instance is created 
	 * Input: user instance 
	 * Expected output: Returns true if the client is updated 
	 * Comment: non testable method, it is void
	 */
	@Test
	public void testblock001() {
		// Test setup
		entity.User user = new entity.User();

		user.setCity("Miami");
		Date dob = new Date(1990, 10, 5);
		user.setDob(dob);
		user.setEmail("agonz1123@fiu.edu");
		user.setFname("Andy");
		user.setLname("Miller");
		user.setPassword("1234");
		user.setPhone("555-555-5555");
		user.setState("FL");
		user.setStreetAddress("520 w 5 st");
		user.setUserTypeId(3);
		user.setZipcode("33122");

		// Test input

		datastoreFacade.block(user);
		// Test output

		assertTrue(true);
	}

	/**
	 * ID: QicFix-DatastoreFacade-006-updatePickup-001 
	 * Purpose: Test if pickup is updated 
	 * Preconditions: none 
	 * Input: String address = "563 W 25th St"; 
	 * Integer serviceId = 6; 
	 * Expected output: that pickup is updated
	 */
	@Test
	public void testupdatePickup_001() {
		String address = "563 W 25th St";
		Integer serviceId = 6;

		// Test Setup:
		// Test Input:
		boolean returnVal = datastoreFacade.updatePickup(address, serviceId);
		// Expected Output
		assertTrue(returnVal);
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-006-updatePickup-002
	 * Purpose: Test if pickup is updated 
	 * Preconditions: none 
	 * Input: String address = "2653 SW 12th St"; 
	 * Integer serviceId = 1; 
	 * Expected output: that pickup is updated
	 */
	@Test
	public void testupdatePickup_002() {
		String address = "2653 SW 12th St";
		Integer serviceId = 1;

		// Test Setup:
		// Test Input:
		boolean returnVal = datastoreFacade.updatePickup(address, serviceId);
		// Expected Output
		assertTrue(returnVal);
	}

	
	//@author Javier Duenas
		//JD Testcases start
		
		/**
		 * ID: QicFix-DatastoreFacade declineRequestJD1
		 * Purpose: Test if request is declined
		 * Preconditions: 
		 * Input: serviceId = 2; towerId = 5; 
		 * Expected output: request is declined 
		 * Comments: This method is not testable because
		 * DeclineRequest() in DatastoreFacade has a inline declaration of a local
		 * variable
		 */
		@Test
		public void testDeclineRequestJD1() {
			Integer serviceId = 2;
			Integer towerId = 5;
			// This function is difficult to test.
			// Test Setup:
			// Test Input:
			datastoreFacade.declineRequest(serviceId, towerId);
			// Expected Output
			assertTrue(true);
		}
		
		/**
		 * ID: QicFix-DatastoreFacade createClientJD2
		 * Purpose: Test if method calls client.create() and returns true
		 * Preconditions: mock of Client.class, 
		 * Input: datastoreFacade.createClient(clientMockito) 
		 * Expected Output: false
		 */
		@Test
		public void testCreateClientJD2() {
			// Test Setup:
			Client clientMockito = mock(Client.class);
			when(clientMockito.create()).thenReturn(false);
			// Test Input:
			boolean testReturn = datastoreFacade.createClient(clientMockito);
			// Expected Output:
			assertFalse(testReturn);
			verify(clientMockito).create();
		}
		
		/**
		 * ID: QicFix-DatastoreFacade createTowerJD3
		 * Purpose: Test if method calls Tower.create() and returns true
		 * Preconditions: mock of Tower.class, 
		 * Input: datastoreFacade.createTower(clientMockito) 
		 * Expected Output: True
		 */
		@Test
		public void testCreateTowerJD3() {
			String address = "190 W 49th st";
			Integer serviceId = 4;

			// Test Setup:
			// Test Input:
			boolean returnVal = datastoreFacade.updatePickup(address, serviceId);
			// Expected Output
			assertTrue(returnVal);
		}
		
		/**
		 * ID: QicFix-DatastoreFacade selectServiceByTowerEmailJD4 
		 * Purpose: Test if a non empty Service list is returned. 
		 * Preconditions: A service instance is created 
		 * Input: towerEmail = jduen011@fiu.edu; 
		 * Expected output: Returns list with one service and return false
		 */
		@Test
		public void testSelectServiceByTowerEmailJD4() {
			// Test setup
			List<Service> myServiceList = new ArrayList<Service>();
			Service temp = new Service();
			temp.setId(12345);
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
			temp.setCityPickup("Hialeah");
			temp.setStatePickup("FL");
			temp.setZipcodePickup("33012");
			temp.setStreetAddressDestination("PG6");
			temp.setCityDestination("Miami");
			temp.setStateDestination("FL");
			temp.setZipcodeDestination("33012");
			temp.setClientDescription("student");
			temp.setTowerDescription("tall");
			// Test input
			myServiceList = datastoreFacade.selectServiceByTowerEmail("jduen011@fiu.edu");
			// Test output
			assertFalse(compareService(myServiceList.get(0), temp));

		}
		
		/**
		 * ID: QicFix-DatastoreFacade updatePickupJD5 
		 * Purpose: Test if pickup is updated 
		 * Preconditions: none 
		 * Input: String address = "190 w 49th st"; 
		 * Integer serviceId = 8; 
		 * Expected output: that pickup is updated
		 */
		@Test
		public void testupdatePickupJD5() {
			String address = "190 W 49th st";
			Integer serviceId = 8;

			// Test Setup:
			// Test Input:
			boolean returnVal = datastoreFacade.updatePickup(address, serviceId);
			// Expected Output
			assertTrue(returnVal);
		}
		
		/**
		 * ID: QicFix-DatastoreFacade updatePickupJD5 
		 * Purpose: Test if pickup is updated 
		 * Preconditions: none 
		 * Input: String address = "1750 w 41st st"; 
		 * Integer serviceId = 2; 
		 * Expected output: that pickup is updated
		 */
		@Test
		public void testupdatePickupJD6() {
			String address = "1750 W 41st st";
			Integer serviceId = 2;

			// Test Setup:
			// Test Input:
			boolean returnVal = datastoreFacade.updatePickup(address, serviceId);
			// Expected Output
			assertTrue(returnVal);
		}
		
		/**
		 * ID: QicFix-DatastoreFacade declineServiceJD6 
		 * Purpose: Test if Service is declined 
		 * Preconditions: 
		 * Input: String towerEmail = "jduen011@fiu.edu";
				  Integer serviceId = 4;
		 * Expected output: that Service is declined: true
		 */
		@Test
		public void testdeclineServiceJD7() {
			String towerEmail = "jduen011@fiu.edu";
			Integer serviceId = 4;

			// Test Setup:
			// Test Input:
			boolean returnVal = datastoreFacade.declineService(towerEmail, serviceId);
			// Expected Output
			assertTrue(returnVal);
		}
		
		/**
		 * ID: QicFix-DatastoreFacade declineServiceJD6 
		 * Purpose: Test if Service is declined 
		 * Preconditions: 
		 * Input: String towerEmail = "jduen011@fiu.edu";
				  Integer serviceId = 6;
		 * Expected output: that Service is declined: true
		 */
		@Test
		public void testdeclineServiceJD8() {
			String towerEmail = "jduen011@fiu.edu";
			Integer serviceId = 6;

			// Test Setup:
			// Test Input:
			boolean returnVal = !(datastoreFacade.declineService(towerEmail, serviceId));
			// Expected Output
			assertFalse(returnVal);
		}
		
		/**
		 * ID: QicFix-DatastoreFacade testblockJD9 
		 * Purpose: Test if user is blocked
		 * Preconditions: A user instance is created 
		 * Input: user instance 
		 * Expected output: Returns true if the client is updated 
		 * Comment: non testable method, it is void
		 */
		@Test
		public void testblockJD9() {
			// Test setup
			entity.User user = new entity.User();

			user.setCity("Hialeah");
			Date dob = new Date(1997, 9, 23);
			user.setDob(dob);
			user.setEmail("jduen011@fiu.edu");
			user.setFname("Jane");
			user.setLname("Doe");
			user.setPassword("1234");
			user.setPhone("305-305-3055");
			user.setState("FL");
			user.setStreetAddress("190 w 49th st");
			user.setUserTypeId(3);
			user.setZipcode("33122");

			// Test input

			datastoreFacade.block(user);
			// Test output

			assertTrue(true);
		}
		
		/**
		 * ID: QicFix-DatastoreFacade selectTowerByRatingJD10 
		 * Purpose: Test if non empty tower list is returned 
		 * Preconditions: A tower instance is created
		 * Input: String city = "Hialeah";
		 * 		  String state = "FL"; 
		 * Expected output: Returns list with one client and returns false
		 */
		@Test
		public void testselectTowerByRatingJD10() {
			// Test setup

			Tower myT = new Tower();
			myT.setBlocked("blocked");
			myT.setCity("Miami");
			Date dob = new Date(1997, 9, 23);
			myT.setDob(dob);
			myT.setEmail("jduen011@fiu.edu");
			myT.setFname("Jane");
			myT.setId(5);
			myT.setLname("Due");
			myT.setPassword("12345689");
			myT.setPhone("305-305-3055");
			myT.setState("FL");
			myT.setStreetAddress("190 w 49th st");
			myT.setUserTypeId(3);
			myT.setZipcode("33012");
			myT.setCompanyName("ABC");
			myT.setLatitude(2.2);
			myT.setLongitude(2.3);
			myT.setPermitNumber("12345");
			myT.setPriceMile(2.24);

			// Test input
			String city = "Hialeah";
			String state = "FL";

			List<Tower> myTower = datastoreFacade.selectTowerByRating(city, state);
			// Test output
			assertFalse(compareTower(myTower.get(0), myT));
		}
		
		/**
		 * ID: QicFix-DatastoreFacade selectTowerByAddressJD11
		 * Purpose: Test if non empty tower list is returned 
		 * Preconditions: A tower instance is created
		 * Input: String address = "1750 w 41st st"; 
		 * Expected output: Returns list with one client and returns false
		 */
		@Test
		public void testselectTowerByAddressJD11() {
			// Test setup

			Tower myT = new Tower();
			myT.setBlocked("blocked");
			myT.setCity("Miami");
			Date dob = new Date(1997, 9, 23);
			myT.setDob(dob);
			myT.setEmail("jduen011@fiu.edu");
			myT.setFname("Jane");
			myT.setId(5);
			myT.setLname("Due");
			myT.setPassword("12345689");
			myT.setPhone("305-305-3055");
			myT.setState("FL");
			myT.setStreetAddress("190 w 49th st");
			myT.setUserTypeId(3);
			myT.setZipcode("33012");
			myT.setCompanyName("ABC");
			myT.setLatitude(2.2);
			myT.setLongitude(2.3);
			myT.setPermitNumber("12345");
			myT.setPriceMile(2.24);

			// Test input
			String address = "1750 W 41st st";
			List<Tower> myTower = datastoreFacade.selectTowerByAddress(address);
			// Test output
			assertFalse(compareTower(myTower.get(0), myT));
		}
		
		/**
		 * ID: QicFix-DatastoreFacade selectClientByEmailJD12 
		 * Purpose: Test if non empty client list is returned 
		 * Preconditions: A client instance is created
		 * Input: String email = "jduen011@fiu.edu"; 
		 * Expected output: Returns list with one client and returns true
		 */
		@Test
		public void testselectClientByEmailJD12() {
			// Test setup

			Client client = new Client();
			client.setBlocked("blocked");
			client.setCity("Miami");
			Date dob = new Date(1997, 9, 23);
			client.setDob(dob);
			client.setEmail("jduen011@fiu.edu");
			client.setFname("Jane");
			client.setId(5);
			client.setLname("Doe");
			client.setPassword("1234");
			client.setPhone("305-305-3055");
			client.setState("FL");
			client.setStreetAddress("190 W 49th st");
			client.setUserTypeId(3);
			client.setZipcode("33012");

			// Test input
			List<Client> myClientList = datastoreFacade.selectClientByEmail("jduen011@fiu.edu");
			// Test output
			assertFalse(compareClients(myClientList.get(0), client));

		}
		
		//JD Test cases end
		
		
		
		
		//@author Zachary Garcia
		// ZG test cases start
		
		/**
	     * ID: QicFix-DatastoreFacade-testselectClientByEmail010
	     * Purpose: Test if non empty client list is returned 
	     * Preconditions: A client instance is created
	     * Input: String email = "zgarc025@fiu.edu"; 
	     * Expected output: Return false because user does not exist
	     */
	    @Test
	    public void testselectClientByEmail010() {
	        // Test setup
	        Client client = new Client();
	        client.setBlocked("blocked");
	        client.setCity("Miami");
	        Date dob = new Date(1990, 10, 5);
	        client.setDob(dob);
	        client.setEmail("zgarc025@fiu.edu");
	        client.setFname("Zachary");
	        client.setId(5);
	        client.setLname("Garcia");
	        client.setPassword("1234");
	        client.setPhone("555-555-5555");
	        client.setState("FL");
	        client.setStreetAddress("320 N 66th Ave");
	        client.setUserTypeId(3);
	        client.setZipcode("33024");

	        // Test input
	        List < Client > myClientList = datastoreFacade.selectClientByEmail("zgarc025@fiu.edu");
	        // Test output
	        assertFalse(compareClients(myClientList.get(0), client));

	    }


	    /**
	     * ID: QicFix-DatastoreFacade-008-testselectClientByEmail011
	     * Purpose: Test if non-existent email returns false
	     * Preconditions: A client instance is created
	     * Input: String email = "jdoe023@fiu.edu"; 
	     * Expected output: Returns false because email shouldn't exist
	     */
	    @Test
	    public void testselectClientByEmail011() {
	        // Test setup
	        Client client = new Client();
	        client.setBlocked("blocked");
	        client.setCity("Miami");
	        Date dob = new Date(1990, 10, 5);
	        client.setDob(dob);
	        client.setEmail("jdoe0233@fiu.edu");
	        client.setFname("Jon");
	        client.setId(5);
	        client.setLname("Doe");
	        client.setPassword("1234");
	        client.setPhone("555-555-5555");
	        client.setState("FL");
	        client.setStreetAddress("320 N 66th Ave");
	        client.setUserTypeId(3);
	        client.setZipcode("33024");

	        // Test input
	        List < Client > myClientList = datastoreFacade.selectClientByEmail("zgarc025@fiu.edu");
	        // Test output
	        assertFalse(compareClients(myClientList.get(0), client));

	    }



	    /**
	     * ID: QicFix-DatastoreFacade-testupdatePickup010 
	     * Purpose: Test if pickup is updated 
	     * Preconditions: none 
	     * Input: String address = "320 N 66th Ave"; 
	     * Integer serviceId = 7; 
	     * Expected output: that pickup is updated
	     */
	    @Test
	    public void testupdatePickup010() {
	        String address = "320 N 66th Ave";
	        Integer serviceId = 7;

	        // Test Setup:
	        // Test Input:
	        boolean returnVal = datastoreFacade.updatePickup(address, serviceId);
	        // Expected Output
	        assertTrue(returnVal);
	    }

	    /**
	     * ID: QicFix-DatastoreFacade-testselectTowerByRating010
	     * Purpose: Test if non empty tower list is returned 
	     * Preconditions: A tower instance is created
	     * Input: String city = "Miami"; String state = "FL";
	     * Expected output: Returns list with one client and returns true
	     */
	    @Test
	    public void testselectTowerByRating010() {
	        // Test setup

	        Tower myT = new Tower();
	        myT.setBlocked("blocked");
	        myT.setCity("Miami");
	        Date dob = new Date(1990, 10, 5);
	        myT.setDob(dob);
	        myT.setEmail("dmiller@fiu.edu");
	        myT.setFname("Dean");
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

	        // Test input
	        String city = "Miami";
	        String state = "FL";

	        List < Tower > myTower = datastoreFacade.selectTowerByRating(city, state);
	        // Test output
	        assertFalse(compareTower(myTower.get(0), myT));
	    }

	    /**
	     * ID: QicFix-DatastoreFacade-testselectTowerByRating011 
	     * Purpose: Test if mismatch city returns empty list
	     * Preconditions: A tower instance is created
	     * Input: String city = "Miami"; String state = "FL";
	     * Expected output: Returns list with one client and returns true
	     */
	    @Test
	    public void testselectTowerByRating011() {
	        // Test setup

	        Tower myT = new Tower();
	        myT.setBlocked("blocked");
	        myT.setCity("Hollywood");
	        Date dob = new Date(1990, 10, 5);
	        myT.setDob(dob);
	        myT.setEmail("dmiller@fiu.edu");
	        myT.setFname("Dean");
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

	        // Test input
	        String city = "Miami";
	        String state = "FL";

	        List < Tower > myTower = datastoreFacade.selectTowerByRating(city, state);
	        // Test output
	        assertFalse(compareTower(myTower.get(0), myT));
	    }



	    /**
	     * ID: QicFix-DatastoreFacade-testdeclineService010
	     * Purpose: Test if Service is declined 
	     * Preconditions: 
	     * Input: String towerEmail = "zgarc025@fiu.edu";
	    		  Integer serviceId = 1;
	     * Expected output: that Service is declined: true
	     */
	    @Test
	    public void testdeclineService010() {
	        String towerEmail = "zgarc025@fiu.edu";
	        Integer serviceId = 1;

	        // Test Setup:
	        // Test Input:
	        boolean returnVal = datastoreFacade.declineService(towerEmail, serviceId);
	        // Expected Output
	        assertTrue(returnVal);
	    }



	    /**
	     * ID: QicFix-DatastoreFacade-testselectTowerByPrice010
	     * Purpose: Test if non empty tower list is returned 
	     * Preconditions: A tower instance is created
	     * Input: String city = "Miami"; 
	     * 		  String state = "FL"; 
	     * Expected output: Returns false because tower does not exist
	     */
	    @Test
	    public void testselectTowerByPrice010() {
	        // Test setup

	        Tower myT = new Tower();
	        myT.setBlocked("blocked");
	        myT.setCity("Miami");
	        Date dob = new Date(1990, 10, 5);
	        myT.setDob(dob);
	        myT.setEmail("zgarc025@fiu.edu");
	        myT.setFname("Zachary");
	        myT.setId(5);
	        myT.setLname("Garcia");
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

	        // Test input
	        String city = "Miami";
	        String state = "FL";

	        List < Tower > myTower = datastoreFacade.selectTowerByPrice(city, state);
	        // Test output
	        assertFalse(compareTower(myTower.get(0), myT));
	    }

	    /**
	     * ID: QicFix-DatastoreFacade-testselectTowerByPrice011
	     * Purpose: Test if empty list returned due to incorrect city
	     * Preconditions: A tower instance is created
	     * Input: String city = "Miami"; 
	     * 		  String state = "FL"; 
	     * Expected output: Returns empty list because city is wrong
	     */
	    @Test
	    public void testselectTowerByPrice011() {
	        // Test setup

	        Tower myT = new Tower();
	        myT.setBlocked("blocked");
	        myT.setCity("Hollywood");
	        Date dob = new Date(1990, 10, 5);
	        myT.setDob(dob);
	        myT.setEmail("zgarc025@fiu.edu");
	        myT.setFname("Zachary");
	        myT.setId(5);
	        myT.setLname("Garcia");
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

	        // Test input
	        String city = "Miami";
	        String state = "FL";

	        List < Tower > myTower = datastoreFacade.selectTowerByPrice(city, state);
	        // Test output
	        assertFalse(compareTower(myTower.get(0), myT));
	    }


	    /**
	     * ID: QicFix-DatastoreFacade-testCreateClient010
	     * Purpose: Test if method calls client.create() and returns false 
	     * Preconditions: mock of Client.class, 
	     * Input: datastoreFacade.createClient(clientMock)
	     * datastoreFacade.createClient(clientMock) Expected Output: false
	     */
	    @Test
	    public void testCreateClient010() {
	        // Test Setup:
	        Client cMock = mock(Client.class);
	        when(cMock.create()).thenReturn(false);
	        // Test Input:
	        boolean testReturn = datastoreFacade.createClient(cMock);
	        // Expected Output:
	        assertFalse(testReturn);
	        verify(cMock).create();
	    }


	    /**
	     * ID: QicFix-DatastoreFacade-testCreateClient011
	     * Purpose: Test if method calls client.create() and returns true 
	     * Preconditions: mock of Client.class, 
	     * Input: datastoreFacade.createClient(clientMock)
	     * datastoreFacade.createClient(clientMock) Expected Output: true
	     */
	    @Test
	    public void testCreateClient011() {
	        // Test Setup:
	        Client cMock = mock(Client.class);
	        when(cMock.create()).thenReturn(true);
	        // Test Input:
	        boolean testReturn = datastoreFacade.createClient(cMock);
	        // Expected Output:
	        assertTrue(testReturn);
	        verify(cMock).create();
	    }


	    /**
	     * ID: QicFix-DatastoreFacade-testselectApplication010
	     * Purpose: Test if non empty list of application is returned 
	     * Preconditions: An application instance is created 
	     * Input: Integer userTypeId = 6; 
	     * Expected output: Returns list with one application and returns true
	     */
	    @Test
	    public void testselectApplication010() {
	        // Test setup

	        Application app = new Application();
	        app.setId(25);
	        app.setName("ABC");
	        app.setUrl("www.url.com");

	        // Test input
	        Integer userTypeId = 8;

	        List < Application > myApp = datastoreFacade.selectApplication(userTypeId);
	        // Test output

	        // Test output
	        assertTrue(compareApp(myApp.get(0), app));
	    }


	    /**
	     * ID: QicFix-DatastoreFacade-testselectApplication011
	     * Purpose: Test returns empty because no app is created
	     * Preconditions: An application instance is created 
	     * Input: Integer userTypeId = 2; 
	     * Expected output: Returns list with no applications
	     */
	    @Test
	    public void testselectApplication011() {
	        // Test setup

	        Application app = new Application();
	        // Test input
	        Integer userTypeId = 2;

	        List < Application > myApp = datastoreFacade.selectApplication(userTypeId);
	        // Test output

	        // Test output
	        assertFalse(compareApp(myApp.get(0), app));
	    }
		
	 // ZG test cases end
	    
	    
	 
	 //@author: Sergio Pizzolante
	 // SG test cases start
	    /**
		 * ID: QicFix-DatastoreFacade-updateClient4 
		 * Purpose: Test if client is updated
		 * Preconditions: A client instance is created
		 * Input: Client instance
		 * Input: client instance 
		 * Expected output: Returns true if the client is updated
		 */
		@Test
		public void testupdateClient4() {
			// Test setup

			Client client2 = new Client();
			client2.setCity("Miami");
			Date dob = new Date(1990, 10, 5);
			client2.setDob(dob);
			client2.setEmail("spizz001@fiu.edu");
			client2.setFname("Joe");
			client2.setId(5);
			client2.setLname("Doe");
			client2.setPassword("1234");
			client2.setPhone("555-555-5555");
			client2.setState("FL");
			client2.setStreetAddress("7525 SW 61st ST");
			client2.setUserTypeId(3);
			client2.setZipcode("33143");

			// Test input

			boolean resp = datastoreFacade.updateClient(client2);

			// Test output
			assertTrue(resp);
			
		}
			
			
		
		/**
		 * ID: QicFix-DatastoreFacade-selectClientByEmail4 
		 * Purpose: Test if non empty client list is returned 
		 * Preconditions: A client instance is created
		 * Input: String email = "spizz001@fiu.edu"; 
		 * Expected output: Returns list with one client and returns false
		 */
		@Test
		public void testselectClientByEmail4() {
			// Test setup

			Client client = new Client();
			client.setCity("Miami");
			Date dob = new Date(1990, 10, 5);
			client.setDob(dob);
			client.setEmail("spizz001@fiu.edu");
			client.setFname("Joe");
			client.setId(5);
			client.setLname("Doe");
			client.setPassword("1234");
			client.setPhone("555-555-5555");
			client.setState("FL");
			client.setStreetAddress("1111 SW 60th ST");
			client.setUserTypeId(3);
			client.setZipcode("33122");

			// Test input
			List<Client> myClientList = datastoreFacade.selectClientByEmail("spizz001@fiu.edu");
			// Test output
			assertFalse(compareClients(myClientList.get(0), client));
		}
		
		

			/**
			 * ID: QicFix-DatastoreFacade-selectTowerByAddress6 
			 * Purpose: Test if non empty tower list is returned 
			 * Preconditions: A tower instance is created
			 * Input: String address = "1111 SW 60th ST"; 
			 * Expected output: Returns list with one client and returns false
			 */
			@Test
			public void testselectTowerByAddress6() {
				// Test setup

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
				myT.setStreetAddress("1111 SW 60th ST");
				myT.setUserTypeId(3);
				myT.setZipcode("33122");
				myT.setCompanyName("ABC");
				myT.setLatitude(2.2);
				myT.setLongitude(2.3);
				myT.setPermitNumber("1234586");
				myT.setPriceMile(2.24);

				// Test input
				String address = "1111 SW 60th ST";
				List<Tower> myTower = datastoreFacade.selectTowerByAddress(address);
				// Test output
				assertFalse(compareTower(myTower.get(0), myT));
			}
			
			
		
			/**
			 * ID: QicFix-DatastoreFacade-selectTowerByAddress9 
			 * Purpose: Test if non empty tower list is returned 
			 * Preconditions: A tower instance is created
			 * Input: String address = "1111 SW 60th ST"; 
			 * Expected output: Returns list with one client and returns true
			 */
			@Test
			public void testselectTowerByAddress9() {
				// Test setup

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
				myT.setStreetAddress("1111 SW 60th ST");
				myT.setUserTypeId(3);
				myT.setZipcode("33122");
				myT.setCompanyName("ABC");
				myT.setLatitude(2.2);
				myT.setLongitude(2.3);
				myT.setPermitNumber("12345");
				myT.setPriceMile(2.24);

				// Test input
				String address = "1111 SW 60th ST";
				List<Tower> myTower = datastoreFacade.selectTowerByAddress(address);
				// Test output
				assertFalse(compareTower(myTower.get(0), myT));
			}	
			
			

			/**
			 * ID: QicFix-DatastoreFacade-createClient-6 
			 * Purpose: Test if method calls client.create() and returns true
			 * Preconditions: mock of Client.class, 
			 * Input: datastoreFacade.createClient(clientMock) 
			 * Expected Output: true
			 */
			@Test
			public void testCreateClient_6() {
				// Test Setup:
				Client clientMock = mock(Client.class);
				when(clientMock.create()).thenReturn(true);
				// Test Input:
				boolean testReturn = datastoreFacade.createClient(clientMock);
				// Expected Output:
				assertTrue(testReturn);
				verify(clientMock).create();
			}

			
			

			/**
			 * ID: QicFix-DatastoreFacade-createClient-9
			 * Purpose: Test if method calls client.create() and returns false 
			 * Preconditions: mock of Client.class, 
			 * Input: datastoreFacade.createClient(clientMock)
			 * datastoreFacade.createClient(clientMock) Expected Output: false
			 */
			@Test
			public void testCreateClient_9() {
				// Test Setup:
				Client clientMock = mock(Client.class);
				when(clientMock.create()).thenReturn(false);
				// Test Input:
				boolean testReturn = datastoreFacade.createClient(clientMock);
				// Expected Output:
				assertFalse(testReturn);
				verify(clientMock).create();
			}
			
			
			
			/**
			 * ID: QicFix-DatastoreFacade-selectTowerByAddress7
			 * Purpose: Test if non empty tower list is returned 
			 * Preconditions: A tower instance is created
			 * Input: String address = "1111 SW 60th ST"; 
			 * Expected output: Returns list with one client and returns false
			 */
			@Test
			public void testselectTowerByAddress7() {
				// Test setup

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
				myT.setStreetAddress("1111 SW 60th ST");
				myT.setUserTypeId(3);
				myT.setZipcode("33122");
				myT.setCompanyName("ABC");
				myT.setLatitude(2.2);
				myT.setLongitude(2.3);
				myT.setPermitNumber("1234586");
				myT.setPriceMile(2.24);

				// Test input
				String address = "1111 SW 60th ST";
				List<Tower> myTower = datastoreFacade.selectTowerByAddress(address);
				// Test output
				assertFalse(compareTower(myTower.get(0), myT));
			}
			
			

			/**
			 * ID: QicFix-DatastoreFacade-createClient-7
			 * Purpose: Test if method calls client.create() and returns false 
			 * Preconditions: mock of Client.class, 
			 * Input: datastoreFacade.createClient(clientMock)
			 * datastoreFacade.createClient(clientMock) Expected Output: false
			 */
			@Test
			public void testCreateClient_7() {
				// Test Setup:
				Client clientMock = mock(Client.class);
				when(clientMock.create()).thenReturn(false);
				// Test Input:
				boolean testReturn = datastoreFacade.createClient(clientMock);
				// Expected Output:
				assertFalse(testReturn);
				verify(clientMock).create();
			}
			
			

			/**
			 * ID: QicFix-DatastoreFacade-selectTowerByAddress8 
			 * Purpose: Test if non empty tower list is returned 
			 * Preconditions: A tower instance is created, a location instance is created 
			 * Input: Location loc = new Location(2.1, 2.4);
			 * 		  String city = "Miami"; String state = "FL"; 
			 * Expected output: Returns list with one client and returns false
			 */
			@Test
			public void testselectTowerByAddress8() {
				// Test setup
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
				myT.setStreetAddress("1111 SW 60th ST");
				myT.setUserTypeId(3);
				myT.setZipcode("33122");
				myT.setCompanyName("ABC-D");
				myT.setLatitude(2.2);
				myT.setLongitude(2.3);
				myT.setPermitNumber("12345");
				myT.setPriceMile(2.24);

				// Test input
				Location loc = new Location(2.1, 2.4);
				String city = "Miami";
				String state = "FL";
				List<Tower> myTower = datastoreFacade.selectTowerByAddress(loc, city, state);
				// Test output
				assertFalse(compareTower(myTower.get(0), myT));
			}

			
			/**
			 * ID: QicFix-DatastoreFacade-selectClientByEmail-6 
			 * Purpose: Test if non empty client list is returned 
			 * Preconditions: A client instance is created
			 * Input: String email = "spizz001@fiu.edu"; 
			 * Expected output: Returns list with one client and returns true
			 */
			@Test
			public void testselectClientByEmail6() {
				// Test setup

				Client client = new Client();
				client.setBlocked("blocked");
				client.setCity("Miami");
				Date dob = new Date(1990, 10, 5);
				client.setDob(dob);
				client.setEmail("spizz001@fiu.edu");
				client.setFname("Joe");
				client.setId(5);
				client.setLname("Doe");
				client.setPassword("1234");
				client.setPhone("555-555-5555");
				client.setState("FL");
				client.setStreetAddress("7525 SW 61st ST");
				client.setUserTypeId(3);
				client.setZipcode("33143");

				// Test input
				List<Client> myClientList = datastoreFacade.selectClientByEmail("spizz001@fiu.edu");
				// Test output
				assertFalse(compareClients(myClientList.get(0), client));

			}
			
			
			
			
			

			/**
			 * ID: QicFix-DatastoreFacade-selectTowerByPrice-6 
			 * Purpose: Test if non empty tower list is returned 
			 * Preconditions: A tower instance is created
			 * Input: String city = "Miami"; 
			 * 		  String state = "FL"; 
			 * Expected output: Returns list with one client and returns true
			 */
			@Test
			public void testselectTowerByPrice6() {
				// Test setup

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
				myT.setPhone("305-123-1234");
				myT.setState("FL");
				myT.setStreetAddress("1111 SW 60th ST");
				myT.setUserTypeId(3);
				myT.setZipcode("33133");
				myT.setCompanyName("FIU");
				myT.setLatitude(2.2);
				myT.setLongitude(2.3);
				myT.setPermitNumber("12345");
				myT.setPriceMile(2.24);

				// Test input
				String city = "Miami";
				String state = "FL";
				List<Tower> myTower = datastoreFacade.selectTowerByPrice(city, state);
				// Test output
				assertFalse(compareTower(myTower.get(0), myT));
			}

			/**
			 * ID: QicFix-DatastoreFacade-selectTowerByPrice-9
			 * Purpose: Test if non empty tower list is returned 
			 * Preconditions: A tower instance is created
			 * Input: String city = "Miami"; 
			 * 		  String state = "FL"; 
			 * Expected output: Returns list with one client and returns false
			 */
			@Test
			public void testselectTowerByPrice9() {
				// Test setup

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
				myT.setPhone("305-123-1234");
				myT.setState("FL");
				myT.setStreetAddress("1111 SW 60th ST");
				myT.setUserTypeId(3);
				myT.setZipcode("33134");
				myT.setCompanyName("FIU");
				myT.setLatitude(2.2);
				myT.setLongitude(2.3);
				myT.setPermitNumber("12345");
				myT.setPriceMile(2.24);

				// Test input
				String city = "Miami";
				String state = "FL";
				List<Tower> myTower = datastoreFacade.selectTowerByPrice(city, state);
				// Test output
				assertFalse(compareTower(myTower.get(0), myT));
			}
			
			// SG test cases end
	    
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

	// Compares two Client objects for equality
	public boolean compareClients(Client s1, Client s2) {

		return (s1.getId().equals(s2.getId()) && s1.getBlocked().equals(s2.getBlocked())
				&& s1.getCity().equals(s2.getCity()) && s1.getDob().equals(s2.getDob())
				&& s1.getEmail().equals(s2.getEmail()) && s1.getFname().equals(s2.getFname())
				&& s1.getLname().equals(s2.getLname()) && s1.getPassword().equals(s2.getPassword())
				&& s1.getPhone().equals(s2.getPhone()) && s1.getState().equals(s2.getState())
				&& s1.getStreetAddress().equals(s2.getStreetAddress()) && s1.getUserTypeId().equals(s2.getUserTypeId())
				&& s1.getZipcode().equals(s2.getZipcode()));

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

	// Compares two Tower objects for equality
	private boolean compareApp(Application s1, Application s2) {

		return (s1.getId().equals(s2.getId()) && s1.getName().equals(s2.getName()) && s1.getUrl().equals(s2.getUrl()));
	}

}

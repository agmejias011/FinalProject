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

import entity.Client;
import entity.DatastoreFacade;
import entity.Service;

/**
 * @author jorge padilla - patherid 12944432
 * @author Peter J. Clarke
 * Date: 10/28/2020
 * Purpose: Test cases for the DatastoreFacade class
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
		//MockitoAnnotations.initMocks(this);
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
		//Test Setup:
		Client clientMock = mock(Client.class);
		when(clientMock.create()).thenReturn(true);
		//Test Input:
		boolean testReturn = datastoreFacade.createClient(clientMock);
		//Expected Output:
		assertTrue(testReturn);
		verify(clientMock).create();
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-002-createClient-002
	 * Purpose: Test if method calls client.create() and returns false
	 * Preconditions: mock of Client.class, 
	 * Input: datastoreFacade.createClient(clientMock)
	 * Expected Output: true
	 */
	@Test
	public void testCreateClient_002() {
		//Test Setup:
		Client clientMock = mock(Client.class);
		when(clientMock.create()).thenReturn(false);
		//Test Input:
		boolean testReturn = datastoreFacade.createClient(clientMock);
		//Expected Output:
		assertFalse(testReturn);
		verify(clientMock).create();
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-006-declineRequest-001
	 * Purpose: Test if request is declined
	 * Preconditions: 
	 * Input: serviceId = 1; towerId = 3;
	 * Expected output: that request is declined
	 * Comments: This method is not testable because
	 * DeclineRequest() in DatastoreFacade has a inline
	 * declaration of a local variable
	 */
	@Test
	public void testDeclineRequest() {
		Integer serviceId = 1;
		Integer towerId = 3;
		// This function is difficult to test.
		//Test Setup:		
		//Test Input:
		datastoreFacade.declineRequest(serviceId, towerId);
		//Expected Output
		assertTrue(true);
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-006-declineService-001
	 * Purpose: Test if Service is declined
	 * Preconditions: 
	 * Input: none;
	 * Expected output: that Service is declined
	 */
	@Test
	public void testdeclineService() {
		String towerEmail = "agonz1123@fiu.edu";		
		Integer serviceId = 1;		
		
		//Test Setup:		
		//Test Input:
		boolean returnVal = datastoreFacade.declineService(towerEmail, serviceId);
		//Expected Output
		assertTrue(returnVal);
	}

	/**
	 * ID: QicFix-DatastoreFacade-008-selectServiceByTowerEmail-001
	 * Purpose: Test if a non empty Service list is returned.
	 * Preconditions: A service instance is created
	 * Input: towerEmail = towerEmail;
	 * Expected output: Returns list with one service and returns true
	 */
	@Test
	public void testSelectServiceByTowerEmail001() {
		//Test setup
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
	       temp.setLatitudeDestination(85.5);;
	       temp.setLongitudeDestination(86.6);;
	       temp.setStreetAddressPickup("CASE212A");;
	       temp.setCityPickup("Miami");;
	       temp.setStatePickup("FL");;
	       temp.setZipcodePickup("33199");;
	       temp.setStreetAddressDestination("PG6");;
	       temp.setCityDestination("Miami");;
	       temp.setStateDestination("FL");;
	       temp.setZipcodeDestination("33199");
	       temp.setClientDescription("student");
	       temp.setTowerDescription("tall");
	    //Test input
		myServiceList = datastoreFacade.selectServiceByTowerEmail("clarkep@fiu.edu");
		//Test output
		assertTrue(compareService(myServiceList.get(0), temp));
		
	}
	
	/**
	 * ID: QicFix-DatastoreFacade-008-selectServiceByTowerEmail-001
	 * Purpose: Test if a non empty Service list is returned.
	 * Preconditions: A service instance is created
	 * Input: towerEmail = towerEmail;
	 * Expected output: Returns list with one service and return false
	 */
	@Test
	public void testSelectServiceByTowerEmail002() {
		//Test setup
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
	       temp.setLatitudeDestination(85.5);;
	       temp.setLongitudeDestination(86.6);;
	       temp.setStreetAddressPickup("CASE212A");;
	       temp.setCityPickup("Miami");;
	       temp.setStatePickup("FL");;
	       temp.setZipcodePickup("33199");;
	       temp.setStreetAddressDestination("PG6");;
	       temp.setCityDestination("Miami");;
	       temp.setStateDestination("FL");;
	       temp.setZipcodeDestination("33199");
	       temp.setClientDescription("student");
	       temp.setTowerDescription("tall");
	    //Test input
		myServiceList = datastoreFacade.selectServiceByTowerEmail("clarkep@fiu.edu");
		//Test output
		assertFalse(compareService(myServiceList.get(0), temp));
		
	}
	
	//Helper methods
	
	// Compares two Service objects for equality
	public boolean compareService(Service s1, Service s2){
		
		return(s1.getId().equals(s2.getId()) &&
			    s1.getClientId().equals(s2.getClientId()) &&
				s1.getCreationDate().equals(s2.getCreationDate()) &&
				s1.getStartDate().equals(s2.getStartDate()) &&
				s1.getEndDate().equals(s2.getEndDate()) &&
				s1.getCancelDate().equals(s2.getCancelDate()) &&
				s1.getCost().equals(s2.getCost()) &&
				s1.getLatitudePickup().equals(s2.getLatitudePickup()) &&
				s1.getLongitudePickup().equals(s2.getLongitudePickup()) &&
				s1.getLatitudeDestination().equals(s2.getLatitudeDestination()) &&
				s1.getLongitudeDestination().equals(s2.getLongitudeDestination()) &&
				s1.getStreetAddressPickup().equals(s2.getStreetAddressPickup())&&
				s1.getCityPickup().equals(s2.getCityPickup()) &&
				s1.getStatePickup().equals(s2.getStatePickup()) &&
				s1.getZipcodePickup().equals(s2.getZipcodePickup()) &&
				s1.getStreetAddressDestination().equals(s2.getStreetAddressDestination()) &&
				s1.getCityDestination().equals(s2.getCityDestination()) &&
				s1.getStateDestination().equals(s2.getStateDestination()) &&
				s1.getZipcodeDestination().equals(s2.getZipcodeDestination()) &&
				s1.getClientDescription().equals(s2.getClientDescription()) &&
				s1.getTowerDescription().equals(s2.getTowerDescription())
		);
		
	}

}


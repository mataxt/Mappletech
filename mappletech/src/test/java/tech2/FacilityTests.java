package tech2;

import model.Facility;
import model.Reservation;
import testdao.FacilityDAO;

import java.util.List;
import junit.framework.TestCase;

public class FacilityTests extends TestCase {
	
	Facility facility;
	Reservation reservation;
	List<Reservation> reservations;
	
	// assigning the values
	protected void setUp() {
		facility = new Facility();
		reservation = new Reservation();
		
		facility.setFacilityId(1);
		facility.setFacilityName("FacilityName");
		facility.setDescription("FacilityDesc");
		facility.setLocation("FacilityLoc");
		facility.setAvailable(true);
		facility.setReservations(reservations);
	}
	
	protected void tearDown() {
		
	}
	
	public void testAddRemove() throws Exception {
		try {
			assertTrue(FacilityDAO.addFacility(facility));
			assertTrue(FacilityDAO.removeFacility(facility));
			System.out.println("Success");
		} catch (AssertionError e) {
			System.out.println("Failed at testAddRemove");
			throw e;
		}
	}
	
	public void testFetch() throws Exception {
		int facilityId = facility.getFacilityId();
		if (FacilityDAO.addFacility(facility)) {
			try {
				Facility tmp = FacilityDAO.fetchFacility(facilityId);
				assertTrue(tmp != null);
				assertEquals(facility.getFacilityName(), tmp.getFacilityName());
				assertEquals(facility.getDescription(), tmp.getDescription());
				assertEquals(facility.getLocation(), tmp.getLocation());
				assertEquals(facility.getReservations(), tmp.getReservations());
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at facility testFetch");
				throw e;
			} finally {
				FacilityDAO.removeFacility(facility);
			}
		} else {
			throw new Exception("Failed to add facility in facility fetch test");
		}
	}
	
	public void testGetAllFacilities() {
		facility.setFacilityName("testGetAllFacilities");
		Facility facility2 = new Facility();
		facility2.setFacilityName("Facility2Test");
		facility2.setDescription("desc2");
		if (FacilityDAO.addFacility(facility) && FacilityDAO.addFacility(facility2)) {
			try {
				assertEquals(2, FacilityDAO.getAllFacilities().size());
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testGettAllFacilities");
				throw e;
			} finally {
				FacilityDAO.removeFacility(facility);
				FacilityDAO.removeFacility(facility2);
			}
		}
	}


	public void testChangeEvent() {
		try {
			System.out.println("Success");
		} catch (AssertionError e) {
			System.out.println("Failed at facility testChangeEvent");
			throw e;
		} finally {
			FacilityDAO.removeFacility(facility);
		}
	}
}
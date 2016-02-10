package tech2;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import model.Report;
import model.User;
import testdao.ReportDAO;
import testdao.UserDAO;

public class ReportTests extends TestCase {

	Report report1;
	User user;
	Integer reportId;

	// assigning the values
	protected void setUp() {
		
		user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setFullName("fullname");
		user.setEmail("email");
		user.setAddress("address");
		user.setPrivilege(0);
		user.setPhoneNumber("phonenumber");
		
		UserDAO.addUser(user);
		
		report1 = new Report();
		report1.setReporter(user);
		report1.setReason("reason1");
		report1.setDescription("description1");
		report1.setStatus("status1");
		report1.setDate(Date.valueOf("2001-10-10"));
		
	}
	
	public void testConnect() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			System.out.println("Failed at testConnect");
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
	}

	public void testAdd() {
		try {
			assertTrue(ReportDAO.addReport(report1));
			System.out.println("Success add report");
		} catch (AssertionError e) {
			System.out.println("Failed at testAdd report");
			throw e;
		} finally {
			reportId = report1.getReportId(); 
			//UserDAO.removeUser(user);
		}
	}
	
	public void testFetchReport() {
		try {
			System.out.println("asdasd"+reportId);
			Report tmpReport = new Report();
			tmpReport = ReportDAO.fetchReport(reportId);
			
			assertTrue(tmpReport != null);
			assertEquals(report1.getReportId(), tmpReport.getReportId());
			assertEquals(report1.getReporter(), tmpReport.getReporter());
			assertEquals(report1.getReason(), tmpReport.getReason());
			assertEquals(report1.getDescription(), tmpReport.getDescription());
			assertEquals(report1.getStatus(), tmpReport.getStatus());
			assertEquals(report1.getDate(), tmpReport.getDate());
			
			System.out.println("Success fetch report");
		} catch (AssertionError e) {
			System.out.println("Failed at testFetchReport");
			throw e;
		} finally {
		
		}
		
	}	
	
	public void testGetAllReports() {
		
		try {
			assertTrue(ReportDAO.getAllReports() != null);
			System.out.println("Success getAllReports");
		} catch (AssertionError e) {
			System.out.println("Failed at testFetchReport");
			throw e;
		} finally {
			
		}
		
	}
	
	
	public void testChangeReport() {
		String newValue = "testNewValue";
		try {
			Report tmpReport=new Report();
			
			
			assertTrue(ReportDAO.changeReport(report1, newValue, "reason"));
			tmpReport = ReportDAO.fetchReport(4);
			assertTrue(tmpReport != null);
			
			assertEquals(report1.getReportId(), tmpReport.getReportId());
			assertEquals(report1.getReporter(), tmpReport.getReporter());
			assertEquals(report1.getReason(), tmpReport.getReason());
			assertEquals(report1.getDescription(), tmpReport.getDescription());
			assertEquals(report1.getStatus(), tmpReport.getStatus());
			assertEquals(report1.getDate(), tmpReport.getDate());
			
			System.out.println("Success");
		} catch (AssertionError e) {
			System.out.println("Failed at testChangeReport");
			throw e;
		} finally {
			
		}	
	}
	
	public void testRemove() {
		try {
			
			assertTrue(ReportDAO.removeReport(report1));
			System.out.println("Success remove reports");
		} catch (AssertionError e) {
			System.out.println("Failed at testRemove");
			throw e;
		} finally {
			//UserDAO.removeUser(user); 
		}
	}
}

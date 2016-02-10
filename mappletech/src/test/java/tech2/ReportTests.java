package tech2;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import model.Report;
import model.User;
import testdao.GroupDAO;
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

	protected void tearDown() {
		UserDAO.removeUser(user);
	}

	public void testAddRemove() {
		try {
			report1.setReportId(ReportDAO.addReport(report1));
			assertTrue(report1.getReportId() != null);
			assertTrue(ReportDAO.removeReport(report1));
			System.out.println("Success add and remove report");
		} catch (AssertionError e) {
			System.out.println("Failed at testAddRemove report");
			throw e;
		}
	}

	public void testFetchReport() {
		report1.setReportId(ReportDAO.addReport(report1));
		if (report1.getReportId() != null) {
			try {
				System.out.println("asdasd" + report1.getReportId());
				Report tmpReport = new Report();
				tmpReport = ReportDAO.fetchReport(report1.getReportId());

				assertTrue(tmpReport != null);
				assertEquals(report1.getReportId(), tmpReport.getReportId());
				assertEquals(report1.getReporter().getUsername(), tmpReport.getReporter().getUsername());
				assertEquals(report1.getReason(), tmpReport.getReason());
				assertEquals(report1.getDescription(), tmpReport.getDescription());
				assertEquals(report1.getStatus(), tmpReport.getStatus());
				assertEquals(report1.getDate(), tmpReport.getDate());

				System.out.println("Success fetch report");
			} catch (AssertionError e) {
				System.out.println("Failed at testFetchReport");
				throw e;
			} finally {
				ReportDAO.removeReport(report1);
			}

		}
	}

	public void testGetAllReports() {
		Report report2 = new Report();
		report2.setReporter(user);
		report2.setReason("reason2");
		report2.setDescription("description2");
		report2.setStatus("status2");
		report2.setDate(Date.valueOf("2001-10-10"));
		if (ReportDAO.addReport(report1) != null && ReportDAO.addReport(report2) != null) {
			try {
				assertEquals(2, ReportDAO.getAllReports().size());
				System.out.println("Success getAllReports");
			} catch (AssertionError e) {
				System.out.println("Failed at testFetchReport");
				throw e;
			} finally {
				ReportDAO.removeReport(report1);
				ReportDAO.removeReport(report2);
			}
		}

	}

	public void testChangeReport() {
		String newValue = "testNewValue";
		report1.setReportId(ReportDAO.addReport(report1));
		if (report1.getReportId() != null) {
			try {
				Report tmpReport = new Report();
				report1.setReason(newValue);
				assertTrue(ReportDAO.changeReport(report1));
				tmpReport = ReportDAO.fetchReport(report1.getReportId());
				assertTrue(tmpReport != null);
	
				assertEquals(report1.getReportId(), tmpReport.getReportId());
				assertEquals(report1.getReporter().getUsername(), tmpReport.getReporter().getUsername());
				assertEquals(report1.getDescription(), tmpReport.getDescription());
				assertEquals(report1.getStatus(), tmpReport.getStatus());
				assertEquals(report1.getDate(), tmpReport.getDate());
	
				System.out.println("Success");
			} catch (AssertionError e) {
				System.out.println("Failed at testChangeReport");
				throw e;
			} finally {
				ReportDAO.removeReport(report1);
			}
		}
	}
}

package testdao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Report;
import model.User;

/**
 * The Class ReportDAO.
 */
public class ReportDAO {

	/**
	 * Adds the Report.
	 *
	 * @param Report
	 *            model.Report
	 * @return true, if successful
	 */
	public static Integer addReport(Report report) {
		Integer id = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(report);
			em.getTransaction().commit();
			id = report.getReportId();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return id;
	}

	public static List<Report> getAllReports() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		List<Report> reports = new ArrayList<Report>();
		try {
			em.getTransaction().begin();
			reports = em.createQuery("from Report", Report.class).getResultList();
			em.getTransaction().commit();

		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}

		return reports;
	}

	/**
	 * Gets the Report info.
	 *
	 * @param Reportname
	 *            of the Report
	 * @return model.Report
	 */
	public static Report fetchReport(Integer reportId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		Report report = null;
		try {
			report = em.find(Report.class, reportId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return report;
	}

	/**
	 * Change Report.
	 *
	 * @param Report
	 *            to be changed
	 * @param value
	 *            Username of user or description string
	 * @param operation
	 *            {"host", "description", "add", "remove}
	 * @return true, if successful
	 */
	public static boolean changeReport(Report Report, String value, String operation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Report u = em.find(Report.class, Report.getReportId());
			if (u != null) {
				switch (operation) {
				case "date":
					u.setDate(Date.valueOf(value));
					success = true;
					break;
				case "description":
					u.setDescription(value);
					success = true;
					break;
				case "reason":
					u.setReason(value);
					success = true;
					break;
				case "reporter":
					u.setReporter(new User(value));
					success = true;
					break;
				case "status":
					u.setStatus(value);
					success = true;
					break;
				default:
					break;
				}
				em.merge(u);
				em.getTransaction().commit();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return success;
	}

	/**
	 * Removes the Report.
	 *
	 * @param model
	 *            .Report
	 * @return true, if successful
	 */
	public static boolean removeReport(Report report) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Report u = em.find(Report.class, report.getReportId());
			if (u != null) {
				em.remove(u);
				success = true;
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return success;
	}

}
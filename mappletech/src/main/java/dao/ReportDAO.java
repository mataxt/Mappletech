package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Report;

/**
 * The Class ReportDAO.
 */
public class ReportDAO {

	/**
	 * Adds the Report.
	 *
	 * @param Report
	 *            model.Report
	 * @return id, if successful otherwise null
	 */
	public static Integer addReport(Report report) {
		Integer id = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
	public static Report fetchReport(String reportname) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		Report report = null;
		try {
			report = em.find(Report.class, reportname);
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
	public static boolean changeReport(Report report) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Report u = em.find(Report.class, report.getReportId());
			if (u != null) {
				u.setDate(report.getDate());
				u.setDescription(report.getDescription());
				u.setReason(report.getReason());
				u.setReporter(report.getReporter());
				u.setStatus(report.getStatus());
				em.merge(u);
				success = true;
			}

			em.getTransaction().commit();
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
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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
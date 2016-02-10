package testdao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 */
public class UserDAO {

	/**
	 * Adds the user.
	 *
	 * @param user
	 *            model.user
	 * @return true, if successful
	 */
	public static boolean addUser(User user) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			registered = true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			registered = false;
		} finally {
			if (em != null) {
				em.close();
			}
			if (emf != null) {
				emf.close();
			}
		}
		return registered;
	}

	public static List<User> getAllUsers() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		List<User> users = new ArrayList<User>();
		try {
			em.getTransaction().begin();
			users = em.createQuery("from User", User.class).getResultList();
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

		return users;
	}

	/**
	 * Gets the user info.
	 *
	 * @param username
	 *            of the user
	 * @return model.User
	 */
	public static User fetchUser(String username) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		User user = null;
		try {
			user = em.find(User.class, username);
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
		return user;
	}

	/**
	 * Gets the user info.
	 *
	 * @param username
	 *            of the user
	 * @return model.User
	 */
	public static User confirmUser(String username, String password) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		User u = null;
		try {
			String query = "from User where username = ?1 and password = ?2";
			u = em.createQuery(query, User.class).setParameter(1, username)
					.setParameter(2, password).getSingleResult();
		} catch (NoResultException e) {
			System.out.println("not found");
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
		return u;
	}

	/**
	 * Change user.
	 *
	 * @param user to be changed
	 * @param value
	 *            to be changed into OBS: for "privilege" operation use integers
	 *            as String e.g. "1" or "3"
	 * @param operation
	 *            {"password", "fullname", "email", "phonenumber","address",
	 *            "privilege"}
	 * @return true, if successful
	 */
	public static boolean changeUser(User user) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, user.getUsername());
			if (u != null) {
				u.setPassword(user.getPassword());
				u.setFullName(user.getFullName());
				u.setAddress(user.getAddress());
				u.setPhoneNumber(user.getPhoneNumber());
				u.setEmail(user.getEmail());
				u.setPrivilege(user.getPrivilege());
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
	 * Removes the user.
	 *
	 * @param model.User 
	 * @return true, if successful
	 */
	public static boolean removeUser(User user) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("TestPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, user.getUsername());
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
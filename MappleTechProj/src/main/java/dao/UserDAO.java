package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
	 * @param user model.user
	 * @return true, if successful
	 */
	public static boolean addUser(User user) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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

	/**
	 * Gets the user info.
	 *
	 * @param username of the user
	 * @return model.User
	 */
	public static User fetchUser(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
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

//	/**
//	 * Gets the user info.
//	 *
//	 * @param username of the user
//	 * @return model.User
//	 */
//	public static ArrayList<User> getAllUsers(String username) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
//		EntityManager em = emf.createEntityManager();
//		User user = null;
//		try {
//			user = em.find(User.class, username);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (em != null) {
//				em.close();
//			}
//			if (emf != null) {
//				emf.close();
//			}
//		}
//		return user;
//	}

	
	/**
	 * Change user.
	 *
	 * @param user to be changed
	 * @param value to be changed into OBS: for "privilege" operation use integers as String e.g. "1" or "3"
	 * @param operation {"username", "password", "fullname", "email", "phonenumber","address", "privilege"}
	 * @return true, if successful
	 */
	public static boolean changeUser(User user, String value, String operation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, user.getUserName());
			if (u != null) {
				switch (operation) {
				case "username":
					u.setUserName(value);
					break;
				case "password":
					u.setPassWord(value);
					break;
				case "fullname":
					u.setFullName(value);
					break;
				case "address":
					u.setAdress(value);
					break;
				case "phonenumber":
					u.setPhoneNumber(value);
					break;
				case "email":
					u.setEmail(value);
					break;
				case "privilege":
					u.setPrivilege(Integer.parseInt(value));
					break;
				default:
					break;
				}
				em.merge(u);
				em.flush();
				em.getTransaction().commit();
				success = true;
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
	 * Removes the user.
	 *
	 * @param model.User
	 * @return true, if successful
	 */
	public static boolean removeUser(User user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			User u = em.find(User.class, user.getUserName());
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

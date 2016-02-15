package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Group;
import model.User;

/**
 * The Class GroupDAO.
 */
public class GroupDAO {

	/**
	 * Adds the Group.
	 *
	 * @param Group
	 *            model.Group
	 * @return true, if successful
	 */
	public static boolean addGroup(Group group) {
		boolean registered = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(group);
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

	public static List<Group> getAllGroups() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Group> groups = new ArrayList<Group>();
		try {
			em.getTransaction().begin();
			groups = em.createQuery("from Group", Group.class).getResultList();
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

		return groups;
	}

	/**
	 * Gets the Group info.
	 *
	 * @param Groupname
	 *            of the Group
	 * @return model.Group
	 */
	public static Group fetchGroup(String groupname) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		Group group = null;
		try {
			group = em.find(Group.class, groupname);
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
		return group;
	}

	/**
	 * Change Group.
	 *
	 * @param Group
	 *            to be changed
	 * @param value
	 *            Username of user or description string
	 * @param operation
	 *            {"host", "description", "add", "remove}
	 * @return true, if successful
	 */
	public static boolean changeGroup(Group Group, String value, String operation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Group g = em.find(Group.class, Group.getGroupName());
			if (g != null) {
				switch (operation) {
				case "host":
					User u = em.find(User.class, value);
					g.setHost(u);
					success = true;
					break;
				case "description":
					g.setDescription(value);
					success = true;
					break;
				case "add":
					List<User> uAdd = g.getUsers();
					User u2 = em.find(User.class, value);
					if (uAdd.add(u2)) {
						g.setUsers(uAdd);
						success = true;
					}
					break;
				case "remove":
					List<User> uRem = g.getUsers();
					User u3 = em.find(User.class, value);
					if (uRem.remove(u3)) {
						g.setUsers(uRem);
						success = true;
					}
					break;
				default:
					break;
				}
				em.merge(g);
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
	 * Removes the Group.
	 *
	 * @param model
	 *            .Group
	 * @return true, if successful
	 */
	public static boolean removeGroup(Group group) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Group u = em.find(Group.class, group.getGroupName());
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

	public static List<Group> getAllGroups(String username) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		List<Group> groups = new ArrayList<Group>();
		try {
			em.getTransaction().begin();
			User usr = em.find(User.class, username);
			groups = em.createQuery("from Group where host = ?1", Group.class).setParameter(1, usr).getResultList();
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

		return groups;
	}

}
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
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("UserPU");
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
		List<Group> Groups = new ArrayList<Group>();
		try {
			em.getTransaction().begin();
			Groups = em.createQuery("from Group", Group.class).getResultList();
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

		return Groups;
	}

	/**
	 * Gets the Group info.
	 *
	 * @param Groupname
	 *            of the Group
	 * @return model.Group
	 */
	public static Group fetchGroup(String Groupname) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		Group Group = null;
		try {
			Group = em.find(Group.class, Groupname);
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
		return Group;
	}

	/**
	 * Change Group.
	 *
	 * @param Group
	 *            to be changed
	 * @param operation
	 *            {"host", "description", "users"}
	 * @return true, if successful
	 */
	@SuppressWarnings("unchecked")
	public static boolean changeGroup(Group Group, Object value, String operation) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Group u = em.find(Group.class, Group.getGroupName());
			if (u != null) {
				switch (operation) {
				case "host":
					u.setHost(new User((String)value));
					break;
				case "description":
					u.setDescription((String)value);
					break;
				case "users":
					u.setUsers((List<User>) value); 
					break;
				default:
					break;
				}
				em.merge(u);
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
	 * Removes the Group.
	 *
	 * @param model
	 *            .Group
	 * @return true, if successful
	 */
	public static boolean removeGroup(Group Group) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserPU");
		EntityManager em = emf.createEntityManager();
		boolean success = false;
		try {
			em.getTransaction().begin();
			Group u = em.find(Group.class, Group.getGroupName());
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
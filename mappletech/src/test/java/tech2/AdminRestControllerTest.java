//package tech2;
//
//import org.springframework.web.client.RestTemplate;
//import testdao.UserDAO;
//import vm.UserVM;
//import junit.framework.TestCase;
//import model.User;
//
//public class AdminRestControllerTest extends TestCase {
//
//	User user;
//	User user2;
//	
//	// assigning the values
//	protected void setUp() {
//		user = new User();
//		user.setUsername("testFetchUserThatDoesntExist");
//		user.setPassword("password");
//		user.setFullName("fullname");
//		user.setEmail("email");
//		user.setAddress("address");
//		user.setPrivilege(0);
//		user.setPhoneNumber("phonenumber");
//		
//		user2 = new User();
//		user2.setUsername("testFetchUserThatExists");
//		user2.setPassword("password");
//		user2.setFullName("fullname");
//		user2.setEmail("email");
//		user2.setAddress("address");
//		user2.setPrivilege(0);
//		user2.setPhoneNumber("phonenumber");
//	}
//	
//	protected void tearDown() {
//		UserDAO.removeUser(user);
//		
//	}
//
//	
//	/**
//	 * We don't add any user in this method. Therefore there shouldn't exist a user and 
//	 * the expected value should be false from the AdminRestController method 
//	 */
//	public void testFetchUserThatDoesntExist() {
//		
//		try {
//			final String URI = "http://130.237.84.211:8080/mappletech/rest/administrator/anvandare/nyanvandare";
//			
//			UserVM newUser = new UserVM(user.getUsername(), "testFetchUserNoExist", user.getFullName(), user.getPrivilege());
//			RestTemplate restTemplate = new RestTemplate();
//			boolean userNotExist = restTemplate.postForObject(URI, newUser, Boolean.class);
//			
//			assertEquals(true , userNotExist);
//			System.out.println("Success");
//			
//		} catch(AssertionError e) {
//			System.out.println("Failed at testFetchUserThatDoesntExist");
//			throw e;
//		} finally {
//			UserDAO.removeUser(user);
//		}
//
//	}
//	
//	
//	/**
//	 * We add a user in this method. Therefore there should exist a user and 
//	 * the expected value should be true from the AdminRestController method 
//	 * @throws Exception 
//	 */
//	public void testFetchUserThatExists() throws Exception {
//		
//		if(UserDAO.addUser(user2)) {
//			try {	
//				UserVM newUser = new UserVM(user2.getUsername(), "testFetchUserExist", user2.getFullName(), user2.getPrivilege());
//				RestTemplate restTemplate = new RestTemplate();
//				final String URI = "http://130.237.84.211:8080/mappletech/rest/administrator/anvandare/nyanvandare";
//				boolean userNotExist = restTemplate.postForObject(URI, newUser, Boolean.class);
//				
//				assertEquals(false , userNotExist);
//				System.out.println("Success");
//				
//			} catch(AssertionError e) {
//				System.out.println("Failed at testFetchUserThattExists");
//				throw e;
//			} finally {
//				UserDAO.removeUser(user2);
//			}
//		}
//		else {
//			throw new Exception("Failed to add correct user in testFetchUserThatExists");
//		}
//
//	}
//		
//}

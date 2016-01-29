package model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 */
public class User implements Serializable{

	/** The user name. */
	private String userName;
	
	/** The pass word. */
	private String passWord;
	
	/** The full name. */
	private String fullName;
	
	/** The email. */
	private	String email;
	
	/** The phone number. */
	private	String phoneNumber;
	
	/** The adress. */
	private String adress;
	
	/** The privilege. */
	private Integer privilege;
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
		
	}


	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 * @param passWord the pass word
	 * @param fullName the full name
	 * @param email the email
	 * @param phoneNumber the phone number
	 * @param adress the adress
	 * @param privilege the privilege
	 */
	public User(String userName, String passWord, String fullName,
			String email, String phoneNumber, String adress, Integer privilege) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.privilege = privilege;
	}

	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 */
	public User(String userName) {
		super();
		this.userName = userName;
	}
	

	/**
	 * Instantiates a new user.
	 *
	 * @param userName the user name
	 * @param passWord the pass word
	 */
	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}


	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the pass word.
	 *
	 * @return the pass word
	 */
	public String getPassWord() {
		return passWord;
	}

	/**
	 * Sets the pass word.
	 *
	 * @param passWord the new pass word
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the adress.
	 *
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * Sets the adress.
	 *
	 * @param adress the new adress
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	/**
	 * Gets the privilege.
	 *
	 * @return the privilege
	 */
	public Integer getPrivilege() {
		return privilege;
	}

	/**
	 * Sets the privilege.
	 *
	 * @param privilege the new privilege
	 */
	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8735816986597638420L;
	
}

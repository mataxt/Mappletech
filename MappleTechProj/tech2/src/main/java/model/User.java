package model;

import java.io.Serializable;

public class User implements Serializable{

	private String userName;
	private String passWord;
	private String fullName;
	private	String email;
	private	String phoneNumber;
	private String adress;
	private Integer privilege;
	
	public User() {
		
	}


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

	public User(String userName) {
		super();
		this.userName = userName;
	}
	

	public User(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
	
	private static final long serialVersionUID = -8735816986597638420L;
	
}
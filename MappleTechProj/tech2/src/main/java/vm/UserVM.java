package vm;

public class UserVM {

	private String userName;
	private String passWord;
	private String fullName;
	private	String email;
	private	String phoneNumber;
	private String adress;
	private Integer privilege;
	
	public UserVM() {
		
	}

	public UserVM(String userName, String passWord, String fullName,
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
	
	public UserVM(String userName, String fullName,
			String email, String phoneNumber, String adress, Integer privilege) {
		super();
		this.userName = userName;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.adress = adress;
		this.privilege = privilege;
	}

	public UserVM(String userName) {
		super();
		this.userName = userName;
	}
	

	public UserVM(String userName, String passWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
	}


	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	
	public String getAdress() {
		return adress;
	}

	public Integer getPrivilege() {
		return privilege;
	}
}

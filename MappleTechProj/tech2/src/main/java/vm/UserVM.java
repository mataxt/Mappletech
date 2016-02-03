package vm;

public class UserVM {

	private String username;
	private String password;
	private String fullName;
	private	String email;
	private	String phoneNumber;
	private String address;
	private Integer privilege;
	
	public UserVM() {
		
	}

	public UserVM(String username, String password, String fullName,
			String email, String phoneNumber, String address, Integer privilege) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.privilege = privilege;
	}
	
	public UserVM(String username, String fullName,
			String email, String phoneNumber, String adress, Integer privilege) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = adress;
		this.privilege = privilege;
	}

	public UserVM(String username) {
		super();
		this.username = username;
	}
	

	public UserVM(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPrivilege() {
		return privilege;
	}
}


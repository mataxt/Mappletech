package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users", catalog = "mappletech")
public class User implements Serializable {

	private String userName;
	private String passWord;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String mobileNumber;
	private String address;
	private Integer privilege;
	private List<Group> groups;
	private List<Report> reports;
	private List<Reservation> reservations;
	private List<Event> events;

	public User() {

	}

	public User(String userName, String passWord, String mobileNumber, String fullName, String email, String phoneNumber, String address,
			Integer privilege, List<Group> groups, List<Report> reports, List<Reservation> reservations,
			List<Event> events) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.mobileNumber = mobileNumber;
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.privilege = privilege;
		this.groups = groups;
		this.reports = reports;
		this.reservations = reservations;
		this.events = events;
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

	@Id
	@Column(name = "Username", nullable = false)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "Password", nullable = false)
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name = "FullName", nullable = false)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "Email", nullable = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PhoneNumber", nullable = true)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "MobileNumber", nullable = true)
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "Address", nullable = true)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Privilege", nullable = false)
	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reporter", cascade=CascadeType.ALL)
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "host", cascade = CascadeType.ALL)
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator", cascade = CascadeType.ALL)
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	private static final long serialVersionUID = -8735816986597638420L;

}
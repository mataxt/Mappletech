package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Groups")
public class Group implements Serializable {

	private String groupName;
	private String description;
	private User host;
	private List<User> users = new ArrayList<>();

	public Group() {

	}

	public Group(String groupName, String description, User host, List<User> users) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.host = host;
		this.users = users;
	}

	@Id
	@Column(name = "GroupName", nullable = false)
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "Description", columnDefinition = "TEXT", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Host", nullable = true)
	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Users_Groups", joinColumns = {
			@JoinColumn(name = "GroupName", nullable = false, updatable = true, referencedColumnName = "GroupName") }, inverseJoinColumns = {
					@JoinColumn(name = "Username", nullable = false, updatable = true, referencedColumnName = "Username") })
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	private static final long serialVersionUID = 4995712236937229725L;

}

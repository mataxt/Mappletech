package model;

import java.io.Serializable;

public class Group implements Serializable{

	private String groupName;
	private String description;
	private String host;
	
	public Group() {
		
	}
	
	
	public Group(String groupName, String description, String host) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.host = host;
	}


	


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	private static final long serialVersionUID = 4995712236937229725L;

}

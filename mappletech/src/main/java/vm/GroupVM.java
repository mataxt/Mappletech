package vm;

import java.util.List;

public class GroupVM {

	private String groupName;
	private String description;
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHost(UserVM host) {
		this.host = host;
	}

	private UserVM host;
	private List<UserVM> members;
	public GroupVM() {

	}

	public GroupVM(String groupName, String description, UserVM host,List<UserVM> members) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.host = host;
		this.members = members;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getDescription() {
		return description;
	}

	public UserVM getHost() {
		return host;
	}

	public List<UserVM> getMembers() {
		return members;
	}

	public void setMembers(List<UserVM> members) {
		this.members = members;
	}
}

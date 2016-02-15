package vm;

public class GroupVM {

	private String groupName;
	private String description;
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHost(String host) {
		this.host = host;
	}

	private String host;

	public GroupVM() {

	}

	public GroupVM(String groupName, String description, String host) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.host = host;
	}

	public String getGroupName() {
		return groupName;
	}

	public String getDescription() {
		return description;
	}

	public String getHost() {
		return host;
	}
}

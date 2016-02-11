package vm;

public class FacilityVM {

	private Integer facilityId;
	private String facilityName;
	private String description;
	private String location;
	private Boolean available;

	public FacilityVM() {

	}

	public FacilityVM(Integer facilityId, String facilityName, String description, String location, Boolean available) {
		super();
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.description = description;
		this.location = location;
		this.available = available;
	}

	public Integer getFacilityId() {
		return facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Boolean getAvailable() {
		return available;
	}
}

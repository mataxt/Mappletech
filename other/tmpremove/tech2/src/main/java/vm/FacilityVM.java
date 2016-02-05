package vm;

public class FacilityVM {
	
	private Integer facilityId;
	private String facilityName;
	private String description;
	private	String location;
	private Integer available;
	
	public FacilityVM() {
		
	}
	
	public FacilityVM(Integer facilityId, String facilityName,
			String description, String location, Integer available) {
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

	public Integer getAvailable() {
		return available;
	}
}

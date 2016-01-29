package model;

import java.io.Serializable;

public class Facility implements Serializable {

	private Integer facilityId;
	private String facilityName;
	private String description;
	private	String location;
	private Integer available;
	
	public Facility() {
		
	}
	
	public Facility(Integer facilityId, String facilityName,
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

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}

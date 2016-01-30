package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Facilities", catalog = "mappletech")
public class Facility implements Serializable {
	private Integer facilityId;
	private String facilityName;
	private String description;
	private	String location;
	private Boolean available;
	private List<Reservation> reservations;
	
	public Facility() {
		
	}
	
	public Facility(Integer facilityId, String facilityName,
			String description, String location, Boolean available,List<Reservation> reservations) {
		super();
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.description = description;
		this.location = location;
		this.available = available;
		this.reservations = reservations;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FacilityId", nullable = false)
	public Integer getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Integer facilityId) {
		this.facilityId = facilityId;
	}
	
	@Column(name = "FacilityName", nullable = false)
	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}
	@Column(name = "Description",columnDefinition = "TEXT", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "Location", nullable = true)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@Column(name = "Available", nullable = false)
	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facilityID",cascade=CascadeType.ALL)
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	private static final long serialVersionUID = 1L;
	

}

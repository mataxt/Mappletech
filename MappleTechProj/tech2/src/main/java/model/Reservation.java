package model;

import java.io.Serializable;
import java.sql.Date;

public class Reservation implements Serializable {
	
	private Integer reservationId;
	private String host;
	private Integer facilityID;
	private Date timeFrom;
	private Date timeTo;
	
	public Reservation() {
		
	}
	
	
	
	
	
	public Reservation(Integer reservationId, String host, Integer facilityID,
			Date timeFrom, Date timeTo) {
		super();
		this.reservationId = reservationId;
		this.host = host;
		this.setFacilityID(facilityID);
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	

	public Integer getReservationId() {
		return reservationId;
	}


	


	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}





	public String getHost() {
		return host;
	}





	public void setHost(String host) {
		this.host = host;
	}





	public Date getTimeFrom() {
		return timeFrom;
	}





	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}





	public Date getTimeTo() {
		return timeTo;
	}





	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}



	public Integer getFacilityID() {
		return facilityID;
	}





	public void setFacilityID(Integer facilityID) {
		this.facilityID = facilityID;
	}



	private static final long serialVersionUID = -8197501379045971105L;
}

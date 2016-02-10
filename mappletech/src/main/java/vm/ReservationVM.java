package vm;

import java.sql.Date;

public class ReservationVM {

	private Integer reservationId;
	private String host;
	private Integer facilityID;
	private Date timeFrom;
	private Date timeTo;
	private String title;
	
	public ReservationVM() {
		
	}
	
	public ReservationVM(Integer reservationId, String host, Integer facilityID,
			Date timeFrom, Date timeTo, String title) {
		super();
		this.reservationId = reservationId;
		this.host = host;
		this.facilityID = facilityID;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.setTitle(title);
	}

	

	public Integer getReservationId() {
		return reservationId;
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

	public Date getTimeTo() {
		return timeTo;
	}


	public Integer getFacilityID() {
		return facilityID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

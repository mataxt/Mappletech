package vm;

import java.sql.Date;

public class ReservationVM {

	private Integer reservationId;
	private String host;
	private Integer facilityID;
	private Date timeFrom;
	private Date timeTo;
	
	public ReservationVM() {
		
	}
	
	public ReservationVM(Integer reservationId, String host, Integer facilityID,
			Date timeFrom, Date timeTo) {
		super();
		this.reservationId = reservationId;
		this.host = host;
		this.facilityID = facilityID;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public ReservationVM(String host, Integer facilityID,
			Date timeFrom, Date timeTo) {
		super();
		this.host = host;
		this.facilityID = facilityID;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
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
}

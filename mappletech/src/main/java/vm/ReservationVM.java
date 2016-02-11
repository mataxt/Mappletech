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

		this.reservationId = 0;
		this.host = "";
		this.facilityID = 0;
		this.timeFrom = Date.valueOf("2000-01-01");
		this.timeTo = Date.valueOf("2000-01-01");
		this.title = "";
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	public void setFacilityID(Integer facilityID) {
		this.facilityID = facilityID;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	public String getTitle() {
		title = "Medlem: "+host+" Bokat: "+facilityID+" Tid: "+timeFrom.toString()+" - "+timeTo.toString();
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

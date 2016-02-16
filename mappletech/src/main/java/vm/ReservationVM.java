package vm;

import java.sql.Date;

public class ReservationVM {

	private Integer reservationId;
	private String host;
	private Integer facilityID;
	private String facility;
	private Date timeFrom;
	private Date timeTo;
	private String title;
	
	public ReservationVM() {

		this.reservationId = 0;
		this.host = "";
		this.facility = "";
		this.timeFrom = Date.valueOf("2000-01-01");
		this.timeTo = Date.valueOf("2000-01-01");
		this.title = "";
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	public String getTitle() {
		title = "Medlem: "+host+" Bokat: "+facility+" Tid: "+timeFrom.toString()+" - "+timeTo.toString();
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ReservationVM(Integer reservationId, String host, Integer facilityId,String facility,
			Date timeFrom, Date timeTo) {
		super();
		this.reservationId = reservationId;
		this.host = host;
		this.facilityID = facilityId;
		this.facility = facility;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public ReservationVM(String host, Integer facilityId,String facility,
			Date timeFrom, Date timeTo) {
		super();
		this.host = host;
		this.facilityID = facilityId;
		this.facility = facility;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	public int getReservationId() {
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


	public String getFacility() {
		return facility;
	}

	public Integer getFacilityID() {
		return facilityID;
	}

	public void setFacilityID(Integer facilityID) {
		this.facilityID = facilityID;
	}
}

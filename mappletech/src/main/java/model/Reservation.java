package model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Reservations")
public class Reservation implements Serializable {

	private Integer reservationId;
	private User host;
	private Facility facility;
	private Date timeFrom;
	private Date timeTo;

	public Reservation() {}

	public Reservation(Integer reservationId, User host, Facility facility, Date timeFrom, Date timeTo) {
		super();
		this.reservationId = reservationId;
		this.host = host;
		this.setFacility(facility);
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ReservationId", nullable = false)
	public Integer getReservationId() {
		return reservationId;
	}

	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Host", nullable = false)
	public User getHost() {
		return host;
	}

	public void setHost(User host) {
		this.host = host;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FacilityId", nullable = false)
	public Facility getFacility() {
		return facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}
	

	@Column(name = "TimeFrom",columnDefinition="DATETIME", nullable = false)
	public Date getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(Date timeFrom) {
		this.timeFrom = timeFrom;
	}

	@Column(name = "TimeTo",columnDefinition="DATETIME", nullable = false)
	public Date getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(Date timeTo) {
		this.timeTo = timeTo;
	}

	private static final long serialVersionUID = -8197501379045971105L;
}

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
@Table(name = "Reports", catalog = "mappletech")
public class Report implements Serializable {

	private Integer reportId;
	private User reporter;
	private String reason;
	private String status;
	private Date date;
	private String description;

	public Report() {

	}

	public Report(Integer reportId, User reporter, String reason,String description, String status, Date date) {
		super();
		this.reportId = reportId;
		this.reporter = reporter;
		this.reason = reason;
		this.description = description;
		this.status = status;
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ReportId", nullable = false)
	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Reporter", nullable = true)
	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}
	
	@Column(name = "Reason", nullable = false)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Column(name = "Description",columnDefinition = "TEXT", nullable = true)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "Status", nullable = false)
	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "Date",columnDefinition="DATETIME", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private static final long serialVersionUID = 1L;

}

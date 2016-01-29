package model;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable{
	
	private	Integer reportId;
	private	String	reporter;
	private	String	reason;
	private	String	status;
	private Date 	date;
	
	public Report() {
		
	}

	public Report(Integer reportId, String reporter, String reason,
			String status, Date date) {
		super();
		this.reportId = reportId;
		this.reporter = reporter;
		this.reason = reason;
		this.status = status;
		this.date = date;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

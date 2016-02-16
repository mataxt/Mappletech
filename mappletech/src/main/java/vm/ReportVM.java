package vm;

import java.sql.Date;

public class ReportVM {

	private	Integer reportId;
	private	String	reporter;
	private	String	reason;
	private	String	status;
	private String description;
	private Date 	date;
	
	public ReportVM() {
		
	}

	public ReportVM(Integer reportId, String reporter, String reason,
			String status, Date date, String description) {
		super();
		this.reportId = reportId;
		this.reporter = reporter;
		this.reason = reason;
		this.status = status;
		this.date = date;
		this.description = description;
	}

	public Integer getReportId() {
		return reportId;
	}

	public String getReporter() {
		return reporter;
	}

	public String getReason() {
		return reason;
	}

	
	public String getStatus() {
		return status;
	}

	public Date getDate() {
		return date;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

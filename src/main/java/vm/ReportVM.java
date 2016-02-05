package vm;

import java.sql.Date;

public class ReportVM {

	private	Integer reportId;
	private	String	reporter;
	private	String	reason;
	private	String	status;
	private Date 	date;
	
	public ReportVM() {
		
	}

	public ReportVM(Integer reportId, String reporter, String reason,
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
}

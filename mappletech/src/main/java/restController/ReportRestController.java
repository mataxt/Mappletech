package restController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import dao.ReportDAO;
import model.Report;
import vm.ReportVM;

@Controller
@SessionAttributes("sessUser")
public class ReportRestController {

	
	private final String URI = "http://130.237.84.211:8080/mappletech/rest/";
	
	
	@RequestMapping(value="/report/remove")
	public Boolean removeReport(@RequestBody(required=true) ReportVM reportVM)
	{
		Report report = new Report();
		report.setReportId(reportVM.getReportId());
		return ReportDAO.removeReport(report);
	}
	
	
	@RequestMapping(value="/report/getAllReports")
	public ArrayList<ReportVM> getAllReports()
	{
		List<Report> list=ReportDAO.getAllReports();
		ArrayList<ReportVM> vmList = new ArrayList<>();
	
		for(Report r: list){
			vmList.add(new ReportVM(r.getReportId(),r.getReporter().getFullName(),r.getReason(),r.getStatus(),r.getDate(),r.getDescription()));
		}
		
		return vmList;
	}
	
}

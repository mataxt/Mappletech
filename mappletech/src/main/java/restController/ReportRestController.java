package restController;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.ReportDAO;
import model.Report;
import vm.ReportVM;

@RestController
public class ReportRestController {

	
	@RequestMapping(value="/report/remove")
	public Boolean removeReport(@RequestBody(required=true) ReportVM reportVM)
	{
		Report report = new Report();
		report.setReportId(reportVM.getReportId());
		return ReportDAO.removeReport(report);
	}
	
	
	@RequestMapping(value="/report/getAllReports")
	public List<ReportVM> getAllReports()
	{
		List<Report> list=ReportDAO.getAllReports();
		ArrayList<ReportVM> vmList = new ArrayList<>();
	
		for(Report r: list){
			vmList.add(new ReportVM(r.getReportId(),r.getReporter().getFullName(),r.getReason(),r.getStatus(),r.getDate(),r.getDescription()));
		}
		
		return vmList;
	}
	
}

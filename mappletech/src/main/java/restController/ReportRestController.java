package restController;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.ReportDAO;
import dao.UserDAO;
import model.Report;
import vm.ReportVM;

@RestController
public class ReportRestController {

	@RequestMapping(value = "/report/add")
	public Boolean addReport(@RequestBody(required = true) ReportVM reportVM){
		Report report = new Report();
		report.setReportId(reportVM.getReportId());
		report.setDescription(reportVM.getDescription());
		report.setReason(reportVM.getReason());
		report.setReporter(UserDAO.fetchUser(reportVM.getReporter()));
		report.setDate(reportVM.getDate());
		report.setStatus(reportVM.getStatus());
		int i = ReportDAO.addReport(report);
		if(i != 0)
			return true;
		else
			return false;
	}
	
	@RequestMapping(value = "/report/remove")
	public Boolean removeReport(@RequestBody(required = true) ReportVM reportVM) {
		Report report = new Report();
		report.setReportId(reportVM.getReportId());
		return ReportDAO.removeReport(report);
	}

	@RequestMapping(value = "/report/getAllReports")
	public List<ReportVM> getAllReports() {
		List<Report> list = ReportDAO.getAllReports();
		ArrayList<ReportVM> vmList = new ArrayList<>();

		for (Report r : list) {
			vmList.add(new ReportVM(r.getReportId(), r.getReporter().getFullName(), r.getReason(), r.getStatus(),
					r.getDate(), r.getDescription()));
		}

		return vmList;
	}

}

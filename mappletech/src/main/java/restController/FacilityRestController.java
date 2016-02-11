package restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.FacilityDAO;
import model.Facility;
import vm.FacilityVM;

@RestController
public class FacilityRestController {

	@RequestMapping(value = "/facility/add")
	public Boolean createFacility(@RequestBody(required = true) FacilityVM facilityVM) {
		Facility facility = new Facility();
		facility.setFacilityId(facilityVM.getFacilityId());
		facility.setFacilityName(facilityVM.getFacilityName());
		facility.setDescription(facilityVM.getDescription());
		facility.setLocation(facilityVM.getLocation());

		return FacilityDAO.addFacility(facility);
	}

	@RequestMapping(value = "/facility/remove")
	public Boolean deleteFacility(@RequestBody(required = true) FacilityVM facilityVM) {
		return FacilityDAO.removeFacility(facilityVM.getFacilityId());
	}

	@RequestMapping(value = "/facility/getFacilities")
	public List<FacilityVM> getAllFacilitys() {
		List<Facility> facilityList = FacilityDAO.getAllFacilities();
		List<FacilityVM> facilityVMList = new ArrayList<FacilityVM>();
		for (Facility facility : facilityList) {
			FacilityVM rVM = new FacilityVM(facility.getFacilityId(), facility.getFacilityName(),
					facility.getDescription(), facility.getLocation(), facility.getAvailable());
			facilityVMList.add(rVM);
		}
		return facilityVMList;
	}

}

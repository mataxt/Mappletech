package restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.ReservationDAO;
import model.Reservation;
import dao.FacilityDAO;
import dao.UserDAO;
import vm.ReservationVM;

@RestController
public class ReservationRestController {
	
	@RequestMapping(value="/reservation/add")
	public Boolean createReservation(@RequestBody(required=true) ReservationVM reservationVM )
	{
		Reservation reservation = new Reservation();
		reservation.setFacility(FacilityDAO.fetchFacility(reservationVM.getFacilityID()));
		reservation.setHost(UserDAO.fetchUser(reservationVM.getHost()));
		reservation.setTimeFrom(reservationVM.getTimeFrom());
		reservation.setTimeTo(reservationVM.getTimeTo());
		reservation.setTitle(reservationVM.getTitle());
		
		return ReservationDAO.addReservation(reservation);
	}
	
	@RequestMapping(value="/reservation/removeReservation")
	public Boolean deleteReservation(@RequestBody(required=true) ReservationVM reservationVM)
	{
		return ReservationDAO.removeReservation(reservationVM.getReservationId());
	}
	
	@RequestMapping(value="/reservation/getReservations")
	public List<ReservationVM> getAllReservations()
	{
		List<Reservation> reservationList = ReservationDAO.getAllReservations();
		List<ReservationVM> reservationVMList = new ArrayList<ReservationVM>();
		for(Reservation reservation:reservationList)
		{
			ReservationVM rVM = new ReservationVM(reservation.getReservationId(),
					reservation.getHost().getUsername(),
					reservation.getFacility().getFacilityId(),
					reservation.getTimeFrom(),
					reservation.getTimeTo(),
					reservation.getTitle());
			reservationVMList.add(rVM);
		}
		return reservationVMList;
	}

}

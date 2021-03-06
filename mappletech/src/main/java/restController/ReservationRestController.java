package restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
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
		
		return ReservationDAO.addReservation(reservation);
	}
	
	@RequestMapping(value="/reservation/remove")
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
					reservation.getFacility().getFacilityName(),
					reservation.getTimeFrom(),
					reservation.getTimeTo());
			reservationVMList.add(rVM);
		}
		return reservationVMList;
	}
	
	@RequestMapping(value="/reservation/getReservations/{user}")
	public List<ReservationVM> getUserReservations( @PathVariable("user") String username)
	{
		List<Reservation> reservationList = ReservationDAO.getAllReservations(username);
		List<ReservationVM> reservationVMList = new ArrayList<ReservationVM>();
		for(Reservation reservation:reservationList)
		{
			ReservationVM rVM = new ReservationVM(reservation.getReservationId(),
					reservation.getHost().getUsername(),
					reservation.getFacility().getFacilityId(),
					reservation.getFacility().getFacilityName(),
					reservation.getTimeFrom(),
					reservation.getTimeTo());
			reservationVMList.add(rVM);
		}
		return reservationVMList;
	}

}

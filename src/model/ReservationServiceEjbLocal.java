package model;

import java.util.List;

import javax.ejb.Local;

/*
 * l'interface m�tier locale est d�finie dans cette interface
 */

@Local
public interface ReservationServiceEjbLocal {

	public Reservationservice persistReservation(Reservationservice rs);

	public Reservationservice mergeReservation(Reservationservice rs);

	public Reservationservice removeReservation(Reservationservice rs);

	public List<Reservationservice> getReservationServiceFindAll();



}

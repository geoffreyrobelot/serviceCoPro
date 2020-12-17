package model;

import java.util.List;

import javax.ejb.Local;

/*
 * l'interface m�tier locale est d�finie dans cette interface
 */

@Local
public interface ReservationObjetEjbLocal {

	public Reservationobjet persistReservation(Reservationobjet ro);

	public Reservationobjet mergeReservation(Reservationobjet ro);

	public Reservationobjet removeReservation(Reservationobjet ro);

	public List<Reservationobjet> getReservationObjetFindAll();



}

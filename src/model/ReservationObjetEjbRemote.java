package model;

import java.util.List;
import javax.ejb.Remote;

/*
 * l'interface m�tier locale est d�finie dans cette interface
 */

@Remote
public interface ReservationObjetEjbRemote {

	public Reservationobjet persistReservation(Reservationobjet ro);

	public Reservationobjet mergeReservation(Reservationobjet ro);

	public Reservationobjet removeReservation(Reservationobjet ro);

	public List<Reservationobjet> getReservationObjetFindAll();



}

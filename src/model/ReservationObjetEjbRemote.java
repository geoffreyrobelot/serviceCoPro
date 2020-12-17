package model;

import java.util.List;
import javax.ejb.Remote;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Remote
public interface ReservationObjetEjbRemote {

	public Reservationobjet persistReservation(Reservationobjet ro);

	public Reservationobjet mergeReservation(Reservationobjet ro);

	public Reservationobjet removeReservation(Reservationobjet ro);

	public List<Reservationobjet> getReservationObjetFindAll();



}

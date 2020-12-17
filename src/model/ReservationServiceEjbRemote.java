package model;

import java.util.List;
import javax.ejb.Remote;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Remote
public interface ReservationServiceEjbRemote {

	public Reservationservice persistReservation(Reservationservice rs);

	public Reservationservice mergeReservation(Reservationservice rs);

	public Reservationservice removeReservation(Reservationservice rs);

	public List<Reservationservice> getReservationServiceFindAll();



}

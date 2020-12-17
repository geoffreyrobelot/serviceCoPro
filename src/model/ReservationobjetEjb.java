package model;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
 * Cette classe est implémentée sous la forme d'un EJB de type Stateless.
 */

@Stateless(mappedName = "reservationObjetEjb")
@LocalBean
public class ReservationobjetEjb implements ReservationObjetEjbLocal, ReservationObjetEjbRemote {

	@Resource
	SessionContext sessionContext;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	/**
	 * Default constructor.
	 */

	public ReservationobjetEjb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reservationobjet persistReservation(Reservationobjet ro) {
		em.persist(ro);
		return ro;
	}

	@Override
	public Reservationobjet mergeReservation(Reservationobjet ro) {
		return em.merge(ro);
	}

	@Override
	public Reservationobjet removeReservation(Reservationobjet ro) {
		em.remove(ro);
		return ro;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservationobjet> getReservationObjetFindAll() {
		return em.createNamedQuery("Reservationobjet.findAll").getResultList();
	}

}

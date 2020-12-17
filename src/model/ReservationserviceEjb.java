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

@Stateless(mappedName = "reservationServiceEjb")
@LocalBean
public class ReservationserviceEjb implements ReservationServiceEjbLocal, ReservationServiceEjbRemote {

	@Resource
	SessionContext sessionContext;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	/**
	 * Default constructor.
	 */

	public ReservationserviceEjb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Reservationservice persistReservation(Reservationservice rs) {
		em.persist(rs);
		return rs;
	}


	@Override
	public Reservationservice mergeReservation(Reservationservice rs) {
		return em.merge(rs);
	}


	@Override
	public Reservationservice removeReservation(Reservationservice rs) {
		em.remove(rs);
		return rs;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Reservationservice> getReservationServiceFindAll() {	
		return em.createNamedQuery("Reservationservice.findAll").getResultList();
	}


	


	



}

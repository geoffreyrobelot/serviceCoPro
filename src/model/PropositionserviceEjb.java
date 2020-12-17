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

@Stateless(mappedName = "propositionServiceEjb")
@LocalBean
public class PropositionserviceEjb implements PropositionServiceEjbLocal, PropositionServiceEjbRemote {

	@Resource
	SessionContext sessionContext;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	/**
	 * Default constructor.
	 */

	public PropositionserviceEjb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Propositionservice persistProposition(Propositionservice ps) {
		em.persist(ps);
		return ps;
	}

	@Override
	public Propositionservice mergeProposition(Propositionservice ps) {
		return em.merge(ps);
	}

	@Override
	public Propositionservice removeProposition(Propositionservice ps) {
		em.remove(ps);
		return ps;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propositionservice> getPropositionServiceFindAll() {
		return em.createNamedQuery("Propositionservice.findAll").getResultList();
		
	}

}

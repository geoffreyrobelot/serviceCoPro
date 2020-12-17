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

@Stateless(mappedName = "propositionObjetEjb")
@LocalBean
public class PropositionobjetEjb implements PropositionObjetEjbLocal, PropositionObjetEjbRemote {

	@Resource
	SessionContext sessionContext;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	/**
	 * Default constructor.
	 */

	public PropositionobjetEjb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Propositionobjet persistProposition(Propositionobjet po) {
		em.persist(po);
		return po;
	}

	@Override
	public Propositionobjet mergeProposition(Propositionobjet po) {
		return em.merge(po);
	}

	@Override
	public Propositionobjet removeProposition(Propositionobjet po) {
		em.remove(po);
		return po;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Propositionobjet> getPropositionObjetFindAll() {
		return em.createNamedQuery("Propositionobjet.findAll").getResultList();
	}

}

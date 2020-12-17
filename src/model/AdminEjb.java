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

@Stateless(mappedName = "adminEjb")
@LocalBean
public class AdminEjb implements AdminEjbLocal, AdminEjbRemote {

	@Resource
	SessionContext sessionContext;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	/**
	 * Default constructor.
	 */

	public AdminEjb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Administrateur persistAdministrateur(Administrateur administrateur) {
		em.persist(administrateur);
		return administrateur;
	}

	@Override
	public Administrateur mergeAdministrateur(Administrateur administrateur) {
		em.merge(administrateur);
		return administrateur;
	}

	@Override
	public Administrateur removeAdministrateur(String login) {
		List<Administrateur> listAdmin = getAdministrateurFindAll();

		for (Administrateur a : listAdmin) {
			if (a.getLogin().equals(login)) {
				em.remove(a);
				return a;
			}
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Administrateur> getAdministrateurFindAll() {
		return em.createNamedQuery("Administrateur.findAll").getResultList();

	}

	@Override
	public Administrateur getAdministrateur(String login) {
		List<Administrateur> listAdmin = getAdministrateurFindAll();

		for (Administrateur a : listAdmin)
			if (a.getLogin().equals(login)) {
				return a;
			}
		return null;
	}

}

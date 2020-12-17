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

@Stateless(mappedName = "utilisateurEjb")
@LocalBean
public class UtilisateurEjb implements UtilisateurEjbLocal, UtilisateurEjbRemote {

	@Resource
	SessionContext sessionContext;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	/**
	 * Default constructor.
	 */

	public UtilisateurEjb() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Utilisateur persistUtilisateur(Utilisateur utilisateur) {
		em.persist(utilisateur);
		return utilisateur;
	}

	@Override
	public Utilisateur mergeUtilisateur(Utilisateur utilisateur) {
		return em.merge(utilisateur);
	}

	@Override
	public Utilisateur getUtilisateur(String nomutilisateur) {
		List<Utilisateur> listUtilisateur = getUtilisateurFindAll();

		for (Utilisateur u : listUtilisateur)
			if (u.getNom().equals(nomutilisateur)) {
				return u;
			}
		return null;
	}

	@Override
	public Utilisateur removeUtilisateur(String nom) {
		List<Utilisateur> listUtilisateur = getUtilisateurFindAll();

		for (Utilisateur u : listUtilisateur) {
			if (u.getNom().equals(nom)) {
				em.remove(u);
				return u;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> getUtilisateurFindAll() {
		return em.createNamedQuery("Utilisateur.findAll").getResultList();
	}

}

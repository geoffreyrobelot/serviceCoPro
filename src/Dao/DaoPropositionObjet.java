package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import model.Propositionobjet;

public class DaoPropositionObjet {

	public EntityManager em;

	public DaoPropositionObjet() {
		// Spécifie le nom de l'unité de la persistence en paramètre
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("serviceCoPro");
		em = factory.createEntityManager();
	}

	public void ajouterObjet(Propositionobjet objet) {
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
	}

	public void supprimerObjet(Propositionobjet objet) {
		em.getTransaction().begin();
		em.remove(objet);
		em.getTransaction().commit();
	}

	public void updateObjet(Propositionobjet objet) {
		em.getTransaction().begin();
		em.merge(objet);
		em.getTransaction().commit();
	}

	public List<Propositionobjet> afficherListeObjet() {
/*
		TypedQuery<Propositionobjet> query = em.createQuery("SELECT p FROM Propositionobjet p", Propositionobjet.class);
		List<Propositionobjet> liste = query.getResultList();
		return liste;
		
*/		
		System.out.println("");
		System.out.println("Service qui permet d'afficher tous les objets");
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/serviceobjet/json/tous");

		List<Propositionobjet> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Propositionobjet>>() {
				}); 
		liste.forEach(a -> System.out
				.println("num : " + a.getNum() + " " + a.getCommentaire() + " nature de l'objet : " +a.getObjet() + " utilisateur " +a.getUtilisateur()));

		return liste;
		
	}
}

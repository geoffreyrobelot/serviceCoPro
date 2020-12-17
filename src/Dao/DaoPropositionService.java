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

import model.Propositionservice;

public class DaoPropositionService {

	public EntityManager em;

	public DaoPropositionService() {
		// Spécifie le nom de l'unité de la persistence en paramètre
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("serviceCoPro");
		em = factory.createEntityManager();
	}

	public void ajouterService(Propositionservice service) {
		em.getTransaction().begin();
		em.persist(service);
		em.getTransaction().commit();
	}

	public void supprimerService(Propositionservice service) {
		em.getTransaction().begin();
		em.remove(service);
		em.getTransaction().commit();
	}

	public void updateService(Propositionservice service) {
		em.getTransaction().begin();
		em.merge(service);
		em.getTransaction().commit();
	}

	public List<Propositionservice> afficherListeService() {
/*
		TypedQuery<Propositionservice> query = em.createQuery("SELECT p FROM Propositionservice p",
				Propositionservice.class);
		List<Propositionservice> liste = query.getResultList();
		return liste;
*/
		
		System.out.println("");
		System.out.println("Service qui permet d'afficher tous les services");
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/service/json/tous");

		List<Propositionservice> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Propositionservice>>() {
				}); 
		liste.forEach(a -> System.out
				.println("num : " + a.getNum() + " " + a.getCommentaire() + " type de service: " +a.getService()));

		return liste;
		
		
	}
}

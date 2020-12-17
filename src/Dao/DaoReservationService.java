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

import model.Reservationservice;

public class DaoReservationService {

	public EntityManager em;

	public DaoReservationService() {
		// Spécifie le nom de l'unité de la persistence en paramètre
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("serviceCoPro");
		em = factory.createEntityManager();
	}

	public void ajouterService(Reservationservice service) {
		em.getTransaction().begin();
		em.persist(service);
		em.getTransaction().commit();
	}

	public void supprimerService(Reservationservice service) {
		em.getTransaction().begin();
		em.remove(service);
		em.getTransaction().commit();
	}

	public void updateService(Reservationservice service) {
		em.getTransaction().begin();
		em.merge(service);
		em.getTransaction().commit();
	}

	public List<Reservationservice> afficherReservationService() {
		
		System.out.println("Service qui permet d'afficher toutes les réservations de service");
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/reservation/json/tous");

		List<Reservationservice> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Reservationservice>>() {
				});
		liste.forEach(a -> System.out
				.println(a.getService() + a.getUtilisateur()));

		return liste;
	}
}

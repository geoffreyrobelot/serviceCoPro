package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import model.Reservationobjet;

public class DaoReservationObjet {

	public EntityManager em;

	public DaoReservationObjet() {
		// Spécifie le nom de l'unité de la persistence en paramètre
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("serviceCoPro");
		em = factory.createEntityManager();
	}

	public void ajouterObjet(Reservationobjet objet) {
		em.getTransaction().begin();
		em.persist(objet);
		em.getTransaction().commit();
	}

	public void supprimerObjet(Reservationobjet objet) {
		em.getTransaction().begin();
		em.remove(objet);
		em.getTransaction().commit();
	}

	public void updateObjet(Reservationobjet objet) {
		em.getTransaction().begin();
		em.merge(objet);
		em.getTransaction().commit();
	}

	
	public List<Reservationobjet> afficherReservationObjet() {

		System.out.println("Service qui permet d'afficher toutes les réservations d'objets");
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/reservationobjet/json/tous");

		List<Reservationobjet> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Reservationobjet>>() {
				});
		liste.forEach(a -> System.out.println(a.getObjet() + a.getUtilisateur() + a.getDateEmprunt()));

		return liste;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reservationobjet> afficherObjetReserveEnCours(){
		String requete = ("SELECT r FROM Reservationobjet r WHERE r.dateRendu is null");
		Query query = em.createQuery(requete);
		List<Reservationobjet> listero = null;
		listero = query.getResultList();
		return listero;
		
	}
	
}

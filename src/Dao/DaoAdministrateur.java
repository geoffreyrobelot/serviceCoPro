package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Administrateur;

public class DaoAdministrateur {
	/*
	 * Initialisation de JPA
	 * 
	 */

	public EntityManager em;

	/*
	 * public static void main (String args[]) throws ClassNotFoundException,
	 * SQLException { Class.forName("com.mysql.cj.jdbc.Driver"); Connection co =
	 * DriverManager.getConnection("jdbc:mysql://localhost:3306/serviceCoPro",
	 * "admin","admin"); System.out.println("connecte a la base"); }
	 */

	public DaoAdministrateur() {

		/*
		 * Spécifie le nom de l'unité de la persistence en paramètre
		 */
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("serviceCoPro");
		em = factory.createEntityManager();
	}

	/*
	 * ajoute un objet utilisateur dans la table utilisateur de la base de données
	 * serviceCoPro
	 */
	public void ajouterAdministrateur(Administrateur administrateur) {

		System.out.println("");
		System.out.println("\n****Invoquer le service ajouter un administrateur****");

		////////////////// service web /////////////////////
		Form forme = new Form();
		forme.param("login", administrateur.getLogin());
		forme.param("login", administrateur.getMdp());
		forme.param("login", administrateur.getRole());
		
		Client client = ClientBuilder.newClient(); // Obtenir le service cible
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/administrateur/addadmin");
		Invocation.Builder lesadministrateurs = service.request(MediaType.APPLICATION_JSON);
		Response response = lesadministrateurs.post(Entity.entity(forme, MediaType.APPLICATION_FORM_URLENCODED_TYPE),
				Response.class);
		Administrateur monAdmin = response.readEntity(Administrateur.class);
		System.out.println(monAdmin.toString());

	}

	public void supprimerAdmin(Administrateur administrateur) {
		/*
		 * em.getTransaction().begin(); if (!em.contains(utilisateur)) { utilisateur =
		 * em.merge(utilisateur); } em.remove(utilisateur);
		 * em.getTransaction().commit();
		 */
		//////////////// service web ///////////////////////
		String login = administrateur.getLogin();	
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/utilisateur/delete" + login);
		Invocation.Builder lesadministrateurs = service.request(MediaType.APPLICATION_JSON);
		Response response = lesadministrateurs.delete();
		System.out.println(response.getStatus());

		Administrateur a = response.readEntity(Administrateur.class);
		if (a != null)
			System.out.println("Admin " + login + " supprimé");
		else
			System.out.println("Admin inconnu");
	}

	public List<Administrateur> afficherListeAdministrateur() {

		/////////////////// service web /////////////////////
		System.out.println("");
		System.out.println("Service qui permet d'afficher tous les administrateurs");
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/administrateur");

		List<Administrateur> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Administrateur>>() {
				});
		liste.forEach(a -> System.out
				.println("num : " + a.getNum() + " / " + "login : " + a.getLogin() + " / " + "role : " + a.getRole()));

		return liste;

	}

	public List<Administrateur> afficherUnAdministrateur(Administrateur administrateur) {
		////////////////// service web ////////////////////
		System.out.println("");
		String login = administrateur.getLogin();
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/utilisateur" + login);

		List<Administrateur> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Administrateur>>() {
				});
		liste.forEach(a -> System.out.println("num : " + a.getNum() + " / " + "login : " + a.getLogin()));
		return liste;

	}

}

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

import model.Utilisateur;

public class DaoUtilisateur {
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

	public DaoUtilisateur() {

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
	
	/////////////////////// méthode POST ////////////////////
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		System.out.println("");
		System.out.println("\n****Invoquer le service ajouter un utilisateur****");
		////////////////// service web /////////////////////
		Form forme = new Form();
		forme.param("lenom", utilisateur.getNom());
		forme.param("email", utilisateur.getEmail());
		forme.param("batiment", utilisateur.getBatiment());
		forme.param("genre", utilisateur.getGenre());
		forme.param("appartement", utilisateur.getAppartement());
		forme.param("mdp", utilisateur.getMdp());
		Client client = ClientBuilder.newClient(); // Obtenir le service cible
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/utilisateur/adduser");
		Invocation.Builder lesutilisateurs = service.request(MediaType.APPLICATION_JSON);
		Response response = lesutilisateurs.post(Entity.entity(forme, MediaType.APPLICATION_FORM_URLENCODED_TYPE),
				Response.class);
		Utilisateur monutilisateur = response.readEntity(Utilisateur.class);
		System.out.println(monutilisateur.toString());

	}

	public void modifierUtilisateur(Utilisateur utilisateur) {
		em.getTransaction().begin();
		em.merge(utilisateur);
		em.getTransaction().commit();

	}

	/*
	 * supprime un objet utilisateur dans la table utilisateur de la base de données
	 * serviceCoPro
	 */

	public void supprimerUtilisateur(Utilisateur utilisateur) {
		/*
		 * em.getTransaction().begin(); if (!em.contains(utilisateur)) { utilisateur =
		 * em.merge(utilisateur); } em.remove(utilisateur);
		 * em.getTransaction().commit();
		 */
		//////////////// service web ///////////////////////
		String nom = utilisateur.getNom();
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/utilisateur/delete" + nom);
		Invocation.Builder lesutilisateurs = service.request(MediaType.APPLICATION_JSON);
		Response response = lesutilisateurs.delete();
		System.out.println(response.getStatus());

		Utilisateur i = response.readEntity(Utilisateur.class);
		if (i != null)
			System.out.println("Utilisateur " + nom + " supprimé");
		else
			System.out.println("Utilisateur inconnu");
	}

	//////////////////////// méthode GET /////////////////////
	public List<Utilisateur> afficherListeUtilisateur() {

		/////////////////// service web /////////////////////
		System.out.println("");
		System.out.println("Service qui permet d'afficher tous les utilisateurs");
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/utilisateur");

		List<Utilisateur> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Utilisateur>>() {
				});
		liste.forEach(a -> System.out
				.println("num : " + a.getNum() + " / " + "nom : " + a.getNom() + " / " + "email : " + a.getEmail()));

		return liste;

	}

	public List<Utilisateur> afficherUnUtilisateur(Utilisateur utilisateur) {
		////////////////// service web ////////////////////
		System.out.println("");
		String nom = utilisateur.getNom();
		Client client = ClientBuilder.newClient();
		WebTarget service = client.target("http://localhost:8080/serviceCoPro/jaxrs/utilisateur" + nom);

		List<Utilisateur> liste = service.request(MediaType.APPLICATION_JSON_TYPE)
				.get(new GenericType<List<Utilisateur>>() {
				});
		liste.forEach(a -> System.out.println("num : " + a.getNum() + " / " + "nom : " + a.getNom()));
		return liste;

	}

}

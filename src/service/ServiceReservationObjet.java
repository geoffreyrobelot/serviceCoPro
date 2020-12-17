package service;

import java.util.Collection;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Reservationobjet;
import model.ReservationobjetEjb;

@Path("reservationobjet")
public class ServiceReservationObjet {

	@EJB
	ReservationobjetEjb reservationObjetEjb;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;
	
	@Path("/json/tous")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Reservationobjet> lireToutesLesReservationsObjetJson() {
		Collection<Reservationobjet> col = reservationObjetEjb.getReservationObjetFindAll();
		return col;
	}
	

	
	/*
	@Path("{nomutilisateur}")
	@PUT
	@Produces(MediaType.TEXT_HTML)
	public Utilisateur modifierUtilisateur(@PathParam("nomutilisateur") String n, @FormParam("email") String e,
			@FormParam("batiment") String b, @FormParam("genre") String g, @FormParam("appartement") String a,
			@FormParam("mdp") String m) {
		Utilisateur utilisateur = new Utilisateur(g, n, m, b, a, e);
		utilisateur.setNom(n);
		utilisateur.setEmail(e);
		utilisateur.setBatiment(b);
		utilisateur.setGenre(g);
		utilisateur.setAppartement(a);
		utilisateur.setMdp(m);
		utilisateurEjb.mergeUtilisateur(utilisateur);
		// Utilisateur i  = utilisateurEjb.getUtilisateurFindAll().get(0);
		return utilisateur;
	}

	*/
}

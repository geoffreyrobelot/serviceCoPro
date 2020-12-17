package service;

import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Utilisateur;
import model.UtilisateurEjbLocal;

@DeclareRoles({ "admin", "client", "user" })
@Path("utilisateur")
public class ServiceUtilisateur {

	@EJB
	UtilisateurEjbLocal utilisateurEjb;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	//@RolesAllowed("admin")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Utilisateur> getusers() {
		return utilisateurEjb.getUtilisateurFindAll();
	}

	//@RolesAllowed("admin")
	@Path("{nomutilisateur}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur getUser(@PathParam("nomutilisateur") String nomutilisateur) {
		return utilisateurEjb.getUtilisateur(nomutilisateur);
	}

	@Path("/adduser")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur crerUtilisateur(@FormParam("lenom") String n, @FormParam("email") String e,
			@FormParam("batiment") String b, @FormParam("genre") String g, @FormParam("appartement") String a,
			@FormParam("mdp") String m) {
		Utilisateur utilisateur = new Utilisateur(g, n, m, b, a, e);
		utilisateurEjb.persistUtilisateur(utilisateur);
		return utilisateur;
	}

	//@RolesAllowed("admin")
	@Path("/updateuser")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Utilisateur updateUtilisateur(@FormParam("lenom") String n, @FormParam("email") String e,
			@FormParam("batiment") String b, @FormParam("genre") String g, @FormParam("appartement") String a,
			@FormParam("mdp") String m) {
		Utilisateur utilisateur = new Utilisateur(g, n, m, b, a, e);
		utilisateurEjb.mergeUtilisateur(utilisateur);
		return utilisateur;

	}

	@Path("/delete/{nomutilisateur}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur supprimeUtilisateur(@PathParam("nomutilisateur") String nom) {
		return utilisateurEjb.removeUtilisateur(nom);
	}
	
	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String checkLogin(@FormParam ("username") String u, @FormParam("password") String p) {
		
		return null;
		
	}
	
}

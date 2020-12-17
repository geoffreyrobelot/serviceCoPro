package service;

import java.util.List;
import javax.annotation.security.DeclareRoles;
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
import model.AdminEjbLocal;
import model.Administrateur;

@DeclareRoles({ "admin", "client", "user" })
@Path("administrateur")
public class ServiceAdmin {

	@EJB
	AdminEjbLocal adminEjb;

	@PersistenceContext(unitName = "serviceCoPro")
	private EntityManager em;

	// @RolesAllowed("admin")
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Administrateur> getAdmin() {
		return adminEjb.getAdministrateurFindAll();
	}

	@Path("{nomadmin}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Administrateur getAdmin(@PathParam("nomadmin") String login) {
		return adminEjb.getAdministrateur(login);
	}

	@Path("/addadmin")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Administrateur crerAdmin(@FormParam("login") String l, @FormParam("mdp") String m,
			@FormParam("role") String r) {
		Administrateur administrateur = new Administrateur(l, m, r);
		adminEjb.persistAdministrateur(administrateur);
		return administrateur;
	}

	@Path("/updateadmin")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Administrateur updateAdmin(@FormParam("login") String l, @FormParam("mdp") String m,
			@FormParam("role") String r) {
		Administrateur administrateur = new Administrateur(l, m, r);
		adminEjb.mergeAdministrateur(administrateur);
		return administrateur;

	}

	@Path("/delete/{login}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Administrateur supprimeAdmin(@PathParam("login") String login) {
		return adminEjb.removeAdministrateur(login);
	}
	
	@Path("/login")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String checkLogin(@FormParam ("username") String u, @FormParam("password") String p) {
		
		return null;
		
	}
	
}

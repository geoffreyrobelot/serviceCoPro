package model;

import java.util.List;

import javax.ejb.Remote;


/*
 * l'interface métier distante est définie dans cette interface 
 */

@Remote
public interface UtilisateurEjbRemote {

	public Utilisateur persistUtilisateur(Utilisateur utilisateur);

	public Utilisateur mergeUtilisateur(Utilisateur utilisateur);

	public Utilisateur removeUtilisateur(String nom);

	public List<Utilisateur> getUtilisateurFindAll();

	public Utilisateur getUtilisateur(String utilisateur);
	
	
}

package model;

import java.util.List;

import javax.ejb.Local;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Local
public interface UtilisateurEjbLocal {

	public Utilisateur persistUtilisateur(Utilisateur utilisateur);

	public Utilisateur mergeUtilisateur(Utilisateur utilisateur);

	public Utilisateur removeUtilisateur(String nom);

	public List<Utilisateur> getUtilisateurFindAll();
	
	public Utilisateur getUtilisateur(String utilisateur);
	

}

package model;

import java.util.List;

import javax.ejb.Remote;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Remote
public interface AdminEjbRemote {

	public Administrateur persistAdministrateur(Administrateur administrateur);

	public Administrateur mergeAdministrateur(Administrateur administrateur);

	public Administrateur removeAdministrateur(String login);

	public List<Administrateur> getAdministrateurFindAll();
	
	public Administrateur getAdministrateur(String login);
	

}

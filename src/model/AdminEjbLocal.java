package model;

import java.util.List;

import javax.ejb.Local;

/*
 * l'interface métier locale est définie dans cette interface
 */

@Local
public interface AdminEjbLocal {

	public Administrateur persistAdministrateur(Administrateur administrateur);

	public Administrateur mergeAdministrateur(Administrateur administrateur);

	public Administrateur removeAdministrateur(String login);

	public List<Administrateur> getAdministrateurFindAll();
	
	public Administrateur getAdministrateur(String login);
	

}

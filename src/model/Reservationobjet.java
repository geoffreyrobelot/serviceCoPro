package model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reservationobjet database table.
 * 
 */
@Entity
@NamedQuery(name="Reservationobjet.findAll", query="SELECT r FROM Reservationobjet r")
public class Reservationobjet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	@Temporal(TemporalType.DATE)
	private Date dateEmprunt;

	@Temporal(TemporalType.DATE)
	private Date dateRendu;

	private String lieuRestitution;

	private String objet;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JsonbTransient
	@JoinColumn(name="numUtilisateur")
	private Utilisateur utilisateur;

	/*
	 * @JsonbTransient 
	 * Empêche le mapping de la liste d'invitations en représentation JSON 
	 * Sinon boucle infinie avec résultat affiché StackOverflow
	 */
	
	//bi-directional many-to-one association to Propositionobjet
	@ManyToOne
	@JsonbTransient
	@JoinColumn(name="numObjet")
	private Propositionobjet propositionobjet;

	public Reservationobjet() {
	}
	
	public Reservationobjet(Date dateEmprunt, Date dateRendu, String lieuRestitution, String objet, 
			Utilisateur utilisateur, Propositionobjet propositionobjet) {
		this.dateEmprunt = dateEmprunt;
		this.dateRendu = dateRendu;
		this.lieuRestitution = lieuRestitution; 
		this.objet = objet;
		this.utilisateur = utilisateur;
		this.propositionobjet = propositionobjet;
	}
	
	public String toString() {
		return objet;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getDateEmprunt() {
		return this.dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRendu() {
		return this.dateRendu;
	}

	public void setDateRendu(Date dateRendu) {
		this.dateRendu = dateRendu;
	}

	public String getLieuRestitution() {
		return this.lieuRestitution;
	}

	public void setLieuRestitution(String lieuRestitution) {
		this.lieuRestitution = lieuRestitution;
	}

	public String getObjet() {
		return this.objet;
	}

	public void setObjet(String objet) {
		this.objet = objet;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Propositionobjet getPropositionobjet() {
		return this.propositionobjet;
	}

	public void setPropositionobjet(Propositionobjet propositionobjet) {
		this.propositionobjet = propositionobjet;
	}

}
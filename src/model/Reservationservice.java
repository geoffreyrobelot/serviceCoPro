package model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the reservationservice database table.
 * 
 */
@Entity
@NamedQuery(name = "Reservationservice.findAll", query = "SELECT r FROM Reservationservice r")
public class Reservationservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int num;

	@Temporal(TemporalType.DATE)
	private Date dateService;

	private String service;

	/*
	 * @JsonbTransient 
	 * Empêche le mapping de la liste d'invitations en représentation JSON 
	 * Sinon boucle infinie avec résultat affiché StackOverflow
	 */
	
	// bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JsonbTransient
	@JoinColumn(name = "numUtilisateur")
	private Utilisateur utilisateur;

	// bi-directional many-to-one association to Propositionservice
	@ManyToOne
	@JsonbTransient
	@JoinColumn(name = "numService")
	private Propositionservice propositionservice;

	public Reservationservice() {
	}

	public Reservationservice(Date dateService, String service, Utilisateur utilisateur, Propositionservice propositionservice) {
		this.dateService = dateService;
		this.service = service;
		this.utilisateur = utilisateur; 
		this.propositionservice = propositionservice;
	}
	
	public String toString() {
		return service + " " +utilisateur;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getDateService() {
		return this.dateService;
	}

	public void setDateService(Date dateService) {
		this.dateService = dateService;
	}

	public String getService() {
		return this.service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Propositionservice getPropositionservice() {
		return this.propositionservice;
	}

	public void setPropositionservice(Propositionservice propositionservice) {
		this.propositionservice = propositionservice;
	}

}
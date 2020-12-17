package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the propositionservice database table.
 * 
 */
@Entity
@NamedQuery(name="Propositionservice.findAll", query="SELECT p FROM Propositionservice p")
public class Propositionservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	private String commentaire;


	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	private String heure;

	private String service;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="numUtilisateur")
	private Utilisateur utilisateur;

	//bi-directional many-to-one association to Reservationservice
	@OneToMany(mappedBy="propositionservice")
	private List<Reservationservice> reservationservices;

	public Propositionservice() {
	}
	
	public Propositionservice(String commentaire, Date dateDebut, String heure, String service, Utilisateur utilisateur) {
		this.commentaire = commentaire;
		this.dateDebut = dateDebut;
		this.heure = heure; 
		this.service = service; 
		this.utilisateur = utilisateur;
	}
	
	public String toString() {
		return num + " - " +service;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getHeure() {
		return this.heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
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

	public List<Reservationservice> getReservationservices() {
		return this.reservationservices;
	}

	public void setReservationservices(List<Reservationservice> reservationservices) {
		this.reservationservices = reservationservices;
	}

	public Reservationservice addReservationservice(Reservationservice reservationservice) {
		getReservationservices().add(reservationservice);
		reservationservice.setPropositionservice(this);

		return reservationservice;
	}

	public Reservationservice removeReservationservice(Reservationservice reservationservice) {
		getReservationservices().remove(reservationservice);
		reservationservice.setPropositionservice(null);

		return reservationservice;
	}

}
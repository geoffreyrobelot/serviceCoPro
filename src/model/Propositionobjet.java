package model;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the propositionobjet database table.
 * 
 */
@Entity
@NamedQuery(name="Propositionobjet.findAll", query="SELECT p FROM Propositionobjet p")
public class Propositionobjet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	private String commentaire;

	@Temporal(TemporalType.DATE)
	private Date dateDebut;

	private String delai;

	private String objet;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="numUtilisateur")
	private Utilisateur utilisateur;

	/*
	 * @JsonbTransient 
	 * Empêche le mapping de la liste d'invitations en représentation JSON 
	 * Sinon boucle infinie avec résultat affiché StackOverflow
	 */
	
	//bi-directional many-to-one association to Reservationobjet
	@JsonbTransient 
	@OneToMany(mappedBy="propositionobjet")
	private List<Reservationobjet> reservationobjets;

	public Propositionobjet() {
	}
	
	public Propositionobjet(String commentaire, Date dateDebut, String delai, String objet, Utilisateur utilisateur) {
		this.commentaire = commentaire;
		this.dateDebut = dateDebut;
		this.delai = delai; 
		this.objet = objet; 
		this.utilisateur = utilisateur;
	}
	
	public String toString() {
		return num + " - " +objet;
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

	public String getDelai() {
		return this.delai;
	}

	public void setDelai(String delai) {
		this.delai = delai;
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

	@JsonbTransient 
	public List<Reservationobjet> getReservationobjets() {
		return this.reservationobjets;
	}

	@JsonbTransient 
	public void setReservationobjets(List<Reservationobjet> reservationobjets) {
		this.reservationobjets = reservationobjets;
	}

	public Reservationobjet addReservationobjet(Reservationobjet reservationobjet) {
		getReservationobjets().add(reservationobjet);
		reservationobjet.setPropositionobjet(this);

		return reservationobjet;
	}

	public Reservationobjet removeReservationobjet(Reservationobjet reservationobjet) {
		getReservationobjets().remove(reservationobjet);
		reservationobjet.setPropositionobjet(null);

		return reservationobjet;
	}

}
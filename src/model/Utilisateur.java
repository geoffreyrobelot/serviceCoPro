package model;

import java.io.Serializable;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the utilisateur database table.
 * 
 */
@Entity
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	private String appartement;

	private String batiment;

	private String email;

	private String genre;

	private String mdp;

	private String nom;

	//bi-directional many-to-one association to Propositionobjet
	@OneToMany(mappedBy="utilisateur")
	private List<Propositionobjet> propositionobjets;

	//bi-directional many-to-one association to Propositionservice
	@OneToMany(mappedBy="utilisateur")
	private List<Propositionservice> propositionservices;

	//bi-directional many-to-one association to Reservationobjet
	@OneToMany(mappedBy="utilisateur")
	private List<Reservationobjet> reservationobjets;

	//bi-directional many-to-one association to Reservationservice
	@OneToMany(mappedBy="utilisateur")
	private List<Reservationservice> reservationservices;

	public Utilisateur() {
	}
	
	public Utilisateur(String nom, String mdp) {
		this.nom = nom;
		this.mdp = mdp;
	}
	
	public Utilisateur(String genre, String nom, String mdp, String batiment, String appartement, String email) {
		this.genre = genre;
		this.nom = nom;
		this.mdp = mdp;
		this.batiment = batiment;
		this.appartement = appartement;
		this.email = email;
	}
	
	public String toString() {
		return nom;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAppartement() {
		return this.appartement;
	}

	public void setAppartement(String appartement) {
		this.appartement = appartement;
	}

	public String getBatiment() {
		return this.batiment;
	}

	public void setBatiment(String batiment) {
		this.batiment = batiment;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/*
	 * @JsonbTransient 
	 * Empêche le mapping de la liste d'invitations en représentation JSON 
	 * Sinon boucle infinie avec résultat affiché StackOverflow
	 */
	@JsonbTransient
	public List<Propositionobjet> getPropositionobjets() {
		return this.propositionobjets;
	}

	public void setPropositionobjets(List<Propositionobjet> propositionobjets) {
		this.propositionobjets = propositionobjets;
	}

	public Propositionobjet addPropositionobjet(Propositionobjet propositionobjet) {
		getPropositionobjets().add(propositionobjet);
		propositionobjet.setUtilisateur(this);

		return propositionobjet;
	}

	public Propositionobjet removePropositionobjet(Propositionobjet propositionobjet) {
		getPropositionobjets().remove(propositionobjet);
		propositionobjet.setUtilisateur(null);

		return propositionobjet;
	}

	@JsonbTransient
	public List<Propositionservice> getPropositionservices() {
		return this.propositionservices;
	}

	public void setPropositionservices(List<Propositionservice> propositionservices) {
		this.propositionservices = propositionservices;
	}

	public Propositionservice addPropositionservice(Propositionservice propositionservice) {
		getPropositionservices().add(propositionservice);
		propositionservice.setUtilisateur(this);

		return propositionservice;
	}

	public Propositionservice removePropositionservice(Propositionservice propositionservice) {
		getPropositionservices().remove(propositionservice);
		propositionservice.setUtilisateur(null);

		return propositionservice;
	}

	@JsonbTransient
	public List<Reservationobjet> getReservationobjets() {
		return this.reservationobjets;
	}

	public void setReservationobjets(List<Reservationobjet> reservationobjets) {
		this.reservationobjets = reservationobjets;
	}

	public Reservationobjet addReservationobjet(Reservationobjet reservationobjet) {
		getReservationobjets().add(reservationobjet);
		reservationobjet.setUtilisateur(this);

		return reservationobjet;
	}

	public Reservationobjet removeReservationobjet(Reservationobjet reservationobjet) {
		getReservationobjets().remove(reservationobjet);
		reservationobjet.setUtilisateur(null);

		return reservationobjet;
	}

	@JsonbTransient
	public List<Reservationservice> getReservationservices() {
		return this.reservationservices;
	}

	public void setReservationservices(List<Reservationservice> reservationservices) {
		this.reservationservices = reservationservices;
	}

	public Reservationservice addReservationservice(Reservationservice reservationservice) {
		getReservationservices().add(reservationservice);
		reservationservice.setUtilisateur(this);

		return reservationservice;
	}

	public Reservationservice removeReservationservice(Reservationservice reservationservice) {
		getReservationservices().remove(reservationservice);
		reservationservice.setUtilisateur(null);

		return reservationservice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utilisateur other = (Utilisateur) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (num != other.num)
			return false;
		return true;
	}

}
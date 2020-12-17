package model;


import java.io.Serializable;




public class Utilisateurbis implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private int num;

	private String appartement;

	private String batiment;

	private String email;

	private String genre;

	private String mdp;

	private String nom;

	

	public Utilisateurbis() {
	}
	
	public Utilisateurbis(String genre, String nom, String mdp, String batiment, String appartement, String email) {
		this.genre = genre;
		this.nom = nom;
		this.mdp = mdp;
		this.batiment = batiment;
		this.appartement = appartement;
		this.email = email;
	}
	
	public String toString() {
		return nom + " " +batiment + " " +appartement + " "+email;
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

}
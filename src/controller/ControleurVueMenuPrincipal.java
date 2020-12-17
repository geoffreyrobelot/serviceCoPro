package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import Dao.DaoPropositionObjet;
import Dao.DaoPropositionService;
import Dao.DaoReservationObjet;
import Dao.DaoUtilisateur;
import application.Main;
import application.MainAdmin;
import application.MainConnexionAdmin;
import application.MainMenuPrincipal;
import application.MainMonCompte;
import application.MainPoster;
import application.MainReserver;
import application.MainRestitution;
import dbConnection.DataBaseHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Propositionobjet;
import model.Propositionservice;
import model.Reservationobjet;
import model.Utilisateur;
import session.SessionUtilisateur;

public class ControleurVueMenuPrincipal {

	private DaoUtilisateur dao;

	private DaoPropositionService daoService;

	private DaoPropositionObjet daoObjet;
	
	private DaoReservationObjet daoResObjet;
	
	private MainConnexionAdmin	mainConnexionAdmin;
	
	private MainAdmin mainAdmin;

	private MainReserver mainReserver;

	private MainPoster mainPoster;

	private MainMonCompte mainMonCompte;

	private MainRestitution mainRestitution;

	private MainMenuPrincipal mainPrincipal;

	private Main main;

	private Button deconnexion;
	
	

	@FXML
	private ListView<Propositionservice> service;
	private ObservableList<Propositionservice> observableService = FXCollections.observableArrayList();

	@FXML
	private ListView<Propositionobjet> objet;
	private ObservableList<Propositionobjet> observableObjet = FXCollections.observableArrayList();

	@FXML
	private ListView<Utilisateur> utilisateur;
	private ObservableList<Utilisateur> observableUtilisateur = FXCollections.observableArrayList();

	@FXML
	private TextField nomAdmin;
	
	@FXML
	private TextField mdpAdmin;
	
	@FXML
	private Label identite;
	
	private String username;
	
	private String mdp;
	
	private DataBaseHandler handler;
	private Connection connection;
	private PreparedStatement pst;
	
	
	/*
	 * constructeur de la classe
	 */
	public ControleurVueMenuPrincipal() {

		dao = new DaoUtilisateur();
		daoService = new DaoPropositionService();
		daoObjet = new DaoPropositionObjet();
		daoResObjet = new DaoReservationObjet();
	}

	@FXML
	private void initialize() {
		//utilisateur.setItems(observableUtilisateur);
		//afficherUtilisateur();
		
		service.setItems(observableService);
		afficherService();
		
		objet.setItems(observableObjet);
		afficherObjet();
		
		handler = new DataBaseHandler();
		
		String iden = SessionUtilisateur.getUsername();
		System.out.println("Bienvenue :" +iden);
		
		recupereIdUser();
	}
	
	/*
	 * méthode qui permet de récupérer l'id de l'utilisateur connecté à l'application
	 */
	@FXML
	public void recupereIdUser() {
		 try {
			 	String iden = SessionUtilisateur.getUsername();
			 	System.out.println(iden);
	            connection = handler.getConnection();
	            Statement stmt = connection.createStatement();
	            ResultSet rs;
	 
	            rs = stmt.executeQuery("SELECT num FROM Utilisateur WHERE nom='"+iden+"'");
	            while ( rs.next() ) {
	                String id = rs.getString("num");
	                System.out.println(id);
	                int i = Integer.parseInt(id);
	                System.out.println(i);
	                identite.setText("Bonjour " +iden);
	            }
	            connection.close();
	            
	        } catch (Exception e) {
	            System.err.println("Erreur!");
	            System.err.println(e.getMessage());
	        }
	}

	

	@FXML
	private void afficherUtilisateur() {
		observableUtilisateur.clear();
		List<Utilisateur> liste = dao.afficherListeUtilisateur();
		for (Utilisateur i : liste)
			observableUtilisateur.add(i);

	}

	@FXML
	private void afficherService() {
		observableService.clear();
		List<Propositionservice> liste = daoService.afficherListeService();
		for (Propositionservice i : liste)
			observableService.add(i);

	}

	@FXML
	private void afficherObjet() {
		observableObjet.clear();
		List<Propositionobjet> liste = daoObjet.afficherListeObjet();
		for (Propositionobjet i : liste)
				observableObjet.add(i);
	}
	

	@FXML
	private void infoSupplementaire() {
		Propositionobjet selObjet = objet.getSelectionModel().getSelectedItem();
		if (selObjet != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Objet sélectionné : " + selObjet.getObjet());
			alert.setContentText(
					"A propos : " + selObjet.getCommentaire() + " à partir du : " + selObjet.getDateDebut());
			alert.show();
			System.out.println(selObjet);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Vous n'avez pas sélectionné d'objet dans la liste");
			alert.show();
		}
	}

	@FXML
	private void infoSuppService() {
		Propositionservice selService = service.getSelectionModel().getSelectedItem();
		if (selService != null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Service sélectionné : " + selService.getService());
			alert.setContentText(
					"A propos : " + selService.getCommentaire() + " à partir du : " + selService.getDateDebut());
			alert.show();
			System.out.println(selService);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Vous n'avez pas sélectionné de service dans la liste");
			alert.show();
		}
	}

	/*
	 * accesseurs qui donnent la référence des différents objets
	 */
	public void setMainReserver(MainReserver mainReserver) {
		this.mainReserver = mainReserver;
	}

	public void setMainPoster(MainPoster mainPoster) {
		this.mainPoster = mainPoster;
	}

	public void setMainMonCompte(MainMonCompte mainMonCompte) {
		this.mainMonCompte = mainMonCompte;
	}

	public void setMainRestitution(MainRestitution mainRestitution) {
		this.mainRestitution = mainRestitution;
	}

	public void setMainPrincipal(MainMenuPrincipal mainPrincipal) {
		this.mainPrincipal = mainPrincipal;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	public void setMainConnexionAdmin(MainConnexionAdmin mainConnexionAdmin) {
		this.mainConnexionAdmin = mainConnexionAdmin;
	}

	@FXML
	private void reserver() {
		mainReserver.montrerLaVue();
	}

	@FXML
	private void poster() {
		mainPoster.montrerLaVue();
	}

	@FXML
	private void monCompte() {
		mainMonCompte.montrerLaVue();
	}

	@FXML
	private void restituer() {
		mainRestitution.montrerLaVue();
	}

	@FXML
	public void contact() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Application ServiceCoPro");
		alert.setContentText("Appelez le 01 XX XX XX XX pour contacter l'assistance");
		alert.show();
	}

	@FXML
	public void mentions() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Application ServiceCoPro");
		alert.setContentText("A venir");
		alert.show();
	}
	
	public void setMainAdmin(MainAdmin mainAdmin) {
		this.mainAdmin = mainAdmin;
	}
	
	@FXML
	private void admin() {
		mainConnexionAdmin.montrerLaVue();
		
	}

	@FXML
	private void deconnexion() throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Attention");
		alert.setContentText("Etes vous sûr de vouloir quitter l'application ?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			SessionUtilisateur.cleanSessionUtilisateur();
			Platform.exit();
			
		} else {
			}

	}
}

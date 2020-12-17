package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import Dao.DaoPropositionObjet;
import Dao.DaoPropositionService;
import Dao.DaoUtilisateur;
import dbConnection.DataBaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Propositionobjet;
import model.Propositionservice;
import model.Utilisateur;
import session.SessionUtilisateur;

public class ControleurVuePoster {
	
	private DaoUtilisateur dao;
	
	private DaoPropositionObjet daoPropositionObjet;
	
	private DaoPropositionService daoPropositionService;

	

	public ControleurVuePoster() {
		dao = new DaoUtilisateur();
		daoPropositionObjet = new DaoPropositionObjet();
		daoPropositionService = new DaoPropositionService();
	}

	@FXML
	private DatePicker choixDate;
	
	@FXML
	private ToggleGroup categorie;
	
	@FXML
	private RadioButton buttonObjet;
	
	@FXML
	private RadioButton buttonService;

	@FXML
	private Button accueil;

	@FXML
	private Button annuler;
	
	@FXML
	private TextField nomObjet;
	
	@FXML
	private TextField information;
	
	@FXML
	private TextField delai;

	@FXML
	private ComboBox<Utilisateur> choixNom;
	
	@FXML
	private ComboBox<String> choixHeure;
	
	@FXML
	private ListView<Utilisateur> utilisateur;
	private ObservableList<Utilisateur> observableUtilisateur = FXCollections.observableArrayList();
	
	private ObservableList<String> lesheures = FXCollections.observableArrayList("8", "9", "10", "11", "12", "13", "14",
			"15", "16", "17", "18", "19", "20");

	private DataBaseHandler handler;
	private Connection connection;
	private PreparedStatement pst;
	
	
	@FXML
	private void initialize() {
		choixHeure.setItems(lesheures);
		choixHeure.setValue("8");
		afficherUtilisateur();
		choixNom.setItems(observableUtilisateur);
		choixDate.setValue(LocalDate.now());
		
		handler = new DataBaseHandler();
	}

	

	@FXML
	private void soumettre() {
		if(buttonObjet.isSelected()) {
			 
			Utilisateur selUtilisateur = choixNom.getSelectionModel().getSelectedItem();
		      
			
			String obj = nomObjet.getText();
			String commentaire = information.getText();
			
			// récupère la valeur saisie dans le DatePicker
			LocalDate localDate = choixDate.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			// stocke dans la variable dateDebut la date en type date
			Date dateDebut = Date.from(instant);   
			System.out.println(localDate + " / " +instant + " / " + dateDebut);
			
			String duree = delai.getText();
			
			Propositionobjet propObjet = new Propositionobjet(commentaire, dateDebut, 
					duree, obj, selUtilisateur);
			
			daoPropositionObjet.ajouterObjet(propObjet);
			System.out.println("Vous proposer l'objet : " +propObjet);
			
			Stage dialogStage = (Stage) annuler.getScene().getWindow();
			dialogStage.close();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Mise à disposition");
			alert.setContentText("Objet : " +propObjet);
			alert.show();
			
		}
	}
	
	@FXML
	private void soumettreService() {
		if(buttonService.isSelected()) {
			
			Utilisateur selUtilisateur = choixNom.getSelectionModel().getSelectedItem();
			
			String service = nomObjet.getText();
			String commentaire = information.getText();
			

			// permet de récupérer un Objet Date venant du DatePicker
			LocalDate localDate = choixDate.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			
			Date dateDebut = Date.from(instant);
			System.out.println(localDate + " / " +instant + " / " + dateDebut);
			
			
			String heure = choixHeure.getValue();
			
			Propositionservice propService = new Propositionservice(commentaire, dateDebut, 
					heure, service, selUtilisateur);
			
			daoPropositionService.ajouterService(propService);
			System.out.println("Vous proposez le service : " +propService);
			
			Stage dialogStage = (Stage) annuler.getScene().getWindow();
			dialogStage.close();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Mise à disposition");
			alert.setContentText("Objet : " +propService);
			alert.show();
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
	private void accueil() {
		Stage dialogStage = (Stage) accueil.getScene().getWindow();
		dialogStage.close();
	}

	@FXML
	private void annuler() {
		Stage dialogStage = (Stage) annuler.getScene().getWindow();
		dialogStage.close();
	}

	

}

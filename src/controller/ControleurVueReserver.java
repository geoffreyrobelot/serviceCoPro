package controller;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import Dao.DaoPropositionObjet;
import Dao.DaoPropositionService;
import Dao.DaoReservationObjet;
import Dao.DaoReservationService;
import Dao.DaoUtilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Propositionobjet;
import model.Propositionservice;
import model.Reservationobjet;
import model.Reservationservice;
import model.Utilisateur;
import session.SessionUtilisateur;

public class ControleurVueReserver {

	private DaoReservationService daoReservationService;

	private DaoReservationObjet daoReservationObjet;

	private DaoUtilisateur dao;

	private DaoPropositionObjet daoObjet;

	private DaoPropositionService daoService;

	public ControleurVueReserver() {
		dao = new DaoUtilisateur();
		daoObjet = new DaoPropositionObjet();
		daoService = new DaoPropositionService();
		daoReservationObjet = new DaoReservationObjet();
		daoReservationService = new DaoReservationService();
	}

	@FXML
	private TextArea console;
	
	@FXML
	private TextField indication;

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
	private Button buttonReserver;

	@FXML
	private Button buttonReserverService;

	@FXML
	private TextField reserver;

	@FXML
	private ComboBox<Utilisateur> choixNom;

	@FXML
	private ComboBox<Propositionobjet> choixObjet;

	@FXML
	private ComboBox<Propositionservice> choixService;

	@FXML
	private ListView<Utilisateur> utilisateur;
	private ObservableList<Utilisateur> observableUtilisateur = FXCollections.observableArrayList();

	@FXML
	private ListView<Propositionservice> service;
	private ObservableList<Propositionservice> observableService = FXCollections.observableArrayList();

	@FXML
	private ListView<Propositionobjet> objet;
	private ObservableList<Propositionobjet> observableObjet = FXCollections.observableArrayList();

	@FXML
	private void initialize() {

		// objet.setItems(observableObjet);
		// service.setItems(observableService);
		choixDate.setValue(LocalDate.now());
		// utilisateur.setItems(observableUtilisateur);
		afficherUtilisateur();
		choixNom.setItems(observableUtilisateur);
		choixObjet.setItems(observableObjet);
		choixService.setItems(observableService);
		choixDate.setValue(LocalDate.now());
		
	}
	
	@FXML
	private void afficheInfos() {
		Propositionobjet selObjet = choixObjet.getSelectionModel().getSelectedItem();
		Propositionservice selService = choixService.getSelectionModel().getSelectedItem();
		
		if (selObjet !=null) {
			// modifie le format de la date à l'affichage
			SimpleDateFormat formater = null;
			formater = new SimpleDateFormat("EEEE dd MMM yyyy");
			String dateDebutObjet = formater.format(selObjet.getDateDebut());
			console.setText(selObjet.getCommentaire()+ " à partir du " +dateDebutObjet);
		} 
		if (selService !=null) {
			SimpleDateFormat formater1 = null;
			formater1 = new SimpleDateFormat("EEEE dd MMM yyyy");
			String dateDebutService = formater1.format(selService.getDateDebut());
			console.setText(selService.getCommentaire()+ " à partir du " +dateDebutService);
		} 
	}

	@FXML
	private void reserver() {
		if (buttonObjet.isSelected()) {

			buttonReserverService.setDisable(true);
			
			Propositionobjet objetReserve = choixObjet.getSelectionModel().getSelectedItem();
			
			String objet = indication.getText();
		
			// permet de récupérer un Objet Date venant du DatePicker
			LocalDate localDate = choixDate.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date dateEmprunt = Date.from(instant);
			System.out.println(localDate + " / " + instant + " / " + dateEmprunt);

			Date dateRendu = null;
			String restitution = null;
			
			
			Utilisateur nomUtilisateur = choixNom.getSelectionModel().getSelectedItem();
			System.out.println(nomUtilisateur);
			
			Reservationobjet reservationObjet = new Reservationobjet(dateEmprunt, dateRendu, restitution, objet,
					nomUtilisateur, objetReserve);

			daoReservationObjet.ajouterObjet(reservationObjet);
			System.out.println(reservationObjet);

			Stage dialogStage = (Stage) annuler.getScene().getWindow();
			dialogStage.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Votre réservation");
			alert.setContentText("Objet " + reservationObjet);
			alert.show();

		}
	}

	@FXML
	private void reserverService() {
		if (buttonService.isSelected()) {

			buttonReserver.setDisable(true);
			
			Propositionservice serviceReserve = choixService.getSelectionModel().getSelectedItem();

			String service = indication.getText();

			// permet de récupérer un Objet Date venant du DatePicker
			LocalDate localDate = choixDate.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date dateEmpruntService = Date.from(instant);
			System.out.println(localDate + " / " + instant + " / " + dateEmpruntService);

			Utilisateur nomUtilisateurService = choixNom.getSelectionModel().getSelectedItem();
			System.out.println(nomUtilisateurService);

			Reservationservice reservationService = new Reservationservice(dateEmpruntService, service,
					nomUtilisateurService, serviceReserve);

			daoReservationService.ajouterService(reservationService);
			System.out.println(reservationService);

			Stage dialogStage = (Stage) annuler.getScene().getWindow();
			dialogStage.close();

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Votre réservation");
			alert.setContentText("Objet " + reservationService);
			alert.show();
		}
	}

	@FXML
	private void objetOuService() {
		if (buttonObjet.isSelected()) {
			objet.setItems(observableObjet);
			afficherObjet();
			if (buttonService.isSelected()) {
				service.setItems(observableService);
				afficherService();
			}
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
		observableObjet.clear();

	}

	@FXML
	private void afficherObjet() {
		observableObjet.clear();
		List<Propositionobjet> liste = daoObjet.afficherListeObjet();
		for (Propositionobjet i : liste)
			observableObjet.add(i);
		observableService.clear();

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

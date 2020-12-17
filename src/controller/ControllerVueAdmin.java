package controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import Dao.DaoPropositionObjet;
import Dao.DaoPropositionService;
import Dao.DaoReservationObjet;
import Dao.DaoReservationService;
import Dao.DaoUtilisateur;
import application.MainSignUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Propositionobjet;
import model.Propositionservice;
import model.Reservationobjet;
import model.Reservationservice;
import model.Utilisateur;

public class ControllerVueAdmin {

	private DaoUtilisateur dao;

	private DaoPropositionObjet daoPropObjet;

	private DaoPropositionService daoPropService;

	private DaoReservationObjet daoResObjet;

	private DaoReservationService daoResService;

	public ControllerVueAdmin() {
		dao = new DaoUtilisateur();
		daoPropObjet = new DaoPropositionObjet();
		daoPropService = new DaoPropositionService();
		daoResObjet = new DaoReservationObjet();
		daoResService = new DaoReservationService();

	}

	@FXML
	private TextArea console;

	@FXML
	private Button deconnexion;

	@FXML
	private TableView<Utilisateur> tableau;

	@FXML
	private TableColumn<Utilisateur, Integer> num;

	@FXML
	private TableColumn<Utilisateur, String> nom;

	@FXML
	private TableColumn<Utilisateur, String> genre;

	@FXML
	private TableColumn<Utilisateur, String> email;

	@FXML
	private TableColumn<Utilisateur, String> batiment;

	@FXML
	private TableColumn<Utilisateur, String> appartement;

	private ObservableList<Utilisateur> observableUtilisateur = FXCollections.observableArrayList();
	private ObservableList<Propositionobjet> observableObjet = FXCollections.observableArrayList();
	private ObservableList<Propositionservice> observableService = FXCollections.observableArrayList();
	private ObservableList<Reservationservice> observableResService = FXCollections.observableArrayList();
	private ObservableList<Reservationobjet> observableResObjet = FXCollections.observableArrayList();

	@FXML
	private void initialize() {

		tableau.setItems(observableUtilisateur);
		afficherUtilisateur();

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
		List<Propositionservice> liste = daoPropService.afficherListeService();
		for (Propositionservice i : liste)
			observableService.add(i);

	}

	@FXML
	private void afficherObjet() {
		observableObjet.clear();
		List<Propositionobjet> liste = daoPropObjet.afficherListeObjet();
		for (Propositionobjet i : liste)
			observableObjet.add(i);

	}

	@FXML
	private void utilisateur() {
		num.setCellValueFactory(new PropertyValueFactory<Utilisateur, Integer>("num"));
		nom.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("nom"));
		genre.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("genre"));
		email.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("email"));
		batiment.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("batiment"));
		appartement.setCellValueFactory(new PropertyValueFactory<Utilisateur, String>("appartement"));
		observableUtilisateur.clear();
		tableau.setItems(observableUtilisateur);
		afficherUtilisateur();

	}

	@FXML
	private void serviceProp() {
		console.setText("En cours de développement");
	}

	@FXML
	private void objetProp() {
		console.setText("En cours de développement");
	}

	@FXML
	private void serviceRes() {
		console.setText("En cours de développement");
	}

	@FXML
	private void objetRes() {
		console.setText("En cours de développement");
	}

	public void setMainSignUp(MainSignUp mainSignUp) {
	}

	@FXML
	private void ajouter() throws IOException {
		Stage ajouter = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/view/VueSignUp.fxml"));
		Scene scene = new Scene(root);
		ajouter.setScene(scene);
		ajouter.show();
		ajouter.setResizable(false);
		
		afficherUtilisateur();
	}

	@FXML
	private void supprimer() {
		Utilisateur selUtilisateur = tableau.getSelectionModel().getSelectedItem();

		if (selUtilisateur != null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText("Attention");
			alert.setContentText("Etes vous sûr de vouloir supprimer cet utilisateur ? ");
			Optional<ButtonType> result = alert.showAndWait();

			if (result.get() == ButtonType.OK) {
				dao.supprimerUtilisateur(selUtilisateur);
				console.setText(
						"Vous avez supprimé : " + selUtilisateur.getNom() + " email : " + selUtilisateur.getEmail());
				System.out.println(
						"Vous avez supprimé : " + selUtilisateur.getNom() + " email : " + selUtilisateur.getEmail());
				observableUtilisateur.clear();
				afficherUtilisateur();

			} else {
				}
			
		}else {
			Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setHeaderText("Suppression utilisateur");
			alert1.setContentText("Aucun utilisateur séléctionée");
			alert1.showAndWait();
			System.out.println("Aucun utilisateur sélectionné");
		}
	}

	@FXML
	private void deconnexion() {
		Stage dialogStage = (Stage) deconnexion.getScene().getWindow();
		dialogStage.close();
	}
	
	@FXML 
	private void modifier() throws IOException {
		Utilisateur user = tableau.getSelectionModel().getSelectedItem();
		if (user !=null) {
			
			Stage ajouter = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/view/VueSignUp.fxml"));
			Scene scene = new Scene(root);
			ajouter.setScene(scene);
			ajouter.show();
			ajouter.setResizable(false);
			
			
			
			
			afficherUtilisateur();
		}
	}

}

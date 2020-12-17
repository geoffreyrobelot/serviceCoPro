package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurVueRestitution {

	public ControleurVueRestitution() {
		
	}
	
	@FXML
	private Button accueil;
	
	@FXML
	private Button annuler;

	
	
	@FXML
	private void initialize() {
		
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

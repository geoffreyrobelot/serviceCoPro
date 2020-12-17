package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControleurVueMonCompte {
	
	public ControleurVueMonCompte() {
		
	}

	@FXML
	private Button accueil;
	

	@FXML 
	private void initialize() {
		
	}
	
	@FXML
	private void accueil() {
		Stage dialogStage = (Stage) accueil.getScene().getWindow();
		dialogStage.close();
	}
	
	
	
}

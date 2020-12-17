package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import application.MainAdmin;
import dbConnection.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ControllerVueConnexionAdmin {
	
	private MainAdmin mainAdmin;
	
	private DataBaseHandler handler;
	private Connection connection;
	private PreparedStatement pst;

	
	public ControllerVueConnexionAdmin() {
		
	}
	
	@FXML
	private Button connexion;
	
	@FXML
	private Button annuler;
	
	@FXML
	private TextField nomAdmin;
	
	@FXML
	private PasswordField mdpAdmin;
	
	@FXML
	private void initialize() {
		handler = new DataBaseHandler();
	}
	
	
	public void setMainAdmin(MainAdmin mainAdmin) {
		this.mainAdmin = mainAdmin;
	}
	
	@FXML
	private void connexion() throws IOException {
		connection = handler.getConnection();
		String sql = "SELECT * FROM Administrateur WHERE login=? AND mdp=?";
		
		try {
			pst = connection.prepareStatement(sql);
			pst.setString(1, nomAdmin.getText());
			pst.setString(2, mdpAdmin.getText());
			ResultSet rs = pst.executeQuery();

			int count = 0;

			while (rs.next()) {

				count = count + 1;

			}
			if (count == 1) {

				System.out.println("Connexion réussie");
				
				Stage admin = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/view/VueAdmin.fxml"));
				Scene scene = new Scene(root);
				admin.setScene(scene);
				admin.show();
				admin.setResizable(false);
				
				connexion.getScene().getWindow().hide();
				
			} else {
				System.out.println("Vous n'avez pas les droits");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Vous n'êtes pas administrateur");
				Optional<ButtonType> result = alert.showAndWait();

				if (result.get() == ButtonType.OK) {
					connexion.getScene().getWindow().hide();
				}
				alert.show();
				
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void annuler() {
		Stage dialogStage = (Stage) annuler.getScene().getWindow();
		dialogStage.close();
	}
	
}

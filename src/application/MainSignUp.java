package application;

import java.io.IOException;

import controller.ControleurVueLogin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSignUp {
	
	private Stage primaryStage;
	
	public MainSignUp(Stage stage) {
		primaryStage = stage;
	}
	
	public void montrerLaVue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControleurVueLogin.class.getResource("/view/VueSignUp.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Sign Up");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(false);

			Scene scene = new Scene(page, 700, 450);
			dialogStage.setScene(scene);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

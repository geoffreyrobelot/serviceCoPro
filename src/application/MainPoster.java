package application;

import java.io.IOException;
import controller.ControleurVueLogin;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainPoster {

	private Stage primaryStage;
	
	public MainPoster(Stage stage) {
		primaryStage = stage;
	}
	
	public void montrerLaVue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControleurVueLogin.class.getResource("/view/VuePoster.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Poster");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(false);

			Scene scene = new Scene(page, 800, 500);
			dialogStage.setScene(scene);
			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

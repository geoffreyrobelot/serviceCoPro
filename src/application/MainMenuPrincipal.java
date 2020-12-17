package application;

import java.io.IOException;
import controller.ControleurVueLogin;
import controller.ControleurVueMenuPrincipal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuPrincipal {

	private Stage primaryStage;
	
	public MainMenuPrincipal(Stage stage) {
		primaryStage = stage;
	}
	
	public void montrerLaVue() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControleurVueLogin.class.getResource("/view/VueMenuPrincipal.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Menu principal");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			dialogStage.setResizable(false);

			Scene scene = new Scene(page, 800, 500);
			dialogStage.setScene(scene);
			
			
			
			MainReserver mainReserver = new MainReserver(primaryStage);
			ControleurVueMenuPrincipal controleur1 = loader.getController();
			controleur1.setMainReserver(mainReserver);
			
			MainPoster mainPoster = new MainPoster(primaryStage);
			ControleurVueMenuPrincipal controleur2 = loader.getController();
			controleur2.setMainPoster(mainPoster);
			
			MainMonCompte mainMonCompte = new MainMonCompte(primaryStage);
			ControleurVueMenuPrincipal controleur3 = loader.getController();
			controleur3.setMainMonCompte(mainMonCompte);
			
			MainRestitution mainRestitution = new MainRestitution(primaryStage);
			ControleurVueMenuPrincipal controleur4 = loader.getController();
			controleur4.setMainRestitution(mainRestitution);
			
			MainAdmin mainAdmin = new MainAdmin(primaryStage);
			ControleurVueMenuPrincipal controleur5 = loader.getController();
			controleur5.setMainAdmin(mainAdmin);
			
			MainConnexionAdmin mainConnexionAdmin = new MainConnexionAdmin(primaryStage);
			ControleurVueMenuPrincipal controleur6 = loader.getController();
			controleur6.setMainConnexionAdmin(mainConnexionAdmin);
			
			
			
			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

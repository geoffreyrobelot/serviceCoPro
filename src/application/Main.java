package application;

import controller.ControleurVueLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/view/VueLogin1.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("styleLogin.css").toExternalForm());
			primaryStage.setTitle("ServiceCoPro");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			
			MainMenuPrincipal mainMenuPrincipal = new MainMenuPrincipal(primaryStage);
			ControleurVueLogin controleur = loader.getController();
			controleur.setMainMenuPrincipal(mainMenuPrincipal);
			
			MainSignUp mainSignUp = new MainSignUp(primaryStage);
			ControleurVueLogin controleurSignUp = loader.getController();
			controleurSignUp.setMainSignUp(mainSignUp);
			
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}

package controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.MainMenuPrincipal;
import application.MainSignUp;
import dbConnection.DataBaseHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
import model.Utilisateur;
import password.ClassCrypto;
import password.Crypto;
import password.Encrypt;
import password.MyBlowfish;
import session.SessionUtilisateur;

public class ControleurVueLogin {

	private MainMenuPrincipal mainMenuPrincipal;

	private MainSignUp mainSignUp;

	/*
	 * constructeur de la classe
	 */
	public ControleurVueLogin() {

	}

	@FXML
	private TextField username;

	@FXML
	private PasswordField mdp;

	@FXML
	private Label statut;

	@FXML
	private Button signUp;

	@FXML
	private Button mdpOublie;

	private DataBaseHandler handler;
	private Connection connection;
	private PreparedStatement pst;

	@FXML
	private void initialize() {
		handler = new DataBaseHandler();
	}

	/*
	 * accesseur référence sur l'objet de type MainMenuPrincipal
	 */
	public void setMainMenuPrincipal(MainMenuPrincipal mainMenuPrincipal) {
		this.mainMenuPrincipal = mainMenuPrincipal;
	}

	public void setMainSignUp(MainSignUp mainSignUp) {
		this.mainSignUp = mainSignUp;
	}

	private boolean isEmpty() {

		String Login = username.getText();
		String Mdp = mdp.getText();
		if (Login.length() < 1 || Mdp.length() < 1) {

			statut.setTextFill(Color.RED);
			statut.setText("Saisir vos identifiants de connexion");
			if (Login.length() < 1)
				username.setPromptText(".....");
			mdp.setPromptText(".....");

			if (Mdp.length() < 1)
				mdp.setPromptText("....");
			mdp.setPromptText("....");

			return false;
		} else
			return true;
	}

	@FXML
	private void login() throws GeneralSecurityException, IOException, InstantiationException, IllegalAccessException {

		if (isEmpty()) {

			connection = handler.getConnection();
			String sql = "SELECT * FROM Utilisateur WHERE nom=? AND mdp=?";

			try {
				pst = connection.prepareStatement(sql);

				pst.setString(1, username.getText());

				String code = mdp.getText();
				String encrypt = new String(Encrypt.encryptPassword(code));
				
				// cryptage du mot de passe utilisateur
				//Crypto crypto = new ClassCrypto();
				//String encrypt = new String(crypto.encrypt(mdp.getText().getBytes()));
				// String decrypt = new String(crypto.decrypt(encrypt.getBytes()));
				System.out.println(encrypt);

				pst.setString(2, encrypt);

				ResultSet rs = pst.executeQuery();

				int count = 0;

				while (rs.next()) {

					count = count + 1;

				}
				if (count == 1) {

					System.out.println("Connexion réussie");
					statut.setTextFill(Color.GREEN);
					statut.setText("Connexion réussie");

					SessionUtilisateur identite = SessionUtilisateur.getInstance(username.getText(), encrypt);
					String iden = SessionUtilisateur.getUsername();
					System.out.println(iden);
					System.out.println(identite);

					KeyFrame kf = new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							statut.setText("Chargement ... ");

						}

					});

					KeyFrame kf1 = new KeyFrame(Duration.seconds(1.5), new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							mainMenuPrincipal.montrerLaVue();
							SessionUtilisateur su = new SessionUtilisateur(username.getText(), mdp.getText());
							su.toString();
						}

					});

					KeyFrame kf2 = new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							statut.setText("");
							username.setText("");
							mdp.setText("");

						}

					});

					Timeline tl = new Timeline(kf, kf1, kf2);
					tl.play();

				} else {
					System.out.println("Erreur identifiant ou mot de passe");
					statut.setTextFill(Color.RED);
					statut.setText("Identifiant ou mdp incorrect");
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	protected void infoSession(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("loginuser");
		String password = request.getParameter("mdp");

		Utilisateur user = new Utilisateur(login, password);
		HttpSession maSession = request.getSession();
		maSession.setAttribute("utilisateur", user);
		System.out.println(maSession);

		RequestDispatcher dispatcher = request.getRequestDispatcher("VueMenuPrincipal.fxml");
		dispatcher.forward(request, response);
	}

	@FXML
	private void signUp() {
		mainSignUp.montrerLaVue();
	}

	@FXML
	private void mdpOublie() {
		System.out.println("Méthode en cours de développement");
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Information");
		alert.setContentText("Méthode en cours de développement, merci de votre compréhension");
		alert.show();
	}

}

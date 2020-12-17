package controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.EntityManager;
import Dao.DaoUtilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Utilisateur;
import password.ClassCrypto;
import password.Crypto;
import password.Encrypt;
import password.MyBlowfish;

public class ControleurVueSignUp {

	private DaoUtilisateur dao;

	public EntityManager em;

	public ControleurVueSignUp() {

		dao = new DaoUtilisateur();
	}

	@FXML
	private ToggleGroup genre;

	@FXML
	private RadioButton homme;

	@FXML
	private RadioButton femme;

	@FXML
	private TextField utilisateur;

	@FXML
	private PasswordField mdp;
	
	@FXML 
	private PasswordField mdp1;

	@FXML
	private TextField email;

	@FXML
	private TextField batiment;

	@FXML
	private TextField appartement;

	@FXML
	private Button annuler;

	@FXML
	private void initialize() {

	}
	
	/*
	 * m�thode qui v�rifie si les champs du formulaire sont corrects 
	 */
	private boolean isEmpty() throws SQLException {
		boolean Bouton = (homme.isSelected() || femme.isSelected());
		String Nom = utilisateur.getText();
		String Mdp = mdp.getText();
		String Mdp1 = mdp1.getText();
		String Mail = email.getText();
		// v�rifie le format de mail saisi par l'utilisateur
		String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
				+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(Mail);
		
		String Bat = batiment.getText();
		String Apt = appartement.getText();
		if (Bouton == false) {
			System.out.println("Saisir votre genre");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Veuillez s�lectionner votre genre");
			alert.showAndWait();
			return false;
		}
		if (Nom.length()<3) {
			System.out.println("Le nom d'utilisateur doit contenir au moins 3 caract�res");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Le nom d'utilisateur doit contenir au moins 3 caract�res");
			alert.showAndWait();
			return false;
		}
		if (!controler.matches()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Le mail saisi est incorrect");
			alert.showAndWait();
			System.out.println("le mail est incorrect");
			return false;
		} 
		if (Mdp.length()<6) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Le mot de passe doit contenir au moins 6 caract�res");
			alert.showAndWait();
			System.out.println("le mot de passe doit contenir au moins 6 caract�res");
			return false;
		}
		
		if (!Mdp1.equals(Mdp)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Les mots de passe sont diff�rents");
			alert.showAndWait();
			System.out.println("Les mots de passe sont diff�rents");
			return false;
		}
		
		if(Bat.length()<1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrez un caract�re pour indiquer votre b�timent");
			alert.showAndWait();
			System.out.println("Entrez un caract�re pour indiquer votre b�timent");
			return false;
		}
		if(Apt.length()<1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Entrez un caract�re pour indiquer votre appartement");
			alert.showAndWait();
			System.out.println("Entrez un caract�re pour indiquer votre appartement");
			return false;
		} else {
			return true;
		}
	}

	@FXML
	public void creer() throws GeneralSecurityException, IOException, SQLException {

		if (isEmpty()) {
			// si le radiobutton "homme" est s�l�ctionn�
			if (homme.isSelected()) {
				String genreButton = "Homme";
				String nom = utilisateur.getText();
				
				String code = mdp.getText();
				String encrypt = new String(Encrypt.encryptPassword(code));
				System.out.println(encrypt);
				
				
				// cryptage du mot de passe saisi dans le formulaire
				//Crypto crypto = new ClassCrypto();
				//String code = mdp.getText();
				//String encrypt = new String(crypto.encrypt(code.getBytes()));
				
				
				
				String mail = email.getText();
				String bat = batiment.getText();
				String apt = appartement.getText();
				// cr�er un utilisateur avec comme param�tres les donn�es du formulaire
				Utilisateur utilisateur = new Utilisateur(genreButton, nom, encrypt, bat, apt, mail);
				// v�rifie si l'utilisateur existe dans la base de donn�es
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servicecopro", "admin",
						"admin");
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// requ�te dans la base de donn�es
				ResultSet rs = stmt.executeQuery("SELECT * FROM Utilisateur WHERE nom like '" + nom + "'");

				if (rs.next()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("L'utilisateur : " + nom + " existe d�j�");
					alert.setContentText("Veuillez choisir un autre nom d'utilisateur");
					alert.show();
					System.out.println("l'utilisateur existe");
				} else {
					// persiste l'utilisateur dans la base de donn�es
					dao.ajouterUtilisateur(utilisateur);
					System.out.println("ajout: " + utilisateur + " / mot de passe : " + encrypt);
					Stage dialogStage = (Stage) annuler.getScene().getWindow();
					dialogStage.close();
				}

			}

			// si le radiobutton "femme" est s�l�ctionn�
			if (femme.isSelected()) {
				String genreButton = "Femme";
				String nom = utilisateur.getText();

				
				String code = mdp.getText();
				String encrypt = new String(Encrypt.encryptPassword(code));
				System.out.println(encrypt);
				
				//Crypto crypto = new ClassCrypto();
				//String code = mdp.getText();
				//String encrypt = new String(crypto.encrypt(code.getBytes()));
				//String decrypt = new String(crypto.decrypt(encrypt.getBytes()));

				String mail = email.getText();
				String bat = batiment.getText();
				String apt = appartement.getText();

				Utilisateur utilisateur = new Utilisateur(genreButton, nom, encrypt, bat, apt, mail);

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servicecopro", "admin",
						"admin");
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				ResultSet rs = stmt.executeQuery("SELECT * FROM Utilisateur WHERE nom like '" + nom + "'");
				if (rs.next()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText("L'utilisateur : " + nom + " existe d�j�");
					alert.setContentText("Veuillez choisir un autre nom d'utilisateur");
					alert.show();
					System.out.println("l'utilisateur existe");
				} else {
					dao.ajouterUtilisateur(utilisateur);
					System.out.println("ajout: " + utilisateur + " / mot de passe : " + encrypt);
					Stage dialogStage = (Stage) annuler.getScene().getWindow();
					dialogStage.close();
				}

			}
		}
	}

	@FXML
	private void annuler() throws Exception {
		Stage dialogStage = (Stage) annuler.getScene().getWindow();
		dialogStage.close();
	}

	public static void hashPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[32];
		random.nextBytes(salt);

		String password = "Bonjour1";
		System.out.println("code en clair : " + password);

		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		Provider p = factory.getProvider();
		System.out.println(p);
		SecretKey secret = factory.generateSecret(spec);
		byte[] hash = secret.getEncoded();
		System.out.println("hash code: " + hash.length + " " + bytesToHex(hash));
		salt = spec.getSalt();
		System.out.println("salt : " + salt.length + " " + bytesToHex(salt));
		String encrypt = bytesToHex(salt) + bytesToHex(hash);
		System.out.println(encrypt);

	}

	public static String bytesToHex(byte[] b) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buffer = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buffer.append(hexDigits[(b[j] >> 4) & 0x0f]);
			buffer.append(hexDigits[b[j] & 0x0f]);
		}
		return buffer.toString();
	}

}

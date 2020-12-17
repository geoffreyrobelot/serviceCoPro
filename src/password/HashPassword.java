package password;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashPassword {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

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
		String decrypt = "";
		System.out.println(decrypt);

		
	}
	
	public static String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[32];
		random.nextBytes(salt);

		//String password = "Bonjour1";
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
		return encrypt;
		
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
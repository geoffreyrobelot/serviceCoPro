package password;

public class MainCrypto {

	public static void main (String [] args) {
		Crypto crypto = new ClassCrypto();
		
		String data = "Azerty1";
		String encrypt = new String (crypto.encrypt(data.getBytes()));
		String decrypt = new String (crypto.decrypt(encrypt.getBytes()));
		
		System.out.println("clair : " +data);
		System.out.println(encrypt);
		System.out.println(decrypt);
	}
}

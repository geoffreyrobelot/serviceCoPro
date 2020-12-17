package password;

public class ClassCrypto implements Crypto {

	@Override
	public byte[] encrypt(byte[] data) {
		byte[] encrypt = new byte[data.length];

		for (int i = 0; i < data.length; i++) {
			encrypt[i] = (byte) ((i % 2 == 0) ? data[i] + 1 : data[i] - 1);
		}
		return encrypt;
	}
 
	@Override
	public byte[] decrypt(byte[] data) {
		byte[] encrypt = new byte[data.length];

		for (int i = 0; i < data.length; i++) {
			encrypt[i] = (byte) ((i % 2 == 0) ? data[i] - 1 : data[i] + 1);
		}
		return encrypt;
	}

}

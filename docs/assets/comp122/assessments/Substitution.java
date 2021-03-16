/**
 * An abstract class that provides stubs for single character substitution and
 * uses those stubs to provide string encryption.
 *
 * @author Ben Weston
 * @version 1.0 
 */
public abstract class Substitution implements Cipher{
	/**
	 * An abstract method for encrypting a single character.
	 *
	 * @param c The character to be Encrypted.
	 * @return The encrypted character.
	 */
	public abstract char encrypt(char c);
	/**
	 * An abstract method for decrypting a single character.
	 *
	 * @param c The character to be decrypted.
	 * @return The decrypted character.
	 */
	public abstract char decrypt(char c);
	/**
	 * A method that uses {@link #encrypt(char c)} to encrypt a whole
	 * string.
	 *
	 * @param plaintext The input string to be converted to cipher-text.
	 * @return The encrypted string.
	 */
	public String encrypt(String plaintext){
		String cryptotext = "";
		for (int i = 0; i < plaintext.length(); i++)
			cryptotext += encrypt(plaintext.charAt(i));
		return cryptotext;
	}
	/**
	 * A method that uses {@link #decrypt(char c)} to decrypt a whole
	 * string.
	 *
	 * @param cryptotext The string to be converted back to plain-text.
	 * @return The decrypted string.
	 */
	public String decrypt(String cryptotext){
		String plaintext = "";
		for (int i = 0; i < cryptotext.length(); i++)
			plaintext += decrypt(cryptotext.charAt(i));
		return plaintext;
	}
}

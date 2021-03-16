/**
 * An interface that declares methods related to encoding/decoding.
 *
 * @author Patrick Totzke
 */
public interface Cipher {
	/**
	 * Encodes the given plain text into a secret cipher text.
	 *
	 * @param plainText the plain text to encode
	 * @return the cipher text
	 */
	public String encrypt(String plainText);
	/**
	 * Determines the plain text string for a given cipher text.
	 *
	 * @param cipherText the cipher text to decode
	 * @return the plain text original
	 */
	public String decrypt(String cipherText);
}

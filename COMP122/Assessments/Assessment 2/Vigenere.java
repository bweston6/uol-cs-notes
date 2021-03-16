/**
* Extension of {@link Substitution} that implements Vigenere encryption.
*
* @author Ben Weston
* @version 1.0
*/
public class Vigenere extends Substitution{
	private String ciphers = "";
	private int progress = 0;
	/**
	 * Tests the input parameters for validity and returns
	 * {@link #encrypt(String s)} or {@link #decrypt(String s)} depending
	 * on the input.
	 *
	 * @param args The list of arguments. Argument 1 is {@code encrypt} or
	 * {@code decrypt}, argument 2 is the encryption string and argument 3 is
	 * the cipher-text.
	 */
	public static void main(String[] args){
		if (args.length == 3) {
			if (args[0].equals("encrypt")) {
				Vigenere v = new Vigenere(args[1]);
				System.out.println(v.encrypt(args[2]));
			}
			else if (args[0].equals("decrypt")) {
				Vigenere v = new Vigenere(args[1]);
				System.out.println(v.decrypt(args[2]));
			}
			else {
				System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
				System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
			}
		}
		else {
			if (args.length < 3)
				System.out.println("Too few parameters!");
			else if (args.length > 3)
				System.out.println("Too many parameters!");
			System.out.println("Usage: java Vigenere encrypt key \"cipher text\"");
		}
	}
	/**
	* Sets an encryption key of 'a' which gives no offset. 
	*/
	public Vigenere(){
		ciphers = "a"; // gives no offset
	}
	/**
	* Converts the encryption key to lowercase and stores it in the 
	* {@code ciphers} variable.
	* 
	* @param key The encription key. Each letter acts as an offset.
	*/
	public Vigenere(String key){
		ciphers = key.toLowerCase(); // cipher is always lowercase
	}
	/**
	 * An overriding method to encrypt a single char using this encryption
	 * scheme.
	 *
	 * @param c The character to be encrypted.
	 * @return The encrypted character.
	 */
	public char encrypt(char c){
		int location;
		if (Character.isUpperCase(c)){
			location = (int)(c - 65);
			location = (location + (int)(ciphers.charAt(progress % ciphers.length())) - 97) % 26;
			c = (char)(location + 65);
		}
		else if (Character.isLowerCase(c)){
			location = (int)(c - 97);
			location = (location + (int)(ciphers.charAt(progress % ciphers.length())) - 97) % 26;
			c = (char)(location + 97);
		}
		progress++;
		return c;
	}
	/**
	 * An overriding method to decrypt a single char using this decryption
	 * scheme.
	 *
	 * @param c The character to be decrypted.
	 * @return The decrypted character.
	 */
	public char decrypt(char c){
		int location;
		if (Character.isUpperCase(c)){
			location = (int)(c - 65);
			location = (location - ((int)(ciphers.charAt(progress % ciphers.length())) - 97)) % 26;
			if (location < 0){
				location = 26 + location; // Convert to positive offset
			}
			c = (char)(location + 65);
		}
		else if (Character.isLowerCase(c)){
			location = (int)(c - 97);
			location = (location - ((int)(ciphers.charAt(progress % ciphers.length())) - 97)) % 26;
			if (location < 0){
				location = 26 + location; // Convert to positive offset
			}
			c = (char)(location + 97);
		}
		progress++;
		return c;
	}
}

/**
 * Provides encryption using a lookup table.
 *
 * @author Ben Weston
 * @version 1.0
 */
public class MonoAlphaSubstitution extends Substitution{
	/**
	 * Tests the input parameters for validity and returns
	 * {@link #encrypt(String s)} or {@link #decrypt(String s)} depending
	 * on the input.
	 *
	 * @param args The list of arguments. Argument 1 is {@code encrypt} or
	 * {@code decrypt}, argument 2 is the mapping string and argument 3 is
	 * the cipher-text.
	 */
	private char[][] mapping = new char[26][2];
	public static void main(String[] args) {
		if (args.length == 3) {
			if (args[0].equals("encrypt")) {
				MonoAlphaSubstitution m = new MonoAlphaSubstitution(args[1]);
				System.out.println(m.encrypt(args[2]));
			}
			else if (args[0].equals("decrypt")) {
				MonoAlphaSubstitution m = new MonoAlphaSubstitution(args[1]);
				System.out.println(m.decrypt(args[2]));
			}
			else {
				System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
				System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
			}
		}
		else {
			if (args.length < 3)
				System.out.println("Too few parameters!");
			else if (args.length > 3)
				System.out.println("Too many parameters!");
			System.out.println("Usage: java MonoAlphaSubstitution encrypt key \"cipher text\"");
		}	
	}
	/**
	 * Sets up a 1:1 mapping for each letter in the alphabet.
	 */
	public MonoAlphaSubstitution(){
		for (int i = 0; i < 26; i++) {
			mapping[i][0] = "abcdefghijklmnopqrstuvwxyz".charAt(i);
			mapping[i][1] = "abcdefghijklmnopqrstuvwxyz".charAt(i);
		}
	}
	/**
	 * Sets up a mapping based on an input string.
	 *
	 * <p>Each pair of letters in the input string is considered a mapping.
	 * If there is more than one for the same letter then the last is taken.
	 * If the input is capitalised or lower-case then this must be
	 * maintained.</p>
	 *
	 * <p>This is then stored as an offset from 0 to 25 in the mapping
	 * array. The shortest mapping is always chosen.</p>
	 *
	 * @param s The string to be converted into the mapping. Every character
	 * at an odd position is the encoding of the one directly before it. 
	 */
	public MonoAlphaSubstitution(String s) {
		MonoAlphaSubstitution m = new MonoAlphaSubstitution(); // set 1:1 mapping
		mapping = m.mapping;
		int offset;
		for (int i = 0; i < s.length(); i += 2) {
			if (s.charAt(i) < 97) // test if upper-case
				offset = 65; // decimal Unicode A
			else
				offset = 97; // decimal Unicode a
			mapping[(int)(s.charAt(i) - offset)][1] = (char)(s.charAt(i + 1) - offset + 97); // make lower-case
		}
	}
	/**
	 * An overriding method to encrypt a single char using this encryption
	 * scheme.
	 *
	 * @param c The character to be encrypted.
	 * @return The encrypted character.
	 */
	public char encrypt(char c) {
		if (Character.isUpperCase(c)) {
			c = (char)(mapping[(int)(c - 65)][1] - 32); // convert lower-case to upper
		}
		else if (Character.isLowerCase(c)) {
			c = mapping[(int)(c - 97)][1];
		}
		return c;
	}
	/**
	 * An overriding method to decrypt a single char using this decryption
	 * scheme.
	 *
	 * @param c The character to be decrypted.
	 * @return The decrypted character.
	 */
	public char decrypt(char c) {
		if (Character.isUpperCase(c) || Character.isLowerCase(c)){
			boolean upper = false;
			if (Character.isUpperCase(c)) {
				upper = true;
				c += 32; // convert to lower-case
			}
			boolean flag = false;
			int i = 0;
			while (flag == false && i < 26) {
				if (c == mapping[i][1])
					flag = true;
				else
					i++;
			}
			if (flag)
				c = mapping[i][0];
			if (upper)
				c -=32; // back to upper
		}
		return c;
	}
}

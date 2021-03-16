/**
* Extension of {@link MonoAlphaSubstitution} that implements Caesar shift
* encryption.
*
* @author Ben Weston
* @version 1.0
*/
public class Caesar extends MonoAlphaSubstitution{
	private int shift;
	private char[][] mapping = new char[26][2];
	/**
	* A constructor to give a zero shift.
	*/
	public Caesar(){
		shift = 0;
		MonoAlphaSubstitution m = new MonoAlphaSubstitution(); // set 1:1 mapping
		mapping = m.getMapping();
	}
	/**
	* A constructor to initialise the shift based on the input {@code key}.
	*
	* @param key The amount to shift the input by.
	*/
	public Caesar(int key){
		shift = key;
		if (shift < 0){
			shift %= 26; // Account for wraparound
			shift = 26 + shift; // Convert to positive offset
		}
		for (int i = 0; i < 26; i++){
			mapping[i][0] = "abcdefghijklmnopqrstuvwxyz".charAt(i);
			mapping[i][1] = "abcdefghijklmnopqrstuvwxyz".charAt((i + shift) % 26);
		}
	}
	/**
	 * Tests the input parameters for validity and returns
	 * {@link #encrypt(String s)} or {@link #decrypt(String s)} depending
	 * on the input.
	 *
	 * @param args The list of arguments. Argument 1 is {@code encrypt} or
	 * {@code decrypt}, argument 2 is the shift key and argument 3 is
	 * the cipher-text.
	*/
	public static void main(String[] args){
		if (args.length == 3) {
			if (args[0].equals("encrypt")) {
				Caesar c = new Caesar(Integer.parseInt(args[1]));
				System.out.println(c.encrypt(args[2]));
			}
			else if (args[0].equals("decrypt")) {
				Caesar c = new Caesar(Integer.parseInt(args[1]));
				System.out.println(c.decrypt(args[2]));
			}
			else {
				System.out.println("The first parameter must be \"encrypt\" or \"decrypt\"!");
				System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
			}
		}
		else {
			if (args.length < 3)
				System.out.println("Too few parameters!");
			else if (args.length > 3)
				System.out.println("Too many parameters!");
			System.out.println("Usage: java Caesar encrypt n \"cipher text\"");
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

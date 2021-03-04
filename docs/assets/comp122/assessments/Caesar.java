/**
 * A command line tool to apply a shift cipher to an input string, given any
 * shift integer.
 * 
 * <p>Usage: Caesar {@code n} "cipher text"</p>
 *
 * @author Ben Weston
 * @version 1.0
 */
public class Caesar {
	/**
	 * Applies a shift cipher to the cipher text given a shift integer {@code n}.
	 *
	 * <p>This code will validate the number of arguments and provide the
	 * user with feedback if their input in incorrect.</p>
	 *
	 * <p>If the input is correct then this function will convert the inputs
	 * to appropriate data types and then pass them on to the function 
	 *  rotate().</p>
	 *
	 * @param args {@code args[0]} is the rotation as a {@code String} and {@code args[1]}
	 * is the text to be rotated.
	 */
	public static void main(String[] args) {
		if (args.length == 2){
			int shift = Integer.parseInt(args[0]); // Convert string to int
			System.out.println(rotate(shift, args[1]));
		}
		else if (args.length > 2) {
			System.out.println("Too many parameters!");
			System.out.println("Usage: java Caesar n \"cipher text\"");
		}
		else {
			System.out.println("Too few parameters!");
			System.out.println("Usage: java Caesar n \"cipher text\"");
		}

	}
	/**
	 * Rotates a string into cipher text by the given {@code shift} value.
	 *
	 * <p> Rotation is achieved by starting with an empty string and
	 * iterating through each letter of the input string. This letter is
	 * passed to {@code rotate(int, char)} and appended onto the 
	 * resultant string.</p>
	 *
	 * @param shift An integer value to shift the text by. Can be positive 
	 * or negative and will wrap around if it exceeds the alphabet.
	 * @param text The string to be converted to cipher text.
	 * @return The shifted cipher text.
	 */
	public static String rotate(int shift, String text) {
		String shiftText = "";
		for (int i = 0; i < text.length(); i++)
		       shiftText += rotate(shift, text.charAt(i));
		return shiftText;
	}
	/**
	 * Rotates a single char by the given {@code shift} value.
	 *
	 * <p>Rotation is achieved by first converting the {@code shift} value
	 * to a positive offset. It is then checked whether the character is
	 * upper-case or lower; if it is neither then the original character is
	 * returned.</p>
	 * <p>For upper-case the new character is calculated as a positive
	 * offset from A mod 26. This offset is then added to A and the
	 * character is returned.</p>
	 * <p> For lower-case the new character is calculates as a positive
	 * offset from a mod 26. This offset is then added to a and the
	 * character is returned.</p>
	 * 
	 * @param shift An integer value to shift the text by. Can be positive
	 * or negative and will wrap around if it exceeds the alphabet.
	 * @param letter A char to be shifted.
	 * @return In the case of a letter - the shifted letter will be
	 *  returned. For all other characters, the original character is 
	 *  returned.
	 */
	public static char rotate(int shift, char letter) {
		if (shift < 0)
			shift %= 26; // Account for wraparound
			shift = 26 + shift; // Convert to positive offset
		if (Character.isUpperCase(letter)) {
			int position = (int)((letter + shift - 65) % 26); // Calculate new position
			letter = (char)('A' + position); // Apply new position as an offset on 'A'
		}
		else if (Character.isLowerCase(letter)) {
			int position = (int)((letter + shift - 97) % 26); // Calculate new position
			letter = (char)('a' + position); // Apply new position as an offset on 'a'
		}
		return letter;
	}
}

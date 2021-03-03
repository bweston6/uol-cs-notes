public class Caesar {
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
	public static String rotate(int shift, String text) {
		String shiftText = "";
		for (int i = 0; i < text.length(); i++)
		       shiftText += rotate(shift, text.charAt(i));
		return shiftText;
	}
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

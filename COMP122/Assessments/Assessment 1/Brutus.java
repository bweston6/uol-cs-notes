/**
 * A command line tool to brute force a rotational cipher text, by using the chi-squared law and 
 * letter frequencies for English.
 *
 * @author Ben Weston
 * @version 1.0 
 */
public class Brutus {
	/**
	 * A set array of the frequencies of each letter in English language.
	 */
	public static final double[] english = {
        	0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        	0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        	0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    	};
	/**
	 * Takes a single string argument and attempts to brute force this
	 * cipher-text as English.
	 *
	 * <p> The method first checks the number of arguments and prompts the
	 * user with an example if the arguments are not acceptable.</p>
	 * <p> If there is only one argument then the function will iterate
	 * through all 26 rotations to find the rotation with the lowest 
	 * chi-squared score.</p>
	 * <p> {@code Caesar.roate()} is then used to print the most likely
	 * rotation as calculated before.</p> 
	 *
	 * @param args Takes a single argument which is the cipher text.
	 */
	public static void main(String[] args){
		if (args.length == 1){
			int lowest = 0;
			double prev = chiSquared(frequency(args[0]), english);
			for (int i = 1; i < 26; i++) { // increment through all offsets
				double current = chiSquared(frequency(Caesar.rotate(i, args[0])), english);
				if (current < prev) {
					lowest = i;
					prev = current;
				}
			}
			System.out.println(Caesar.rotate(lowest, args[0]));
		}
		else if (args.length > 1) {
			System.out.println("Too many parameters!");
			System.out.println("Usage: java Brutus \"cipher text\"");
		}
		else {
			System.out.println("Too few parameters!");
			System.out.println("Usage: java Brutus \"cipher text\"");
		}
	}
	/**
	 * Returns an array with 26 elements, counting the number of occurrences
	 * of each letter in a given input string.
	 *
	 * <p> A nested loop is used to iterate through each letter in the
	 * array. For each upper or lower-case occurrence of this letter, the
	 * counter is incremented.</p>
	 *
	 * @param text The input string to be counted.
	 * @return An array of 26 length, counting the number of occurrences of
	 * each letter in the source text.
	 */
    	public static int[] count(String text){
		int[] occurrences = new int[26];
		for (int i = 0; i < 26; i++) {
			for (int j = 0; j < text.length(); j++) {
				if (text.charAt(j) == (char)(i + 65) || text.charAt(j) == (char)(i + 97)) // if character matches upper or lower-case
					occurrences[i]++;
			}
		}
		return occurrences;
	}
	/**
	 * Returns an array with 26 elements; counting the frequency of each 
	 * letter in a given input string.
	 *
	 * <p> This function will call {@code count()} with the input string to
	 * find the occurrences of each letter. It will then go through each 
	 * letter and divide it by the length of the string to get the
	 * frequency.</p>
	 *
	 * @param text The input string.
	 * @return An array of 26 length, counting the frequency of each letter
	 * in the source text. 
	 */
	public static double[] frequency(String text) {
		int[] occurrences = count(text);
		double[] freq = new double[26];
		double length = text.length(); // set length as to not recalculate with each iteration
		for (int i = 0; i < occurrences.length; i++)
			freq[i] = (double)(occurrences[i]) / length; // all double to avoid data loss
		return freq;
	}
	/**
	 * Returns the chi-squared score for two arrays; representing the
	 * frequencies of each letter in the alphabet.
	 *
	 * @param freq A frequency list of the letters in the source text.
	 * @param ref A reference frequency list for the letters in your
	 * language.
	 * @return The chi-squared score of the two arrays.
	 */
	public static double chiSquared(double[] freq, double[] ref) {
		double sum = 0;
		for (int i = 0; i < freq.length; i++) 
			sum += (Math.pow(freq[i]-ref[i],2) / ref[i]);
		return sum;
	}
}

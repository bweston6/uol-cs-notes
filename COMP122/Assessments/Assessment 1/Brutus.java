import java.util.Arrays;
public class Brutus {
	public static final double[] english = {
        	0.0855, 0.0160, 0.0316, 0.0387, 0.1210, 0.0218, 0.0209, 0.0496, 0.0733,
        	0.0022, 0.0081, 0.0421, 0.0253, 0.0717, 0.0747, 0.0207, 0.0010, 0.0633,
        	0.0673, 0.0894, 0.0268, 0.0106, 0.0183, 0.0019, 0.0172, 0.0011
    	};
	public static void main(String[] args){
		System.out.println(Arrays.toString(count(args[0])));
	}
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
}

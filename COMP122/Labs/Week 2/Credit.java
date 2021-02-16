public class Credit {
  public static void main(String[] args) {
    // This reads some number from the terminal.
    // Don't worry about this for now and just use the variable
    long number = Comp122.getLong("Number: ");
    // Calculate checksum.
    long num = number;
    int sum = 0;
    int pos = 0;
    while (num > 0){
        long digit = num % 10;
        // Select odd position integer.
        if (pos % 2 == 1){
            digit *= 2;
            // Add digits to sum.
            while (digit > 0) {
                sum += digit % 10;
                digit /= 10;
            }
        }
        num /= 10;
        pos++;
    }
    // Add other digits to sum.
    num = number;
    pos = 0;
    while (num > 0){
        long digit = num % 10;
        // Select odd position integer.
        if (pos % 2 == 0){
            sum += digit;
        }
        num /= 10;
        pos++;
    }
    sum = sum % 10;
    // Check checksum.
    if (sum == 0){
        // Check provider.
        if (((Math.log10(number)-14) < 1) && ((Integer.parseInt(String.valueOf(number).substring(0,1)) == 34) || (Integer.parseInt(String.valueOf(number).substring(0,1)) == 37))){
            System.out.println("AMEX\n");
        }
        else if (((Math.log10(number)-15) < 1) && ((Integer.parseInt(String.valueOf(number).substring(0,1)) >= 51) && (Integer.parseInt(String.valueOf(number).substring(0,1)) <= 55))){
            System.out.println("MASTERCARD");
        }
        else if (((Math.log10(number)-12) < 1 || (Math.log10(number)-15) < 1) && (Character.digit(String.valueOf(number).charAt(0), 10) == 4)){
            System.out.println("VISA");
        }

        else {
            System.out.println("INVALID len/start");
        }
    }
    else {
        System.out.println("INVALID check");
    }
  }
}


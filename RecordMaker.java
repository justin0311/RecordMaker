import java.util.HashSet;
import java.util.Random;

/**
 * Created by myunginkim on 5/22/16.
 */

public class RecordMaker {
	
	HashSet<Integer> employeeIDs;       // Use HashSet for a fast search & an uniqueness of the elements
	Random random;
	private static employeeDigit = 5;   // Number of the digits for employeeID

	public RecordMaker() {
        // Constructor
		employeeIDs = new HashSet<Integer>();
		random = new Random();
	}
	
	private int getRand(int min, int max) {
        // Return a pseudorandom integer between min (inclusive) and max (inclusive)

        return random.nextInt(max - min + 1) + min;
	}

    private int generateEID() {
		int newID;
		
		do {
			newID = getRand(0, 99999);
		} while (employeeIDs.contains(newID));
		
		employeeIDs.add(newID);
		
		return newID;
		
	}
	
	private String generateRecord() {
		StringBuilder sb = new StringBuilder();
        sb.append("NT");

		int newEID = generateEID();

        // Determine the number of digits of employeeID
		int numDigits = newEID == 0 ? 1 : (int) (Math.floor(Math.log10(newEID))+1) ;

        // Number of zeros to fill
        int numZeroFills = employeeDigit - numDigits;

        for (int i = 0 ; i < numZeroFills ; i++) {
			sb.append(0);
		}
		sb.append(newEID);
		
		sb.append(" ");
		sb.append(getRand(0, 99));      // Score of an individual employee
		
		return sb.toString();
	}
	
	
	
	
	public static void main(String[] args) {
		int count = -1;

        if (args.length < 1) {
            // Throw IllegalArgument exception if no parameter is given
            throw new IllegalArgumentException("No parameter is given!");

        } else if (args.length > 1) {
            // Throw IllegalArgument exception if more than 1 parameters are given
            throw new IllegalArgumentException("Too many parameters are given!");

        } else {    // Exact one Argument is given

            try {
                // Convert argument into integer
                count = Integer.parseInt(args[0]);

            } catch (NumberFormatException e) {
                System.err.println("Argument (" + args[0] + ") must be an integer.");
                System.exit(-1);
            }

            RecordMaker rm = new RecordMaker();
            for (int i = 0 ; i < count ; i++) {
                System.out.println(rm.generateRecord());
            }
        }
	}
}

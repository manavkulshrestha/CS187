package fizzbuzz;

public class FizzBuzz {
	private final int fizzNumber;
	private final int buzzNumber;

	/**
	 * Construct an object that can compute fizzbuzz values for a game of 
	 * Fizz and Buzz.
	 * 
	 * @param fizzNumber: an integer between 1 and 9
	 * @param buzzNumber: an integer between 1 and 9
	 */
	public FizzBuzz(int fizzNumber, int buzzNumber) {
		this.fizzNumber = fizzNumber;
		this.buzzNumber = buzzNumber;

	}

	/**
	 * Returns the fizzbuzz value for n. The rules are:
	 * - if n is divisible by fizzNumber, or contains the digit fizzNumber, return "fizz" 
	 * - if n is divisible by buzzNumber, or contains the digit buzzNumber, return "buzz"
	 * - however, if both the above conditions are true, you must return "fizzbuzz"
	 * - if none of the above conditions is true, return the number itself.
	 *
	 * For example, getValue(1) returns "1".
	 * 
	 * @param n: a positive integer
	 * @return the fizzbuzz value, as a String
	 */
	public String getValue(int n) {
//		if(n==3) return "fizz";
//		if(n==4) return "buzz";
//		return Integer.toString(n); // return the number itself as a String
        String ret = "";

        if(n%fizzNumber == 0 || contains(n, fizzNumber))
            ret += "fizz";
        if(n%buzzNumber == 0 || contains(n, buzzNumber))
            ret += "buzz";

        return (ret == "") ? Integer.toString(n) : ret;
	}

	private boolean contains(int number, int checkFor) {
        for(; number>0; number /= 10)
            if(number%10 == checkFor)
                return true;
        return false;
    }

	/**
	 * Returns an array of the fizzbuzz values from start to end, inclusive.
	 * 
	 * For example, if the fizz number is 3 and buzz number is 4,
	 * getValues(2,6) should return an array of Strings:
	 * 
	 * {"2", "fizz", "buzz", "5", "fizz"}
	 * 
	 * @param start
	 *            the number to start from; start > 0
	 * @param end
	 *            the number to end at; end >= start
	 * @return the array of fizzbuzz values
	 */
	public String[] getValues(int start, int end) {
//		return new String[] {"1", "2", "fizz", "buzz"};
        String[] ret = new String[end-start+1];

        for(int i=start; i<=end; i++)
            ret[i-start] = getValue(i);

        return ret;
	}
}

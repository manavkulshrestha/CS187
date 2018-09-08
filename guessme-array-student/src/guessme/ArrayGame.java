package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess;
	private int[] priorGuesses;
	boolean de;
//	private boolean[] notCandidates;

    private boolean[] candidates;
	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.

	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		this.guess = 1000;
//        this.notCandidates = new boolean[9000];
        this.priorGuesses = new int[0];

        this.candidates = new boolean[10000];
        for(int i=1000; i<10000; i++)
            candidates[i] = true;
    }
	
	// Resets data members and game state so we can play again
	public void reset() {
        this.guess = 1000;
//        this.notCandidates = new boolean[9000];
        this.priorGuesses = new int[0];

        this.candidates = new boolean[10000];
        for(int i=1000; i<10000; i++)
            candidates[i] = true;
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
	    for(int priorGuess: this.priorGuesses)
	        if(n == priorGuess)
	            return true;
		return false;
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		return this.priorGuesses.length;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
		// TODO
        int matches = 0;
		for(int i=0; i<4; i++, a/=10, b/=10)
		    if(a%10 == b%10)
		        matches++;
		return matches;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		if(isPriorGuess(this.guess))
		    return true;

		//probably covered?
        for(int i=1000; i<candidates.length; i++)
            if(candidates[i])
                return false;
        return true;
	}
	
	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() {
        int[] temp = new int[this.priorGuesses.length+1];

        for(int i=0; i<this.priorGuesses.length; i++)
            temp[i] = this.priorGuesses[i];
        temp[this.priorGuesses.length] = this.guess;
        this.priorGuesses = temp;

		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
//        for(int i=1000; i<=9999; i++) {
//            if(!this.notCandidates[i-1000]) {
//                if(numMatches(i, this.guess) != nmatches)
//                    this.notCandidates[i-1000] = true;
//                else
//                    this.guess = i;
//            }
//        }
//
//        //error state
//        for(boolean notCandidate: notCandidates)
//            if(!notCandidate)
//                return true;
//		return false;
        boolean nextGuessEstablished = false;
        int nextGuess = -1;

        for(int i=1000; i<=9999; i++) {
            if(i==4321)
                de = false;
            if(this.candidates[i]) {
                if(numMatches(i, this.guess) == nmatches) {
                    if(!nextGuessEstablished) {
                        nextGuessEstablished = true;
                        nextGuess = i;
                    }
                } else {
                    this.candidates[i] = false;
                }
            }
        }

        if(nextGuess == -1)
            return false;
        this.guess = nextGuess;

        for(int i=1000; i<candidates.length; i++)
            if(candidates[i])
                return true;
        return false;
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		return (priorGuesses.length != 0) ? priorGuesses : null;
	}
}

package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

    private int guess;
    private LinkedList candidates;
    private LinkedList priorGuesses;

    /********************************************************
     * NOTE: for this project you must use linked lists
     * implemented by yourself. You are NOT ALLOWED to use
     * Java arrays of any type, or any class in the java.util
     * package (such as ArrayList).
     *******************************************************/
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		this.guess = 1000;

        this.candidates = new LinkedList();
        for(int i=1000; i<=9999; i++)
            candidates.add(i);

        this.priorGuesses = new LinkedList();

    }
	
	// Resets data members and game state so we can play again
	public void reset() {
        this.guess = 1000;

        this.candidates = new LinkedList();
        for(int i=1000; i<=9999; i++)
            candidates.add(i);

        this.priorGuesses = new LinkedList();
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
        return this.priorGuesses.contains(n);
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		return priorGuesses.size;
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
	public static int numMatches(int a, int b) {
		int matches = 0;

		for(int i=0; i<4; i++, a/=10, b/=10)
			if(a%10 == b%10)
				matches++;

		return matches;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		return priorGuesses.contains(this.guess);
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		priorGuesses.add(this.guess);
		return this.guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
        LLIntegerNode iterator = candidates.head.getLink();
        LLIntegerNode previous = candidates.head;
        while(iterator != null) {
            if(numMatches(iterator.getInfo(), this.guess) != nmatches) {
                if(iterator != candidates.tail) {
                    previous.setLink(iterator.getLink());
                    iterator.setLink(null);
                    iterator = previous.getLink();
                } else {//deleting tail
                    previous.setLink(null);
                    candidates.tail = previous;
                    iterator = null;
                }
                candidates.size--;
            } else {
                previous = iterator;
                iterator = iterator.getLink();
            }
        }

        //delete head?
        if(numMatches(candidates.head.getInfo(), this.guess) != nmatches) {
            LLIntegerNode newHead = candidates.head.getLink();
            candidates.head.setLink(null);
            candidates.head = newHead;
            candidates.size--;
        }

        if(candidates.size == 0)
            return false;
        this.guess = candidates.head.getInfo();
        return true;
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		return priorGuesses.head;
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		return priorGuesses.toString();
	}
}

class LinkedList {
    public LLIntegerNode head;
    public LLIntegerNode tail;
    public int size;

    public LinkedList(LLIntegerNode head) {
        this.head = head;
        this.tail = head;
        this.size = (head == null) ? 0 : 1;
    }

    public LinkedList() {
        this(null);
    }

    public void add(int info) {
        if(this.head == null) {
            this.head = new LLIntegerNode(info);
            this.tail = this.head;
        } else {
            LLIntegerNode nextNode = new LLIntegerNode(info);
            this.tail.setLink(nextNode);
            this.tail = nextNode;
        }
        this.size++;
    }

    public boolean contains(int info) {
        if(this.head == null)
            return false;
        for(LLIntegerNode iterator = this.head; iterator != null; iterator = iterator.getLink())
            if(iterator.getInfo() == info)
                return true;
        return false;
    }

    @Override
    public String toString() {
        if(this.size == 0)
            return "";

        String ret = ""+this.head;

        for(LLIntegerNode iterator = this.head.getLink(); iterator != null; iterator = iterator.getLink())
            ret += ", "+iterator;

        return ret;
    }
}

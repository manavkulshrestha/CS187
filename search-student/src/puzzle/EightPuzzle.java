package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	public final List<Integer> startingValues;
	public final List<Integer> goal;

	public EightPuzzle(List<Integer> startingValues) {
		if(startingValues.size() != 9)
			throw new IllegalArgumentException();

		for(int i=0; i<=8; i++)
			if(!startingValues.contains(i))
			    throw new IllegalArgumentException();

		this.startingValues = startingValues;
		this.goal = Arrays.asList(1,2,3,4,5,6,7,8,0);
	}

	@Override
	public List<Integer> getInitialState() {
		return startingValues;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
	    List<List<Integer>> ret = new ArrayList<>();
	    List<Integer> sIndexes = new ArrayList<>();
		int zeroIndex = currentState.indexOf(0);

		if(((zeroIndex+1)%3) == 0)
            sIndexes.add(zeroIndex-1);
		else if(zeroIndex%3 == 0)
		    sIndexes.add(zeroIndex+1);

		if(zeroIndex < 3)
		    sIndexes.add(zeroIndex+3);
		else if(zeroIndex > 6)
            sIndexes.add(zeroIndex-3);

		if((zeroIndex-1)%3 == 0) {
		    sIndexes.add(zeroIndex-1);
		    sIndexes.add(zeroIndex+1);
        }
        if(2 < zeroIndex && zeroIndex < 6) {
            sIndexes.add(zeroIndex-3);
            sIndexes.add(zeroIndex+3);
        }

        for(int sIndex: sIndexes) {
		    List<Integer> tempArray = new ArrayList<>(currentState);
		    tempArray.set(zeroIndex, currentState.get(sIndex));
            tempArray.set(sIndex, currentState.get(zeroIndex));
		    ret.add(tempArray);
        }

        return ret;
	}

	@Override
	public boolean isGoal(List<Integer> state) {
		return state.equals(this.goal);
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}

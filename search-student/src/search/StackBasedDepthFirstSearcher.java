package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {

    public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

    @Override
	public List<T> findSolution() {
        return null;
	}
}

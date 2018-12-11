package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

    public final List<T> states;
    public final List<T> prevs;

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);

		this.states = new ArrayList<>();
		this.prevs = new ArrayList<>();
	}

	@Override
	public List<T> findSolution() {
	    Queue<T> q = new LinkedList<>();
	    List<T> path = new ArrayList<>();
	    final T initial = searchProblem.getInitialState();

	    visited.add(initial);
	    q.add(initial);
	    prevs.add(initial);

	    while(!q.isEmpty()) {
	        T temp = q.poll();
	        if(searchProblem.isGoal(temp)) {
	            path.add(temp);
	            for(T t; !temp.equals(initial); temp = t) {
                    t = prevs.get(states.indexOf(temp));
                    path.add(t);
                }
                Collections.reverse(path);
	            break;
            }
	        for(T tempt=getNextUnvisitedNeighbor(temp); tempt != null; tempt=getNextUnvisitedNeighbor(temp)) {
	            visited.add(tempt);
	            q.add(tempt);
            }
        }
        return path;
	}

	private T getNextUnvisitedNeighbor(T node) {
	    for(T successor: searchProblem.getSuccessors(node)) {
	        if(!visited.contains(successor)) {
	            if(!states.contains(successor)) {
	                states.add(successor);
	                prevs.add(successor);
                }
                prevs.set(states.indexOf(successor), node);
	            return successor;
            }
        }
        return null;
    }
}

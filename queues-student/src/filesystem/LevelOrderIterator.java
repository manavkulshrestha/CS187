package filesystem;

import structures.Queue;
import structures.UnboundedQueueInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
    UnboundedQueueInterface<File> iter;
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		if(!rootNode.exists())
			throw new FileNotFoundException();

		iter = new Queue<>();
        UnboundedQueueInterface<File> queue = new Queue<>();

		queue.enqueue(rootNode);

		while(!queue.isEmpty()) {
            File file = queue.dequeue();
            iter.enqueue(file);

            if(file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if(subFiles != null) {
                    Arrays.sort(subFiles);
                    for(File subFile: subFiles)
                        queue.enqueue(subFile);
                }
            }
        }
	}
	
	@Override
	public boolean hasNext() {
	    return !iter.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
	    return iter.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}

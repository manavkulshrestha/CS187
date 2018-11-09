package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) throws NullPointerException {
	    if(t == null)
	        throw new NullPointerException();
		return (get(t) != null);
	}

	public boolean remove(T t) throws NullPointerException {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			if (node.getLeft() != null) { 
				node.getLeft().setParent(node);
			}
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				if (node.getLeft() != null) { 
					node.getLeft().setParent(node);
				}
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
			return node;
		}
	}

	public T get(T t) throws NullPointerException {
	    if(t == null)
            throw new NullPointerException();
		for(BSTNode<T> node = this.root; node != null;) {
            T data = node.getData();
			if(data.compareTo(t) == 0)
			    return node.getData();
            if(t.compareTo(data) < 0)
                node = node.getLeft();
            else
                node = node.getRight();
		}
		return null;
	}


	public void add(T t) throws NullPointerException{
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
			if (node.getLeft() != null) { 
				node.getLeft().setParent(node);
			}
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
			if (node.getRight() != null){
				node.getRight().setParent(node);
			}
		}
		return node;
	}

	@Override
	public T getMinimum() {
        BSTNode<T> node = this.root;

        if(node == null)
            return null;

        while(node.getLeft() != null)
            node = node.getLeft();

        return node.getData();
	}


	@Override
	public T getMaximum() {
        BSTNode<T> node = this.root;

        if(node == null)
            return null;

        while(node.getRight() != null)
            node = node.getRight();

        return node.getData();
	}


	@Override
	public int height() {
        return height(this.root);
	}

	private int height(BSTNode<T> node) {
        if(node == null)
            return -1;
        return 1+Math.max(height(node.getLeft()), height(node.getRight()));
    }


	public Iterator<T> preorderIterator() {
        Queue<T> queue = new LinkedList<T>();
        preorderTraverse(queue, this.root);
        return queue.iterator();
	}

    private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
        if (node != null) {
            queue.add(node.getData());
            preorderTraverse(queue, node.getLeft());
            preorderTraverse(queue, node.getRight());
        }
    }

	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, this.root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
        Queue<T> queue = new LinkedList<T>();
        postorderTraverse(queue, this.root);
        return queue.iterator();
	}

    private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
        if (node != null) {
            postorderTraverse(queue, node.getLeft());
            postorderTraverse(queue, node.getRight());
            queue.add(node.getData());
        }
    }

	@Override
	public boolean equals(BSTInterface<T> other) throws NullPointerException {
	    if(other == null)
	        throw new NullPointerException();
		return equals(this.root, other.getRoot());
	}

    private boolean equals(BSTNode<T> a, BSTNode<T> b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.getData().equals(b.getData()) &&
                equals(a.getLeft(), b.getLeft()) &&
                equals(a.getRight(), b.getRight());
    }

	@Override
	public boolean sameValues(BSTInterface<T> other) throws NullPointerException {
	    if(other == null)
	        throw new NullPointerException();
		Queue<T> q1 = new LinkedList<>();
		inorderTraverse(q1, this.root);

		Queue<T> q2 = new LinkedList<>();
        inorderTraverse(q2, other.getRoot());

        return q1.equals(q2);
    }

	@Override
	public boolean isBalanced() {
        return isBalanced(this.root);
    }

    private boolean isBalanced(BSTNode<T> node) {
//        int n = size(), h = height();
//        return (Math.pow(2, h) <= n && n < Math.pow(2, h+1));
        if(node == null)
            return true;

        if(Math.abs(height(node.getLeft()) - height(node.getRight())) <= 1
                && isBalanced(node.getLeft())
                && isBalanced(node.getRight()))
            return true;
        return false;
    }

	@Override
    @SuppressWarnings("unchecked")

	public void balance() {
		Queue<T> q = new LinkedList<>();
		inorderTraverse(q, this.root);
		T[] a = (T[]) new Comparable[q.size()];
        for(int i=0; i<a.length; i++)
            a[i] = q.poll();
		this.root = balance(a, 0, a.length-1);
	}

	private BSTNode<T> balance(T[] array, int lower, int upper) {
        if(lower > upper)
            return null;
        int middle = (lower+upper)/2;
        BSTNode<T> node = new BSTNode<>(array[middle], null, null);
        node.setLeft(balance(array, lower, middle-1));
        node.setRight(balance(array, middle+1, upper));
        return node;
    }


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}
package structures;

/**
 * A node in a BST.
 * 
 * Note that BSTNode MUST implement BSTNodeInterface; removing this will resulit
 * in your program failing to compile for the autograder.
 * 
 * @author liberato
 *
 * @param <T>
 */
public class BSTNode<T extends Comparable<T>> implements BSTNodeInterface<T> {
	private T data;
	private BSTNode<T> left;
	private BSTNode<T> right;
	private BSTNode<T> parent;
	
	public static final boolean RED   = true;
    public static final boolean BLACK = false;
	private boolean color;

	public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
		parent = null;
	}
	
	public BSTNode(T data, BSTNode<T> left, BSTNode<T> right, boolean color) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.color = color;
		parent = null;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public boolean getColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<T> left) {
		this.left = left;
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public void setRight(BSTNode<T> right) {
		this.right = right;
	}
	
	public BSTNode<T> getParent() {
		return parent;
	}
	
	public void setParent(BSTNode<T> p) {
		this.parent = p;
	}
	
	public void printSubtree(int spaces) {
		if (right != null) {
			right.printSubtree(spaces + 5);
		}
		for (int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}
		System.out.print(data);
		System.out.print('-');
		System.out.println(color);
		if (left != null) {
			left.printSubtree(spaces + 5);
		}
	}

	@Override
    public String toString() {
	    return this.data+""+((this.color) ? "R" : "B");
    }
}
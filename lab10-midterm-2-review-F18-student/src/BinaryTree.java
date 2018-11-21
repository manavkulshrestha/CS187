/**
 * Lab 10 - Midterm Review Practice Exercise
 *
 * Q1. Tree Traversal
 *   - Preorder traversal
 *   - Inorder traversal
 *   - Postorder traversal
 */
import java.util.Stack;

class Node { 
	int data; 
	Node left, right; 

	public Node (int val) { 
		data = val; 
		left = right = null; 
	} 
} 

public class BinaryTree { 
	// global variable
	Node root; 

	// constructor
	BinaryTree() { 
		root = null; 
	} 

	/**
	 * TODO:
	 * Given a binary tree, print its nodes according to the 
	 * "bottom-up" postorder traversal. 
	 */
	void printPostOrder(Node n) {
		if(n == null)
			return;
		printPostOrder(n.left);
		printPostOrder(n.right);
		System.out.print(n.data);
	} 

	/**
	 * TODO:
	 *  Given a binary tree, print its nodes in inorder
	 */
	void printInOrder(Node n) {
		if(n == null)
			return;
		printInOrder(n.left);
		System.out.print(n.data);
		printInOrder(n.right);
	} 

	/**
	 * TODO:
	 * Given a binary tree, print its nodes in preorder
	 */
	void printPreOrder(Node n) {
		if(n == null)
			return;
		System.out.print(n.data);
		printPreOrder(n.left);
		printPreOrder(n.right);
	} 

	void post_order_iterative(Node root){
        //prints the post-order traversal of the BST
        if(root == null) return;

        Stack<Node> s = new Stack();
        Stack<Node> for_printing = new Stack();

        s.push(root);

        while(!s.empty()) {
            Node n = s.pop();
            for_printing.push(n);
            if(n.left != null)
                s.push(n.left);
            if(n.right != null)
                s.push(n.right);
        }

        while(!for_printing.empty()){
            // print out the post order traversal
            Node temp = for_printing.pop();
            System.out.print(temp.data);
        }
	}

	void in_order_iterative(Node root){
        //prints the in-order traversal of the BST
        if(root == null) return;
        Stack<Node> s = new Stack();

        Node n = root;
        while(n != null || !s.empty()) {
            while(n != null) {
                s.push(n);
                n = n.left;
            }
            n = s.pop();
            System.out.print(n.data);
            n = n.right;
        }
	}



	// Driver program
	public static void main(String[] args) { 
		BinaryTree t = new BinaryTree(); 
		t.root = new Node(1); 
		t.root.left = new Node(2); 
		t.root.right = new Node(3); 
		t.root.left.left = new Node(4); 
		t.root.left.right = new Node(5); 

		System.out.println("Recursive Preorder traversal of binary tree is "); 
		t.printPreOrder(t.root); 

		System.out.println("\n Inorder traversal of binary tree is "); 
		System.out.println("For recursive: ");
		t.printInOrder(t.root); 

		System.out.println("\nFor iterative: ");
		t.in_order_iterative(t.root);

		System.out.println("\nPostorder traversal of binary tree is ");
		System.out.println("For recursive: ");
		t.printPostOrder(t.root);

		System.out.println("For iterative: ");
		t.post_order_iterative(t.root);

	} 
} 
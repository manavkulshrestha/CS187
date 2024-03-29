package structures;

public class RedBlackTree<T extends Comparable<T>> extends
			BinarySearchTree<T> {
	
	  public void printTree() {
		  System.out.println("------------------------");
	      if (root != null) root.printSubtree(0);
	  }
	
	  private boolean isRed(BSTNode<T> node) {
		  if (node == null) return false;
		  return node.getColor() == BSTNode.RED;
	  }
	
	  private boolean isBlack(BSTNode<T> node) {
		  if (node == null) return true;
		  return node.getColor() == BSTNode.BLACK;
	  }
	  
	  private void rotateLeft(BSTNode<T> node) {
		  BSTNode<T> r = node.getRight();
	      node.setRight(r.getLeft());
	      
	      if (r.getLeft() != null) r.getLeft().setParent(node);
	      r.setParent(node.getParent());
	      if (node.getParent() == null) this.root = r;
	      else {
	    	  if (node.getParent().getLeft() == node)
	              node.getParent().setLeft(r);
	          else
	              node.getParent().setRight(r);
	      }
	      
	      r.setLeft(node);
	      node.setParent(r);
	  }
	  
	  private void rotateRight(BSTNode<T> node) {
		  BSTNode<T> l = node.getLeft();
		  node.setLeft(l.getRight());
		  
		  if (l.getRight() != null) l.getRight().setParent(node);
		  l.setParent(node.getParent());
		  if (node.getParent() == null) this.root = l;
	      else {
	    	  if (node == node.getParent().getRight())
	    		  node.getParent().setRight(l);
	    	  else
	    		  node.getParent().setLeft(l);
	      }
		  
		  l.setRight(node);
		  node.setParent(l);
	  }

	  // After inserting node, color it red and check BST property as followed,
	  // 1. If node is root, make it black.
	  // 2. if parent node is black, do nothing.
	  // 3. if parent node is red, keep doing recursively for BST property by following,
	  //   3.1 If uncle is RED, set color properly based on the instruction. 
	  //       Then check for BST property on grandparent node.
	  //   3.2 If uncle is black, and node is inside node, 
	  //       do rotation on parent node to make a outside node case.
	  //       If target node is the right child of parent node and 
	  //       parent node is the left child of grandparent node, do left rotation.
	  //       If target node is the left child of parent node and 
	  //       parent node is the right child of grandparent node, do right rotation.
	  //       After the rotation, checking for BST property on parent node.
	  //   3.3 If uncle is black, and node is outside node, 
	  //       make parent black and grandparent red,
	  //       then do rotation on grandparent node.
	  //       If target node is the right child of parent node and 
	  //       parent node is the right child of grandparent node, do left rotation.
	  //       If target node is the left child of parent node and 
	  //       parent node is the left child of grandparent node, do right rotation.
 	  // HINT: It's helpful if you build getGrandparent and getUncle method first
	  @Override
	  public void add(T t) throws NullPointerException {
			// TODO
          if(t == null)
              throw new NullPointerException();

          BSTNode<T> toAdd = new BSTNode<>(t, null, null, BSTNode.RED);

          this.root = addToSubtree(this.root, toAdd);

          addHelper(toAdd);
	  }

	  private void addHelper(BSTNode<T> node) {
	      if(node == this.root) {
	          node.setColor(BSTNode.BLACK);
	          return;
          }

          if(node.getParent() == null || node.getParent().getColor() == BSTNode.BLACK)
	          return;

	      BSTNode<T> uncle = getUncle(node);
          BSTNode<T> grandparent = getGrandparent(node);

	      if(uncle == null || uncle.getColor() == BSTNode.BLACK) {
	          BSTNode<T> parent = node.getParent();

              if(grandparent.getLeft() == parent && parent.getRight() == node) {//left right - inside
                  rotateLeft(parent);
                  addHelper(node.getLeft());
              } else if(grandparent.getRight() == parent && parent.getLeft() == node) {//right left - inside
                  rotateRight(parent);
                  addHelper(node.getRight());
              } else if(grandparent.getLeft() == parent && parent.getLeft() == node) {// left left - outside
                  parent.setColor(BSTNode.BLACK);
                  grandparent.setColor(BSTNode.RED);
                  rotateRight(grandparent);
              } else if(grandparent.getRight() == parent && parent.getRight() == node) {// right right - outside
                  parent.setColor(BSTNode.BLACK);
                  grandparent.setColor(BSTNode.RED);
                  rotateLeft(grandparent);
              }
              return;
          }

          if(uncle.getColor() == BSTNode.RED) {
              node.getParent().setColor(BSTNode.BLACK);
              uncle.setColor(BSTNode.BLACK);
              grandparent.setColor(BSTNode.RED);
              addHelper(grandparent);
              return;
          }
      }

      private BSTNode<T> getGrandparent(BSTNode<T> node) {
          if(node == null || node.getParent() == null)
              return null;
          return node.getParent().getParent();
      }

	  private BSTNode<T> getUncle(BSTNode<T> node) {
	      BSTNode<T> grandparent = getGrandparent(node);
          if(grandparent.getRight() != null && grandparent.getRight() == node.getParent())
              return node.getParent().getParent().getLeft();
          return node.getParent().getParent().getRight();
      }
	
	  private BSTNode<T> successor(BSTNode<T> node) {
		  BSTNode<T> snode = node;
		  snode = snode.getRight();
	      while (snode.getLeft() != null)
	    	  snode = snode.getLeft();
	      return snode;
	  }
	  
	  private void recover(BSTNode<T> parent) {
		  BSTNode<T> other_child;
		  
		// we set node as null since it has no child
		  BSTNode<T> node = null;

		  while ((node == null || isBlack(node)) && (node != this.root)) {
			  if (parent.getLeft() == node) {
				  other_child = parent.getRight();
				  // Case 1: if brother node's color is red, change it to black
				  //         and set parent node's color to red, then do rotation on parent node
		          if (isRed(other_child)) {
		        	  other_child.setColor(BSTNode.BLACK);
		              parent.setColor(BSTNode.RED);
		              rotateLeft(parent);
		              other_child = parent.getRight();
		          }
		          // Case 2: if brother node's color is black and both its children's color are also black,
		          //         change it to red, then check the parent node  
		          else if ((other_child.getLeft() == null || isBlack(other_child.getLeft())) &&
		                  (other_child.getRight() == null || isBlack(other_child.getRight()))) {
		        	  other_child.setColor(BSTNode.RED);
		              node = parent;
		              parent = node.getParent();
		          } else {
		        	  // Case 3: if brother node's color is black and 
		        	  //         its left child's color is red and right child's color is black
		              if (other_child.getRight() == null || isBlack(other_child.getRight())) { 
		            	  other_child.getLeft().setColor(BSTNode.BLACK);
		                  other_child.setColor(BSTNode.RED);
		                  rotateRight(other_child);
		                  other_child = parent.getRight();
		              }
		              // Case 4: if brother node's color is black and its right child's color is red
		              other_child.setColor(parent.getColor());
		              parent.setColor(BSTNode.BLACK);
		              other_child.getRight().setColor(BSTNode.BLACK);
		              rotateLeft(parent);
		              node = this.root;
		              break;
		          }
			  } else {
				  other_child = parent.getLeft();
				  
				  // Case 1: if brother node's color is red, change it to black
				  //         and set parent node's color to red, then do rotation on parent node
		          if (isRed(other_child)) {
		        	  other_child.setColor(BSTNode.BLACK);
		              parent.setColor(BSTNode.RED);
		              rotateRight(parent);
		              other_child = parent.getLeft();
		          }
		          // Case 2: if brother node's color is black and both its children's color are also black,
		          //         change it to red, then check the parent node  
		          else if ((other_child.getLeft() == null || isBlack(other_child.getLeft())) &&
		                  (other_child.getRight() == null || isBlack(other_child.getRight()))) {
		        	  other_child.setColor(BSTNode.RED);
		              node = parent;
		              parent = node.getParent();
		          } else {
		        	  // Case 3: if brother node's color is black and 
		        	  //         its right child's color is red and left child's color is black
		              if (other_child.getLeft() == null || isBlack(other_child.getLeft())) { 
		            	  other_child.getRight().setColor(BSTNode.BLACK);
		                  other_child.setColor(BSTNode.RED);
		                  rotateLeft(other_child);
		                  other_child = parent.getLeft();
		              }
		              
		              // Case 4: if brother node's color is black and its right child's color is red
		              other_child.setColor(parent.getColor());
		              parent.setColor(BSTNode.BLACK);
		              other_child.getLeft().setColor(BSTNode.BLACK);
		              rotateRight(parent);
		              node = this.root;
		              break;
		          }
			  }
		  }
		  if (node != null) node.setColor(BSTNode.BLACK);
	  }
	  
	  // Search node contains target element, then 
	  // Step 1: If node has two children, replace its data by its successor's data,
	  // then change target node to the successor (case 1 or case 2).	  
	  // Step 2: after step 1, the node must have one or no child.
	  // If node has one child, replace it by the child,
	  // then change target node to the child (case 3).
	  // Step 3: after step 1 and 2, the node must have no child and we can remove it.  
	  // In the end, if the removed node's color is black, call recover method on its parent node.	  
      @Override
      public boolean remove(T element) throws NullPointerException {
          if(element == null)
              throw new NullPointerException();
          if(!contains(element))
              return false;
          return remove(getNode(element));
      }

    private boolean remove(BSTNode<T> node) {
        if(node.getLeft() != null && node.getRight() != null) {
            BSTNode<T> snode = successor(node);
            node.setData(snode.getData());
            return remove(snode);
        }

        if(node.getRight() != null) {
            node.setData(node.getRight().getData());
            return remove(node.getRight());
        }

        if(node.getLeft() != null) {
            node.setData(node.getLeft().getData());
            return remove(node.getLeft());
        }

        if(node.getParent().getLeft() == node)
            node.getParent().setLeft(null);
        else
            node.getParent().setRight(null);

        if(node.getColor() == BSTNode.BLACK)
            recover(node.getParent());

        return true;
    }

    private BSTNode getNode(T t) throws NullPointerException {
        if(t == null)
            throw new NullPointerException();
        for(BSTNode<T> node = this.root; node != null;) {
            T data = node.getData();
            if(data.compareTo(t) == 0)
                return node;
            if(t.compareTo(data) < 0)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return null;
    }
}

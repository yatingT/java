// --== CS400 File Header Information ==--
// Name: Yating Tian
// Email: ytian83@wisc.edu
// Team: MF
// Role: Test Engineer
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: NA
import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes within a binary search tree. You can use this class' insert method
 * to build a binary search tree, and its toString method to display the level
 * order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>>  {

	/**
	 * This class represents a node holding a single value within a binary tree the
	 * parent, left, and right child references are always be maintained.
	 */
	private static class Node<T> {
		public T data;
		public Node<T> parent; // null for root node
		public Node<T> leftChild;
		public Node<T> rightChild;
		public boolean isBlack = false; // this instance tracks the color of each node

		public Node(T data) {
			this.data = data;
		}

		public Node() {
			this.data = null;
		}

		/**
		 * @return true when this node has a parent and is the left child of that
		 *         parent, otherwise return false
		 */
		public boolean isLeftChild() {
			return parent != null && parent.leftChild == this;
		}

		/**
		 * @return true when this node has a parent and is the right child of that
		 *         parent, otherwise return false
		 */
		public boolean isRightChild() {
			return parent != null && parent.rightChild == this;
		}

		/**
		 * This method performs a level order traversal of the tree rooted at the
		 * current node. The string representations of each data value within this tree
		 * are assembled into a comma separated string within brackets (similar to many
		 * implementations of java.util.Collection).
		 * 
		 * @return string containing the values of this tree in level order
		 */
		@Override
		public String toString() { // display subtree in order traversal
			String output = "[";
			LinkedList<Node<T>> q = new LinkedList<>();
			q.add(this);
			while (!q.isEmpty()) {
				Node<T> next = q.removeFirst();
				if (next.leftChild != null)
					q.add(next.leftChild);
				if (next.rightChild != null)
					q.add(next.rightChild);
				output += next.data.toString();
				if (!q.isEmpty())
					output += ", ";
			}
			return output + "]";
		}
	}

	public Node<T> root; // reference to root node of tree, null when empty

	/**
	 * Performs a naive insertion into a binary search tree: adding the input data
	 * value to a new node in a leaf position within the tree. After this insertion,
	 * no attempt is made to restructure or balance the tree. This tree will not
	 * hold null references, nor duplicate data values.
	 * 
	 * @param data to be added into this binary search tree
	 * @throws NullPointerException     when the provided data argument is null
	 * @throws IllegalArgumentException when the tree already contains data
	 */
	public void insert(T data) throws NullPointerException, IllegalArgumentException {
		// null references cannot be stored within this tree
		if (data == null)
			throw new NullPointerException("This RedBlackTree cannot store null references.");

		Node<T> newNode = new Node<>(data);
		if (root == null) {
			root = newNode;
		} // add first node to an empty tree
		else
			insertHelper(newNode, root); // recursively insert into subtree

		newNode.isBlack = false; // set the new node as red color
		enforceRBTreePropertiesAfterInsert(newNode); // check the RBT properties 
		root.isBlack = true; // always set the root as black
	}

	/**
	 * Recursive helper method to find the subtree with a null reference in the
	 * position that the newNode should be inserted, and then extend this tree by
	 * the newNode in that position.
	 * 
	 * @param newNode is the new node that is being added to this tree
	 * @param subtree is the reference to a node within this tree which the newNode
	 *                should be inserted as a descenedent beneath
	 * @throws IllegalArgumentException when the newNode and subtree contain equal
	 *                                  data references (as defined by
	 *                                  Comparable.compareTo())
	 */
	private void insertHelper(Node<T> newNode, Node<T> subtree) {
		int compare = newNode.data.compareTo(subtree.data);
		// do not allow duplicate values to be stored within this tree
		if (compare == 0)
			throw new IllegalArgumentException("This RedBlackTree already contains that value.");

		// store newNode within left subtree of subtree
		else if (compare < 0) {
			if (subtree.leftChild == null) { // left subtree empty, add here
				subtree.leftChild = newNode;
				newNode.parent = subtree;
				// otherwise continue recursive search for location to insert
			} else
				insertHelper(newNode, subtree.leftChild);
		}

		// store newNode within the right subtree of subtree
		else {
			if (subtree.rightChild == null) { // right subtree empty, add here
				subtree.rightChild = newNode;
				newNode.parent = subtree;
				// otherwise continue recursive search for location to insert
			} else
				insertHelper(newNode, subtree.rightChild);
		}
	}

	/**
	 * This method performs a level order traversal of the tree. The string
	 * representations of each data value within this tree are assembled into a
	 * comma separated string within brackets (similar to many implementations of
	 * java.util.Collection, like java.util.ArrayList, LinkedList, etc).
	 * 
	 * @return string containing the values of this tree in level order
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * Performs the rotation operation on the provided nodes within this BST. When
	 * the provided child is a leftChild of the provided parent, this method will
	 * perform a right rotation (sometimes called a left-right rotation). When
	 * the provided nodes are not related in one of these ways, this method will
	 * throw an IllegalArgumentException.
	 * 
	 * @param x is the parent node that will be right rotate with its left child
	 * @throws IllegalArgumentException when the provided child and parent node
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	public void rightrotate(Node<T> x) throws IllegalArgumentException {
		Node<T> y = x.leftChild; // set the child node that about to rotate as y 
		x.leftChild = y.rightChild; // switch the middle subtree 
		if (y.rightChild != null) { // if the child subtree contains node, 
			y.rightChild.parent = x; //reset its the parent node
		}
		y.parent = x.parent; // child's parent node should be grand parent
		if (x.parent == null) { // if the grand parent is null, this is the root case 
			this.root = y;
		} else if (x == x.parent.leftChild) { //check the side of parent
			x.parent.leftChild = y; // connect the child with grand parent 
		} else {
			x.parent.rightChild = y;// connect the child with grand parent 
		}
		y.rightChild = x; // reset parent as child's right subtree
		x.parent = y; // reset the parent of parent as child
	}

	/**
	 * Performs the rotation operation on the provided nodes within this BST.
	 * When the provided child is a rightChild of the provided parent, this method
	 * will perform a left rotation (sometimes called a right-left rotation). When
	 * the provided nodes are not related in one of these ways, this method will
	 * throw an IllegalArgumentException.
	 * 
	 * @param x is the parent node that will be right rotate with its right child
	 * @throws IllegalArgumentException when the provided child and parent node
	 *                                  references are not initially (pre-rotation)
	 *                                  related that way
	 */
	public void leftrotate(Node<T> x) throws IllegalArgumentException { // left rotate function
		Node<T> y = x.rightChild; // set the child node that about to rotate as y 
		x.rightChild = y.leftChild; // switch the middle subtree 
		if (y.leftChild != null) { // if the child subtree contains node, 
			y.leftChild.parent = x; //reset its the parent node
		}
		y.parent = x.parent; // child's parent node should be grand parent
		if (x.parent == null) { // if the grand parent is null, this is the root case 
			this.root = y;
		} else if (x == x.parent.leftChild) { //check the side of parent
			x.parent.leftChild = y; // connect the child with grand parent 
		} else {
			x.parent.rightChild = y; // connect the child with grand parent 
		}
		y.leftChild = x; // reset parent as child's left subtree
		x.parent = y; // reset the parent of parent as child
	}

	
	/**
	 * To resolve red child under red parent red black tree property violations
	 *  that are introduced by inserting new nodes into a red black tree. 
	 * @param x this is the new node that just added in red black tree
	 */
	private void enforceRBTreePropertiesAfterInsert(Node<T> x) {
		while (!x.equals(root) && x.parent.isBlack == false) { // check if x is the root and the parent is red 
			Node<T> sibling = null; // create the sibling node
			if (x.parent.isLeftChild()) { // if parent is left child 
				sibling = x.parent.parent.rightChild; // then the sibling is the right child 
				if (sibling != null && sibling.isBlack == false) { //red sibling --> case 1
					x.parent.isBlack = true; //reset the parent and sibling as black
					sibling.isBlack = true;
					x.parent.parent.isBlack = false; // reset the grandparents as red 
					x = x.parent.parent; // update the child node, and then check if there is more "red red" node case
				} else { // when sibling is black 
					if (x.isRightChild()) {  // when sibling is same side with the child --> case 3
						x = x.parent; // prepare for the left rotate 
						leftrotate(x); // left rotate between new node and parent
					}// either opposite side sibling, or become opposite side after left rotate --> case 2
					x.parent.isBlack = true; // switch color between parent and grand parent 
					x.parent.parent.isBlack = false; 
					rightrotate(x.parent.parent); // right rotate parent & grandpa
				}
			} else {
				sibling = x.parent.parent.leftChild; // if parent is left child
				if (sibling != null && sibling.isBlack == false) { // case 1 red sibling
					x.parent.isBlack = true; //reset the parent and sibling as black
					sibling.isBlack = true;
					x.parent.parent.isBlack = false;// reset the grandparents as red 
					x = x.parent.parent; // update the child node, and then check if there is more "red red" node case
				} else {// when sibling is black 
					if (x.isLeftChild()) { // same side --> case 3
						x = x.parent;
						rightrotate(x); // right rotate between new node and parent
					} // opposite side--> case 2 
					x.parent.isBlack = true;
					x.parent.parent.isBlack = false;
					leftrotate(x.parent.parent);// right rotate parent & grandpa
				}
			}
		}
	}

}

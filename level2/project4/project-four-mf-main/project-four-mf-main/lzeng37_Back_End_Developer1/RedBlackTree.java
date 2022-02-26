// --== CS400 File Header Information ==--
// Name: Linxiu Zeng
// Email: lzeng37@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;

/**
 * Binary Search Tree implementation with a Node inner class for representing the nodes within a
 * binary search tree. You can use this class' insert method to build a binary search tree, and its
 * toString method to display the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always be maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;
    public boolean isBlack = false; // every new node is red by default

    public Node(T data) {
      this.data = data;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * This method performs a level order traversal of the tree rooted at the current node. The
     * string representations of each data value within this tree are assembled into a comma
     * separated string within brackets (similar to many implementations of java.util.Collection).
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

  protected Node<T> root; // reference to root node of tree, null when empty

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
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
      root.isBlack = true;// set the root node of the tree to be black
    } // add first node to an empty tree
    else {
      insertHelper(newNode, root); // recursively insert into subtree
      root.isBlack = true;// set the root node of the tree to be black
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   * 
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references (as
   *                                  defined by Comparable.compareTo())
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
        // resolve the property violations while adding a new node into the tree
        enforceRBTreePropertiesAfterInsert(newNode);
      } else {
        insertHelper(newNode, subtree.leftChild);
      }
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;
        // resolve the property violations while adding a new node into the tree
        enforceRBTreePropertiesAfterInsert(newNode);
        // otherwise continue recursive search for location to insert
      } else
        insertHelper(newNode, subtree.rightChild);
    }

  }

  /**
   * resolve red child under red parent tree property violations that are introduced by inserting
   * new nodes into a red black tree.Multiple cases are required to be handled.
   * 
   * @param newNode the newNode inserted into the tree
   */
  private void enforceRBTreePropertiesAfterInsert(Node<T> newNode) {

    if (newNode == null) {
      return;
    }

    Node<T> parent = newNode.parent;// the parent node of the inserted node
    Node<T> sibling;// the other child of the parent node
    Node<T> grandParent;// the parent of the parent node

    try {
      // cascading fix
      // red property violation: C's parent P is red
      while (newNode != null && (!newNode.isBlack) && parent != null && !parent.isBlack) {
        grandParent = parent.parent;

        if (grandParent == null) {
          break;
        }

        // find parent's sibling
        if (grandParent.leftChild != null && grandParent.leftChild.equals(parent)) {// parent is a
                                                                                    // leftChild
          sibling = grandParent.rightChild;
        } else {// parent is a rightChild
          if (grandParent.rightChild == null || !grandParent.rightChild.equals(parent)) {
            break;
          }
          sibling = grandParent.leftChild;
        }

        // if P's sibiling is black, do restructure and recolor
        if (sibling == null || sibling.isBlack) {
          try {
            rotate(parent, grandParent);
          } catch (NullPointerException e) {
            System.out.println("rotate has nullpointer exception");
          }
          parent.isBlack = true;
          grandParent.isBlack = false;
          newNode.isBlack = false;

          // check red violation at a higher level
          newNode = grandParent;
          parent = grandParent.parent;


          // if P's sibling is red, do recolor
        } else if (sibling != null && !sibling.isBlack) {
          grandParent.isBlack = false;
          parent.isBlack = true;
          sibling.isBlack = true;

          // check red violation at a higher level
          newNode = grandParent;
          parent = grandParent.parent;
        }
      }

      // throw and catch exceptions if any occurs
    } catch (IllegalArgumentException e) {
      System.out.println("IllegalArgumentException occurs");
    } catch (NullPointerException e) {
      System.out.println("NullPointer exception occurs");
    } catch (Exception e) {
      System.out.println("Unexpected exception occurs");
    }
  }

  /**
   * This method performs a level order traversal of the tree. The string representations of each
   * data value within this tree are assembled into a comma separated string within brackets
   * (similar to many implementations of java.util.Collection, like java.util.ArrayList, LinkedList,
   * etc).
   * 
   * @return string containing the values of this tree in level order
   */
  @Override
  public String toString() {
    return root.toString();
  }

  /**
   * Performs the rotation operation on the provided nodes within this BST. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation (sometimes
   * called a left-right rotation). When the provided child is a rightChild of the provided parent,
   * this method will perform a left rotation (sometimes called a right-left rotation). When the
   * provided nodes are not related in one of these ways, this method will throw an
   * IllegalArgumentException.
   * 
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
    
    if (child == null || parent == null) {
      return;
    }

    Node<T> grandParent = parent.parent;// store a grandParent variable to do the resign
    Node<T> gNode;// grandChild node
    Node<T> tempChild = child;
    Node<T> tempParent = parent;

    if (child != null && child.isLeftChild()) {// perform a right rotation or left-right rotation
      gNode = child.rightChild;// the grandson node
      parent = tempChild;// shift the child node to parent position
      parent.rightChild = tempParent;// shift the parent node to the right child of the child node
      parent.rightChild.leftChild = gNode;// shift the grandson node to the left of the parent node

      /// reassign the parent variable to the new one
      // situation 1: if the parent node is root: reassign the root node
      if (root.data.equals(tempParent.data)) {
        root = parent;
        // situation 2: if the parent is a leftChild
      } else if (grandParent.leftChild != null
          && grandParent.leftChild.data.equals(tempParent.data)) {
        grandParent.leftChild = parent;
        // situation 3: if the parent is a rightChild
      } else if (grandParent.rightChild != null
          && grandParent.rightChild.data.equals(tempParent.data)) {
        grandParent.rightChild = parent;
      }

    } else if (parent != null && parent.rightChild.equals(child)) { // perform a left
      // rotation or
      // right-left
      // rotation
      gNode = child.leftChild;
      parent = tempChild;
      parent.leftChild = tempParent;
      parent.leftChild.rightChild = gNode;

      /// reassign the parent variable to the new one
      // situation 1: if the parent node is root: reassign the root node
      if (root.data.equals(tempParent.data)) {
        root = parent;
        // situation 2: if the parent is a leftChild
      } else if (grandParent.leftChild != null
          && grandParent.leftChild.data.equals(tempParent.data)) {
        grandParent.leftChild = parent;
        // situation 3: if the parent is a rightChild
      } else if (grandParent.rightChild != null
          && grandParent.rightChild.data.equals(tempParent.data)) {
        grandParent.rightChild = parent;
      }

    } else {
      System.out.println("the relationship is not correct");
      throw new IllegalArgumentException();
    }
  }

}

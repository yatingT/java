//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Ascii Art

// Course: cs300
//
// Author: Yating Tian
// Email: ytian83@wisc.edu
// Lecturer's Name: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than the course staff must fully
// acknowledge and credit those sources here. If you did not receive any help
// of any kind from outside sources, explicitly indicate NONE next to each of
// the labels below.
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

// import required package
import java.util.EmptyStackException;
import java.util.Iterator;
/**
 * @author Yatin. This class help us create the stack of changes that we made in order to undo and
 *         redo.
 */
public class DrawingStack implements StackADT<DrawingChange>, Iterable<DrawingChange> {

  private LinkedNode<DrawingChange> top; // The head of the stack
  private int size; // keep track of the size of the stack

 
  /**
   * @return return a new DrawingStackIterator that starts at the top of the stack of DrawingChanges
   */
  @Override
  public Iterator<DrawingChange> iterator() {
    return new DrawingStackIterator(this.top);
  }

  /**
   * Add an element to this stack
   * 
   * @param element an element to be added
   * @throws java.util.IllegalArgumentException with a descriptive error message if the input
   *                                            element is null
   */
  @Override
  public void push(DrawingChange element) {
    if (element == null) { // to check if the input parameter is null.
      throw new IllegalArgumentException("Invalid input");// if so, throw the error
    }
    if (isEmpty()) { // add to the head directly if it is an empty stack.
      top = new LinkedNode<DrawingChange>(element);
      size++; // Increment of the size
    } else { // if the stack is not empty, store the new data into a node.
      LinkedNode<DrawingChange> current = new LinkedNode<DrawingChange>(element);
      current.setNext(top); // link the new node with the old top
      top = current;// update the top
      size++;// Increment of the size
    }
  }

  /**
   * Remove the element on the top of this stack and return it
   * 
   * @return the element removed from the top of the stack
   * @throws java.util.EmptyStackException without error message if the stack is empty
   */
  @Override
  public DrawingChange pop() {
    if (isEmpty()) { // to check if the stack is an empty list.
      throw new EmptyStackException(); // if so, throw the error
    }
    LinkedNode<DrawingChange> re = top; // store the old head into a local variable, return it later
    top = top.getNext(); // update the new head to the next node, since the old top has been removed
    size--; // Decrement of size
    return re.getData(); // return the data of the old head node.
  }

  /**
   * Get the element on the top of this stack
   * 
   * @return the element on the stack top
   * @throws java.util.EmptyStackException without error message if the stack is empty
   */
  @Override
  public DrawingChange peek() {
    if (isEmpty()) { // to check if the stack is an empty list.
      throw new EmptyStackException();// if so, throw the error
    }
    return top.getData(); // return the data of head node.
  }

  /**
   * Check whether this stack is empty or not
   * 
   * @return true if this stack contains no elements, otherwise false
   */
  @Override
  public boolean isEmpty() {
    return top == null;
  }

  /**
   * Get the number of elements in this stack
   * 
   * @return the size of the stack
   */
  @Override
  public int size() {
    return size;
  }
  


}

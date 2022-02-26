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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Yatin This class help us iterate the stack of changes that we made. 
 */
public class DrawingStackIterator implements Iterator < DrawingChange >{

  private LinkedNode < DrawingChange > next; // to keep track of the next element in the iteration
  
  /**
   * @param top set the top as the first next item
   */
  public DrawingStackIterator(LinkedNode < DrawingChange > top ) {
    next=top;
  }

  /**
   * Checks whether the iteration has a next item or not.
   * 
   * @return true if there are further objects in the list, false otherwise
   */
  @Override
  public boolean hasNext() {
    return next!=null;
  }
  
  /**
   * @return the data of next node
   */
  @Override
  public DrawingChange next() {
    if (!hasNext()) { //check if the node has next node 
      throw new NoSuchElementException("There is no next of the node"); // if not, throw error
    }
    DrawingChange current=next.getData(); // get the data of the next node 
    next=next.getNext(); // update the next 
    return current; 
  }
  
}

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: p04 Storage Unit Organizer
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

/**
 * @author Yatin this clase create the linked list taht link the node objects together, and can
 *         add/remove node.
 */
public class LinkedBoxList {

  private LinkedBoxNode head; // head of this LinkedBoxList (refers to the element
  // stored at index 0 within this list)
  private int size; // number of boxes already stored in this list
  private int capacity; // capacity of this LinkedBoxList
  // maximum number of box elements that this LinkedBoxList
  // can store

  // Creates an empty LinkedBoxList with a given initial capacity
  public LinkedBoxList(int capacity) {
    this.capacity = capacity; // set the capacity form the input
  }

  // Returns the size of this list
  public int size() {
    return size; // get the size;
  }

  // Return the capacity of this list
  public int getCapacity() {
    return this.capacity;
  }

  // Expands the capacity of this LinkedBoxList with the specified number a of
  // additional elements
  public void expandCapacity(int a) {
    this.capacity = this.capacity + a; // the current capacity should be old capacity add new spot
  }

  // Checks whether this LinkedBoxList is empty
  // returns true if this LinkedBoxList is empty, false otherwise
  public boolean isEmpty() {
    return this.head == null; // head should be the first item to add in the list, so check the head
                              // to see if it is a empty list.
  }

  // Checks whether this LinkedBoxList is full
  // Returns true if this list is full, false otherwise
  public boolean isFull() {
    return size == this.capacity; // check the tail of the list, if tail has a node, the list if
                                  // full
  }

  // Adds a new box into this sorted list
  // Throws IllegalArgumentException if newBox is null
  // Throws IllegalStateException if this list is full
  public void add(Box newBox) {
    if (newBox == null) { // check if the parameter if null
      throw new IllegalArgumentException("invaild input");
    } else if (this.isFull()) { // check if the list is full
      throw new IllegalStateException("The list if full, can't add any more");
    } else { // then we can add node
      LinkedBoxNode newNode = new LinkedBoxNode(newBox); // put the box object inside a node
      if (head == null) { // check if the list is empty
        head = newNode; // set the new node as head
      } else if (newBox.compareTo(head.getBox()) > 0) { // if the new box is heavier that head
        newNode.setNext(head); // put the new node before the head, and link with old head to next
        head = newNode; // and set it as new head
      } else {
        LinkedBoxNode current = head.getNext(); // set a runner
        LinkedBoxNode before = head; // and set the runnner's previous
        while (current != null) { // check if this is the tail of the list is null
          if (current.getBox().compareTo(newBox) < 0) { // if the runner is smaller than new box
            break; // stop the loop
          }
          before = current; // move to next runner
          current = current.getNext(); // move to next runner
        }
        before.setNext(newNode); // if the new box is bigger than the runner, set the new box
        newNode.setNext(current);// between the before and current
      }
      size++; // add size at every time when add a node
    }
  }

  // Checks if this list contains a box that matches with (equals) a specific box object
  // Returns true if this list contains findBox, false otherwise
  public boolean contains(Box findBox) {
    for (int i = 0; i < capacity; i++) {
      if (this.get(i) == findBox) {
        return true;
      }
    }
    return false;
  }

  // Returns a box stored in this list given its index
  // Throws IndexOutOfBoundsException if index is out of the range 0..size-1
  public Box get(int index) {
    if (index < 0 || index >= size) { // check if index out of bounds
      throw new IndexOutOfBoundsException(
          "\nWARNING: Index " + "should be from 0 to " + (size() - 1) + "!");
    } else if (this.isEmpty()) { // check if the list is empty so nothing to remove.
      System.out.println("this list is empty");
    }
    LinkedBoxNode current = head; // set the runner
    for (int i = 0; i <= index - 1; i++) { // loop over the index to get to the index
      current = current.getNext(); // update the runner to next one by each loop
    }
    return current.getBox(); // return the box of that index
  }

  // Removes and returns the box stored at index from this LinkedBoxList
  // Throws IndexOutOfBoundsException if index is out of bounds. index should be in
  // the range of [0.. size()-1]
  public Box remove(int index) {
    if (index < 0 || index >= size) { // check index out of bounds
      throw new IndexOutOfBoundsException(
          "\nWARNING: Index " + "should be from 0 to " + (size() - 1) + "!");
    } else if (this.isEmpty()) { // check if the list is empty
      System.out.println("this list is empty");
    }
    LinkedBoxNode current = head; // set the runner
    LinkedBoxNode before = null; // set the runner
    int count = 0; // set the number of loop
    while (count < index) { // loop the number of times same as index
      before = current; // update the runner each loop
      current = current.getNext();// update the runner each loop
      count++; // update the count
    }
    if (before == null) { // if we are a the head (index 0) if the list
      head = current.getNext(); // remove the head
      size--; // update the size
    } else {
      before.setNext(current.getNext()); // link the previous node with next one (jump middle one)
      size--; // update the size
    }
    return current.getBox(); // return the node's box that has been removed
  }

  // Removes all the boxes from this list
  public void clear() {
    while (head != null) { // when the head is not null
      this.remove(0); // remove the head as long as there is node inside the list.
    }
  }

  // Returns a String representation of the state and content of this LinkedBoxList
  // An example of source code for this method is provided you in the next paragraph
  /**
   * Returns a String representation for this LinkedBoxList
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder(); // creates a StringBuilder object
    String newLine = System.getProperty("line.separator");
    result.append("------------------------------------------------" + newLine);
    if (!isEmpty()) {
      LinkedBoxNode runner = head;
      int index = 0;
      // traverse the list and add a String representation for each box
      while (runner != null) {
        result.insert(0,
            "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs" + newLine);
        runner = runner.getNext();
        index++;
      }
      result.insert(0, "Box List Content:" + newLine);
    }
    result.insert(0, "List size: " + size + " box(es)." + newLine);
    result.insert(0, "Box List is empty: " + isEmpty() + newLine);
    result.insert(0, "------------------------------------------------" + newLine);
    return result.toString();
  }


}

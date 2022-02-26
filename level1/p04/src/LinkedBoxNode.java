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
 * @author Yatin node object that linked with next and have box object inside it.
 */
public class LinkedBoxNode {

  private Box box; // box that represents the data for this Linked node
  private LinkedBoxNode next; // reference to the next Linked Box Node
  // constructors

  public LinkedBoxNode(Box box) {
    this.box = box;
  } // creates a new LinkedBoxNode object with a given
  // box and without referring to any next LinkedBoxNode

  public LinkedBoxNode(Box box, LinkedBoxNode next) {
    this.box = box;
    this.next = next;
  } // creates a new LinkedBoxNode
  // object and sets its instance fields box and next to the specified ones

  // getters and setters methods
  /**
   * @return the next node of the node
   */
  public LinkedBoxNode getNext() {
    return next; // get the next of each node
  }

  /**
   * @param next set the next node of the node
   */
  public void setNext(LinkedBoxNode next) {
    this.next = next;
  }

  /**
   * @return return the box object that is inside the node
   */
  public Box getBox() {
    return this.box;
  }

  /**
   * @param box set the box, put the box inside the node.
   */
  public void setBox(Box box) {
    this.box = box;
  }

}

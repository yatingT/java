//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Patient Record System

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

// import the required package
import java.util.NoSuchElementException;

/**
 * This class implements a binary search tree (BST) which stores a set of patient records. The left
 * subtree contains the patient records of all the patients who are older than the patient who's
 * PatientRecord is stored at a parent node. The right subtree contains the patient records of all
 * the patients who are younger than the patient who's PatientRecord is stored at a parent node.
 *
 */
public class PatientRecordTree {
  private PatientRecordNode root; // root of this binary search tree
  private int size; // total number of patient records stored in this tree.

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this PatientRecordTree is empty, false otherwise
   */
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * Returns the number of patient records stored in this BST.
   * 
   * @return the size of this PatientRecordTree
   */
  public int size() {
    return size;
  }

  /**
   * Recursive helper method to add a new PatientRecord to a PatientRecordTree rooted at current.
   * 
   * @param current   The "root" of the subtree we are inserting newRecord into.
   * @param newRecord The PatientRecord to be added to a BST rooted at current.
   * @return true if the newRecord was successfully added to this PatientRecordTree, false otherwise
   */
  public static boolean addPatientRecordHelper(PatientRecord newRecord, PatientRecordNode current) {
    PatientRecordNode node = new PatientRecordNode(newRecord); // put the new record into a node
    if (node.getPatientRecord().compareTo(current.getPatientRecord()) < 0) { // if new patient older
      if (current.getLeftChild() == null) { // if there is no left child for current
        current.setLeftChild(node); // set the node as left child.
        return true; // return the node was successfully added.
      } // if the left node is not empty , set the left node as current and then recursive compare
      return addPatientRecordHelper(newRecord, current.getLeftChild());
    }
    if (node.getPatientRecord().compareTo(current.getPatientRecord()) > 0) {// if it is younger
      if (current.getRightChild() == null) {// if there is no right child for current
        current.setRightChild(node);// set the node as right child.
        return true;// return the node was successfully added.
      } // if the right node is not empty , set the right node as current and then recursive compare
      return addPatientRecordHelper(newRecord, current.getRightChild());
    }
    return false; // return the node was not successfully added.
  }

  /**
   * Adds a new PatientRecord to this PatientRecordTree
   * 
   * @param newRecord a new PatientRecord to add to this BST.
   * @return true if the newRecord was successfully added to this BST, and returns false if there is
   *         a match with this PatientRecord already already stored in this BST.
   */
  public boolean addPatientRecord(PatientRecord newRecord) {
    PatientRecordNode node = new PatientRecordNode(newRecord); // put the record into a new node
    if (isEmpty()) { // Add newRecord to an empty PatientRecordTree
      root = node;// set the root as the new node
      size++;// Increment of size
      return true;
    } else if (addPatientRecordHelper(newRecord, root)) {// then Add newRecord to an non-empty tree
      size++; // Increment of size
      return true;// return the node was successfully added.
    } else {
      return false;// return the node was not successfully added.
    }
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a PatientRecordTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current PatientRecordNode within this BST.
   * @return a String representation of all the PatientRecords stored in the sub-tree
   *         PatientRecordTree rooted at current in increasing order with respect to the patients
   *         dates of birth. Returns an empty String "" if current is null.
   */
  public static String toStringHelper(PatientRecordNode current) {
    String record = new String(); // create new empty string
    if (current == null) { // if the current is null, return nothing, then the method jump to
      return ""; // Previous level.
    } else {// print record one by one in order.
      record = record + toStringHelper(current.getLeftChild()); // print left child first
      record = record + current.getPatientRecord().getName() + "("// then print the data
          + current.getPatientRecord().getStringDateOfBirth() + ")" + "\n";
      record = record + toStringHelper(current.getRightChild()); // last, print the right child
    }
    return record; // return the big, final string of the entire tree.
  }

  /**
   * Returns a String representation of all the PatientRecords stored within this BST in the
   * increasing order, separated by a newline "\n". For instance: "Sarah(1/2/1935)" + "\n" +
   * "George(5/27/1943)" + "\n" + "Adam(8/12/1972)" + "\n" + "Norah(11/23/1985)" + "\n" +
   * "William(6/4/1998)" + "\n" + "Nancy(9/12/2003)" + "\n" + "Sam(4/20/2019)" + "\n"
   * 
   * @return a String representation of all the PatientRecords stored within this BST sorted in an
   *         increasing order with respect to the dates of birth of the patients (i.e. from the
   *         oldest patient to the youngest patient). Returns an empty string "" if this BST is
   *         empty.
   */
  public String toString() { // call the helper function.
    return toStringHelper(root);
  }

  /**
   * Search for a patient record (PatientRecord) given the date of birth as lookup key.
   * 
   * @param date a String representation of the date of birth of a patient in the format mm/dd/yyyy
   * @return the PatientRecord of the patient born on date.
   * @throws a NoSuchElementException with a descriptive error message if there is no PatientRecord
   *           found in this BST having the provided date of birth
   */
  public PatientRecord lookup(String date) {
    PatientRecord findRecord = new PatientRecord("", date); // put the string into a PatientRecord
    return this.lookupHelper(findRecord, root);
  }

  /**
   * Recursive helper method to lookup a PatientRecord given a reference PatientRecord with the same
   * date of birth in the subtree rooted at current
   * 
   * @param findRecord a reference to a PatientRecord target we are lookup for a match in the BST
   *                   rooted at current.
   * @param current    "root" of the subtree we are looking for a match to findRecord within it.
   * @return reference to the PatientRecord stored stored in this BST which matches findRecord.
   * @throws NoSuchElementException with a descriptive error message if there is no patient record
   *                                whose date of birth matches date, stored in this BST.
   */
  private PatientRecord lookupHelper(PatientRecord findRecord, PatientRecordNode current) {
    if (isEmpty()) { // check if the tree is empty, if so, throw NoSuchElementException
      throw new NoSuchElementException(
          "No patient record whose date of birth matches date," + " stored in this BST");
    }
    if (current != null) { // if the the current is not null, avoid the null pointer exception
      if (findRecord.compareTo(current.getPatientRecord()) == 0) { // if they compared same
        return current.getPatientRecord(); // return the current node
      }
      if (findRecord.compareTo(current.getPatientRecord()) < 0) { // if the target is older
        return lookupHelper(findRecord, current.getLeftChild()); // move to the left child compare
      }
      if (findRecord.compareTo(current.getPatientRecord()) > 0) { // if the target is younger
        return lookupHelper(findRecord, current.getRightChild());// move to the right child compare
      }
    } else { // if the current is null and didn't find the target
      throw new NoSuchElementException( // no such element in this tree.
          "No patient record whose date of birth matches date," + " stored in this BST");
    }
    return null;
  }

  /**
   * Computes and returns the height of this BST, counting the number of nodes (PatientRecordNodes)
   * from root to the deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current PatientRecordNode within a PatientRecordTree
   * @return height of the subtree rooted at current, counting the number of PatientRecordNodes
   */
  public static int heightHelper(PatientRecordNode current) {
    if (current == null) { // if empty tree
      return 0; // the height should be null
    } else {
      int left = heightHelper(current.getLeftChild()); // get the depth from the left
      int right = heightHelper(current.getRightChild()); // get the depth from the right
      return 1 + Math.max(left, right);// add the max depth of left or right with one root level.
    }
  }

  /**
   * @param current "root" of the subtree we are looking for
   * @return reference to the PatientRecord stored stored in this BST which is the youngest patient.
   */
  public static PatientRecord youngestHelper(PatientRecordNode current) {
    if (current.getRightChild() != null) { // if the current have right child
      return youngestHelper(current.getRightChild());// move to the right child until there is
    } else {// no right child
      return current.getPatientRecord(); // return the most right and deepest child node.
    }
  }

  /**
   * Returns the PatientRecord of the youngest patient in this BST.
   * 
   * @return the PatientRecord of the youngest patient in this BST and null if this tree is empty.
   */
  public PatientRecord getRecordOfYoungestPatient() {
    return youngestHelper(root);
  }


  /**
   * @param current "root" of the subtree we are looking for
   * @return reference to the PatientRecord stored stored in this BST which is the youngest patient.
   */
  public static PatientRecord oldestHelper(PatientRecordNode current) {
    if (current.getLeftChild() != null) { // if the current have left child
      return oldestHelper(current.getLeftChild());// move to the left child until there is
    } else {// no left child
      return current.getPatientRecord(); // return the most left and deepest child node.
    }
  }

  /**
   * Returns the PatientRecord of the oldest patient in this BST.
   * 
   * @return the PatientRecord of the oldest patient in this BST, and null if this tree is empty.
   */
  public PatientRecord getRecordOfOldestPatient() {
    return oldestHelper(root);
  }

}

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
 * This class checks the correctness of the implementation of the methods defined in the class
 * PatientRecordTree.
 *
 */
public class PatientRecordTreeTester {


  /**
   * @return the tree that we use to test
   */
  public static PatientRecordTree getTree() {
    PatientRecordTree bst = new PatientRecordTree(); // create new tree
    bst.addPatientRecord(new PatientRecord("Norah", "11/23/1985")); // add records
    bst.addPatientRecord(new PatientRecord("George", "5/27/1943"));
    bst.addPatientRecord(new PatientRecord("Adam", "8/12/1972"));
    bst.addPatientRecord(new PatientRecord("William", "6/4/1998"));
    bst.addPatientRecord(new PatientRecord("Sarah", "1/2/1945"));
    bst.addPatientRecord(new PatientRecord("Andrew", "4/20/2019"));
    bst.addPatientRecord(new PatientRecord("Tom", "1/2/1935"));
    bst.addPatientRecord(new PatientRecord("Sam", "9/12/2003"));
    bst.addPatientRecord(new PatientRecord("Emily", "2/28/2020"));
    return bst;
  }

  /**
   * Checks the correctness of the implementation of both addPatientRecord() and toString() methods
   * implemented in the PatientRecordTree class. This unit test considers at least the following
   * scenarios. (1) Create a new empty PatientRecordTree, and check that its size is 0, it is empty,
   * and that its string representation is an empty string "". (2) try adding one patient record and
   * then check that the addPatientRecord() method call returns true, the tree is not empty, its
   * size is 1, and the .toString() called on the tree returns the expected output. (3) Try adding
   * another patientRecord which is older that the one at the root, (4) Try adding a third patient
   * Record which is younger than the one at the root, (5) Try adding at least two further patient
   * records such that one must be added at the left subtree, and the other at the right subtree.
   * For all the above scenarios, and more, double check each time that size() method returns the
   * expected value, the add method call returns true, and that the .toString() method returns the
   * expected string representation of the contents of the binary search tree in an ascendant order
   * from the oldest patient to the youngest one. (6) Try adding a patient whose date of birth was
   * used as a key for a patient record already stored in the tree. Make sure that the
   * addPatientRecord() method call returned false, and that the size of the tree did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordToStringSize() {
    // (1)
    PatientRecordTree bst = new PatientRecordTree(); // create new empty tree
    if (bst.size() != 0 || bst.toString() != "") { // check size and string
      System.out.println("1");
      return false;
    }
    // (2)
    if (!bst.addPatientRecord(new PatientRecord("Norah", "11/23/1985"))) { // add one node
      System.out.println("2");
      return false;
    }
    if (bst.size() != 1) {// check the size
      System.out.println("2a");
      return false;
    }
    if (!bst.toString().equals("Norah(11/23/1985)\n")) {// check the string
      System.out.println("2b");
      return false;
    }
    if (bst.isEmpty()) { // check if it is empty
      System.out.println("2c");
      return false;
    }
    // (3)
    if (!bst.addPatientRecord(new PatientRecord("George", "5/27/1943"))) { // add more node
      System.out.println("3");
      return false;
    }
    if (!bst.toString().equals("George(5/27/1943)\n" + "Norah(11/23/1985)\n")) { // check the
                                                                                 // order
      System.out.println("3a"); // of to string
      return false;
    }
    // (4)
    if (!bst.addPatientRecord(new PatientRecord("Tom", "1/2/1935"))) { // make sure the deeper
                                                                       // level record can be
                                                                       // added
      System.out.println("4");
      return false;
    }
    // (5)
    bst.addPatientRecord(new PatientRecord("William", "6/4/1998"));// add more
    bst.addPatientRecord(new PatientRecord("Sarah", "1/2/1945"));
    if (bst.size() != 5 || bst.isEmpty()) { // check the size
      System.out.println("5");
      return false;
    } // check the toString method,and order it printed
    if (!bst.toString().equals("Tom(1/2/1935)\n" + "George(5/27/1943)\n" + "Sarah(1/2/1945)\n"
        + "Norah(11/23/1985)\n" + "William(6/4/1998)\n")) {
      // check the order
      System.out.println("5a");
      return false;
    }
    // (6)
    // check if a patient who have a same birthday with the exist patient can be added or not.
    if (bst.addPatientRecord(new PatientRecord("Temmy", "1/2/1935"))) { // if true, test fail.
      System.out.println(bst.addPatientRecord(new PatientRecord("Temmy", "1/2/1935")));
      return false;
    }
    if (bst.size() != 5) { // check the size after a patient was fail to add.
      return false;
    }
    return true;

  }

  /**
   * This method checks mainly for the correctness of the PatientRecordTree.lookup() method. It must
   * consider at least the following test scenarios. (1) Create a new PatientRecordTree. Then, check
   * that calling the lookup() method with any valid date must throw a NoSuchElementException. (2)
   * Consider a PatientRecordTree of height 3 which consists of at least 5 PatientRecordNodes. Then,
   * try to call lookup() method to search for the patient record at the root of the tree, then a
   * patient records at the right and left subtrees at different levels. Make sure that the lookup()
   * method returns the expected output for every method call. (3) Consider calling .lookup() method
   * on a non-empty PatientRecordTree with a date of birth not stored in the tree, and ensure that
   * the method call throws a NoSuchElementException.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddPatientRecordAndLookup() {
    PatientRecordTree bst = getTree();// get the tree that we have for test
    // try to look up different exist patients
    if (!bst.lookup("6/4/1998").equals(new PatientRecord("William", "6/4/1998"))) {
      return false;
    }
    if (!bst.lookup("11/23/1985").equals(new PatientRecord("Norah", "11/23/1985"))) {
      return false;
    }
    if (!bst.lookup("1/2/1935").equals(new PatientRecord("Tom", "1/2/1935"))) {
      return false;
    }
    try {
      bst.lookup("6/4/1997"); // try to look up someone is not exist.
    } catch (NoSuchElementException e) { // catch the exception
      return true; // if the exception cached, return true
    }
    return false;
  }

  /**
   * Checks for the correctness of PatientRecordTree.height() method. This test must consider
   * several scenarios such as, (1) ensures that the height of an empty patient record tree is zero.
   * (2) ensures that the height of a tree which consists of only one node is 1. (3) ensures that
   * the height of a PatientRecordTree with the following structure for instance, is 4. (*) / \ (*)
   * (*) \ / \ (*) (*) (*) / (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    PatientRecordTree bst = new PatientRecordTree();// create an empty tree
    if (bst.height() != 0) {// depth should be 0
      return false;
    }
    bst.addPatientRecord(new PatientRecord("Norah", "11/23/1985")); // add one node to the tree
    if (bst.height() != 1) { // the depth should be one
      return false;
    }
    PatientRecordTree bst1 = getTree(); // get our big tree
    if (bst1.height() != 4) { // check the depth, should be 4
      return false;
    }
    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfYoungestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfYoungestPatient() {
    PatientRecordTree bst = getTree(); // get the test tree
    if (!bst.getRecordOfYoungestPatient().equals(new PatientRecord("Emily", "2/28/2020"))) {
      return false; // if not match with the youngest expectation, return false
    }
    return true;
  }

  /**
   * Checks for the correctness of PatientRecordTree.getRecordOfOldestPatient() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetRecordOfOldestPatient() {
    PatientRecordTree bst = getTree(); // get the test tree
    if (!bst.getRecordOfOldestPatient().equals(new PatientRecord("Tom", "1/2/1935"))) {
      return false;// if not match with the oldest expectation, return false
    }
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddPatientRecordToStringSize()" + testAddPatientRecordToStringSize());
    System.out.println("testAddPatientRecordAndLookup()" + testAddPatientRecordAndLookup());
    System.out.println("testHeight()" + testHeight());
    System.out.println("testGetRecordOfYoungestPatient()" + testGetRecordOfYoungestPatient());
    System.out.println("testGetRecordOfOldestPatient()" + testGetRecordOfOldestPatient());


  }

}

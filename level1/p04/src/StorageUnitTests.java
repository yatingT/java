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
 * @author Yatin test class for Box , node, and list
 */
public class StorageUnitTests {

  // Checks whether the behavior of equals method is correct
  public static boolean testBoxEquals() {
    // create new box
    Box b1 = new Box(1, 5);
    Box b2 = new Box(1, 5);
    Box b3 = new Box(4, 5);
    // check
    if (!b1.equals(b2)) { // should return true
      System.out.println("return false when two equal box");
      return false;
    }
    if (b1.equals(b3)) { // should return false
      System.out.println("return true when two different box");
      return false;
    }
    return true;
  }

  // Checks whether the behavior of compareTo method is correctly implemented
  public static boolean testBoxCompareTo() {
    // create new boxes
    Box b1 = new Box(1, 5);
    Box b2 = new Box(1, 5);
    Box b3 = new Box(4, 10);
    Box b4 = new Box(4, 3);
    // compare
    if (b1.compareTo(b2) != 0) { // should return 0
      System.out.println("not return 0 for two equal box");
      return false;
    }
    if (b1.compareTo(b3) != -5) { // should return negative number
      System.out.println("not return negetive number for small compare big");
      return false;
    }
    if (b2.compareTo(b4) != 2) { // should return positive number
      System.out.println("not return positive number for big compare small");
      return false;
    }
    return true;

  }

  public static boolean testLinkedBoxListGet() {
    // create new list and box
    LinkedBoxList list = new LinkedBoxList(6);
    Box b1 = new Box(12, 1);
    Box b2 = new Box(12, 2);
    Box b3 = new Box(12, 3);
    Box b4 = new Box(12, 4);
    Box b5 = new Box(12, 5);
    // add box to the new nodes
    list.add(b1);
    list.add(b2);
    list.add(b3);
    list.add(b4);
    list.add(b5);
    // check
    if (list.get(0) != b5) { // should be the same.
      System.out.println("first test false");
      return false;
    } else if (list.get(2) != b3) { // should be the same.
      System.out.println("second test false");
      return false;
    } else if (list.get(4) != b1) {// should be the same.
      System.out.println("thrid test false");
      return false;
    } else {
      return true;
    }
  }

  public static boolean testLinkedBoxListisContains() {
    // create new list and box
    LinkedBoxList list = new LinkedBoxList(6);
    Box b1 = new Box(12, 1);
    Box b2 = new Box(12, 2);
    Box b3 = new Box(12, 3);
    Box b4 = new Box(12, 4);
    Box b5 = new Box(12, 5);
    // add box to the new nodes
    list.add(b1);
    list.add(b2);
    list.add(b3);
    list.add(b4);
    list.add(b5);
    // check
    if (!list.contains(b5)) { // should contain
      System.out.println("first test false");
      return false;
    }
    if (!list.contains(b1)) { // should contain
      System.out.println("first test false");
      return false;
    }
    return true;
  }

  public static boolean testLinkedBoxListAdd() {
    // create new list and box
    LinkedBoxList list = new LinkedBoxList(6);
    Box b1 = new Box(12, 1);
    Box b2 = new Box(12, 3);
    Box b3 = new Box(12, 4);
    Box b4 = new Box(12, 2);
    Box b5 = new Box(12, 5);
    // add box to the new nodes
    list.add(b1);
    list.add(b2);
    list.add(b3);
    list.add(b4);
    list.add(b5);
    // check
    if (list.get(0) != b5) { // check the weight of the index
      System.out.println("first test false");
      return false;
    } else if (list.get(2) != b2) { // check the weight of the index
      System.out.println("second test false");
      return false;
    } else if (list.get(4) != b1) {// check the weight of the index
      System.out.println("thrid test false");
      return false;
    } else {
      return true;
    }
  }

  // Checks whether remove method defined in your LinkedBoxList works correctly
  public static boolean testLinkedBoxListRemove() {
    // create new list and box
    LinkedBoxList list = new LinkedBoxList(6);
    Box b1 = new Box(12, 1);
    Box b2 = new Box(12, 3);
    Box b3 = new Box(12, 4);
    Box b4 = new Box(12, 2);
    Box b5 = new Box(12, 5);
    list.add(b1);
    list.add(b2);
    list.add(b3);
    list.add(b4);
    list.add(b5);
    // add box to the new nodes
    if (list.remove(0) != b5) { // check the one that should be remove is the correck one
      return false;
    }
    if (list.remove(3) != b1) {
      return false;
    }
    if (list.remove(2) != b4) {
      return false;
    }
    return true;
  }


  public static void main(String[] args) {

    System.out.println("testBoxEquals()" + testBoxEquals());
    System.out.println("testBoxCompareTo()" + testBoxCompareTo());
    System.out.println("testLinkedBoxListGet()" + testLinkedBoxListGet());
    System.out.println("testLinkedBoxListisContains()" + testLinkedBoxListisContains());
    System.out.println("testLinkedBoxListAdd()" + testLinkedBoxListAdd());
    System.out.println("testLinkedBoxListRemove()" + testLinkedBoxListRemove());



  }

}

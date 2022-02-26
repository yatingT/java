// --== CS400 File Header Information ==--
// Name: Yating Tian
// Email: ytian83@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


/**
 * test class for the HashTableMap class, testing all of the method of that class.
 * 
 * @author Yating Tian
 *
 */
public class TestHashTable {

  /**
   * test the put method in the HashTableMap class
   * 
   * @return true if the method does that we expect, false otherwise.
   */
  public static boolean test1() { // create a new hash table of the HashTableMap class.
    HashTableMap<Integer, Integer> table = new HashTableMap<Integer, Integer>(6);
    if (!table.put(2, 22)) { // put one data into it, test fail if put method return false.
      System.out.println("test1a");
      return false;
    }
    if (table.size() != 1) { // check the size is increased
      System.out.println("test1b");
      return false;
    }
    if (!table.put(3, 33)) {// put another data into it, test fail if put method return false.
      System.out.println("test1c");
      return false;
    }
    if (table.size() != 2) { // check the size is increased
      System.out.println("test1d");
      return false;
    }
    return true; // if passed four test, return true.
  }

  /**
   * test the get and grow method in the HashTableMap class
   * 
   * @return true if the method does that we expect, false otherwise.
   */
  public static boolean test2() { // create a new hash table of the HashTableMap class.
    HashTableMap<Integer, Integer> table = new HashTableMap<Integer, Integer>(10);
    // put some data in the table
    table.put(12, 121);
    table.put(13, 131);
    table.put(23, 231);
    if (!table.get(13).equals(131)) {// get correct value based on the paired key
      return false;
    }
    if (!table.get(23).equals(231)) {// get correct value based on the paired key
      return false;
    }
    table.put(1, 11); // put in more data here
    table.put(2, 22);
    table.put(3, 33);
    Integer current = table.get(3);
    if (!current.equals(33)) { // get correct value based on the paired key
      return false;
    }
    return true; // if passed three test, return true.
  }

  /**
   * test the containsKey method in the HashTableMap class
   * 
   * @return true if the method does that we expect, false otherwise.
   */
  public static boolean test3() { // create a new hash table of the HashTableMap class.
    HashTableMap<Integer, Integer> table = new HashTableMap<Integer, Integer>(6);
    // put some data in the table
    table.put(1, 11);
    table.put(2, 22);
    table.put(3, 33);
    if (!table.containsKey(1)) {// check if the key exist in the table
      System.out.println("test3a");
      return false;
    }
    if (!table.containsKey(2)) { // check if the key exist in the table
      System.out.println("test3b");
      return false;
    }
    if (!table.containsKey(3)) {// check if the key exist in the table
      System.out.println("test3c");
      return false;
    }
    return true; // if passed three test, return true.
  }

  /**
   * test the remove method in the HashTableMap class
   * 
   * @return true if the method does that we expect, false otherwise.
   */
  public static boolean test4() { // create a new hash table of the HashTableMap class.
    HashTableMap<Integer, Integer> table = new HashTableMap<Integer, Integer>(6);
    // put some data in the table
    table.put(1, 11);
    table.put(2, 22);
    table.put(3, 33);
    table.remove(2);
    if (table.containsKey(2)) { // check if the key was removed
      return false;
    }
    if (table.size() != 2) { // check if the size was changed
      return false;
    }
    return true; // if passed two test, return true.
  }

  /**
   * test the clear and size method in the HashTableMap class
   * 
   * @return true if the method does that we expect, false otherwise.
   */
  public static boolean test5() { // create a new hash table of the HashTableMap class.
    HashTableMap<Integer, Integer> table = new HashTableMap<Integer, Integer>(6);
    // put some data in the table
    table.put(1, 11);
    table.put(2, 22);
    table.put(3, 33);
    table.clear();
    if (table.size() != 0) { // check if the table was cleared
      return false;
    }
    if (table.containsKey(3)) { // check if the old element is still there.
      return false;
    }
    return true;// if passed two test, return true.
  }

}

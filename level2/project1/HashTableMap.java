// --== CS400 File Header Information ==--
// Name: Yating Tian
// Email: ytian83@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

// import required package
import java.util.LinkedList;
import java.util.NoSuchElementException;


/**
 * HashTable class that implements MapADT and store the basketball (NBA) player data. Data can be
 * multiple different types using key-value pair.
 * 
 * @author Yating Tian
 * @param <KeyType>        key type of the key in each pair
 * @param <ValueType>value type of the value in each pair
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private int size; // numbers of key-value pairs being store
  private int capacity; // number of index in our array
  private LinkedList<PlayerData<KeyType, ValueType>>[] hashtable; // Initialize the hash table

  /**
   * Constructor of the hash table
   * 
   * @param capacity let user decide the capacity of each hash table.
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity; // set the capacity for the table.
    this.hashtable = new LinkedList[capacity]; // set how many row (index) that the hash table has.
  }

  /**
   * Default constructor, if user didn't put the capacity, the default index is 10.
   */
  public HashTableMap() {
    this.capacity = 10; // with default capacity = 10
    this.hashtable = new LinkedList[capacity];
  }


  /**
   * insert the each data pair into the hash table.
   * 
   * @return whether successfully insert the data or not.
   * @param key   the Key of the pair data
   * @param value the value of the pair data
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // store the parameter pair data into a PlayerData type
    PlayerData<KeyType, ValueType> data = new PlayerData<KeyType, ValueType>(key, value);
    Integer index = Math.abs(key.hashCode()) % capacity; // get the hash code index of from the key
    if (containsKey(key)) { // check if the key exist in the hash table arealy
      return false; // if so, refuse to insert
    } else {
      if (hashtable[index] == null) { // if the index doesn't have any Linked list
        hashtable[index] = new LinkedList<PlayerData<KeyType, ValueType>>(); // create a new linked
                                                                             // list
      }
      hashtable[index].add(data); // add the data to the list.
      size++; // Increment of size
      if (size >= 0.8 * capacity) { // check the loading factor
        grow(); // if the ratio higher than 80 %, grow (double) the capacity of the hash table .
      }
      return true; // if the data pair successfully insert, return true.
    }
  }


  /**
   * search from the hash table and get the value of the key.
   * 
   * @return the value of the input key
   * @param Key, the key that the method will try to find
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (!containsKey(key)) { // if the table doesn't have the key
      throw new NoSuchElementException("No such key"); // throw error
    } // if the table does have the key
    Integer index = Math.abs(key.hashCode()) % capacity; // get the index of that key
    for (int i = 0; i < hashtable[index].size(); i++) { // loop over the linked list
      PlayerData<KeyType, ValueType> current = hashtable[index].get(i); // get the value of the data
      if (current.key.equals(key)) { // check if the key that we get is same as input key
        return current.value; // return the value of that data.
      }
    }
    throw new NoSuchElementException("No such key"); // if didn't find the data, throw error.
  }

  /**
   * @return the size (the number of index) of the table.
   */
  @Override
  public int size() {
    return size; // return the instance field size
  }

  /**
   * search the hash table and check if the parameter key is inside the table.
   * 
   * @return whether the table contains the key or not.
   * @param key, the key that the method will search for.
   */
  @Override
  public boolean containsKey(KeyType key) {
    if (size == 0) { // if the table does not contain anything, the key does not exist
      return false;
    }
    Integer index = Math.abs(key.hashCode()) % capacity; // get the index of the parameter key
    if (hashtable[index] == null) { // check the linked list of that index exist or not
      return false;
    } else { // if there is a linked list in the index
      LinkedList<PlayerData<KeyType, ValueType>> temp = hashtable[index]; // store the list in a
                                                                          // vialable
      for (int i = 0; i < temp.size(); i++) { // check each key of the list
        if (temp.get(i).key.equals(key)) { // if the key is the same
          return true; // saying there is the key in the table.
        }
      }
      return false;
    }

  }

  /**
   * remove a data pair from the hash table.
   * 
   * @return the value of the removed pair.
   * @param key, the key of the data pair that being to remove
   */
  @Override
  public ValueType remove(KeyType key) {
    Integer index = Math.abs(key.hashCode()) % capacity; // get the index of the parameter's key
    if (!containsKey(key)) { // if the key does not exist in the table, fail to remove anything
      return null;
    } else { // if the table does contains the key
      for (int i = 0; i < hashtable[index].size(); i++) { // check each element of the linked list
        if (hashtable[index].get(i).key.equals(key)) { // if the key is the same
          PlayerData<KeyType, ValueType> current = hashtable[index].get(i); // get the data store
          hashtable[index].remove(i); // remove it from the list
          size--; // decrease size
          return (ValueType) current.value; // return the value of that data pair
        }
      }
    }
    return null; // if not successfully remove, return null
  }

  /**
   * Clear the entire hash table
   */
  @Override
  public void clear() {
    hashtable = new LinkedList[capacity]; // create a new, empty hash table for the instance field
    size = 0; // set the size to empty
  }


  /**
   * if the loading factor of the hash table is higher than 80 %, grow the capacity of the table by
   * double the capacity.
   */
  private void grow() {
    this.capacity = capacity * 2; // double the capacity
    LinkedList<PlayerData<KeyType, ValueType>>[] newtable = new LinkedList[capacity]; // create a
                                                                                      // new table
    for (int i = 0; i < hashtable.length; i++) { // loop over each index of the old table
      if (hashtable[i] != null) { // if the index is empty, skip this index
        KeyType key = hashtable[i].get(0).key; // get the key of the first list element
        ValueType value = hashtable[i].get(0).value;// get the value of the first list element
        PlayerData<KeyType, ValueType> data = new PlayerData<KeyType, ValueType>(key, value);// store
        Integer index = Math.abs(key.hashCode()) % capacity; // get the index based on new capacity
        newtable[index] = new LinkedList<PlayerData<KeyType, ValueType>>(); // create new list
        newtable[index].add(data); // add the data to the new list of the new table
      }
    }
    this.hashtable = newtable; // set the hash table to the new table.
  }

}

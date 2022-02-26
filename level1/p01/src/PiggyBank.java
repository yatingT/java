
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P01 Piggy Bank
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

import java.util.Random; // import Random class that will be used in this class

/*
 * create a class to implement a piggy bank which holds US currency coins. It can add/remove coins
 * and get balance of the bank.
 */
public class PiggyBank {
  // declare constants at the top of class, out side of any method.
  public final static int[] COINS_VALUES = {1, 5, 10, 25}; // coins values in cents
  public final static String[] COINS_NAMES = {"PENNY", "NICKEL", "DIME", "QUARTER"}; // coins names
  public final static String NO_SUCH_COIN = "N/A"; // N/A string
  private final static Random RAND_GEN = new Random(100); // generator of random integers

  /**
   * Returns the name of a specified coin value.
   * 
   * @param coin represents a coin value in cents.
   * @return the String name of a specified coin value if it is valid and N/A if the coin value is
   *         not valid
   */
  public static String getCoinName(int coin) {
    // check for different condition of coins value and match it with correct coins name.
    if (coin == COINS_VALUES[0]) { // Penny
      return COINS_NAMES[0];
    } else if (coin == COINS_VALUES[1]) {// Nickel
      return COINS_NAMES[1];
    } else if (coin == COINS_VALUES[2]) {// Dime
      return COINS_NAMES[2];
    } else if (coin == COINS_VALUES[3]) {// Quarter
      return COINS_NAMES[3];
    } else { // other inputs
      return NO_SUCH_COIN;
    }
  }

  /**
   * Returns the balance of a piggy bank in cents.
   * 
   * @param coins an oversize array which contains all the coins held in a piggy bank
   * @param size  the total number of coins stored in coins array
   * @return the total value of the coins held in a piggy bank in cents
   */
  public static int getBalance(int[] coins, int size) {
    int cents = 0; // Step1: assign local variable to hold the total cent while counting.
    for (int i = 0; i < size; i++) // step2: go over each single coins in piggy bank
      cents += coins[i]; // and add them up to a total value in cents.
    return cents; // step3: return final sum
  }

  /**
   * Returns the total number of coins of a specific coin value held in a piggy bank
   *
   * @param coinValue the value of a specific coin
   * @param coins     an oversize array which contains all the coins held in a piggy bank
   * @param size      the total number of coins stored in coins array
   * @return the number of coins of value coinValue stored in the array coins
   */
  public static int getSpecificCoinCount(int coinValue, int[] coins, int size) {

    int count = 0;// step1: assign local variable to count the target value coins
    for (int i = 0; i < size; i++) // step2: go over each index of coin in the bank.
      if (coins[i] == coinValue) { // to check whether the coin matches with the target value.
        count += 1; // If so, it counts.
      }
    return count; // returns the total number of coins that we are looking for.
  }

  /**
   * Displays information about the content of a piggy bank
   *
   * @param coins an oversize array storing the coins held in a piggy bank
   * @param size  number of coin held array coins
   */
  public static void printPiggyBank(int[] coins, int size) {
    System.out.println("QUARTERS: " + getSpecificCoinCount(25, coins, size));
    System.out.println("DIMES: " + getSpecificCoinCount(10, coins, size));
    System.out.println("NICKELS: " + getSpecificCoinCount(5, coins, size));
    System.out.println("PENNIES: " + getSpecificCoinCount(1, coins, size));
    System.out.println("Balance: $" + getBalance(coins, size) * 0.01);
  }

  /**
   * Adds a given coin to a piggy bank. This operation can terminate successfully or unsuccessfully.
   * For either cases, this method displays a descriptive message for either cases.
   *
   * @param coin  the coin value in cents to be added to the array coins
   * @param coins an oversize array storing the coins held in a piggy bank
   * @param size  the total number of coins contained in the array coins before this addCoin method
   *              is called.
   * @return the new size of the coins array after trying to add coin.
   */
  public static int addCoin(int coin, int[] coins, int size) {
    // check if the integer coin value to be added to the piggy bank is not valid.
    if (getCoinName(coin) == NO_SUCH_COIN) {
      System.out.println(coin + " cents is not a valid US currency coin."); // give a error message.
    } else if (size == coins.length) {// Check if the bank is full.
      System.out.println("Tried to add a " + getCoinName(coin) // give a note that bank is full.
          + ", but could not because the piggy bank is full.");
    } else {
      coins[size] = coin;// finally, add the input coin at the end of the piggy bank array
      ++size; // with increment of size.
      System.out.println("Added a " + getCoinName(coin) + "."); // give a note of finish adding.
    }
    return size; // return with parameter(input) size with added one.
  }

  /**
   * Removes an arbitrary coin from a piggy bank. This method may terminate successfully or
   * unsuccessfully. In either cases, a descriptive message is displayed.
   *
   * @param coins an oversize array which contains the coins held in a piggy bank
   * @param size  the number of coins held in the coins array before this method is called
   * @return the size of coins array after this method returns successfully
   */
  public static int removeCoin(int[] coins, int size) {
    if (size == 0) { // check if the bank is already empty with no coins.
      System.out.println("Tried to remove a coin, but could not because the piggy bank is empty.");
    } else {
      int coin = RAND_GEN.nextInt(size); // assign the random number to a local variable.
      System.out.println("Removed a " + getCoinName(coins[coin]) + "."); // give a note after remove
      coins[coin] = coins[size - 1]; // move the last coin into the previous empty index.
      coins[size - 1] = 0; // change the number of last index into 0 (since it moved)
      --size; // removed one coin, decrement in bank size.
    }
    return size; // return total number of coins after one was removed.
  }

  /**
   * Removes all the coins in a piggy bank. It also displays the total value of the removed coins
   *
   * @param coins an oversize array storing the coins held in a piggy bank application
   * @param size  number of coins held in coins array before this method is called
   * @return the new size of the piggy bank after removing all its coins.
   */
  public static int emptyPiggyBank(int[] coins, int size) {
    int total = getBalance(coins, size); // Assign a local variable to hold the total value of coins
    if (size == 0) { // check if the bank is already empty before remove any.
      System.out.print("Zero coin removed. The piggy bank is already empty."); // give note of empty
    } else {
      for (int i = size; i > 0; i--) // go over each coin from last one to first one
        size = removeCoin(coins, i); // remove each of them and get the size after each remove.
      // Debug: size here might have problems here.
      System.out.print("All done. " + total + " cents removed."); // give note of removed value.
    }
    return size; // this should be 0, since all coins have been removed.
  }

}

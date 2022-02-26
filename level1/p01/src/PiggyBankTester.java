
public class PiggyBankTester {
  /**
   * Calls the test methods implemented in this class and displays their output
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    int[] coins = new int[5]; // give some parameters to test following methods. this is new array
    int size = 0; // new variable.
    coins = new int[] {10, 1, 5, 25, 1, 0, 0}; // a piggy bank array
    size = 5;// the size of the array.
    // print of all the test results with their name. False means the method has bug. 
    System.out.println("testGetCoinName(): " + testGetCoinName());
    System.out.println("testGetBalance(): " + testGetBalance());
    System.out.println("testGetSpecificCoinCount(): " + testGetSpecificCoinCount());
    System.out.println("testAddCoin(): " + testAddCoin(coins, size));
  }

  /**
   * Checks whether PiggyBank.getCoinName() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetCoinName() {
    // change some coin values and names
    PiggyBank.COINS_NAMES[1] = "Two cents";
    PiggyBank.COINS_NAMES[3] = "Fifty Cents";
    PiggyBank.COINS_VALUES[1] = 2;
    PiggyBank.COINS_VALUES[3] = 50;
    // consider all coin values as input arguments
    for (int i = 0; i < PiggyBank.COINS_VALUES.length; i++)
      if (!PiggyBank.getCoinName(PiggyBank.COINS_VALUES[i]).equals(PiggyBank.COINS_NAMES[i]))
        return false;
    // consider input argument which does not correspond to a coin value
    if (!PiggyBank.getCoinName(7).equals(PiggyBank.NO_SUCH_COIN))
      return false;
    if (!PiggyBank.getCoinName(-10).equals(PiggyBank.NO_SUCH_COIN))
      return false;
    // change the values and names back to original.
    PiggyBank.COINS_NAMES[1] = "NICKEL";
    PiggyBank.COINS_NAMES[3] = "QUARTER";
    PiggyBank.COINS_VALUES[1] = 5;
    PiggyBank.COINS_VALUES[3] = 25;
    return true; // the method works correct when return true.
  }

  /**
   * Checks whether PiggyBank.getBalance() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBalance() {
    // scenario 1 - empty piggy bank
    int[] coins = new int[10]; // array storing the coins held in a piggy bank
    int size = 0; // number of coins held in coins array
    if (PiggyBank.getBalance(coins, size) != 0) {
      System.out.println("Problem detected. Your PiggyBank.getBalance() did not "
          + "return the expected output when passed an empty piggy bank."); // debug notes
      return false; // catch if test failed.
    }
    // scenario 2 - non empty piggy bank
    coins = new int[] {10, 1, 5, 25, 1, 0, 0}; // test in different array
    size = 5; // assign the size of the array that tests the method.
    if (PiggyBank.getBalance(coins, size) != 42) {
      System.out.println("Problem detected. Your PiggyBank.getBalance() did not "
          + "return the expected output when passed an piggy bank that holds "
          + "two pennies, a nickel, a dime, and a quarter."); // debug notes
      return false; // catch if test failed.
    }
    // scenario 3 - another non empty piggy bank
    coins = new int[] {10, 1, 5, 25, 1, 0}; // test use a different array.
    size = 3; // with the size that we want.
    if (PiggyBank.getBalance(coins, size) != 16) { // check the getBalance() returns same as expect.
      System.out.println("Problem detected. Your PiggyBank.getBalance() did not "
          + "return the expected output when passed an piggy bank that holds "
          + "a penny, a nickel, and a dime, only."); // debug notes
      return false; // catch if test failed.
    }
    return true; // pass the test if passed all three scenario
  }

  /**
   * Checks whether PiggyBank.getSpecificCoinCount() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetSpecificCoinCount() {
    int coin = 10; // give a target coin value to test.
    // scenario 1 - empty piggy bank
    int[] coins = new int[10]; // array storing the coins held in a piggy bank
    int size = 0; // number of coins held in coins array
    if (PiggyBank.getSpecificCoinCount(coin, coins, size) != 0) { //
      System.out.println("Problem detected. Your PiggyBank.getSpecificCoinCount() did not "
          + "return the expected output when passed an empty piggy bank.");
      return false;
    }
    // scenario 2 - non empty piggy bank
    coins = new int[] {10, 1, 5, 25, 1, 0, 0}; // assign an array, with 1 dime in it.
    size = 5; // with size 5
    if (PiggyBank.getSpecificCoinCount(coin, coins, size) != 1) { // check if returns a correct.
      System.out.println("Problem detected. Your PiggyBank.getSpecificCoinCount() did not "
          + "return the expected output when passed an piggy bank that holds "
          + "two pennies, a nickel, a dime, and a quarter."); // note for debug.
      return false;
    }
    // scenario 3 - another non empty piggy bank
    coins = new int[] {5, 10, 5, 25, 10, 25, 5, 0, 0, 0}; // 2 dime in this longer new array
    size = 7; // assign the size of new array
    if (PiggyBank.getSpecificCoinCount(coin, coins, size) != 2) { // test the methods returns 2.
      System.out.println("Problem detected. Your PiggyBank.getSpecificCoinCount() did not "
          + "return the expected output when passed an piggy bank that holds " + " in  scenario 3"); // debug.
      return false;
    }
    return true; // pass the test if passed all three scenario
  }

  /**
   * Displays information about the content of a piggy bank
   *
   * @param coins an oversize array storing the coins held in a piggy bank
   * @param size  number of coin held array coins
   */
  public static boolean testAddCoin(int[] coins, int size) {
    // check if the method added one more coin and the last in coin is the one we added.
    if (PiggyBank.addCoin(25, coins, size) == size + 1 && coins[size] == 25) {
      return true; // make sure the method works correct.
    } else { // if not true, the test failed
      return false;
    }
  }

}

// --== CS400 File Header Information ==--
// Name: Yating Tian
// Email: ytian83@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
/**
 * store data of the basketball player of NBA
 * 
 * @author Yating Tian
 * @param <KeyType>   the key of the data pair
 * @param <ValueType> the value of the data pair
 */
public class PlayerData<KeyType, ValueType> {

  public KeyType key; // the key of the data pair
  public ValueType value; // the value of the data pair

  /**
   * the constructor of the PlayerData type
   * 
   * @param key   the key of the data pair
   * @param value the value of the data pair
   */
  public PlayerData(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }


  /**
   * @return the key of the data pair
   */
  public KeyType getKey() {
    return key;
  }

  /**
   * @param key set the key of the data pair
   */
  public void setKey(KeyType key) {
    this.key = key;
  }

  /**
   * @return the value of the data pair
   */
  public ValueType getValue() {
    return value;
  }

  /**
   * @param value set the value of the data pair.
   */
  public void setValue(ValueType value) {
    this.value = value;
  }

}

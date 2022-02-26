//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P03 Jungle Park
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

// import packages that we will need for this project
import java.util.ArrayList;

/**
 * @author Yatin all the deer item, include method such as scan for threat and take action.
 */
public class Deer extends Animal implements ParkGUI {

  private static final int SCAN_RANGE = 175; // scan range area to check for a threat in the
                                             // neighborhood
  private static final String IMAGE_FILE_NAME = "images/deer.png";
  private static int nextID = 1; // class variable that represents the identifier of the next deer
                                 // to be created

  private static final String TYPE = "DR"; // A String that represents the deer type
  private final int id; // Deer's id: positive number that represents the order of the deer


  // Constructor that creates a new Deer object positioned at a random position of the display
  // window
  public Deer(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);

    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
  }

  /**
   * scanForThreat() method should scan the area around this deer within a scanRange distance.
   * Checks if there is a threat (a Tiger for instance) at the neighborhood
   * 
   * @param scanRange an integer that represents the range of the area to be scanned around the
   *                  animal
   * @return It returns TRUE if there is at least one Tiger close to the deer within the scanRange
   *         distance.
   */
  public boolean scanForThreat(int scanRange) { // return a boolean to see if there is tiger around.
    ArrayList<ParkGUI> list = processing.listGUI; // get the list of the processing park
    for (int i = 0; i < list.size(); i++) { // loop over every item in the park list
      if (list.get(i) instanceof Tiger) { // to check if there is tiger inside this park
        Tiger tiger = (Tiger) list.get(i);// down casting the type from parkGUI to tiger
        if (this.isClose(tiger, scanRange)) { // check if this tiger is close to the deer.
          return true; // if the tiger is close, the deer facing a threat.
        }
      }
    }
    return false; // if no tiger after the looping, means there is no threat.
  }


  /**
   * Defines the behavior of a Deer object in the Jungle park override the action that the deer
   * might take.
   */
  @Override
  public void action() {
    this.processing.fill(0); // specify font color: black
    if (scanForThreat(SCAN_RANGE)) { // to check if there is threat insider the range area.
      this.processing.text("THREAT!", this.getPositionX(), // write the threat message on the top of
          this.getPositionY() - this.image.height / 2 - 4); // the image.
    }
  }
  
}

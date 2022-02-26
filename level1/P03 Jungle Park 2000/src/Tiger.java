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
 * This class represents a Tiger in the JunglePark application
 *
 */
/**
 * @author Yatin
 *
 */
public class Tiger extends Animal {
  private static final int SCAN_RANGE = 100; // range dimension for scanning the neighborhood for
                                             // food
  private static final String IMAGE_FILE_NAME = "images/tiger.png";
  private static int nextID = 1; // class variable that represents the identifier of the next tiger
                                 // to be created
  // Tiger's identification fields
  private static final String TYPE = "TGR"; // A String that represents the tiger type
  private final int id; // Tiger's id: positive number that represents the order of the tiger
  private int deerEatenCount; // Number of Deers that the current tiger has eaten so far



  /**
   * Creates a new Tiger object positioned at a random position of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Tiger(JunglePark processing) {
    // Set Tiger drawing parameters
    super(processing, IMAGE_FILE_NAME);

    // Set Tiger identification fields
    id = nextID;
    this.label = TYPE + id; // String that identifies the current tiger
    nextID++;
  }


  /**
   * @return how much deer that eaten by this tiger
   */
  public int getDeerEatenCount() {
    return this.deerEatenCount; // count the number of deer that was eaten by this tiger.
  }

  /**
   * Defines the Tiger's behavior in the Jungle Park. Scan for food first, if the food is close
   * enough, the tiger can take action to hop the food.
   */
  @Override
  public void action() {
    ArrayList<ParkGUI> list = processing.listGUI; // get the processing park list contains all
                                                  // animal
    for (int i = 0; i < list.size(); i++) { // loop over all the animals inside the jungle park.
      if (list.get(i) instanceof Deer) { // to check for food: check if there is deer around.
        Deer deer = (Deer) list.get(i); // down casting the type from parkGUI to deer.
        if (this.isClose(deer, SCAN_RANGE)) { // to check if the deer is close enough to hop and eat
          Deer food = (Deer) list.get(i); // if it is close enough, mark it as food.
          this.hop(food); // then the tiger can hop on the food and eat the deer.
          break; // get the lowest index if there is overlap deer.
        }
      }
    }
    if (deerEatenCount > 0) {
      displayDeerEatenCount(); // display deerEatenCount
    }
  }



  /**
   * Displays the number of eaten deers if any on the top of the tiger image
   */
  public void displayDeerEatenCount() {
    this.processing.fill(0); // specify font color: black
    // display deerEatenCount on the top of the Tiger's image
    this.processing.text(deerEatenCount, this.getPositionX(),
        this.getPositionY() - this.image.height / 2 - 4);
  }

  /**
   * if the food is close enough, hop on the food by move to the deer's position.
   * 
   * @param food the deer that should be close enough to hop
   */
  public void hop(Deer food) {
    this.setPositionX(food.getPositionX()); // move to food's x position
    this.setPositionY(food.getPositionY()); // move to food's y position
    processing.listGUI.remove(food); // since the deer was eaten by tiger, remove the deer
    deerEatenCount++; // Increment of food count
  }
}

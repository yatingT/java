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

/**
 * @author Yatin AddAnimalButton is a subclass for Button, Button in order to create new animal.
 */
public class AddAnimalButton extends Button implements ParkGUI {

  private String type; // type of the animal to add



  /**
   * Constructor of AddAnimalButton class
   * 
   * @param type animal type, might be tiger or deer
   * @param x    the x position of this button
   * @param y    the y position of this button
   * @param park the processing park
   */
  public AddAnimalButton(String type, float x, float y, JunglePark park) {
    super(x, y, park); // set the position and the processing park.
    this.type = type.toLowerCase(); // make sure all the type shows in lower case.
    this.label = "Add " + type; // set the name label of each button.
  }

  /**
   * override the mousePressed method. When the user presses the AddAnimalButton, a new animal will
   * be created and added to the park. The type of the animal, either tiger or deer is determined
   * using the type field defined in the AddAnimalButton class.
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) { // when mouse in on the button and pressed
      switch (type) { // use the type to distinguish whether it is a tiger or deer.
        case "tiger":
          processing.listGUI.add(new Tiger(processing)); // add a new tiger in the ParkPUI list.
          break;
        case "deer":
          processing.listGUI.add(new Deer(processing)); // add a new deer in the ParkPUI list.
          break;
      }
    }
  }
}

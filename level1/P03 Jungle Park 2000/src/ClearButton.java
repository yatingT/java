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
 * @author Yatin another type of button that is subclass of the Button class. This button is to
 *         clear all the animals inside the jungle park.
 */
public class ClearButton extends Button implements ParkGUI {


  /**
   * the constructor of ClearButton class.
   * 
   * @param x    the x position of the button
   * @param y    the y position if the button
   * @param park the processing park
   */
  public ClearButton(float x, float y, JunglePark park) {
    super(x, y, park); // create the button
    this.label = "Clear Park"; // set the name of the button.
  }

  /**
   * override the mousePresed method in order to clear all the animals of the jungle park
   */
  @Override
  public void mousePressed() {
    if (isMouseOver()) { // when the mouse is on the button and pressed
      processing.listGUI.clear(); // we clear all the animal clear() function.
    }
  }

}

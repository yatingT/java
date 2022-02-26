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
// File header comes here
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class implements the graphic application Jungle Park 2000
 *
 */
public class JunglePark extends PApplet {
  private PImage backgroundImage; // PImage object that represents the background image
  protected ArrayList<ParkGUI> listGUI; // ArrayList storing the graphical objects
                                        // present in this Jungle Park)



  /**
   * CallBack method Defines initial environment properties such as screen size and to load
   * background images and fonts as the program starts Initializes the backgroundImage and listGUI
   * instance fields.
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Jungle Park"); // Displays text in the title of the display window
    this.textAlign(PApplet.CENTER, PApplet.CENTER); // Sets the current alignment for drawing text
                                                    // to CENTER
    this.imageMode(PApplet.CENTER); // Sets the location from which images are drawn to CENTER
    this.rectMode(PApplet.CORNERS); // Sets the location from which rectangles are drawn.
    // rectMode(CORNERS) interprets the first two parameters of rect() method as the location of one
    // corner, and the third and fourth parameters as the location of the opposite corner.
    // rect() method draws a rectangle to the display window
    this.focused = true; // Confirms that our Processing program is "focused," meaning that
    // it is active and will accept mouse or keyboard input.
    backgroundImage = this.loadImage("images/background.png"); // load the background image

    listGUI = new ArrayList<ParkGUI>(); // create the listGUI ArrayList that would store all the
    // graphic objects (animals and buttons) that would be drawn on the display window
    listGUI.add(new AddAnimalButton("tiger", 43, 16, this)); // add tiger button
    listGUI.add(new AddAnimalButton("deer", 129, 16, this));// add deer button
    listGUI.add(new ClearButton(215, 16, this)); // clear park button.

  }

  /**
   * Sets the size of the application display window
   */
  @Override
  public void settings() {
    size(800, 632); // sets the size of the display window to 800 x 632 pixels
  }


  /**
   * Callback method called in an infinite loop. It draws the Jungle Park's window display
   */
  @Override
  public void draw() {
    // Set the color used for the background of the Processing window
    this.background(245, 255, 250); // Set the mint cream color background
    this.image(backgroundImage, this.width / 2, this.height / 2); // draw the background image at
    // the center of the display window
    // traverse the tigers array and draw each stored tiger
    for (int i = 0; i < listGUI.size(); i++)
      listGUI.get(i).draw();
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  @Override
  public void mousePressed() {
    // traverse listGUI and call mousePressed() of the first graphical object which the mouse is
    // over
    for (int i = 0; i < listGUI.size(); i++)
      if (listGUI.get(i).isMouseOver()) {
        listGUI.get(i).mousePressed();
        break;
      }
  }

  /**
   * Callback method called each time the mouse is released
   */
  @Override
  public void mouseReleased() {
    // traverse listGUI and call mouseReleased() method defined for every graphic object
    for (int i = 0; i < listGUI.size(); i++)
      listGUI.get(i).mouseReleased();
  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override
  public void keyPressed() {

    switch (Character.toUpperCase(this.key)) {
      case 'T': // add new tiger to the Jungle Park
        listGUI.add(new Tiger(this));
        break;
      case 'D': // add new Deer to the Jungle Park
        listGUI.add(new Deer(this));
        break;
      case 'R': // remove an animal from the Jungle Park if the mouse is over it
        // traverse the listGUI list and consider only animal objects to be removed if any
        for (int i = 0; i < listGUI.size(); i++) {
          if (listGUI.get(i) instanceof Animal && listGUI.get(i).isMouseOver()) {
            listGUI.remove(i);
            break; // remove the first animal which the mouse is over it while the r-key is pressed
          }
        }


    }

  }

  /**
   * Removes all the animals from this park
   */
  public void clear() {
    for (int i = 0; i < listGUI.size(); i++) { // loop over each item in the Arraylist to clean it.
      if (listGUI.get(i) instanceof Animal) {
        listGUI.remove(i);
        i--;
      }
    }
  }



  /**
   * This main method starts the application
   * 
   * @param args
   */
  public static void main(String[] args) {
    // starts the application (calls PApplet main() method with the name
    // of the PApplet class to run as parameter)
    PApplet.main("JunglePark");
  }
}

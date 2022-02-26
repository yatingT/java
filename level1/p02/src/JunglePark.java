//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P02 Jungle Park
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
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * @author Yating Tian This class creates a jungle park that users can press T key to add tigers in
 *         random places, use mouse to dragging each tiger to different places and press R key
 *         delete the tiger.
 */
public class JunglePark {
  private static PApplet processing; // PApplet object that represents the graphic
  // interface of the JunglePark application
  private static PImage backgroundImage; // PImage object that represents the
  // background image
  private static Tiger[] tigers; // array storing the current tigers present
  // in the Jungle Park
  private static Random randGen; // Generator of random numbers

  /**
   * to run all the methods
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.startApplication();
  }

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    processing = processingObj; // initialize the processing field to the one passed
                                // into the input argument parameter.s
    // initialize and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");
    randGen = new Random(); // initialize the random object that use for give each tiger's position
    tigers = new Tiger[6]; // initialize the tiger array which contains all the tiger object.
  }

  /**
   * Continuously draw the application display window and updates its content with respect to any
   * change or any event that affects its appearance.
   */
  public static void update() {
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color
    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);
    // width [resp. height]: System variable of the processing library that stores the width
    // [resp. height] of the display window
    for (int i = 0; i < 6; i++) { // check each index of tigers array
      if (tigers[i] != null) { // make sure the index contains a tiger object in it.
        tigers[i].draw(); // draw the image inside the tiger obejct.
      }
    }
  }

  /**
   * return whether the mouse is over the image of the Tiger object passed to it as input parameter
   * 
   * @param tiger ; the tiger object in the tigers array.
   * @return return true if the mouse is over the image of the Tiger object passed to it as input
   *         parameter, and false otherwise.
   */
  public static boolean isMouseOver(Tiger tiger) {
    float centerX = tiger.getPositionX(); // get the tiger's X position
    float centerY = tiger.getPositionY(); // get the tiger's Y position
    float Width = tiger.getImage().width / 2; // get the image's half width
    float Height = tiger.getImage().height / 2; // get the image's half height
    float maxWidth = centerX + Width; // the center adds the half width to get the edge of mouse
    float minWidth = centerX - Width; // the center minus the half width to get the edge of mouse
    float maxHeight = centerY + Height; // the center adds the half height to get the edge of mouse
    float minHeight = centerY - Height; // the center minus the half height to get the edge of mouse
    // check if the mouse inside the edge both vertical and horizontal
    if (processing.mouseX < maxWidth && processing.mouseX > minWidth) {
      if (processing.mouseY < maxHeight && processing.mouseY > minHeight) {
        return true; // the mouse is around the tiger, then we can choose it and move it
      }
      return false; // the mouse is only inside the horizontal range, not the vertical
    } else {
      return false; // the mouse is not inside any range.
    }
  }

  /*
   * This method should check if the mouse is over one of the tiger objects stored in the tigers
   * array and start dragging it.
   */
  public static void mouseDown() {
    // check each index of array so make sure choose one with the lowest index
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) { // check the index of tiger array has a tiger object.
        if (isMouseOver(tigers[i])) { // check if the mouse is close to the tiger image enough.
          tigers[i].setDragging(true); // the we can use our mouse to dragging to the place we want
        }
      }
    }
  }

  /*
   * method for no tiger is being dragged when the mouse is released
   */
  public static void mouseUp() {
    // check each index of array so make sure choose one with the lowest index
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) { // check the tiger object does exist in the index.
        tigers[i].setDragging(false); // set stop dragging after mouse up.
      }
    }
  }

  /*
   * allow the user to add new tigers (up to six tigers) into the JunglePark by pressing the T-key.
   * allow the user to remove tigers from JunglePark by pressing the R-key.
   */
  public static void keyPressed() {
    if (processing.key == 't' || processing.key == 'T') { // check if user dose pressed T key
      for (int i = 0; i < 6; i++) { // loop over each index to add new tiger at the lowest index
        if (tigers[i] == null) { // to check if the index is empty for contains tiger object.
          // generates a random x-position of type float within the width of the display window
          float width = (float) randGen.nextInt(processing.width);
          // generates a random Y-position of type float within the height of the display window
          float height = (float) randGen.nextInt(processing.height);
          tigers[i] = new Tiger(processing, width, height); // create the tiger object with random
                                                            // width and height.
          break; // break after each new tiger drew.
        }
      }
    }
    if (processing.key == 'r' || processing.key == 'R') {// check if user dose pressed R key
      for (int i = 0; i < 6; i++) { // remove the lowest index tiger when overlap.
        if (tigers[i] != null) { // check if the index of tigers array contains an tiger object
          if (isMouseOver(tigers[i])) { // check if the mouse is on the tiger object( overlapped)
            tigers[i] = null; // remove the tiger to null
            break; // break after each tiger has been removed.
          }
        }
      }
    }
  }


}

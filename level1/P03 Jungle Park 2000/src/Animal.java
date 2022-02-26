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
import java.util.Random;
import processing.core.PImage;

/**
 * This class represents an animal in the Jungle Park application It implements the interface
 * ParkGUI
 */
public class Animal implements ParkGUI{

  private static Random randGen = new Random(); // Generator of random numbers
  protected String label; // represents the animal's identifier
  // Fields defined to draw the animal in the application display window
  protected JunglePark processing; // PApplet object that represents the display window
  protected PImage image; // animal's image

  private float[] position; // animal's position in the display window
                            // Usage: position[0: x-coordinate, or 1: y-coordinate]
  private boolean isDragging; // indicates whether the animal is being dragged or not


  /**
   * Creates a new Animal object positioned at a given position of the display window
   * 
   * @param processing    PApplet object that represents the display window
   * @param positionX     x-coordinate of the animal's image in the display window
   * @param positionY     y-coordinate of the animal's image in the display window
   * @param imageFileName filename of the animal image
   */
  public Animal(JunglePark processing, float positionX, float positionY, String imageFileName) {

    // Set Animal drawing parameters
    this.processing = processing; // set the PApplet Object where the animal will be drawn
    this.position = new float[] {positionX, positionY}; // sets the position of the animal object
    this.image = processing.loadImage(imageFileName);
    isDragging = false; // initially the animal is not dragging
  }

  /**
   * Creates a new Animal object positioned at a random position of the display window
   * 
   * @param processing    PApplet object that represents the display window
   * @param imageFileName filename of the animal image
   */
  public Animal(JunglePark processing, String imageFileName) {
    this(processing, (float) randGen.nextInt(processing.width),
        Math.max((float) randGen.nextInt(processing.height), 100), imageFileName);
  }

  /**
   * Draws the animal to the display window. It sets also its position to the mouse position if this
   * animal is being dragged (i.e. if its isDragging field is set to true).
   */
  @Override
  public void draw() {
    // if this animal is dragging, set position to the mouse position with respect to the display
    // window (processing) dimension
    if (this.isDragging) {
      if (this.processing.mouseX < 0) // mouse outside the screen
        this.position[0] = 0;
      else if (this.processing.mouseX > this.processing.width) // mouse outside the screen
        this.position[0] = this.processing.width;
      else
        this.position[0] = this.processing.mouseX;

      if (this.processing.mouseY < 0) // mouse outside the screen
        this.position[1] = 0;
      else if (this.processing.mouseY > this.processing.height) // mouse outside the screen
        this.position[1] = this.processing.height;
      else
        this.position[1] = this.processing.mouseY;
    }

    // draw this animal at its current position
    this.processing.image(this.image, this.position[0], position[1]);
    // display label
    displayLabel();
    // Continuous behavior of the current animal in the Jungle park
    action();
  }


  /**
   * display's this animal's object label on the application window screen
   */
  private void displayLabel() {
    this.processing.fill(0); // specify font color: black
    // display label
    this.processing.text(label, this.position[0], this.position[1] + this.image.height / 2 + 4); // text
  }

  /**
   * Checks if the mouse is over this animal
   * 
   * @return true if the mouse is over this animal, false otherwise
   */
  @Override
  public boolean isMouseOver() {
    int tigerWidth = image.width; // image width
    int tigerHeight = image.height; // image height

    // checks if the mouse is over this animal
    if (processing.mouseX > position[0] - tigerWidth / 2
        && processing.mouseX < position[0] + tigerWidth / 2
        && processing.mouseY > position[1] - tigerHeight / 2
        && processing.mouseY < position[1] + tigerHeight / 2) {
      return true;
    }
    return false;
  }

  @Override
  public void mousePressed() {
    if (isMouseOver())
      isDragging = true;
  }

  @Override
  public void mouseReleased() {
    isDragging = false;
  }

  /**
   * @return the label that represents the tiger's identifier
   */
  public String getLabel() {
    return label;
  }


  /**
   * @return the image of type PImage of the tiger object
   */
  public PImage getImage() {
    return image;
  }


  /**
   * @return the X coordinate of the animal position
   */
  public float getPositionX() {
    return position[0];
  }

  /**
   * @return the Y coordinate of the animal position
   */
  public float getPositionY() {
    return position[1];
  }


  /**
   * @param position the XPosition to set
   */
  public void setPositionX(float position) {
    this.position[0] = position;
  }

  /**
   * @param position the YPosition to set
   */
  public void setPositionY(float position) {
    this.position[1] = position;
  }

  /**
   * @return true if the animal is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Computes the euclidean distance between the current animal and another one
   * 
   * @param otherAnimal reference to another animal
   * @return distance between the current animal and otherAnimal
   */
  public double distance(Animal otherAnimal) {
    return Math.sqrt(Math.pow(this.getPositionX() - otherAnimal.getPositionX(), 2)
        + Math.pow(this.getPositionY() - otherAnimal.getPositionY(), 2));
  }


  /**
   * Continuous behavior done by the current animal in the jungle park
   */
  public void action() {
    // This method should be overridden by a subclasse
  }


  /**
   * Check whether there is another animal that is close to the animal.
   * 
   * @param otherAnimal
   * @param range
   * @return returns TRUE if otherAnimal is located within a range distance [0 .. range] around the
   *         current animal and FALSE otherwise.
   */
  public boolean isClose(Animal otherAnimal, int range) {
    // check the distance of X position
    if (Math.abs(this.getPositionX() - otherAnimal.getPositionX()) <= range) {
      // check the distance of Y position
      if (Math.abs(this.getPositionY() - otherAnimal.getPositionY()) <= range) {
        return true; // if close for both x and y position, determine it is close
      }
      return false;
    } else {
      return false;
    }
  }


}

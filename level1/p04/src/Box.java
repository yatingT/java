//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: p04 Storage Unit Organizer
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


/**
 * @author Yatin This class can create the box object that have different color and weight
 */
public class Box implements Comparable<Box> {

  private static Random randGen = new Random(); // generator of random numbers
  private int color; // color of this box
  private int weight; // weight of this box in lbs between 1 inclusive and 31 exclusive

  // Creates a new Box and initializes its instance fields color and weight to
  // random values
  public Box() {
    this.color = randGen.nextInt(); // get the random value for color
    this.weight = randGen.nextInt(31); // get the random value of weight within the range 0-31
  }

  // Creates a new Box and initializes its instance fields color and weight to the
  // specified values
  // Throws IllegalArgumentException if the provided weight value is out of the
  // range of [1..30]
  public Box(int color, int weight) throws IllegalArgumentException {
    this.color = color; // set the color from the parameter
    if (weight > 0 && weight < 31) { // make sure the input weight is inside the range
      this.weight = weight; // if input weight is fine, set the weight to the input weight
    } else {
      throw new IllegalArgumentException(); // if the input weight is not in the range throw error
    }
  }

  public int getColor() {
    return this.color;
  } // Getter for the instance field color of this box

  public int getWeight() {
    return this.weight;
  } // Getter for the instance field weight of this box

  /**
   * return the difference between the box and another that compare with
   */
  @Override
  public int compareTo(Box otherbox) {
    return this.weight - otherbox.weight; // to get the difference and return it.
  }

  /**
   * return boolean that the box is equal to another box for both color and weight
   */
  public boolean equals(Object other) {
    if (other instanceof Box) { // to check the object is box or not
      Box otherbox = (Box) other; // if so, change the type of object in order to compare.
      if (this.color == otherbox.color && this.weight == otherbox.weight) { // compare the color and
                                                                            // weight
        return true; // if they are same for both color and weight, return true
      } else {
        return false;
      }
    } else { // if the other object is not a box, we cannot compare them, and they are not equal
      return false;
    }
  }



}

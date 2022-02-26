//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Ascii Art

// Course: cs300
//
// Author: Yating Tians
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

/**
 * @author Yatin This class records the details of a single change made to canvas.
 */
public class DrawingChange {

  public final int row; // row (y-coordinate) for this DrawingChange
  public final int col; // col (x-coordinate) for this DrawingChange
  public final char prevChar; // previous character in the (row,col) position
  public final char newChar; // new character in the (row,col) position

  /**
   * @param row      (y-coordinate) for this DrawingChange
   * @param col      (x-coordinate) for this DrawingChange
   * @param prevChar previous character in the (row,col) position
   * @param newChar  new character in the (row,col) position
   */
  public DrawingChange(int row, int col, char prevChar, char newChar) {
    this.row = row;
    this.col = col;
    this.prevChar = prevChar;
    this.newChar = newChar;
  }


}

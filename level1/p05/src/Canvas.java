//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Ascii Art

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


/**
 * @author Yatin This class represents an ASCII-art image. We can set the height,width and draw the
 *         characters on each position.
 */
public class Canvas {

  private final int width; // width of the canvas
  private final int height; // height of the canvas
  private char[][] drawingArray; // 2D character array to store the drawing
  private final DrawingStack undoStack; // store previous changes for undo
  private final DrawingStack redoStack; // store undone changes for redo

  // Constructor creates a new canvas which is initially blank (use the default value
  // for char type or you can use spaces)
  // Throws an IllegalArgumentException with a descriptive error message
  // if width or height is 0 or negative.

  /**
   * @param width  - get the expected number of column
   * @param height - get the expected number of row
   */
  public Canvas(int width, int height) {
    if (width <= 0 || height <= 0) { // check if the input size is valid or not
      throw new IllegalArgumentException("Invalid width or height");
    }
    this.width = width;
    this.height = height;
    this.drawingArray = new char[height][width]; // set the expect 2D character array to store char
    this.undoStack = new DrawingStack(); // stack for undo
    this.redoStack = new DrawingStack(); // stack for redo

  }


  /**
   * @return the width
   */
  public int getWidth() {
    return width;
  }



  /**
   * @return the height
   */
  public int getHeight() {
    return height;
  }



  /**
   * @return the drawingArray
   */
  public char[][] getDrawingArray() {
    return drawingArray;
  }



  /**
   * @return the undoStack
   */
  public DrawingStack getUndoStack() {
    return undoStack;
  }



  /**
   * @return the redoStack
   */
  public DrawingStack getRedoStack() {
    return redoStack;
  }



  // Draw a character at the given position drawingArray[row][col]
  // This method should throw an IllegalArgumentException if the drawing
  // position is outside the canvas
  // Iterator<DrawingChange> current = undoStack.iterator();
  // If that position is already marked with a different character, overwrite it.
  // After making a new change, add a matching DrawingChange to the undoStack
  // so that we can undo if needed.
  // After making a new change, the redoStack should be empty (meaning that
  // you should clear the redoStack if it is not already empty).
  /**
   * @param row - set the horizontal position of drawing
   * @param col - set the vertical position of drawing
   * @param c   - get the character that will draw
   */
  public void draw(int row, int col, char c) {
    if (row >= height || col >= width) { // check if the position within the range of canvas
      throw new IllegalArgumentException("invalid position"); // if not, throw the error
    }
    char pre = drawingArray[row][col]; // store the old char(either default char or other char)
    drawingArray[row][col] = c; // overwrite the old with the new char
    DrawingChange dc = new DrawingChange(row, col, pre, c); // create new change object
    undoStack.push(dc); // push the change object into the undo stack so we can access it later
    while (!redoStack.isEmpty()) { // clean up the redo stack if it is not empty.
      redoStack.pop();
    }
  }

  // Undo the most recent drawing change.
  // Return true if successful. False otherwise.
  // An undone DrawingChange should be popped off the undoStack.
  // An undone DrawingChange should be added to the redoStack so that
  // we can redo if needed.
  // The content of the drawingArray should be updated accordingly to this change.

  /**
   * @return Return true if the undo process is successful. False otherwise.
   */
  public boolean undo() {
    if (undoStack.isEmpty()) { // check the stack is empty or not
      return false; // if it's empty, we cannot go through this process.
    }
    DrawingChange current = undoStack.pop(); // remove this change from undo stack
    redoStack.push(current); // add this change to the redo stack in case we still want to do it
    drawingArray[current.row][current.col] = current.prevChar; // change the position to old char
    return true; // if all three step did correct, return true.
  }

  // Redo the most recent undone drawing change.
  // Return true if successful. False otherwise.
  // A redone DrawingChange should be popped off the redoStack.
  // A redone DrawingChange should be added (back) to the undoStack so that
  // we can undo again if needed.
  // The content of the drawingArray should be updated accordingly to this change.

  /**
   * @return Return true if the redo process is successful. False otherwise.
   */
  public boolean redo() {
    if (undoStack.isEmpty()) {// check the stack is empty or not
      return false; // if it's empty, we cannot go through this process.
    }
    DrawingChange current = redoStack.pop(); // remove this change from redo stack
    undoStack.push(current); // add this change to the undo stack in case we still want to do it
    drawingArray[current.row][current.col] = current.newChar;// update the position to new char
    return true;// if all three step did correct, return true.
  }


  /*
   * Format example: [_ is blank. Use System.lineSeparator() to put a newline character between
   * rows] X___X _X_X_ __X__ _X_X_ X___X
   */
  /**
   * @return a printable string version of the Canvas.
   */
  public String toString() {
    StringBuilder result = new StringBuilder(); // creates a StringBuilder object
    String newLine = System.getProperty("line.separator"); // create the line object
    for (int row = 0; row < height; row++) { // loop over each row
      for (int col = 0; col < width; col++) { // loop over each position on the row.
        result.append(drawingArray[row][col]); // add each single value in the result string.
      }
      result.append(newLine); // after each row, we go to a new line and then add each position
    }
    return result.toString(); // return the string of the result
  }

  /**
   * call the toString method in order to print out current drawing
   */
  public void printDrawing() {
    System.out.println(this.toString());
  }


}

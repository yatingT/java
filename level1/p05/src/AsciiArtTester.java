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
 * @author Yatin this class it to test out the canvas class, DrawingStack class, and
 *         DrawingStackIterator class.
 */
public class AsciiArtTester {

  /**
   * checks for the correctness of DrawingStack.push() and DrawingStack.peek() methods
   * 
   * @return true if the test pass, false if the test did not pass
   */
  public static boolean testStackPushPeek() {
    // create many Drawing change object and a new stack
    DrawingChange dc = new DrawingChange(1, 1, 'a', 'b');
    DrawingChange sec = new DrawingChange(1, 1, 'c', 'd');
    DrawingChange thi = new DrawingChange(1, 1, 'e', 'f');
    DrawingStack stack = new DrawingStack();
    stack.push(dc); // push a DrawingChange into the stack
    if (stack.peek() != dc) { // check if the top of stack is the one that I just pushed.
      return false;
    }
    stack.push(sec); // same process, check if the head of the stack get updated
    if (stack.peek() != sec) { // check if the top of stack is the one that I just pushed.
      return false;
    }
    stack.push(thi);
    if (stack.size() != 3) { // check whether the size was kept tack or not
      // System.out.println(stack.size());
      System.out.println("size has problem");
      return false;
    }
    if (stack.peek() != thi) { // check if the top of stack is the one that I just pushed.
      System.out.println("peek the top has problem");
      return false;
    }
    return true;
  }
  
  
  /**
   * @return checks for the correctness of Canvas.Draw(), return ture if it is right. 
   */
  public static boolean testCanvasDraw(){
     Canvas c= new Canvas(5,5); //create new canvas 
     c.draw(2, 3, 'a'); //draw some char in it 
     c.draw(4, 4, 'b');
     if (c.getDrawingArray()[2][3]!='a') { //check the position does have the expected letter 
       return false;
     }
     if (c.getDrawingArray()[4][4]!='b') { //check the position does have the expected letter 
       return false;
     }
     c.draw(4, 4, 'c');
     if (c.getDrawingArray()[4][4]!='c') { //check if draw method overwrites
       return false;
     }
     // Clean up the stack
     if (c.getRedoStack().equals(null)) { //Check if the redoStack get clean up. 
       System.out.println("redo test3 false");
       return false;
     }
     return true;
  } 

  /**
   * test pop method in DrawingChange class
   * checks for the correctness of DrawingStack.pop(). 
   * It calls DrawingStack.push() and DrawingStack.peek() methods
   * @return true if the test pass, false if the test did not pass
   */
  public static boolean testStackPop() {
    // create many Drawing change object and a new stack
    DrawingChange dc = new DrawingChange(1, 1, 'a', 'b');
    DrawingChange sec = new DrawingChange(1, 1, 'c', 'd');
    DrawingChange thi = new DrawingChange(1, 1, 'e', 'f');
    DrawingStack stack = new DrawingStack();
    // push DrawingChange objects into the stack
    stack.push(dc);
    stack.push(sec);
    stack.push(thi);
    if (stack.pop() != thi) { // check if pop removed the head of the stack
      System.out.println("pop test 1 false");
      return false;
    }
    if (stack.peek() != sec) { // check if updated the head after a pop
      System.out.println("pop test 2 false");
      return false;
    }
    if (stack.size() != 2) {// check if updated the size after a pop
      System.out.println("pop test 3 false");
      return false;
    }
    return true; // if 3 situation passed, return true.
  }


  /**
   *checks for the correctness of DrawingStackIterator.hasNext()&DrawingStackIterator.next() methods
   * @return true if the test pass, false if the test did not pass
   */
  public static boolean testDrawingStackIterator() {
    // create many Drawing change object and a new stack
    DrawingChange dc = new DrawingChange(1, 1, 'a', 'b');
    DrawingChange sec = new DrawingChange(1, 1, 'c', 'd');
    DrawingChange thi = new DrawingChange(1, 1, 'e', 'f');
    DrawingStack stack = new DrawingStack();
    // push DrawingChange objects into the stack
    stack.push(dc);
    stack.push(sec);
    stack.push(thi);
    if (!stack.iterator().hasNext()) { // check if a node has next
      System.out.println("iterator test1 false");
      return false;
    }
    if (stack.iterator().next()!=thi) { // check the next node should return expected data
      System.out.println("iterator test2 false");
      return false;
    }
    DrawingStack em = new DrawingStack();
    if (em.iterator().hasNext()) { // check if an empty list has next.
      System.out.println("iterator test3 false");
      return false;
    }
    return true;
  }
  
  /**
   *  checks for the correctness of the Canvas.undo() method (relying on the Canvas.toString()'s 
   *  return value after calling draw() and undo() methods.)
   * @return  true if the test pass, false if the test did not pass
   */ 
  public static boolean testCanvasUndo(){
    //create new canvas and draw some char
    Canvas c= new Canvas(5,5);
    c.draw(2, 3, 'a');
    c.draw(4, 4, 'b');
    c.draw(4, 4, 'c');
    DrawingChange test1=new DrawingChange(4,4,'b','c');
    if (c.getUndoStack().peek().equals(test1)) { // check the most recent change is at top of stack
      return false;
    }
    c.undo();
    if (c.getDrawingArray()[4][4]!='b') { // check if undo works as expect
      return false;
    }
    c.undo(); //check of undo continuously work 
    if (c.getDrawingArray()[4][4]=='c'||c.getDrawingArray()[4][4]=='b') { 
      return false;
    }
    DrawingChange test2=new DrawingChange(2,3,'\u0000','a');
    if (c.getUndoStack().peek().equals(test2)) { //check if the stack get updated. 
      return false;
    }
    return true;
  }
  /**
   * // checks for the correctness of the Canvas.redo() method (relying on the Canvas.toString()'s 
   * return value after calling draw(), undo(), and then redo() methods.)
   * @return  true if the test pass, false if the test did not pass
   */
  public static boolean testCanvasRedo(){
    //create new canvas and draw some char. 
    Canvas c= new Canvas(5,5);
    c.draw(2, 3, 'a');
    c.draw(4, 4, 'b');
    c.draw(4, 4, 'c');
    c.undo(); //call functions 
    c.redo();
    //Case 1: overwrite situation
    if (c.getDrawingArray()[4][4]!='c') { // test the redo method work as we expect. 
      System.out.println("redo test1 false");
      return false;
    }
    //Case 2: new char situation
    c.draw(0, 0, 'e');
    c.undo();
    c.redo();
    if (c.getDrawingArray()[0][0]!='e') {// test the redo method work as we expect. 
      System.out.println("redo test2 false");
      return false;
    }
    return true;
  } 


  /**
   * a test collection for method
   * 
   * @return true if the test pass, false if the test did not pass
   */
  public static boolean runAsciiArtTestSuite() {
    if (!testStackPop()) { // check the test for pop method
      System.out.println("pop tests false");
      return false;
    }
    if (!testDrawingStackIterator()) { // check the test for iterator method
      System.out.println("Iterator tests false");
      return false;
    }
    if (!testCanvasDraw()) { // check the test for draw method
      System.out.println("testCanvasDraw tests false");
      return false;
    }
    if (!testCanvasUndo()) { // check the test for undo method
      System.out.println("testCanvasUndo tests false");
      return false;
    }
    if (!testCanvasRedo()) { // check the test for redo method
      System.out.println("testCanvasRedo tests false");
      return false;
    }
    return true;

  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testStackPushPeek()" + testStackPushPeek());
    System.out.println("runAsciiArtTestSuite()" + runAsciiArtTestSuite());

  }

}

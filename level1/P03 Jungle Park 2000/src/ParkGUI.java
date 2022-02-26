// DO NOT submit this file on gradescope
/**
 * This interface represents the abstract data type ParkGUI which models any visible and clickable 
 * graphic type in a PApplet graphic application (JunglePark in this project)
 * which can be drawn
 * 
 * @author Mouna
 *
 */
public interface ParkGUI {
  /**
   * draws a ParkGUI object (for instance an animal or a button) to the display window
   */
  public void draw();

  /**
   * called each time the mouse is Pressed
   */
  public void mousePressed();

  /**
   * called each time the mouse is Pressed
   */
  public void mouseReleased(); 

  /**
   * checks whether the mouse is over a ParkGUI object
   * @return true if the mouse is over this object, false otherwise
   */
  public boolean isMouseOver();

}

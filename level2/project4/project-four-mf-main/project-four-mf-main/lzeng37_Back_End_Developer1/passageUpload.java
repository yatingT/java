import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**passageUpload opens the file containing the text passage.  It then returns
 * a newly constructed Passage object that has had every word added to its red
 * black tree.
 * 
 * @author ryanbrumm
 *
 */
public class passageUpload {
  
  /**Uploads file into a Passage object and returns the passage.
   * 
   * @param filepath name of the file in the folder with the desired passage
   * @return
   */
  public Passage upload() {
    try {
      String filepath = "./src/WordTest.txt";
      File file = new File(filepath);
      Scanner scanner = new Scanner(file);
      Passage passage = new Passage(file.toString());
      
      while (scanner.hasNext() == true) {
          String s = scanner.next().toLowerCase();
          passage.add(s);
          //print statement for testing purposes
          System.out.println("added: " + s);
      }
      scanner.close();
      return passage;
      
  } catch (FileNotFoundException e) {
      System.out.println("File not found.");
      return null;
}
    
  }
  public static String getFilePath() {
    System.out.println("Please enter file path for your passage: ");
    Scanner scanner = new Scanner(System.in);
    return scanner.next();
  }
  
}
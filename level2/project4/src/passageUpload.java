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
  public Passage upload(String path) {
    try {
      String filepath = path;
      File file = new File(filepath);
      Scanner scanner = new Scanner(file);
      String s = scanner.next();
		while (scanner.hasNext()) {
			s += " " + scanner.next();
		}
		Passage passage = new Passage(s); 
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

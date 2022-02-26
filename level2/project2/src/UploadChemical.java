import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UploadChemical {
  ArrayList<String[]> getChemical;

  /**
   * getChemicals loads the data from csv file into an ArrayList<String> that is then put into the
   * RBT inside the main method.
   * 
   * @param filepath a string that is the filepath to "ChemStockRoom.csv" file
   * @return ArrayList<String[]> where each String[] has chemical Name at index 0 and chemical
   *         amount at index 1
   */
  public ArrayList<String[]> getChemicals(String filepath) {
    BufferedReader csvReader = null;
    ArrayList<String[]> chemNames = new ArrayList<String[]>();

    try {
      csvReader = new BufferedReader(new FileReader(filepath));
    } catch (FileNotFoundException e1) {
      System.out.println("The file containing the chemicalStockRoom was not found" + " goodbye");
      return null;
    }

    String row = null;
    try {
      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        // put each row from from csv into ArrayList
        // each element of the ArrayList will be formatted as an String[]
        // that looks like [ chemical name, chemical amount ];
        chemNames.add(data);
      }

      csvReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // remove the headers from the CSV before returning
    chemNames.remove(0);
    getChemical = chemNames;
    return chemNames;
  }
}

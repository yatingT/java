import java.util.ArrayList;

// --== CS400 File Header Information ==--
// Name: Hechao Wang
// Email: hwang855@wisc.edu
// Team: MF
// Role: Back End Developer
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>


public class ChemicalInterface {
  // need value type ChemicalStore from data wrangler
  ChemicalTree<Chemical> chemicalTree = new ChemicalTree<Chemical>();

  public ChemicalInterface loadChemicalTree(String fileName) {
    UploadChemical getChemicals = new UploadChemical();

    ArrayList<String[]> chemicalsToBeAdded = getChemicals.getChemicals(fileName);

    // if its null the filepath was incorrect
    if (chemicalsToBeAdded == null) {
      return null;
    }

    ChemicalInterface chemTree = new ChemicalInterface();

    for (String[] chemicals : chemicalsToBeAdded) {
      // Insert the chemical as Name, Quantity
      int quantity = Integer.parseInt(chemicals[1].strip());

      if (quantity > 0)
        chemTree.addChemical(new Chemical(chemicals[0], quantity));
    }
    return chemTree;
  }

  public void addChemical(Chemical newChemical) {
    chemicalTree.insert(newChemical);
  }

  public boolean containsChemical(Chemical chemical) {

    return chemicalTree.contains(chemical);
  }

  public Chemical getChemical(Chemical chemical) {

    if (chemicalTree.contains(chemical)) {
      RedBlackTree.Node<Chemical> current = chemicalTree.getNode(chemical);

      return current.data;

    } else {
      System.out.print("No such chemical in chemical tree");
    }

    return null;
  }

  public int getChemicalQuant(Chemical chemical) {

    if (chemicalTree.contains(chemical)) {
      return getChemical(chemical).chemicalQuant();
    } else {
      System.out.print("No such chemical in chemical tree");
    }
    return 0;
  }

  public int addQuant(Chemical chemical, Chemical addChemical) {

    int Quant = chemical.chemicalQuant();

    if (chemicalTree.contains(chemical)
        && chemical.chemicalName().equals(addChemical.chemicalName())) {

      Quant = chemical.chemicalQuant() + addChemical.chemicalQuant();

      chemical.setChemicalQuant(Quant);
    }

    return Quant;
  }

  public int reduceQuant(Chemical chemical, Chemical deleteChemical) {

    int Quant = chemical.chemicalQuant();

    if (chemicalTree.contains(chemical)
        && chemical.chemicalName().equals(deleteChemical.chemicalName())) {

      if (chemical.chemicalQuant() > deleteChemical.chemicalQuant()) {
        Quant = chemical.chemicalQuant() - deleteChemical.chemicalQuant();

        chemical.setChemicalQuant(Quant);
      }
    }

    return Quant;

  }


  public String printTree() {
    return chemicalTree.toString();
  }


}

// --== CS400 File Header Information ==--
// Name: Yating Tian
// Email: ytian83@wisc.edu
// Team: MF
// Role: Test Engineer
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: NA
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tester methods for the chemical stock application. Contains different tests for the constructor of the Chemical 
 * tree class and all the methods in the ChemicalInterface class. 
 * @author Yatin
 *
 */
class ChemicalTest {

	/**
	 * This test is design for test the constructor and compare/ equal method of the ChemicalTree class
	 * and ChemicalInterface class. 
	 */
	@Test
	void testDataCompare() { //create some ChemicalStore objects 
		Chemical c1 = new Chemical("Acetic Acid", 1500);
		Chemical c2 = new Chemical("Carbon Dioxide", 5000);
		Chemical c3 = new Chemical("Acetic Acid", 1500);
		if (!c1.chemicalName().equals("Acetic Acid") || !(c1.chemicalQuant() == 1500)) { //check the name and quantity
			fail(" the constructor of c1 have something wrong.");
		}
		if (!c2.chemicalName().equals("Carbon Dioxide") || !(c2.chemicalQuant() == 5000)) {
			fail(" the constructor of c2 have something wrong.");
		}
		if (!(c1.compareTo(c2) < 0)) { // "Acetic Acid" should smaller than the "Carbon Dioxide"
			fail(" the compare of two different ChemicalStore went wrong, expect c1 < c2");
		}
		if (!(c1.compareTo(c3) == 0)) { // two same string should be equal 
			fail(" the compare of two same ChemicalStore went wrong, expect c1 =c3");
		}
	}

	/**
	 * Test for the addChemical method, use printTree() to test the form of the tree 
	 *  use containsChemical() to test if each chemical was successfully added, and 
	 *  use isBlack to test the color of node. 
	 */
	@Test
	void testAddChemical() { //create a new tree and some ChemicalStore objects 
		ChemicalInterface tree = new ChemicalInterface();
		Chemical c1 = new Chemical("Acetic Acid", 1500);
		Chemical c2 = new Chemical("Carbon Dioxide", 5000);
		Chemical c3 = new Chemical("Calcium Hydroxide", 0);
		Chemical c4 = new Chemical("random chemical", 20);
		Chemical c5 = new Chemical("Acetic Acid", 30);
		tree.addChemical(c1); // insert some note in the tree
		tree.addChemical(c2);
		tree.addChemical(c3);
		if (!tree.printTree().equals("[Calcium Hydroxide: 0 grams, Acetic Acid: 1500 grams, Carbon Dioxide: 5000 grams]")) { // check the form
																										// first
			fail("to string test failed");
		}
		if (!tree.containsChemical(c1)) { // check if the ChemicalStore object was successfully added
			fail("not contains added Acetic Acid ChemicalStore");
		}
		if (!tree.containsChemical(c2)) { 
			fail("not contains added Carbon Dioxide ChemicalStore");
		}
		if (!tree.containsChemical(c3)) {
			fail("not contains added Calcium Hydroxide ChemicalStore");
		}
		if (tree.containsChemical(c4)) { //Check a never added chemical contains in the method
			fail("contains never added chemical ChemicalStore");
		}
		try {
			tree.addChemical(c5); //add a same node, should throw a error 
			fail("can add same node into the tree");
		} catch (IllegalArgumentException e) {
		}

		if (!tree.chemicalTree.root.isBlack) { // check the root color and then color of other child
			fail("root color is incorrect");
		}
		if (tree.chemicalTree.root.leftChild.isBlack) {
			fail("root.leftChild color is incorrect");
		}
		if (tree.chemicalTree.root.rightChild.isBlack) {
			fail("root.rightChild color is incorrect");
		}

	}

	/**
	 * Test the getChemical() and getChemicalQuant() method to check if it is return the expect ChemicalStore
	 * and expect quantity 
	 */
	@Test
	void testGetQuant() {//create a new tree and some ChemicalStore objects 
		ChemicalInterface tree = new ChemicalInterface();
		Chemical c1 = new Chemical("Acetic Acid", 1500);
		Chemical c2 = new Chemical("Carbon Dioxide", 5000);
		Chemical c4 = new Chemical("random chemical", 20);
		tree.addChemical(c1); // insert some note in the tree
		tree.addChemical(c2);
		if (!tree.getChemical(c1).equals(c1)) { //check get method
			fail("get chemical didn't get the correct chemical");
		}
		if (!tree.getChemical(c2).equals(c2)) {
			fail("get chemical didn't get the correct chemical");
		}
		if (tree.getChemical(c4) != null) { // check a never added ChemicalStore objects 
			fail("get chemical get a unexist  chemical");
		}
		if (tree.getChemicalQuant(c1) != 1500) { // check the quantity 
			fail("get chemical qunaity gives a wrong value");
		}
		if (tree.getChemicalQuant(c4) != 0) {// check a quantity of a never added ChemicalStore objects 
			fail("get chemical qunaity get a unexist  chemical");
		}
	}
		
	/**
	 * test the addQuant() and  reduceQuant() work as expected by checking getChemicalQuant()
	 */
	@Test
	void testQuantChange() {
		ChemicalInterface tree = new ChemicalInterface();
		Chemical c1 = new Chemical("Acetic Acid", 1500);
		Chemical c2 = new Chemical("Carbon Dioxide", 5000);
		Chemical c3 = new Chemical("Acetic Acid", 30);
		Chemical c4 = new Chemical("Carbon Dioxide", 5000);
		Chemical c5 = new Chemical("Acetic Acid", 3000);
		tree.addChemical(c1); // insert some note in the tree
		tree.addChemical(c2);
		tree.addQuant(c1, c3); // added 30 grams of 1500 Acetic Acid
		if (tree.getChemicalQuant(c1) != 1530) {
			fail("add chemical qunaity doen't work correctly");
		}
		tree.reduceQuant(c1, c3); // reduced 30 grams of 1530 Acetic Acid 
		if (tree.getChemicalQuant(c1) != 1500) {
			fail("reduce chemical qunaity doen't work correctly");
		}
		tree.reduceQuant(c1, c5); // reduced 3000 grams of 1500 Acetic Acid 
		if (tree.getChemicalQuant(c1) != 1500) {
			fail("reduce huge number of chemical qunaity doen't work correctly");
		}
		tree.addQuant(c2, c4);
		if (tree.getChemicalQuant(c2) != 10000) { // added 5000 grams of 0 Carbon Dioxide
			fail("add chemical qunaity doen't work correctly");
		}
	}

	/**
	 *  test the loadChemicalTree() method (the data upload process) work as expect 
	 */
	@Test
	void testDataLoad() {
		ChemicalInterface tree = new ChemicalInterface();
		Chemical c1 = new Chemical("Acetic Acid", 1500);
		Chemical c2 = new Chemical("Carbon Dioxide", 5000);
		Chemical c4 = new Chemical("random chemical", 20);
		tree = tree.loadChemicalTree("ChemStockRoom.csv"); // call the method 
		if (!tree.containsChemical(c1)) { // Acetic Acid should in the tree 
			fail("After load data method, the Acetic Acid ChemicalStore is not in the tree");
		}
		if (!tree.containsChemical(c2)) {
			fail("the load data method doesn't work correctly");
		}
		if (tree.containsChemical(c4)) { // random chemical should note in the tree
			fail("tree contains a never added ChemicalStore node");
		}
	}

}

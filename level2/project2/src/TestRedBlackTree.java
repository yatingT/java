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
 * this class is the test class for the RedBalckTree.java, focus on test the insert, rotate, and 
 * enforceRBTreePropertiesAfterInsert method to see if they are work as expected 
 * 
 * @author Yating
 */
class TestRedBlackTree {
	
	/**
	 * test the enforceRBTreePropertiesAfterInsert's  gives the expected form under the balanced tree condition
	 * then run the rotation method for two of the nodes by Junit test
	 * The Junit suilt will show test fail when the methods doesn't work as expected. 
	 *
	 */
	@Test
	void testWhenRBTIsBalanced() { // test for a balanced RBT
		RedBlackTree<Integer> bst = new RedBlackTree<Integer>(); //create new tree
		bst.insert(41); // insert many ordered node in order to create a balanced tree
		bst.insert(27);
		bst.insert(69);
		bst.insert(16);
		bst.insert(32);
		bst.insert(57);
		bst.insert(82);
		if (!bst.toString().equals("[41, 27, 69, 16, 32, 57, 82]")) { // check insert method by to string output
			fail(" testWhenRBTIsBalanced to string has something wrongd.");
		}
		RedBlackTree.Node<Integer> r1 = (RedBlackTree.Node<Integer>) bst.root.leftChild; //create the test node
		bst.leftrotate(r1); // run left rotate method
		if (!bst.toString().equals("[41, 32, 69, 27, 57, 82, 16]")) { // check left rotate 
			fail("testWhenRBTIsBalanced left rotation something wrong");
		}
		RedBlackTree.Node<Integer> r2 = (RedBlackTree.Node<Integer>) bst.root; // test the root case 
		bst.rightrotate(r2);
		if (!bst.toString().equals("[32, 27, 41, 16, 69, 57, 82]")) { // check the right rotate method 
			fail("testWhenRBTIsBalanced right rotation something wrong");
		}
	}

	/**
	 * test the enforceRBTreePropertiesAfterInsert's gives the expected form under the unbalanced tree condition. 
	 * then run the rotation method for two of the nodes by Junit test
	 * The Junit suilt will show test fail when the methods doesn't work as expected.
	 */
	@Test
	void testWhenRBTIsUnbalanced() { // test for a unbalanced RBT
		RedBlackTree<Integer> bst = new RedBlackTree<Integer>();
		bst.insert(41);// insert many unordered node in order to create a unbalanced tree
		bst.insert(16);
		bst.insert(27);
		bst.insert(82);
		bst.insert(32);
		bst.insert(57);
		bst.insert(69);
		if (!bst.toString().equals("[27, 16, 41, 32, 69, 57, 82]")) { // check the insert method 
			fail("testWhenRBTIsUnbalanced to string has something wrongd.");
		}
		RedBlackTree.Node<Integer> r1 = (RedBlackTree.Node<Integer>) bst.root.rightChild;
		bst.leftrotate(r1); 
		if (!bst.toString().equals("[27, 16, 69, 41, 82, 32, 57]")) { // check the left rotate method 
			fail("testWhenRBTIsUnbalanced left rotation has something wrong");
		}
		RedBlackTree.Node<Integer> r2 = (RedBlackTree.Node<Integer>) bst.root; // check the root case 
		bst.rightrotate(r2);
		System.out.println(bst);
		if (!bst.toString().equals("[16, 27, 69, 41, 82, 32, 57]")) {// check the left rotate method 
			fail("testWhenRBTIsUnbalanced right rotation has something wrong");
		}
	}

	/**
	 * test the enforceRBTreePropertiesAfterInsert's gives the expected color under good tree condition. 
	 * The Junit suilt will show test fail when the methods doesn't work as expected.
	 */
	@Test
	void testColorOfGoodTree() {
		RedBlackTree<Integer> bst = new RedBlackTree<Integer>(); //create new RBT 
		bst.insert(23); // insert some note in the tree
		bst.insert(7);
		bst.insert(41);
		bst.insert(37); 
		if (!bst.toString().equals("[23, 7, 41, 37]")) { // check the form first 
			fail("to string test failed");
		} // check the color of each node. 
		if (!bst.root.isBlack) {
			fail("root color test failed");
		}
		if (!bst.root.leftChild.isBlack) {
			fail("left subtree color test failed");
		}
		if (!bst.root.rightChild.isBlack) {
			fail("left subtree color test failed");
		}
		if (bst.root.rightChild.leftChild.isBlack) {
			fail("new node color test failed");
		}
	}

	/**
	 * test the enforceRBTreePropertiesAfterInsert's gives the expected color under bad tree condition(broke tree). 
	 * The Junit suilt will show test fail when the methods doesn't work as expected.
	 */
	@Test
	void testtestColorOfBrokeTree() {
		RedBlackTree<Integer> bst = new RedBlackTree<Integer>(); //create a new RBT
		bst.insert(45); //insert some node 
		bst.insert(26);
		bst.insert(72);
		bst.root.rightChild.isBlack=true; // Change a node to different , make the tree broke
		bst.insert(18);
		if (!bst.toString().equals("[26, 18, 45, 72]")) { // test the form of the broke tree 
			fail("to string test failed");
		}
		if (!bst.root.isBlack) { // test the root color of broke tree
			fail("root color test failed");
		}
		if (bst.root.leftChild.isBlack) { // test the color of each node for this broke tree
			fail("left subtree color test failed");
		}
		if (bst.root.rightChild.isBlack) {
			fail("left subtree color test failed");
		}
		if(!bst.root.rightChild.rightChild.isBlack) {
			fail("new node color test failed");
		}
		
	}

}

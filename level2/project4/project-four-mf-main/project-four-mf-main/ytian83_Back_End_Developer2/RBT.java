//--== CS400 File Header Information ==--
//Name: Yating Tian
//Email: ytian34@wisc.edu
//Team:  MF
//Role: Back End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

public class RBT<T extends Comparable<T>> extends RedBlackTree<T>{
	RedBlackTree<T> tree = new RedBlackTree<>();
	
	
	/**
	 * Get node method that recursively search for the target word
	 * @param input, target word
	 * @return the node that contains save value as the target node.
	 */
	  public Node<T> getNode(T target) {
		  if (!contains(target)) {
			  return null;
		  }
		    Node<T> current = root;
		    while (current != null && target != null && current != null) {
		      if (target.compareTo(current.data) < 0) {
		    	  current = current.leftChild;
		      }else if (target.compareTo(current.data) > 0) {
		    	  current = current.rightChild;
		      }else {return current;}
		    }
		    return null;
		  }
	  
	/**
	 * contain method that  recursively search for the target word
	 * @param input, target word
	 * @return the result of finding the target node in the current tree, true if find one, which means 
	 * there is a target word exist in the tree 
	 */
	  public boolean contains(T target) {
		    Node<T> current = root;
		    while (current != null && target != null && current != null) {
		      if (target.compareTo(current.data) < 0) {
		    	  current = current.leftChild;
		      }else if (target.compareTo(current.data) > 0) {
		    	  current = current.rightChild;
		      }else {return true;}
		    }
		    return false;
		  }
	  

	  
}

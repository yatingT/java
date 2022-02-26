// --== CS400 File Header Information ==--
// Name: Linxiu Zeng
// Email: lzeng37@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

public class RBT<T extends Comparable<T>> extends RedBlackTree<T> {
	RedBlackTree<T> tree = new RedBlackTree<>();

	/**
	 * this method aim to find a specific tree node containing provided data
	 * provided as parameter
	 * 
	 * @param item the target node to look for in the tree
	 * @return null if the node does not in the tree, else return the tree node with
	 *         same data
	 */
	public Node<T> getNode(T item) {
		RedBlackTree.Node<T> current = root;

		// recursively go through every level of the tree to find the target node
		// according to its data
		while (current != null) {
			if (current.data.compareTo(item) == 0)
				return current;

			current = current.data.compareTo(item) < 0 ? current.rightChild : current.leftChild;
		}

		return null;
	}

	/**
	 * this method check if the specific node containing provided as parameter
	 * 
	 * @param data- the data of the target node
	 * @return true if the target node is in the tree, false otherwise
	 */
	public boolean contains(T data) {
		Node<T> newNode = root;

		// check if the data or the tree is valid
		if (newNode == null || data == null) {
			return false;
		}

		// recursively go through every level of the tree to find the target node
		// according to its data
		while (newNode != null) {
			int compare = data.compareTo(newNode.data);

			if (compare < 0) {
				newNode = newNode.leftChild;
			} else if (compare > 0) {
				newNode = newNode.rightChild;
			} else {
				return true;
			}
		}

		return false;
	}
}


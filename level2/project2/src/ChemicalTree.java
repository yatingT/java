// --== CS400 File Header Information ==--
// Name: Hechao Wang
// Email: hwang855@wisc.edu
// Team: MF
// Role: Back End Developer
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
public class ChemicalTree<T extends Comparable<T>> extends RedBlackTree<T> {
  
  public Node<T> getNode(T item) {
    RedBlackTree.Node<T> current = root;

    while (current != null) {
      if (current.data.compareTo(item) == 0)
        return current;

      current = current.data.compareTo(item) < 0 ? current.rightChild : current.leftChild;
    }

    return null;
  }

  public void inOrderTraversal(Node<T> current) {
    if (current == null)
      return;

    inOrderTraversal(current.leftChild);

    System.out.println(current.data.toString());

    inOrderTraversal(current.rightChild);
  }

  public boolean contains(T data) {
    Node<T> newNode = root;

    if (newNode == null || data == null) {
      return false;
    }

    while (newNode != null) {

      int compare = data.compareTo(newNode.data);

      if (compare < 0) {
        newNode = newNode.leftChild;
      }

      else if (compare > 0) {
        newNode = newNode.rightChild;

      }

      else {
        return true;
      }
    }

    return false;
  }
}

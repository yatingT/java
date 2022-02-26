//--== CS400 File Header Information ==--
//Name: Bill Yan
//Email: wyan34@wisc.edu
//Team:  MF
//Role: Front End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

/**
 * Passage class that stores the original text
 * along with its words
 * @author Bill Yan
 *
 */
public class Passage
{
    final private String text;       //The original passage
    private RedBlackTree<Pair> tree; //The tree of words and counts

    private class Pair implements Comparable<Pair>
    {
    	String word;
    	Integer count;

    	Pair(String word)
    	{
    		this.word = word;
    		count = 1;
    	}

    	public int compareTo(Pair other)
    	{
    		return word.compareTo(other.word);
    	}
    	
    	@Override
    	public String toString()
    	{
    		return word + ": " + count + "\n";
    	}
    }
    
    public Passage(String text)
    {
    	this.text = text;
    	tree = new RedBlackTree<Pair>();
    }

    public void addWord(String word)
    {
		Pair current = new Pair(word.toLowerCase());
		RedBlackTree.node<Pair> node = tree.search(current);
	
		if (node == null)
		    tree.insert(current);
		else
		    node.data.count = node.data.count + 1;
    }
    
    public String getText()
    {
    	return text;
    }
    
    public String getList()
    {
    	return tree.inOrderTraversal(tree.root);
    }
}

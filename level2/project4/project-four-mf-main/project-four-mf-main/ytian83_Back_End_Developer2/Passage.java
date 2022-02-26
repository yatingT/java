//--== CS400 File Header Information ==--
//Name: Yating Tian
//Email: ytian34@wisc.edu
//Team:  MF
//Role: Back End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

/**
 * Passage class that store the passage that will be analyzed
 * 
 * @author Yating Tian
 * @param <Word>
 */
public class Passage {
	public String text;
	public RBT<Word> tree;
	public RBT.Node<Word> root;

	/**
	 * the constructor of the Passage object
	 * 
	 * @param input: a string input, article will be analyzed
	 */
	public Passage(String input) {
		this.text = input.toLowerCase();
		this.tree = new RBT<Word>();
		String[] p = input.split(" ");
		for (String s : p) { //after split, we need get rid of the "," and "." for thr string 
			if (s.endsWith(",") && s != null && s.length() > 0) {
				s = s.substring(0, s.length() - 1);
			}
			if (s.endsWith(".") && s != null && s.length() > 0) {
				s = s.substring(0, s.length() - 1);
			}
			Word word = new Word(s);
			if (tree.contains(word)) { //if the word exist
				tree.getNode(word).data.count++;//implementing the count
			}else {
				tree.insert(word); //else the word not in the tree, insert the word
			}
		}
	}

	/**
	 * word object of passage object class.
	 * 
	 * @author Yating Tian
	 */
	public class Word implements Comparable<Word> {

		public String word;
		public int count;

		/**
		 * the constructor of the word object
		 * 
		 * @param word: a string of a word
		 */
		public Word(String word) {
			this.word = word.toLowerCase();
			this.count = 1;
		}
		
		public void addCount() {
			this.count++;
		}

		/**
		 * compare method of the two words
		 * 
		 * @return positive integer if the first word is larger negative if the first
		 *         word is smaller 0 if two words are equal.
		 */
		public int compareTo(Word secondword) {
			return word.compareTo(secondword.word);
		}

	}

	/**
	 * call the contain method in the RBT class, check the target word is in the tree or not
	 * @param target
	 * @return boolean about whether the target word in the tree or not
	 */
	public boolean contain(String target) {
		Word w = new Word(target);
		return tree.contains(w);
	}

	/**
	 * add each word into the tree
	 * 
	 * @param word
	 */
	public void addWord(String word) {
		Word current = new Word(word); //create new word
		if (tree.contains(current)) { //check if the word is already in the tree
			current.count++;
		} else {
			tree.insert(current);
		}
	}

	/**
	 * @return the text of the passage
	 */
	public String getText() {
		return text;
	}
	


}

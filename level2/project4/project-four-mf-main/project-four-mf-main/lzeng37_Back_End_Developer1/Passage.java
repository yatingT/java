// --== CS400 File Header Information ==--
// Name: Linxiu Zeng
// Email: lzeng37@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * The passage class representing every specific passage information to analyze,
 * including a string of the entire passage, a red black tree that stores every
 * word within the passage, and an int that records the number of words each
 * passage has.
 * 
 * @author Linxiu Zeng
 *
 */
public class Passage {
	private String passage;
	protected RBT<Word> wordTree;
	public int wordCount = 0;

	/**
	 * the constructor of the Passage class, aiming to initialize variables when a
	 * new Passage object is created
	 * 
	 * @param wordStream the string representation of the passage content
	 */
	public Passage(String wordStream) {
		this.passage = wordStream;
		this.wordTree = new RBT<>();

		String[] content = wordStream.split(" ");
		this.wordCount = content.length;

		// insert every used word into the red black tree object as individual node
		for (String word : content) {
			Word newWord = new Word(word);

			if (!wordTree.contains(newWord)) {
				wordTree.insert(newWord);
			} else {
				wordTree.getNode(newWord).data.wordCount++;
			}

		}
	}

	/**
	 * the word class that records information of each word presenting in one
	 * passage object
	 * 
	 * @author Linxiu Zeng
	 *
	 */
	protected class Word implements Comparable<Word> {
		String word;
		int wordCount;

		/**
		 * the constructor of the word object
		 * 
		 * @param word: a string of a word
		 */
		public Word(String word) {
			this.word = word;
			this.wordCount = 1;
		}

		public void addWord() {
			this.wordCount++;
		}

		/**
		 * compare two words
		 * 
		 * @return positive if the parameter is larger, negative if the parameter is
		 *         smaller, 0 if two are equal
		 */
		@Override
		public int compareTo(Word wordToCompare) {

			return word.compareTo(wordToCompare.word);
		}

	}

	/**
	 * the getter method of the string object
	 * 
	 * @return a string containing the content of one passage
	 */
	public String passageGetter() {
		return passage;
	}

	/**
	 * check if the target word is stored in the tree
	 * 
	 * @param stringToFind- the target word
	 * @return true if the word is found, false otherwise
	 */
	public boolean contain(String stringToFind) {
		Word wordToFind = new Word(stringToFind);
		return wordTree.contains(wordToFind);
	}

	/**
	 * add the target word into the tree containing every existing word
	 * 
	 * @param word- the word to be added
	 */
	public void add(String word) {
		Word newWord = new Word(word);

		// insert a new word node into the tree if the word has not been stored,
		// increase the word count if the tree has stored the word
		if (wordTree.contains(newWord)) {
			wordTree.getNode(newWord).data.wordCount++;
		} else {
			wordTree.insert(newWord);
		}
	}

	/**
	 * find the target word in the tree containing every presenting word
	 * 
	 * @param targetWord- the word to find
	 * @param root        - the current node
	 * @return
	 */
	public String getWord(Word targetWord, RedBlackTree.Node<Word> root) {
		// the target word is not valid
		if (targetWord == null) {
			return "";
		}

		// recursively looking for the target node in the tree
		if (root.data.word.equals(targetWord.word)) {
			return root.data.word;
		} else if (root.data.compareTo(targetWord) < 0) {
			getWord(targetWord, root.leftChild);
		} else if (root.data.compareTo(targetWord) > 0) {
			getWord(targetWord, root.rightChild);
		}

		// the target node is not stored in the tree
		return "";
	}

}


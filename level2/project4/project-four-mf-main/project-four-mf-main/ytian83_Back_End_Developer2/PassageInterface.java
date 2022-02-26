//--== CS400 File Header Information ==--
//Name: Yating Tian
//Email: ytian34@wisc.edu
//Team:  MF
//Role: Back End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

import java.util.stream.Stream;
import java.util.ArrayList;

/**
 * The interface for the application
 * 
 * @author Yating Tian
 */
public class PassageInterface {

	/**
	 * call the upload method to upload the input txt file
	 * 
	 * @param path:the path of the provided passage
	 * @return return a passage object that is created from the .txt file indicated
	 *         by path
	 */
	public Passage analyzePath(String path) {
		if (path == null || path.equals("")) { //check null
			System.out.println("Invalid path input");
			return null;
		}
		passageUpload uploadPassage = new passageUpload(); //call the upload method from upload class
		Passage p = uploadPassage.upload(path);
		return p;
	}

	/**
	 * convert the input string text into a passage object
	 * 
	 * @param text:the string text that needs to be convert into a passage object
	 * @return a passage object that is created from text
	 */
	public Passage analyzePassage(String text) {
		if (text == null || text.equals("")) {//check null
			System.out.println("Invalid input");
			return null;
		} else {
			Passage p = new Passage(text);
			Stream<String> s = Stream.of(text.split("(\\W)*\\s(\\W)*")); // Stream and regular expression
			s.forEach((word) -> p.addWord(word.toLowerCase())); //add each word to the tree
			return p;
		}
	}

	/**
	 * calculated the most commonly used word in each sentence.
	 * 
	 * @param text the passage to be analyzed
	 * @return the word and the count of the most commonly used word
	 */
	public String getMostUsedStarter(Passage text) {
		if (text == null || text.text == "") {//check null
			return null;
		}
		ArrayList<Passage.Word> list = new ArrayList<Passage.Word>();
		inOrderTraversal(text.tree.root, list); //put the words in the list 
		Stream<Passage.Word> s = list.parallelStream(); //convert the list into stream
		Passage.Word[] mw = { null };
		mw[0] = list.get(0);
		s.forEach((word) -> { //get the maximum amount word
			if (word.count > mw[0].count) {
				mw[0] = word;
			}
		});
		return mw[0].word;
	}

	/**
	 * helper method for the application to print the RBT.
	 * 
	 * @param current: root of the tree
	 * @return : String that contains the inOrderTraversal of the tree
	 */
	private void inOrderTraversal(RedBlackTree.Node<Passage.Word> root, ArrayList<Passage.Word> list) {
		if (root == null) {
			return;
		}
		inOrderTraversal(root.leftChild, list);
		list.add(root.data);//add the value into the list
		inOrderTraversal(root.rightChild, list);
	}

	/**
	 * count each word's frequency in the passage and return a string containing
	 * each existed word and their frequency
	 * 
	 * @param text:the passage object that will be count
	 * @return a string that contains all the words along with their frequency in
	 *         the passage
	 */
	public String getWordList(Passage text) {
		String[] a = {""};
		ArrayList<Passage.Word> list = new ArrayList<Passage.Word>();
		inOrderTraversal(text.tree.root, list);
		Stream<Passage.Word> s = list.parallelStream();
		s.forEach((word) -> {
			a[0] += word.word + ": " + word.count + "\n"; //print out the count
		});
		return a[0];
	}

	/**
	 * check whether the word is present in text
	 * 
	 * @param text: the passage
	 * @param word: target word
	 * @return the boolean result of checking the target word
	 */
	public boolean contains(Passage text, String word) {
		return text.contain(word);
	}
	
	/**
	 * method to get total word count
	 * @param text-the passage that will be count 
	 * @return the number of words in the imput passage
	 */
	public int getWordCount(Passage text) {
		String s= getWordList(text); //use the word list, since it has the count of each word
		String[] list = s.split("\n");
		int total=0;
		for (String each : list) {//add together the count into the total count of words
			String[] forcount=each.split(": ");
			int count=Integer.parseInt(forcount[1]);
			total+=count;
		}
		return total;
	}

}

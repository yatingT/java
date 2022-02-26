// --== CS400 File Header Information ==--
// Name: Linxiu Zeng
// Email: lzeng37@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * the class containing all the required implementations of the Passage class to
 * the application
 * 
 * @author Linxiu Zeng
 *
 */
public class passageInterface {

	/**
	 * Check if the provided file can be open/find as expected
	 * 
	 * @param path the path of the provided passage
	 * @return return a passage object that is created from the .txt file indicated
	 *         by path, return null if no files is found
	 */
	Passage analyzePath(String path) {
		if (path == null || path.equals("")) {
			System.out.println("The input cannot be empty");
		}

		Passage passage = null;
		File file = new File(path);
		Scanner scan = null;

		try {
			// open the provided file, print information if IO exception occurs
			try {
				scan = new Scanner(file);
			} catch (IOException e) {
				System.out.println("The file does not exist");
			}

			// generate a string representing the content of the input passage
			String content = scan.next().toLowerCase();

			while (scan.hasNext()) {
				content += " " + scan.next().toLowerCase();
			}

			passage = new Passage(content);

			// catch and print information sentence if any unexpected exception occurs
		} catch (Exception e) {
			System.out.println("Unexpected exception occurs");
		}

		return passage;
	}

	/**
	 * transform a string object into a passage object
	 * 
	 * @param text the string to be transformed into a passage object
	 * @return a passage object that is created from text
	 */
	Passage analyzePassage(String text) {
		if (text == null || text.equals("")) {
			System.out.println("Sorry, your input text content cannot be empty.");
			return null;
		}

		return new Passage(text.toLowerCase());
	}

	/**
	 * calculated the most commonly used word in each sentence.
	 * 
	 * @param text the passage to be analyzed
	 * @return the most commonly used sentence pick- up
	 */
	String getMostUsedStarter(Passage text) {
		if (text == null) {
			return "Sorry, your input passage/sentence is not valid.";
		} else {
			// create and insert every word node into the list
			ArrayList<Passage.Word> wordList = new ArrayList<Passage.Word>();
			inOrderTraversal(text.wordTree.root, wordList);
			Passage.Word mcWord = wordList.get(0);

			// compare and get the word with the highest word count, keep the first one if
			// every word has same word count
			for (Passage.Word word : wordList) {
				if (word.wordCount > mcWord.wordCount) {
					mcWord = word;
				}
			}
			return mcWord.word;

		}
	}

	/**
	 * get the word count of the entire passage
	 * 
	 * @return an int representing the word count of the entire passage
	 */
	int getWordCount(Passage passage) {
		Stream<String> wordStream = Stream.of(passage.passageGetter().split(" "));

		int[] count = { 0 };

		// increase the word count for each word
		wordStream.forEach((word) -> {
			count[0]++;
		});

		return count[0];
	}

	/**
	 * generate an ArrayList containing every word object presenting in one passage
	 * 
	 * @param root-    the root of the word tree
	 * @param wordList - the ArrayList to store word node
	 */
	private void inOrderTraversal(RedBlackTree.Node<Passage.Word> root, ArrayList<Passage.Word> wordList) {
		// directly return if the tree is not valid
		if (root == null) {
			return;
		}

		// traversely add every tree node into the ArrayList
		inOrderTraversal(root.leftChild, wordList);
		wordList.add(root.data);
		System.out.print(root.data.word);
		inOrderTraversal(root.rightChild, wordList);

	}

	/**
	 * count each word and corresponding occurence of the provided passage and
	 * return a string containing each existed word and their occurance
	 * 
	 * @param text the passage object that contains one provided passage
	 * @return a string that contains all the words along with their occurrence in
	 *         the passage, sample format: “cat: 4\n” + “dog: 10\n”.
	 * 
	 */
	String getWordList(Passage text) {
		String result = "";
		ArrayList<Passage.Word> wordList = new ArrayList<Passage.Word>();

		inOrderTraversal(text.wordTree.root, wordList);

		// generate a string contains all the words as the sample requires
		for (Passage.Word word : wordList) {
			result += word.word + ": " + word.wordCount + "\n";
		}

		return result;
	}

	/**
	 * check whether the word is present in text
	 * 
	 * @param text- the passage to be searched through
	 * @param word- the word to look for in the provided passage object
	 * @return the result of checking
	 */
	boolean contains(Passage text, String word) {

		return text.contain(word);
	}

}


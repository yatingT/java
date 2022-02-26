//--== CS400 File Header Information ==--
//Name: Bill Yan
//Email: wyan34@wisc.edu
//Team:  MF
//Role: Front End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

/**
 * Interface that specifies what features from
 * back end are expected
 * @author Bill Yan
 *
 */
public interface PassageInterface
{
    /**
     * Create a Passage for the .txt file indicated by the path
     */
    Passage analyzePath(String path);

    /**
     * Return a Passage generated from text
     */
    Passage analyzePassage(String text);

    /**
     * Return the most common word in text
     */
    String getMostUsedWord(Passage text);
    
    /**
     * Return a String that contains all the words and their occurrence
     * Example format: "cat: 8\n" + "fish: 12\n"
     */
    String getWordList(Passage text);

    /**
     * Return the total word count in the passage
     */
    int getWordCount(Passage text);
    
    /**
     * Check whether a word is present in text
     */
    boolean contains(Passage text, String word);
}

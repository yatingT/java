import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//--== CS400 File Header Information ==--
//Name: Bill Yan
//Email: wyan34@wisc.edu
//Team:  MF
//Role: Front End Developer
//TA:  Harit
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

/**
 * Back End code that implements the methods
 * required in the interface for Front End
 * @author Bill Yan
 *
 */
public class PassageBE implements PassageInterface
{

	@Override
	public Passage analyzePath(String path) 
	{
		String text = null;
		
		try
		{
			text = new String(Files.readAllBytes(Paths.get(path)));
		}
		catch (IOException e) {}
		
		Passage passage = new Passage(text);
		
		if (text != null)
		{
			Stream<String> words = Stream.of(text.split("(\\W)*\\s(\\W)*"));
			words.forEach((item) -> passage.addWord(item.toLowerCase()));
		}
		
		return passage;
	}

	@Override
	public Passage analyzePassage(String text) 
	{
		Passage passage = new Passage(text);
		
		Stream<String> words = Stream.of(text.split("(\\W)*\\s(\\W)*"));
		words.forEach((item) -> passage.addWord(item.toLowerCase()));
		
		return passage;
	}

	@Override
	public String getMostUsedWord(Passage text) 
	{
		Stream<String> wordList = Stream.of(text.getList().split("\\n"));
		
		String[] mostWord = {""};
	    int[] mostCount = {0};
	    
	    wordList.forEach((line) -> 
	    {
	    	String[] sections = line.split(": ");
	    	String word = sections[0];
	    	int count = Integer.parseInt(sections[1]);
	    	
	    	if (count > mostCount[0])
	    	{
	    		mostWord[0] = word;
	    		mostCount[0] = count;
	    	}
	    });
		
		return "'" + mostWord[0] + "'," + " with " + mostCount[0] + " occurrances";
	}
	
	@Override
	public int getWordCount(Passage text)
	{
		Stream<String> wordList = Stream.of(text.getList().split("\\n"));
		
	    int[] totalCount = {0};
	    
	    wordList.forEach((line) -> 
	    {
	    	String[] sections = line.split(": ");
	    	int count = Integer.parseInt(sections[1]);
	    	totalCount[0] += count;
	    });
	    
	    return totalCount[0];
	}

	@Override
	public String getWordList(Passage text) 
	{
		return text.getList();
	}

	@Override
	public boolean contains(Passage text, String word) 
	{
		return text.getText().contains(word);
	}

}

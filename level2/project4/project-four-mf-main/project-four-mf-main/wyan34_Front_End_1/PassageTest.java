// CS400 File Header Information
// Name: Hechao
// Email: hwang855@wisc.edu
// Team: MF
// Role: Test Engineer 1
// TA: Harit
// Lecturer: Florian Heimerl

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class PassageTest
{

    @Test
    public void testContains()
    {
	PassageBE passage = new PassageBE();
	Passage test = passage.analyzePassage("Word List");

	try
	{
	    if (!passage.contains(test, "Word"))
		fail("Can not find the word");
  
      
	    if(!passage.contains(test, "Word List"))
		fail("Can not find the word");

	}
	catch (Exception e)
	{
	    fail("We have Unexpected exception in constructor test");
	}
  }
  
  @Test
  public void testAnalyzePath()
  {
    
      try
      {
	  PassageBE passage = new PassageBE();
	  String path = "./WordTest.txt";
	  Passage text = passage.analyzePath(path);
      
	  if(!text.getText().equals("This is the random file we choose to make the test work."))
	      fail("Could not find the path");
      
      }
      catch(Exception e)
      {
	  fail("We have Unexpected exception in analyze path test");
      }
  }

  @Test
  public void testAnalyzePassage()
  {
      try
      {
	  PassageBE passage = new PassageBE();
      
	  if(!passage.analyzePassage("").getText().equals(""))
	      fail("Could not find empty");
      
	  Passage text = passage.analyzePassage("Word List");

	  if(!text.getText().equals("Word List"))
	      fail("Could not not find passage");
        
      }
      catch(Exception e)
      {
	  fail("We have Unexpected exception in contain test");
      }
  }
  
  
  @Test
  public void testgetMostUsedWord()
  {
      try
      {
	  PassageBE passage = new PassageBE();
	  Passage test = passage.analyzePassage("Cat Dog Cat");

	  if(!passage.getMostUsedWord(test).equals("'cat', with 2 occurrances"))
	      fail("Can not find most used");
 
      }
      catch(Exception e)
      {
	  fail("We have Unexpected exception in get most used test");
      }
  }
  
  @Test
  public void testWordList()
  {
      try
      {
	  PassageBE passage = new PassageBE();
	  Passage test = passage.analyzePassage("Cat Cat");

	  if(passage.getWordList(test).equals("Cat: 2\n"))
	      fail("Can not find word list");
     
      }
      catch(Exception e)
      {
	  fail("We have Unexpected exception in word list test");
      }
  }
}

// CS400 File Header Information
// Name: Hechao
// Email: hwang855@wisc.edu
// Team: MF
// Role: Test Engineer 1
// TA: Harit
// Lecturer: Florian Heimerl

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



public class PessageTest {

  @Test
  public void testContains() {
    passageInterface passage = new passageInterface();
    Passage test = passage.analyzePassage("Word List");

    try {
      if (!passage.contains(test, "Word")) {
        fail("Can not find the word");
      }
      
      if(!passage.contains(test, "Word List")) {
        fail("Can not find the word");
      }

    } catch (Exception e) {
      fail("We have Unexpected exception in constructor test");
    }
  }
  
  @Test
  public void testAnalyzePath() {
    
    try {
      
      passageInterface passage = new passageInterface();
      String path = "./WordTest.txt";
      Passage text = passage.analyzePath(path);
      System.out.print(text.toString());
      
      if(!text.passageGetter().equals("This is the random file we choose to make the test work.")) {
        fail("Could not find the path");
      }
      
    }catch(Exception e){
      fail("We have Unexpected exception in analyze path test");
    }
    
  }

  @Test
  public void testAnalyzePassage() {
    try {
      passageInterface passage = new passageInterface();
      
      if(passage.analyzePassage("")!=null) {
        fail("Could not find empty");
      }
      
      Passage text = passage.analyzePassage("Word List");
      if(!text.passageGetter().equals("Word List")) {
        fail("Could not not find passage");
      }
        
    }catch(Exception e) {
      fail("We have Unexpected exception in contain test");
    }
  }
  
  
  @Test
  public void testgetMostUsedStarter() {
    try{
      passageInterface passage = new passageInterface();
      Passage test = passage.analyzePassage("Cat Dog Cat");
      if(!passage.getMostUsedStarter(test).equals("Cat")) {
        fail("Can not find most used");
      }
    }catch(Exception e){
      fail("We have Unexpected exception in get most used test");
    }
    
  }
  
  @Test
  public void testUpload() {
    try {
      passageUpload upload = new passageUpload();
      Passage passage = upload.upload("./WordTest.txt");
      
      if(!passage.contain("This")) {
        fail("Can not find existing word");
      }
      
      if(!passage.contain("file")) {
        fail("Can not find existing word");
      }
      
      if(!passage.contain("work")) {
        fail("Can not find existing word");
      }
      
    }catch(Exception e) {
      fail("Can not find that file");
    }
  }
  
  @Test
  public void testWordList() {
    try{
      passageInterface passage = new passageInterface();
      Passage test = passage.analyzePassage("Cat Cat");
      System.out.print(passage.getWordList(test));
      if(passage.getWordList(test).equals("Cat: 2\n")){
        fail("Can not find word list");
      }
    }catch(Exception e) {
      fail("We have Unexpected exception in word list test");
    }
  }
}

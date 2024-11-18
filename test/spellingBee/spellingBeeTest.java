import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import spellingBee.model.SpellingBeeGame;
import spellingBee.model.Word;
import spellingBee.model.WordList;
import spellingBee.model.gameSetUp;

class spellingBeeTest {
    
    WordList listTest1;
    WordList listTest2;
    Word word1;
    Word wordNotInList;
    Word word3;
    Word word4;
    Word word5;
    Word word6;
    Word wordShort;
    Word word7;
    Word word8;
    Word word9;
    
    char[] letters1 = {'c','h','n','g','e','s'};
    char center1 = 'a';

    SpellingBeeGame game;
    gameSetUp setup;
    
	@BeforeEach
	void setUp() throws Exception {
	    
	    listTest1 = new WordList();
	    listTest2 = new WordList();
	    setup = new gameSetUp();
	    word1 = new Word("apple");
	    wordNotInList = new Word("orange");
	    word3 = new Word("acai");
	    word4 = new Word("banana");
	    word5 = new Word("grape");
	    word6 = new Word("almond");
	    word7 = new Word("shed");
	    word8 = new Word("changes");
	    wordShort = new Word("age");
	    word9 = new Word("chcange");
	    listTest1.add(word1);
	    
	    listTest1.add(word4);
	    listTest1.add(word5);
	    listTest1.add(word6);
	    listTest2.add(word8);
	    listTest2.add(word9);
	    listTest2.add(wordShort);
	    
	    game = new SpellingBeeGame();
	    game.startGame();
	    
	    
	 
	    
	}

	@Test
	void notExistTest() {
		//fail("Not yet implemented");
	    
	    // test if word is in the correct words list
	    boolean result1 = listTest1.notExist(word1);
	    assertEquals(false, result1);
	    
	    // test if word is not in the correct words list
	    boolean result2 = listTest1.notExist(wordNotInList);
        assertEquals(true, result2);
	}
	
	@Test
	void binarySearchTest() {
	    //test if word is being add in the alphabetical order   
	   
	    // test if word that is not in the list and goes before the first word
	    int result1 = listTest1.binarySearch(listTest1.getWordList(), word3);
	    
	    assertEquals(-1, result1);
	    
	    // test if word is being added in the middle
        int result2 = listTest1.binarySearch(listTest1.getWordList(), word4);
        
        assertEquals(2, result2);
	    
	    // test if word is being added in the end
        int result3 = listTest1.binarySearch(listTest1.getWordList(), word5);
        
        assertEquals(3, result3);
        
        // test if word is being added in the first position
        int result4 = listTest1.binarySearch(listTest1.getWordList(), word6);
        
        assertEquals(0, result4);
	}
	
	@Test
	void checkLengthTest() {
	    
	    // test if word is too short
	    boolean result1 = wordShort.checkLength();
	    assertEquals(false, result1);
	    
	    // test if word is not short or long enough
	    boolean result2 = word1.checkLength();
        assertEquals(true, result2);
	    
	}
	
	@Test
    void checkCenterTest() {
	    
	    //test if the word contains the center letter (word is "apple" and center letter is 'a')
	    boolean result1 = word1.checkCenter(center1);
        assertEquals(true, result1);
        
        //test if the word doesn't contain the center letter (word is "shed" and center letter is 'a')
        boolean result2 = word7.checkCenter(center1);
        assertEquals(false, result2);
	    
	}
	
	@Test
	void checkLettersTest() {
   // test if word is made up of the set up letters 
	    boolean result1 = word8.checkLetters(letters1, center1);
        assertEquals(true, result1);
	    
	    // test if word is not made up of the set up letters 
        boolean result2 = word7.checkLetters(letters1, center1);
        assertEquals(false, result2);
	    
	}
	
	
	@Test
	void isValidTest() {
	    
	    // test if word is valid
	    boolean result1 = word8.isValid(game.getSetUp().getCorrectWords());
	    assertEquals(true, result1);
	    
	    // test if word is not valid
	    boolean result2 = word9.isValid(game.getSetUp().getCorrectWords());
        assertEquals(false, result2);    
	    
	}
	
	@Test
	void setPangramTest() {
	    // test if word is a pangram
	    word8.setPangram(letters1);
	    boolean result1 = word8.getPangram();
	    assertEquals(true, result1);
	    
	    // test if word is not a pangram
	    word9.setPangram(letters1);
        boolean result2 = word9.getPangram();
        assertEquals(false, result2);
	}
	
	@Test
	void checkWordTest() {
	    // test error cases
	    
	    // test if word is correct/valid
	    int result1 = game.checkWord("changes", letters1, center1);
	    assertEquals(0, result1);
	    
	    // test if word is too short
	    int result2 = game.checkWord("age", letters1, center1);
        assertEquals(1, result2);
	  
	    // test if word has no center letter
        int result3 = game.checkWord("chess", letters1, center1);
        assertEquals(2, result3);
	    
	    // test if word has letters not in honeycomb
        int result4 = game.checkWord("changer", letters1, center1);
        assertEquals(3, result4);

	   
	    // test if word is invalid 
        int result5 = game.checkWord("changess", letters1, center1);
        assertEquals(4, result5);
	    
	    // test if word is already found
        int result6 = game.checkWord("changes", letters1, center1);
        assertEquals(5, result6);
	}
	
	@Test
	void setCountTest() {
	    
	    // test if word count is 0 and calling setCount method will increment count
	    game.getCount().setCount();
	    int result1 = game.getCount().getCount();
	    assertEquals(1, result1);
	    
	    // test if adding a word increments word count
	    game.checkWord("changes", letters1, center1);
	    int result2 = game.getCount().getCount();
        assertEquals(2, result2);

	}
	
	@Test
	void differentLettersTest() {
	    
	    // test if word has 7 not repeating letters
	    boolean result1 = setup.differentLetters("changes");
	    assertEquals(true, result1);
	    
	    // test if word has 7 letters but some letters repeating
	    boolean result2 = setup.differentLetters("bananas");
        assertEquals(false, result2);
	    
	    // test if word has less than 7 letters 
        boolean result3 = setup.differentLetters("change");
        assertEquals(false, result3);
        
        // test if word has more than 7 letters
        boolean result4 = setup.differentLetters("understanding");
        assertEquals(false, result4);
	    
	   
	}

}



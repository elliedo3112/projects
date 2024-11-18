package spellingBee.model;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * gameSetUp Class to read dictionary file and filter appropriate words to set
 * up Spelling Bee game
 */
public class gameSetUp {

    // words for set up the game
    private List<String> dictionary = new ArrayList<>();
    // words for checking the words
    private List<String> correctWords = new ArrayList<>();

    /**
     * read two text files to create set up dictionary and check word dictionary
     */
    public void dictSetUp() {
        List<String> commonWords = new ArrayList<>();
        List<String> englishWords = new ArrayList<>();
        try {
            File text = new File("EnglishWords.txt");
            Scanner myReader = new Scanner(text);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int wordLen = data.length();

                // only words with more than 4 letters are valid in spelling bee
                if (wordLen >= 4) {
                    englishWords.add(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            File text = new File("common_words.txt");
            Scanner myReader = new Scanner(text);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                int wordLen = data.length();

                // set up words need to have at least seven, non-repeated
                // letters
                if (wordLen >= 7 && differentLetters(data)) {
                    commonWords.add(data);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // add words that are both common and valid to the set up dictionary
        for (int i = 0; i < commonWords.size(); i++) {
            if (englishWords.contains(commonWords.get(i))) {
                dictionary.add(commonWords.get(i));

            }
        }
        // set check word dictionary
        setCorrectWords(englishWords);

    }

    // check if there are at least 7 non-repeated letters in the word
    public boolean differentLetters(String word) {
        Set<Character> uniqueLetters = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            uniqueLetters.add(letter);
        }
        if (uniqueLetters.size() == 7) {
            return true;
        } else {
            return false;
        }
    }

    private void setCorrectWords(List<String> correctWords) {
        this.correctWords = correctWords;
    }

    public List<String> getCorrectWords() {
        return this.correctWords;
    }

    public List<String> getDict() {
        return this.dictionary;
    }
}

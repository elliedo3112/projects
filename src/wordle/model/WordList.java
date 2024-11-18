package wordle.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to generate wordList to be guessed from, check validity of word passed
 * in, generate correct word
 */
public class WordList {
	// List of words considered valid for user guesses
	private List<String> guessWordList = new ArrayList<>();
	// List of words the correct word could be taken from
	private List<String> correctWordList = new ArrayList<>();

	/**
	 * Initialize the WordList and create list of possible guess words and correct
	 * words
	 */
	public WordList() {
		this.guessWordList = guessWordList();
		this.correctWordList = correctWordList();
	}

	/**
	 * Get valid 5-letter English words
	 * 
	 * @return list of words considered valid for user guesses
	 */
	private List<String> guessWordList() {
		List<String> wordList = new ArrayList<>();
		String fileName = "EnglishWords.txt";

		try {
			FileReader file = new FileReader(fileName);
			BufferedReader in = new BufferedReader(file);
			String line;
			// Add each 5-letter word in the file to the list
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.length() == 5) {
					wordList.add(line);
				}
			}
			in.close();
			// Catch error if word file isn't found
		} catch (IOException in) {
			System.out.println("File " + fileName + " was not found.");
		}
		return wordList;
	}

	/**
	 * Get 5-letter words where the correct word can be selected from
	 * 
	 * @return list of words where the correct word can be selected from
	 */
	private List<String> correctWordList() {
		List<String> wordList = new ArrayList<>();
		String fileName = "common_words.txt";

		try {
			FileReader file = new FileReader(fileName);
			BufferedReader in = new BufferedReader(file);
			String line;
			// Add each 5-letter word in the file to the list
			while ((line = in.readLine()) != null) {
				line = line.trim();
				if (line.length() == 5) {
					if (guessWordList.contains(line)) {
						wordList.add(line);
					}
				}
			}
			in.close();
			// Catch error if word file isn't found
		} catch (IOException in) {
			System.out.println("File " + fileName + " was not found.");
		}
		return wordList;
	}

	/**
	 * Randomly select a 5-letter word
	 * 
	 * @return word to be guessed
	 */
	public String createCorrectWord() {
		String correctWord;
		Random random = new Random();
		int randomIndex = random.nextInt(correctWordList.size());
		// Get a random word from the correctWordList
		correctWord = correctWordList.get(randomIndex);
		return correctWord;
	}

	/**
	 * Get list of words user can guess from
	 * 
	 * @return list of valid 5-letter words
	 */
	public List<String> getGuessWordList() {
		return guessWordList;
	}
}

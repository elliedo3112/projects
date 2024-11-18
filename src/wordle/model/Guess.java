package wordle.model;

import java.util.List;

/**
 * Class to handle the checking for user's guess
 */
public class Guess {
	// Message to be displayed
	private String message = "";
	// Word List
	private WordList wordList = new WordList();
	// Correct word
	private String correctWord;
	// User's current guess
	private String currentGuess;
	// Result of the guess
	private boolean result;
	// Three kinds of messages
	private static final String TOO_LONG = "Too long!";
	private static final String TOO_SHORT = "Too short!";
	private static final String CORRECT_LENGTH = "Correct length!";
	private static final String INVALID = "Invalid word!";
	private static final String VALID = "Valid word!";

	/**
	 * Construct Guess
	 * 
	 * @param modelRow     current model row to be updated
	 * @param correctWord  correct word to be guessed
	 * @param currentGuess user's current guess
	 */
	public Guess(String correctWord, String currentGuess) {
		this.correctWord = correctWord;
		this.currentGuess = currentGuess;
	}

	/**
	 * Check length of the guess
	 * 
	 * @return whether it's a 5-letter word
	 */
	public String checkLength() {
		// If guess has less than 5 letters, change message
		if (currentGuess.length() < 5) {
			this.message = TOO_SHORT;
			return message;
			// If guess has more than 5 letters, change message
		} else if (currentGuess.length() > 5) {
			this.message = TOO_LONG;
			return message;
		}
		this.message = CORRECT_LENGTH;
		return message;
	}

	/**
	 * Check whether user's guess is a valid 5-letter word
	 * 
	 * @return whether user's guess is a valid 5-letter word
	 */
	public String checkValidity() {
		List<String> guessWordList = wordList.getGuessWordList();
		if (!guessWordList.contains(currentGuess.toLowerCase())) {
			this.message = INVALID;
			return message;
		}
		this.message = VALID;
		return message;
	}

	/**
	 * Check if a letter in the correct word appears in user's guess
	 * 
	 * @param correctWordLetter correct word letter we are searching for in guess
	 * @param col               column we are checking from
	 */
	private void containsLetter(LetterModel[] modelRow, String correctWordLetter, int col) {
		// Loop through letters in user's guess
		for (int i = col; i < 5; i++) {
			// If a letter in the user's guess matches that of correct word
			if (correctWordLetter.equals(String.valueOf(currentGuess.charAt(i)))) {
				// If the letter hasn't been checked or matched with the correct word letter
				// before, change color to yellow
				if (modelRow[i] == null || modelRow[i].getColor() == WordleColor.GRAY) {
					modelRow[i] = new LetterModel(correctWordLetter, WordleColor.YELLOW);
					return;
				}
			}
		}
	}

	/**
	 * Check correctness of guess
	 * 
	 * @return whether the guess is the same as the correct word
	 */
	public LetterModel[] checkCorrectness() {
		result = true;
		correctWord = correctWord.toUpperCase();
		currentGuess = currentGuess.toUpperCase();
		LetterModel[] modelRow = new LetterModel[5];
		// Check each letter of the user's guess to see if it matches that of the
		// correct word
		for (int i = 0; i < 5; i++) {
			String letter = String.valueOf(currentGuess.charAt(i));
			String correctWordLetter = String.valueOf(correctWord.charAt(i));
			// If the correct word's letter matches that of user's guess in the same index
			if (correctWordLetter.equals(letter)) {
				// If the guess letter hasn't been matched before, initialize it with color
				// green
				if (modelRow[i] == null) {
					modelRow[i] = new LetterModel(letter, WordleColor.GREEN);
					// If the guess letter has already been matched, change color to green
				} else {
					modelRow[i].setColor(WordleColor.GREEN);
					// check if the correct word letter it was matched with before matches any
					// remaining
					// letters in the user's guess
					if (i < 4) {
						containsLetter(modelRow, correctWordLetter, i + 1);
					}
				}
				// If the correct word's letter doesn't match that of user's guess in the same
				// index
			} else {
				// If the guess letter hasn't been matched before, change its color to gray
				if (modelRow[i] == null) {
					modelRow[i] = new LetterModel(letter, WordleColor.GRAY);
				}
				// Check if the correct word's letter matches any letter in the user's guess
				containsLetter(modelRow, correctWordLetter, 0);
				result = false;
			}
		}
		// return result of the guess
		return modelRow;
	}

	/**
	 * Get message to be displayed
	 * 
	 * @return message to be displayed
	 */
	public String getMsg() {
		return message;
	}

	/**
	 * Get model row for checkCorrectness testing purposes
	 */
	public boolean getResult() {
		return result;
	}
}
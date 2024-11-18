import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import wordle.model.Guess;
import wordle.model.WordleColor;

class GuessTest {
	String correctWord;
	String currentGuess;
	Guess guess;
	final String TOO_LONG = "Too long!";
	final String TOO_SHORT = "Too short!";
	final String CORRECT_LENGTH = "Correct length!";
	final String INVALID = "Invalid word!";
	final String VALID = "Valid word!";

	@Test
	void testWordTooLong() {
		correctWord = "hello";
		currentGuess = "helloo";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(TOO_LONG, guess.checkLength());
	}

	@Test
	void testWordTooShort() {
		correctWord = "hello";
		currentGuess = "hell";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(TOO_SHORT, guess.checkLength());
	}

	@Test
	void testCorrectLength() {
		correctWord = "hello";
		currentGuess = "house";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(CORRECT_LENGTH, guess.checkLength());
	}

	@Test
	void testInvalidWord() {
		correctWord = "hello";
		currentGuess = "juise";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(INVALID, guess.checkValidity());
	}

	@Test
	void testValidWord() {
		correctWord = "hello";
		currentGuess = "house";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(VALID, guess.checkValidity());
	}

	@Test
	void testCorrectFirstWord() {
		correctWord = "brain";
		currentGuess = "block";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	@Test
	void testCorrectLastWord() {
		correctWord = "block";
		currentGuess = "kayak";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[4].getColor());
	}

	@Test
	void testCorrectWord() {
		correctWord = "hello";
		currentGuess = "hello";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[4].getColor());
	}

	@Test
	void testDifferentCase() {
		correctWord = "hELlo";
		currentGuess = "HelLo";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[4].getColor());
	}

	@Test
	void testWrongStartLetter() {
		correctWord = "brook";
		currentGuess = "crook";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[4].getColor());
	}

	@Test
	void testWrongEndLetter() {
		correctWord = "broom";
		currentGuess = "brook";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	@Test
	void testWrongWord() {
		correctWord = "brain";
		currentGuess = "house";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	// The user's guess last letter is contained in the correct word but is in the
	// wrong order
	@Test
	void testWrongOrderedLastLetter() {
		correctWord = "lemon";
		currentGuess = "grape";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.YELLOW, guess.checkCorrectness()[4].getColor());
	}

	// The user's guess first letter is contained in the correct word but is in the
	// wrong order
	@Test
	void testWrongOrderedFirsttLetter() {
		correctWord = "block";
		currentGuess = "crane";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.YELLOW, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	// Correct word has one letter that is similar to the guess, right ordered
	// letter appears before wrong ordered one
	@Test
	void testOneCorrectLetterRightOrderedFirst() {
		correctWord = "chose";
		currentGuess = "broom";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	// Correct word has one letter that is similar to the guess, right ordered
	// letter appears after wrong ordered one
	@Test
	void testOneCorrectLetterWrongOrderedFirst() {
		correctWord = "mourn";
		currentGuess = "carry";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	// Correct word has 2 letters that is similar to the guess, right ordered letter
	// appears before wrong ordered one
	@Test
	void testTwoCorrectLettersRightOrderedFirst() {
		correctWord = "creme";
		currentGuess = "sheep";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.YELLOW, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	// Correct word has 2 letters that is similar to the guess, right ordered letter
	// appears after wrong ordered one
	@Test
	void testTwoCorrectLettersWrongOrderedFirst() {
		correctWord = "broom";
		currentGuess = "sooth";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.YELLOW, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GREEN, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[4].getColor());
	}

	@Test
	void testMultipleWrongOrderedLetters() {
		correctWord = "crane";
		currentGuess = "actor";
		guess = new Guess(correctWord, currentGuess);
		assertEquals(WordleColor.YELLOW, guess.checkCorrectness()[0].getColor());
		assertEquals(WordleColor.YELLOW, guess.checkCorrectness()[1].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[2].getColor());
		assertEquals(WordleColor.GRAY, guess.checkCorrectness()[3].getColor());
		assertEquals(WordleColor.YELLOW, guess.checkCorrectness()[4].getColor());
	}
}

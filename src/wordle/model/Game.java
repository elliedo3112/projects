package wordle.model;

import java.util.ArrayList;

import javax.swing.JTextField;

import wordle.view.MessageBox;
import wordle.view.PanelListener;

/**
 * Class that manages the entire game, adding listeners, updating them according
 * to results after checking guess
 */
public class Game {
	// Current row the user is on
	private int row;
	// Message to be displayed
	private String message = "";
	// Grid of LetterModel
	private LetterModel[][] letterModelGrid;
	// Listener for changes to panels
	private ArrayList<PanelListener> listeners = new ArrayList<>();
	// Listener for changes to MessageBox
	private MessageBox messageListener;
	// Listener for inputBox changes
	private JTextField inputListener;
	// Object for creating correctWord and reading word list files
	private WordList wordList;
	// Correct word selected randomly
	private String correctWord;
	// Messages for game results
	private static final String WIN = "Congratulations, you win!";
	private static final String LOSE = "You lose! The correct word is ";
	private static final String CORRECT_LENGTH = "Correct length!";
	private static final String VALID = "Valid word!";

	/**
	 * Construct the Game
	 */
	public Game() {
		this.wordList = new WordList();
		this.correctWord = wordList.createCorrectWord();
		this.letterModelGrid = new LetterModel[6][5];
		this.row = 0;
	}

	/**
	 * Add listener for panels
	 * 
	 * @param listener panels listener
	 */
	public void addPanelListener(PanelListener listener) {
		listeners.add(listener);
	}

	/**
	 * Add listener for pop up messages
	 * 
	 * @param messageBox message box listener
	 */
	public void addMessageListener(MessageBox messageBox) {
		this.messageListener = messageBox;
	}

	/**
	 * Add listener for input
	 * 
	 * @param input input listener
	 */
	public void addInputListener(JTextField input) {
		this.inputListener = input;
	}

	/**
	 * Get message to be displayed
	 * 
	 * @return message to be displayed
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Get the current row
	 * 
	 * @return current row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Get letterModel in letterModelGrid
	 * 
	 * @param row row the letterModel is in
	 * @param col column the letterModel is in
	 * @return the letterModel we are looking for
	 */
	public LetterModel getModelLetter(int row, int col) {
		return letterModelGrid[row][col];
	}

	/**
	 * Called when user press enter to process the guess checking
	 * 
	 * @param currentGuess user's guess
	 */
	public void check(String currentGuess) {
		Guess checkGuess = new Guess(correctWord, currentGuess);
		// Check length of guess to ensure it has 5 letters
		if (checkGuess.checkLength().equals(CORRECT_LENGTH)) {
			// Check that guess is a valid word
			if (checkGuess.checkValidity().equals(VALID)) {
				// Check if guess is correct
				letterModelGrid[row] = checkGuess.checkCorrectness();
				this.message = checkGuess.getMsg();
				// Update panel listeners accordingly
				updatePanelListener();
				// Check result of the game
				checkResult(checkGuess.getResult());
				return;
			}
		}
		// Update message value if word is invalid or doesn't have 5 letters
		this.message = checkGuess.getMsg();
	}

	/**
	 * Update keyboard and grid's color and letter inside
	 */
	public void updatePanelListener() {
		// Update every panel listeners
		for (PanelListener listener : listeners) {
			listener.update();
		}
	}

	/**
	 * Called when user pressed enter to check the guess
	 * 
	 * @param currentGuess user's guess
	 */
	public void enter(String currentGuess) {
		// Check our guess
		check(currentGuess);
		// If message needs to be displayed update message listener
		if (!this.message.equals(VALID) && !this.message.equals(CORRECT_LENGTH)) {
			messageListener.update();
			this.message = "";
		}
	}

	/**
	 * Called when user wins or when they run out of tries and lose
	 * 
	 * @param win
	 */
	private void checkResult(boolean result) {
		// If user guessed correctly
		if (result) {
			this.message = WIN;
			// If user guessed wrong and ran out of tries
		} else if (row == 5) {
			this.message = LOSE + correctWord.toLowerCase();
			// Else, let user have another try
		} else {
			row++;
			return;
		}
		// Disable input field if user loses or wins
		inputListener.setEditable(false);
	}
}

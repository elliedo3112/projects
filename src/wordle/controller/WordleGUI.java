package wordle.controller;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import wordle.model.Game;
import wordle.view.Keyboard;
import wordle.view.MessageBox;
import wordle.view.WordGrid;

/**
 * This class provides a graphical user interface that allows a user to enter a
 * valid 5-letter word for 6 times.
 */
public class WordleGUI {
	// Where the user can enter their guess
	private JTextField entryField = new JTextField(5);
	// The virtual keyboard
	private Keyboard keyboard;
	// The word grid
	private WordGrid wordGrid;
	// The pop up message
	private MessageBox messageBox;
	// The main model of the game
	private Game model;

	/**
	 * Construct the user interface for the program.
	 */
	public WordleGUI() {
		model = new Game();
		JFrame frame = new JFrame();
		frame.setTitle("Wordle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		// Create all panels and message box
		keyboard = new Keyboard(model);
		wordGrid = new WordGrid(model, 800);
		messageBox = new MessageBox(model);
		JPanel inputPanel = new JPanel();
		inputPanel.add(new JLabel("Your word: "));
		inputPanel.add(entryField);

		// Add listeners
		model.addPanelListener(wordGrid);
		model.addPanelListener(keyboard);
		model.addMessageListener(messageBox);
		model.addInputListener(entryField);

		// Add listener to input field
		entryField.addActionListener(event -> inputAction());

		// Add all the GUI elements to the display.
		frame.add(wordGrid, BorderLayout.NORTH);
		frame.add(keyboard, BorderLayout.CENTER);
		frame.add(inputPanel, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * Called when user press enter to check user's guess
	 */
	public void inputAction() {
		String guess = entryField.getText();
		model.enter(guess);
		// empty text field after checking
		entryField.setText("");
	}

	/**
	 * The main method to start the program.
	 * 
	 * @param args None required
	 */
	public static void main(String[] args) {
		new WordleGUI();
	}

}

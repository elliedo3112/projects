package wordle.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import wordle.model.Game;
import wordle.model.LetterModel;

/**
 * A panel to display the virtual keyboard
 */
public class Keyboard extends JPanel implements PanelListener {
	// The game's main model
	private Game model;
	// HashMap for keys inside keyboard
	private Map<String, KeyBox> keyboard = new HashMap<>();

	/**
	 * Creates the keyboard
	 * 
	 * @param model the Game model
	 */
	public Keyboard(Game model) {
		super(new GridLayout(0, 1, 0, 0));
		this.model = model;
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		initialize();
	}

	/**
	 * Adding rows to the keyboard
	 */
	private void initialize() {
		// Initialize arrays of key values in the key board
		String[] firstRowKeys = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" };
		String[] secondRowKeys = { "A", "S", "D", "F", "G", "H", "J", "K", "L" };
		String[] thirdRowKeys = { "Enter", "Z", "X", "C", "V", "B", "N", "M", "Del" };

		// Add the rows to the panel
		add(row(firstRowKeys));
		add(row(secondRowKeys));
		add(row(thirdRowKeys));
	}

	/**
	 * Create a row of keys
	 * 
	 * @param keys array of values of keys for the row
	 * @return row of keys
	 */
	private JPanel row(String[] keys) {
		// Create and design the row
		JPanel row = new JPanel(new FlowLayout());
		row.setBackground(Color.WHITE);
		row.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
		// Create and add the buttons to the row
		for (String key : keys) {
			KeyBox button = new KeyBox(key);
			keyboard.put(key, button);
			row.add(button);
		}
		return row;
	}

	@Override
	/**
	 * Update keys when guess has been checked
	 */
	public void update() {
		// Update each key inside the current row
		for (int i = 0; i < 5; i++) {
			int row = model.getRow();
			// Get the color and values of letters from the current guess
			LetterModel letterModel = model.getModelLetter(row, i);
			String letter = letterModel.getLetter();
			Color color = letterModel.getColor();
			// Update the keys with the same value accordingly
			KeyBox keyBox = keyboard.get(letter);
			keyBox.update(color);
		}
	}
}

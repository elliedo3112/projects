package wordle.model;

import java.awt.Color;

/**
 * Class modelling after the letterBox grid for better updates
 */
public class LetterModel {
	// letter of letterBox after checking guess
	private String letter;
	// color of letterBox after checking guess
	private Color color;

	/**
	 * Create LetterModel
	 * 
	 * @param letter letter of letterModel
	 * @param color  color of letterModel
	 */
	public LetterModel(String letter, Color color) {
		this.letter = letter;
		this.color = color;
	}

	// Get letter
	public String getLetter() {
		return letter;
	}

	// Get color
	public Color getColor() {
		return color;
	}

	// Set color
	public void setColor(Color newColor) {
		color = newColor;
	}
}

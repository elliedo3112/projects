package wordle.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import wordle.model.WordleColor;

/**
 * Class representing a key box in the keyboard
 */
public class KeyBox extends JButton {
	// Key of the button
	private String key;
	// Color of the button
	private Color color;

	/**
	 * Construct the key buttons on the keyboard
	 * 
	 * @param key value of the buttons
	 */
	public KeyBox(String key) {
		super(key);
		this.key = key;
		// Set original color for the button
		this.color = WordleColor.WHITEBTN;
		setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		// Construct the size and font for the button based on its value
		if (!key.equals("Enter") && !key.equals("Del")) {
			Font font = new Font("Arial", Font.BOLD, 22);
			setFont(font);
			setPreferredSize(new Dimension(45, 60));
		} else {
			Font font = new Font("Arial", Font.BOLD, 18);
			setFont(font);
			setPreferredSize(new Dimension(70, 60));
		}

		// Change background color and color of the text inside the button
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(color);
		setForeground(Color.BLACK);
	}

	/**
	 * Get key of the button
	 * 
	 * @return button's key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Update the button after guess has been checked
	 * 
	 * @param newColor new color for the button
	 */
	public void update(Color newColor) {
		// Set letter inside to white
		setForeground(Color.WHITE);
		// If the new color is green turn the button into that
		if (newColor.equals(WordleColor.GREEN)) {
			color = newColor;
			setBackground(color);
			// If the new color is yellow or gray
		} else {
			// If the letter inside hasn't been guessed yet change the button into it's new
			// color, if it has been checked keep it's old color
			if (color.equals(WordleColor.WHITEBTN)) {
				color = newColor;
				setBackground(color);
			}
		}

	}
}

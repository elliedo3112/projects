package wordle.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * A class to construct the LetterBox in the grid
 */
public class LetterBox extends Rectangle {
	// Color of the LetterBox
	private Color color;
	// Letter displayed inside the LetterBox
	private String letter;

	/**
	 * Construct the letter box
	 * 
	 * @param x      position for x
	 * @param y      position for y
	 * @param width  of letter box
	 * @param height of letter box
	 */
	public LetterBox(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Drawing the LetterBox
	 * 
	 * @param comp WordGrid panel
	 * @param g graphics 2D
	 */
	public void draw(JComponent comp, Graphics2D g) {
		// If the user's guess for the box hasn't been checked, change color into white
		if (color == null) {
			color = Color.WHITE;
		}
		// Fill the boxes with its color
		g.setColor(color);
		g.fillRect(x, y, width, height);

		// Draw borders for the box
		g.setColor(Color.GRAY);
		g.setStroke(new BasicStroke(0.5f));
		g.drawLine(x, y, x + width, y);
		g.drawLine(x, y + height, x + width, y + height);
		g.drawLine(x, y, x, y + height);
		g.drawLine(x + width, y, x + width, y + height);

		// If the guess for the box has been checked, display the guessed letter and its
		// appropriate color
		if (letter != null) {
			g.setColor(Color.WHITE);
			Font font = new Font("Arial", Font.BOLD, 30);
			g.setFont(font);
			FontMetrics fm = g.getFontMetrics();
			// Position x and y for the letter to be displayed at
			int strX = this.x + (this.width - fm.stringWidth(letter)) / 2;
			int strY = this.y + ((this.height - fm.getHeight()) / 2) + fm.getAscent();
			g.drawString(letter, strX, strY);
		}
	}

	/**
	 * Update color and text inside letter box after checking guess
	 * 
	 * @param newColor  new color for the letterBox to be updated to
	 * @param newLetter new letter to displayed inside the LetterBox
	 */
	public void update(String newLetter, Color newColor) {
		// Update letter
		letter = newLetter;
		// Update color to the appropriate one according to the correctness of the guess
		color = newColor;
	}

}

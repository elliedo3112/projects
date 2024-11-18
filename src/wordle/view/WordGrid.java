package wordle.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JPanel;

import wordle.model.Game;
import wordle.model.LetterModel;

/**
 * A panel that displays the word grid
 */
public class WordGrid extends JPanel implements PanelListener {
	private int topMargin = 10;
	private int letterWidth;
	private int leftMargin;
	// Grid of LetterBox
	private LetterBox[][] grid = new LetterBox[6][5];
	// The game's main model
	private Game model;

	/**
	 * Create the word grid
	 * 
	 * @param model the Game model
	 * @param width width of the word grid
	 */
	public WordGrid(Game model, int width) {
		setLayout(new GridLayout(6, 5));
		this.model = model;
		this.topMargin = 0;
		this.letterWidth = 65;
		// Width of the entire row
		int wordWidth = (letterWidth + 5) * 5;
		this.leftMargin = (width - wordWidth) / 2;
		// Height of the panel
		int height = (letterWidth + 5) * 6 + 2 * topMargin;
		setPreferredSize(new Dimension(width, height));
		setBackground(Color.WHITE);

		this.grid = initialize();
	}

	/**
	 * Create the grid made from LetterBox
	 * 
	 * @return the grid of LetterBox
	 */
	private LetterBox[][] initialize() {
		LetterBox[][] grid = new LetterBox[6][5];

		// Starting position for the LetterBox
		int x = leftMargin;
		int y = topMargin;

		// Initialize the 2D array grid
		for (int row = 0; row < 6; row++) {
			for (int column = 0; column < 5; column++) {
				grid[row][column] = new LetterBox(x, y, letterWidth, letterWidth);
				x += letterWidth + 5;
			}
			x = leftMargin;
			y += letterWidth + 5;
		}

		return grid;
	}

	@Override
	/**
	 * Draw the grid
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Loop through each LetterBox in the grid and draw it
		for (int row = 0; row < grid.length; row++) {
			for (int column = 0; column < grid[row].length; column++) {
				LetterBox letterBox = grid[row][column];
				letterBox.draw(this, (Graphics2D) g);
			}
		}
	}

	/**
	 * Update the word grid when guess has been checked
	 */
	public void update() {
		// Update each LetterBox inside the current row
		for (int i = 0; i < 5; i++) {
			int row = model.getRow();
			// Get the color and values of letters from the current guess
			LetterModel letterModel = model.getModelLetter(row, i);
			String letter = letterModel.getLetter();
			Color color = letterModel.getColor();
			// Update the keys with the same value accordingly
			grid[row][i].update(letter, color);
		}
		repaint();
	}
}
